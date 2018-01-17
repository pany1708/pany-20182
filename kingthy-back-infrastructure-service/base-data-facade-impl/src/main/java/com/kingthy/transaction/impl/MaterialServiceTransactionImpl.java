package com.kingthy.transaction.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kingthy.dto.AccessoriesDto;
import com.kingthy.entity.BaseColourInfo;
import com.kingthy.entity.Material;
import com.kingthy.entity.MaterialImages;
import com.kingthy.mapper.BaseColourInfoMapper;
import com.kingthy.mapper.MaterialImagesMapper;
import com.kingthy.mapper.MaterialMapper;
import com.kingthy.request.AddUpdateMaterialReq;
import com.kingthy.request.UpdateMaterialReq;
import com.kingthy.transaction.MaterialServiceTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author xumin
 * @Description:
 * @DATE Created by 14:57 on 2018/1/9.
 * @Modified by:
 */
@Service
public class MaterialServiceTransactionImpl implements MaterialServiceTransaction {

    @Autowired
    private MaterialMapper materialMapper;

    @Autowired
    private MaterialImagesMapper materialImagesMapper;

    @Autowired
    private BaseColourInfoMapper baseColourInfoMapper;

    @Transactional
    @Override
    public void saveMaterial(AddUpdateMaterialReq req) {
        Material material = JSONObject.parseObject(JSON.toJSONString(req), Material.class);

        String uuid = insertReturnUuid(material);

        //保存面料颜色
        if (!StringUtils.isEmpty(uuid)){

            Example example = new Example(BaseColourInfo.class);
            example.createCriteria().andIn("rgb", req.getColor().stream().map(s ->
                    s.getColor().replace("(", "").replace(")", "")).collect(Collectors.toList()));

            List<BaseColourInfo> baseColourInfoList = baseColourInfoMapper.selectByExample(example);

            if (!baseColourInfoList.isEmpty()){
                for (int m = 0; m < baseColourInfoList.size(); m++){

                    BaseColourInfo baseColourInfo = baseColourInfoList.get(m);

                    MaterialImages images = new MaterialImages();
                    images.setColourId(baseColourInfo.getId());
                    images.setColourRgb(baseColourInfo.getRgb());
                    images.setMaterialUuid(uuid);
                    images.setCreateDate(new Date());
                    images.setModifyDate(new Date());
                    images.setVersion(VERSION);
                    images.setDelFlag(false);
                    images.setStatus(1);
                    images.setColourIdx(m);

                    materialImagesMapper.insert(images);
                }
            }

        }
    }

    @Transactional
    @Override
    public void updateMaterial(UpdateMaterialReq req) {

        Material material = JSONObject.parseObject(JSON.toJSONString(req), Material.class);

        int result = updateMaterial(material);

        if (result > 0){

            Example example = new Example(BaseColourInfo.class);
            example.createCriteria().andIn("rgb", req.getColor().stream().map(s ->
                    s.getColor().replace("(", "").replace(")", "")).collect(Collectors.toList()));

            List<BaseColourInfo> baseColourInfoList = baseColourInfoMapper.selectByExample(example);

            MaterialImages var = new MaterialImages();
            var.setMaterialUuid(material.getUuid());
            var.setDelFlag(false);
            List<MaterialImages> materialImages = materialImagesMapper.select(var);

            Map<Integer, BaseColourInfo> colourInfoMap = baseColourInfoList.stream().collect(Collectors.toMap(BaseColourInfo::getId, Function.identity()));
            Map<Integer, MaterialImages> map = materialImages.stream().collect(Collectors.toMap(MaterialImages::getColourId, Function.identity()));
            Map<String, Integer> mapImage = req.getImages().stream()
                    .collect(Collectors.toMap(AccessoriesDto.ImageDTO::getImgUuid, AccessoriesDto.ImageDTO::getStatus));

            //新增的颜色
            baseColourInfoList.forEach(c -> {

                if (!map.containsKey(c.getId())){
                    MaterialImages images = new MaterialImages();
                    images.setColourId(c.getId());
                    images.setColourRgb(c.getRgb());
                    images.setMaterialUuid(material.getUuid());
                    images.setCreateDate(new Date());
                    images.setModifyDate(new Date());
                    images.setVersion(VERSION);
                    images.setDelFlag(false);
                    images.setStatus(1);
                    images.setColourIdx(0);
                    materialImagesMapper.insert(images);
                }
            });

            //删除的颜色
            materialImages.forEach(m -> {

                if (!colourInfoMap.containsKey(m.getColourId())){

                    MaterialImages images = new MaterialImages();
                    images.setDelFlag(true);
                    images.setModifyDate(new Date());

                    Example example1 = new Example(MaterialImages.class);
                    Example.Criteria criteria = example1.createCriteria();
                    criteria.andEqualTo("uuid", m.getUuid());
                    materialImagesMapper.updateByExampleSelective(images, example1);
                }

            });

            //按创建时间重新排序

            List<String> list = materialImagesMapper.queryUUidByMaterialUuid(material.getUuid());

            for (int m = 0; m < list.size(); m++){

                String uuid = list.get(m);

                Example example2 = new Example(MaterialImages.class);
                Example.Criteria criteria = example2.createCriteria();
                criteria.andEqualTo("uuid", uuid);

                MaterialImages images = new MaterialImages();
                images.setModifyDate(new Date());
                images.setColourIdx(m);

                //修改图片禁用/启用
                if (mapImage.containsKey(uuid)){
                    images.setStatus(mapImage.get(uuid));
                }

                materialImagesMapper.updateByExampleSelective(images, example2);
            }
        }
    }

    @Override
    public String insertReturnUuid(Material material)
    {
        Date currentDate = new Date();
        material.setCreateDate(currentDate);
        material.setModifyDate(currentDate);
        material.setDelFlag(false);
        material.setVersion(VERSION);
        material.setStatus(0);

        return materialMapper.insertSelective(material) == 0 ? null : material.getUuid();
    }

    @Override
    public int updateMaterial(Material material) {

        Material var = new Material();
        var.setUuid(material.getUuid());
        Material materialResult = materialMapper.selectOne(var);
        Integer currentVersion = materialResult.getVersion();
        Example example = new Example(Material.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid", material.getUuid());
        criteria.andEqualTo("version", currentVersion);
        material.setModifyDate(new Date());
        material.setVersion(currentVersion + 1);
        return materialMapper.updateByExampleSelective(material, example);
    }

    @Transactional
    @Override
    public void deleteMaterial(String materialUuid) {
        Example example = new Example(Material.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid", materialUuid);
        criteria.andEqualTo("delFlag", false);

        Material material = new Material();
        material.setDelFlag(true);
        material.setModifyDate(new Date());

        int result = materialMapper.updateByExampleSelective(material, example);

        if (result > 0){
            materialImagesMapper.delImagesByMaterialUuid(materialUuid);
        }
    }

    /**
     * 默认版本号
     */
    private static final int VERSION = 1;
}
