package com.kingthy.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.kingthy.dto.MaterialDto;
import com.kingthy.dubbo.basedata.service.MaterialDubboService;
import com.kingthy.entity.Material;
import com.kingthy.entity.MaterialImages;
import com.kingthy.mapper.MaterialImagesMapper;
import com.kingthy.mapper.MaterialMapper;
import com.kingthy.mapper.MaterielCategoryMapper;
import com.kingthy.request.AddUpdateMaterialReq;
import com.kingthy.request.QueryResourceInfoReq;
import com.kingthy.request.UpdateMaterialReq;
import com.kingthy.response.QueryResourceInfoResp;
import com.kingthy.transaction.MaterialServiceTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * @author zhaochen
 */
@Service(version = "1.0.0", timeout = 3000)
public class MaterialServiceImpl implements MaterialDubboService
{
    /**
     * 默认版本号
     */
    private static final int VERSION = 1;

    @Override
    public int updateResourceList(List<QueryResourceInfoResp> queryResourceInfoRespList)
    {
        return 0;
    }

    @Autowired
    private MaterialMapper materialMapper;

    @Autowired
    private MaterielCategoryMapper materielCategoryMapper;

    @Autowired
    private MaterialImagesMapper materialImagesMapper;

    @Autowired
    private MaterialServiceTransaction materialServiceTransaction;

    @Override
    public List<Material> list(int pageNum, int pageSize, Material var)
    {
        PageHelper.startPage(pageNum, pageSize);
        return materialMapper.select(var);
    }

    @Override
    public int create(Material material)
    {
        Date currentDate = new Date();
        material.setCreateDate(currentDate);
        material.setModifyDate(currentDate);
        material.setDelFlag(false);
        material.setVersion(VERSION);
//        MaterielCategory materielCategory = materielCategoryService.selectByUuid(material.getSourceUuid());
//        material.setCode(materielCategory.getCode());
//        material(materielCategory.getClassName());
        return materialMapper.insertSelective(material);
    }

    @Override
    public String insertReturnUuid(Material material)
    {

        return materialServiceTransaction.insertReturnUuid(material);
    }

    @Override
    public void saveMaterial(AddUpdateMaterialReq req) {

         materialServiceTransaction.saveMaterial(req);
    }

    @Override
    public void updateMaterial(UpdateMaterialReq req) {
        materialServiceTransaction.updateMaterial(req);
    }

    @Override
    public MaterialDto findMaterial(String materialUuid)
    {
        MaterialDto dto = materialMapper.findMaterialByUuid(materialUuid);

        if (dto != null){
            MaterialImages var = new MaterialImages();
            var.setMaterialUuid(materialUuid);
            var.setDelFlag(false);

            List<MaterialDto.ColorDTO> color = new ArrayList<>();
            List<MaterialDto.ImageDTO> images = new ArrayList<>();
            materialImagesMapper.select(var).forEach(m -> {

                MaterialDto.ColorDTO colorDTO = new MaterialDto.ColorDTO();
                colorDTO.setColor("("+m.getColourRgb()+")");
                colorDTO.setDelFlag(StringUtils.isEmpty(m.getImagePath()));
                color.add(colorDTO);

                MaterialDto.ImageDTO imageDTO = new MaterialDto.ImageDTO();
                imageDTO.setImg(m.getImagePath());
                imageDTO.setStatus(m.getStatus());
                imageDTO.setImgNo(dto.getCode() + (m.getColourIdx() < 10 ? "0" + m.getColourIdx() : m.getColourIdx()));
                imageDTO.setImgUuid(m.getUuid());
                images.add(imageDTO);
            });

            dto.setColor(color);
            dto.setImages(images);
        }

        return dto;
    }

    @Override
    public List<QueryResourceInfoResp> queryResourceInfoBySubCategory(QueryResourceInfoReq queryResourceInfoReq)
    {

        List<QueryResourceInfoResp> resourceInfoRespList =
            materialMapper.queryResourceInfoBySubCategory(queryResourceInfoReq);
        if (resourceInfoRespList != null && resourceInfoRespList.size() > 0)
        {
            return resourceInfoRespList;
        }
        else
        {
            QueryResourceInfoResp resourceInfoResp = new QueryResourceInfoResp();
            resourceInfoRespList.add(resourceInfoResp);
            return resourceInfoRespList;
        }
    }

    @Override
    public PageInfo<MaterialDto> findMaterialPage(int pageNum, int pageSize, Material material)
    {
        PageHelper.startPage(pageNum, pageSize);
        List<MaterialDto> result = materialMapper.findMaterialPage(material);

        if (!result.isEmpty())
        {

            //查询面料的颜色 和 图片

            Example example = new Example(MaterialImages.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andIn("materialUuid", result.stream().map(MaterialDto::getUuid).collect(Collectors.toList()));
            criteria.andEqualTo("delFlag", false);

            Gson gson = new Gson();

            Map<String, List<MaterialImages>> map =
                materialImagesMapper.selectByExample(example)
                    .stream()
                    .collect(Collectors.groupingBy(MaterialImages::getMaterialUuid));

            result.stream().filter(c -> map.containsKey(c.getUuid())).forEach(m ->
                {
//                    m.setImages(map.get(m.getUuid()).stream().filter(i -> !StringUtils.isEmpty(i.getImagePath())).map(MaterialImages::getImagePath).collect(Collectors.toList()));
//                    m.setImg(gson.toJson(map.get(m.getUuid()).stream().map(MaterialImages::getImagePath).collect(Collectors.toList())));
                    m.setColors(gson.toJson(map.get(m.getUuid()).stream().map(MaterialImages::getColourRgb).collect(Collectors.toList())));
                }
            );

        }

        return new PageInfo<>(result);
    }

    @Override
    public void deleteMaterial(String materialUuid)
    {
        materialServiceTransaction.deleteMaterial(materialUuid);
    }

    @Override
    public int updateMaterial(Material material)
    {
        return materialServiceTransaction.updateMaterial(material);
    }

    @Override
    public List<String> selectColor(String rgb)
    {
        return materialMapper.selectColor(rgb);
    }

    @Override
    public List<Material> findMaterial(List<String> list)
    {
        return materialMapper.findMaterialByUuidList(list);
    }

    @Override
    public Material selectMaterialByCode(String code)
    {

        Material var = new Material();
        var.setCode(code);
        return materialMapper.selectOne(var);
    }

    @Override
    public Material selectMaterialByUuid(String uuid)
    {
        return materialMapper.selectMaterialByUuid(uuid);
    }

    @Override
    public int selectCountByExample(String materialName, String materialUuid)
    {
        return StringUtils.isEmpty(materialUuid) ? materialMapper.selectCountByName(materialName)
                : materialMapper.selectCountByNameAndUuid(materialName, materialUuid);
    }
}
