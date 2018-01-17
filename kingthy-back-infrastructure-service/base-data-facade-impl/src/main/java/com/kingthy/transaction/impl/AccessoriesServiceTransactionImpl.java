package com.kingthy.transaction.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kingthy.dto.AccessoriesDto;
import com.kingthy.entity.Accessories;
import com.kingthy.entity.AccessoriesImages;
import com.kingthy.entity.BaseColourInfo;
import com.kingthy.mapper.AccessoriesImagesMapper;
import com.kingthy.mapper.AccessoriesMapper;
import com.kingthy.mapper.BaseColourInfoMapper;
import com.kingthy.request.AddUpdateAccessoriesReq;
import com.kingthy.request.UpdateAccessoriesReq;
import com.kingthy.transaction.AccessoriesServiceTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author xumin
 * @Description:
 * @DATE Created by 18:29 on 2018/1/10.
 * @Modified by:
 */
@Service
public class AccessoriesServiceTransactionImpl implements AccessoriesServiceTransaction
{

    @Autowired
    private AccessoriesMapper accessoriesMapper;

    @Autowired
    private BaseColourInfoMapper baseColourInfoMapper;

    @Autowired
    private AccessoriesImagesMapper accessoriesImagesMapper;

    @Transactional
    @Override
    public void saveAccessories(AddUpdateAccessoriesReq req) {

        Accessories accessories = JSONObject.parseObject(JSON.toJSONString(req), Accessories.class);

        String uuid = insertReturnUuid(accessories);

        //保存辅料颜色
        if (!StringUtils.isEmpty(uuid)){

            Example example = new Example(BaseColourInfo.class);
            example.createCriteria().andIn("rgb", req.getColor().stream().map(s ->
                    s.getColor().replace("(", "").replace(")", "")).collect(Collectors.toList()));

            List<BaseColourInfo> baseColourInfoList = baseColourInfoMapper.selectByExample(example);

            if (!baseColourInfoList.isEmpty()){
                for (int m = 0; m < baseColourInfoList.size(); m++){

                    BaseColourInfo baseColourInfo = baseColourInfoList.get(m);

                    AccessoriesImages images = new AccessoriesImages();
                    images.setColourId(baseColourInfo.getId());
                    images.setColourRgb(baseColourInfo.getRgb());
                    images.setAccessoriesUuid(uuid);
                    images.setCreateDate(new Date());
                    images.setModifyDate(new Date());
                    images.setVersion(VERSION);
                    images.setDelFlag(false);
                    images.setStatus(1);
                    images.setColourIdx(m);

                    accessoriesImagesMapper.insert(images);
                }
            }

        }

    }

    @Override
    public String insertReturnUuid(Accessories accessories) {
        Date currentDate = new Date();
        accessories.setCreateDate(currentDate);
        accessories.setModifyDate(currentDate);
        accessories.setDelFlag(false);
        accessories.setVersion(VERSION);
        accessories.setStatus(0);
        int result = accessoriesMapper.insertSelective(accessories);
        return result == 0 ? null : accessories.getUuid();
    }

    @Transactional
    @Override
    public void updateAccessories(UpdateAccessoriesReq req) {

        Accessories accessories = JSONObject.parseObject(JSON.toJSONString(req), Accessories.class);

        int result = updateAccessories(accessories);

        if (result > 0){

            Example example = new Example(BaseColourInfo.class);
            example.createCriteria().andIn("rgb", req.getColor().stream().map(s ->
                    s.getColor().replace("(", "").replace(")", "")).collect(Collectors.toList()));

            List<BaseColourInfo> baseColourInfoList = baseColourInfoMapper.selectByExample(example);

            AccessoriesImages var = new AccessoriesImages();
            var.setAccessoriesUuid(accessories.getUuid());
            var.setDelFlag(false);
            List<AccessoriesImages> accessoriesImages = accessoriesImagesMapper.select(var);

            Map<Integer, BaseColourInfo> colourInfoMap = baseColourInfoList.stream().collect(Collectors.toMap(BaseColourInfo::getId, Function.identity()));
            Map<Integer, AccessoriesImages> map = accessoriesImages.stream().collect(Collectors.toMap(AccessoriesImages::getColourId, Function.identity()));
            Map<String, Integer> mapImage = req.getImages().stream()
                    .collect(Collectors.toMap(AccessoriesDto.ImageDTO::getImgUuid, AccessoriesDto.ImageDTO::getStatus));

            //新增的颜色
            baseColourInfoList.forEach(c -> {

                if (!map.containsKey(c.getId())){
                    AccessoriesImages images = new AccessoriesImages();
                    images.setColourId(c.getId());
                    images.setColourRgb(c.getRgb());
                    images.setAccessoriesUuid(accessories.getUuid());
                    images.setCreateDate(new Date());
                    images.setModifyDate(new Date());
                    images.setVersion(VERSION);
                    images.setDelFlag(false);
                    images.setStatus(1);
                    images.setColourIdx(0);
                    accessoriesImagesMapper.insert(images);
                }
            });

            //删除的颜色
            accessoriesImages.forEach(m -> {

                if (!colourInfoMap.containsKey(m.getColourId())){

                    AccessoriesImages images = new AccessoriesImages();
                    images.setDelFlag(true);
                    images.setModifyDate(new Date());

                    Example example1 = new Example(AccessoriesImages.class);
                    Example.Criteria criteria = example1.createCriteria();
                    criteria.andEqualTo("uuid", m.getUuid());
                    accessoriesImagesMapper.updateByExampleSelective(images, example1);
                }

            });

            //按创建时间重新排序

            List<String> list = accessoriesImagesMapper.queryUUidByMaterialUuid(accessories.getUuid());

            for (int m = 0; m < list.size(); m++){

                String uuid = list.get(m);

                Example example2 = new Example(AccessoriesImages.class);
                Example.Criteria criteria = example2.createCriteria();
                criteria.andEqualTo("uuid", uuid);

                AccessoriesImages images = new AccessoriesImages();
                images.setModifyDate(new Date());
                images.setColourIdx(m);

                //修改图片禁用/启用
                if (mapImage.containsKey(uuid)){
                    images.setStatus(mapImage.get(uuid));
                }

                accessoriesImagesMapper.updateByExampleSelective(images, example2);
            }

        }
    }

    @Override
    public int updateAccessories(Accessories accessories) {

        Example example = new Example(Accessories.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid", accessories.getUuid());
        accessories.setModifyDate(new Date());
        return accessoriesMapper.updateByExampleSelective(accessories, example);
    }

    @Transactional
    @Override
    public void deleteAccessories(String accessoriesUuid) {

        Example example = new Example(Accessories.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid", accessoriesUuid);
        criteria.andEqualTo("delFlag", false);

        Accessories accessories = new Accessories();
        accessories.setDelFlag(true);
        accessories.setModifyDate(new Date());

        int result = accessoriesMapper.updateByExampleSelective(accessories, example);

        if (result > 0){
            accessoriesImagesMapper.delImagesByAccessoriesUuid(accessoriesUuid);
        }

    }

    /**
     * 默认版本号
     */
    private static final int VERSION = 1;
}
