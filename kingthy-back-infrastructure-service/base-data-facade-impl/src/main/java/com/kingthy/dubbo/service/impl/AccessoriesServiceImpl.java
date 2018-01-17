package com.kingthy.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.kingthy.dto.AccessoriesDto;
import com.kingthy.dto.AccessoriesFileDto;
import com.kingthy.dto.MaterialDto;
import com.kingthy.dubbo.basedata.service.AccessoriesDubboService;
import com.kingthy.entity.Accessories;
import com.kingthy.entity.AccessoriesImages;
import com.kingthy.entity.MaterielCategory;
import com.kingthy.exception.BizException;
import com.kingthy.mapper.AccessoriesImagesMapper;
import com.kingthy.mapper.AccessoriesMapper;
import com.kingthy.mapper.MaterielCategoryMapper;
import com.kingthy.request.AddUpdateAccessoriesReq;
import com.kingthy.request.UpdateAccessoriesReq;
import com.kingthy.transaction.AccessoriesServiceTransaction;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author zhaochen
 */
@Service(version = "1.0.0", timeout = 3000)
public class AccessoriesServiceImpl implements AccessoriesDubboService
{
    /**
     * 默认版本号
     */
    private static final int VERSION = 1;
    
    @Autowired
    private AccessoriesMapper accessoriesMapper;
    
    @Autowired
    private MaterielCategoryMapper materielCategoryMapper;
    
    @Autowired
    private MaterielCategoryServiceImpl materielCategoryService;

    @Autowired
    private AccessoriesServiceTransaction accessoriesServiceTransaction;

    @Autowired
    private AccessoriesImagesMapper accessoriesImagesMapper;
    
    @Override
    public int create(Accessories accessories)
    {
        /*Example example = new Example(Accessories.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("name", accessories.getName());
        int count = accessoriesMapper.selectCountByExample(example);
        if (count > 0)
        {
            throw new BizException("已存在同名的辅料");
        }
        Date currentDate = new Date();
        MaterielCategory materielCategory = materielCategoryService.selectByUuid(accessories.getMaterielUuid());
        accessories.setCode(materielCategory.getCode());
        accessories.setMaterielUuid(materielCategory.getClassName());
        accessories.setCreateDate(currentDate);
        accessories.setModifyDate(currentDate);
        accessories.setDelFlag(false);
        accessories.setVersion(VERSION);
        int result = accessoriesMapper.insertSelective(accessories);*/
        return 1;
    }

    @Override
    public void saveAccessories(AddUpdateAccessoriesReq req) {
        accessoriesServiceTransaction.saveAccessories(req);
    }

    @Override
    public String insertReturnUuid(Accessories accessories)
    {

        return accessoriesServiceTransaction.insertReturnUuid(accessories);
    }
    
    @Override
    public AccessoriesDto findAccessories(String accessoriesUuid)
    {
        AccessoriesDto accessoriesDto = accessoriesMapper.findAccessoriesDtoByUuid(accessoriesUuid);
        if (accessoriesDto != null && null != accessoriesDto.getUuid())
        {

            AccessoriesImages var = new AccessoriesImages();
            var.setAccessoriesUuid(accessoriesUuid);
            var.setDelFlag(false);

            List<AccessoriesDto.ColorDTO> color = new ArrayList<>();
            List<AccessoriesDto.ImageDTO> images = new ArrayList<>();
            accessoriesImagesMapper.select(var).stream().forEach(m -> {

                AccessoriesDto.ColorDTO colorDTO = new AccessoriesDto.ColorDTO();
                colorDTO.setColor("("+m.getColourRgb()+")");
                colorDTO.setDelFlag(StringUtils.isEmpty(m.getImagePath()));
                color.add(colorDTO);

                AccessoriesDto.ImageDTO imageDTO = new AccessoriesDto.ImageDTO();
                imageDTO.setImg(m.getImagePath());
                imageDTO.setStatus(m.getStatus());
                imageDTO.setImgNo(accessoriesDto.getCode() + (m.getColourIdx() < 10 ? "0" + m.getColourIdx() : m.getColourIdx()));
                imageDTO.setImgUuid(m.getUuid());
                images.add(imageDTO);

            });

            accessoriesDto.setColor(color);
            accessoriesDto.setImages(images);
            return accessoriesDto;
        }
        else
        {
            return null;
        }
    }
    
    @Override
    public PageInfo<AccessoriesDto> findAccessoriesPage(int pageNum, int pageSize, Accessories accessories)
    {
        PageHelper.startPage(pageNum, pageSize);
        List<AccessoriesDto> result = accessoriesMapper.findAccessoriesPage(accessories);

        if (!result.isEmpty()){

            //查询面料的颜色 和 图片

            Example example = new Example(AccessoriesImages.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andIn("accessoriesUuid", result.stream().map(AccessoriesDto::getUuid).collect(Collectors.toList()));
            criteria.andEqualTo("delFlag", false);

            Gson gson = new Gson();

            Map<String, List<AccessoriesImages>> map =
                    accessoriesImagesMapper.selectByExample(example).stream().collect(Collectors.groupingBy(AccessoriesImages::getAccessoriesUuid));

            result.stream().filter(c -> map.containsKey(c.getUuid())).forEach(m ->
                    {
//                        m.setImages(map.get(m.getUuid()).stream().filter(i -> !StringUtils.isEmpty(i.getImagePath()))
//                                .map(AccessoriesImages::getImagePath).collect(Collectors.toList()));
                        m.setColors(gson.toJson(map.get(m.getUuid()).stream().map(AccessoriesImages::getColourRgb).collect(Collectors.toList())));
                    }
            );

        }

        return new PageInfo<>(result);
    }


    @Override
    public void deleteAccessories(String accessoriesUuid)
    {
        accessoriesServiceTransaction.deleteAccessories(accessoriesUuid);
    }
    
    @Override
    public int updateAccessories(Accessories accessories)
    {
        return accessoriesServiceTransaction.updateAccessories(accessories);
    }

    @Override
    public void updateAccessories(UpdateAccessoriesReq req) {
        accessoriesServiceTransaction.updateAccessories(req);
    }

    @Override
    public List<Accessories> findAccessories(List<String> list)
    {
        return accessoriesMapper.findAccessoriesBatchByUuids(list);
    }
    
    @Override
    public Accessories selectAccessoriesByCode(String code)
    {
        return accessoriesMapper.selectAccessoriesByCode(code);
    }
    
    @Override
    public Accessories selectAccessoriesByUuid(String uuid)
    {
        return accessoriesMapper.selectAccessoriesByUuid(uuid);
    }
    
    @Override
    public int selectCountByExample(String accessoriesName, String accessoriesUuid)
    {
        return StringUtils.isEmpty(accessoriesUuid) ? accessoriesMapper.selectCountByName(accessoriesName)
                : accessoriesMapper.selectCountByNameAndUuid(accessoriesName, accessoriesUuid);
    }

    
    @Override
    public List<AccessoriesFileDto> findFiles()
    {
        return accessoriesMapper.findFiles();
    }
}
