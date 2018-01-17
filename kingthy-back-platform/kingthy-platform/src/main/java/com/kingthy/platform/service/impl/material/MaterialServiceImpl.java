package com.kingthy.platform.service.impl.material;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingthy.exception.BizException;
import com.kingthy.exception.MaterialBizException;
import com.kingthy.platform.dto.material.MaterialDto;
import com.kingthy.platform.entity.basedata.MaterielCategory;
import com.kingthy.platform.entity.material.Material;
import com.kingthy.platform.mapper.basedata.MaterielCategoryMapper;
import com.kingthy.platform.mapper.material.MaterialMapper;
import com.kingthy.platform.service.material.MaterialService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service(value = "materialService")
public class MaterialServiceImpl implements MaterialService
{
    // 默认版本号
    private static final int VERSION = 1;
    
    @Autowired
    private MaterialMapper materialMapper;
    
    @Autowired
    private MaterielCategoryMapper materielCategoryMapper;
    
    @Autowired
    private HttpServletRequest httpServletRequest;
    
    private Map<String, Object> parameterMap;
    
    @Override
    public int create(Material material)
    {
        Date currentDate = new Date();
        Example example = new Example(Material.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("name",material.getName());
        criteria.andEqualTo("delFlag",false);
        int count = materialMapper.selectCountByExample(example);
        if(count > 0)
        {
            throw new BizException("已存在同名的面料");
        }
        material.setCreateDate(currentDate);
        material.setModifyDate(currentDate);
        material.setDelFlag(false);
        material.setCreator(getUuid());
        material.setModifier(getUuid());
        material.setVersion(VERSION);
        int result = materialMapper.insertSelective(material);
        if (result == 0)
        {
            throw new BizException("创建失败");
        }
        return result;
    }
    
    /**
     * @desc 获取请求用户
     *
     * @author yuanml
     *
     * @return
     */
    private String getUuid()
    {
        String uuid = httpServletRequest.getHeader("uuid");
        if (null == uuid)
        {
            uuid = "";
        }
        return uuid;
    }
    
    @Override
    public MaterialDto findMaterial(String materialUuid)
    {
        Example example = new Example(Material.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid", materialUuid);
        criteria.andEqualTo("delFlag",false);
        List<Material> materials = materialMapper.selectByExample(example);
        MaterialDto materialDto = new MaterialDto();
        BeanUtils.copyProperties(materials.get(0), materialDto);
        MaterielCategory materielCategory = new MaterielCategory();
        materielCategory.setUuid(materials.get(0).getMaterielUuid());
        materielCategory = materielCategoryMapper.selectOne(materielCategory);
        materialDto.setMaterielName(null == materielCategory ? null : materielCategory.getClassName());
        if (materialDto != null && null != materialDto.getUuid())
        {
            return materialDto;
        }
        else
        {
            throw MaterialBizException.MATERIAL_ONE;
        }
        
    }
    
    @Override
    public PageInfo<Material> findMaterialPage(int pageNum ,int pageSize ,Material material)
    {
        Example example = new Example(Material.class);
        Example.Criteria criteria = example.createCriteria();
        if(material.getCode() != null)
        {
            criteria.andEqualTo("code",material.getCode());
        }
        if(material.getName() != null)
        {
            criteria.andLike("name",material.getName());
        }
        if(material.getMaterielUuid() != null)
        {
            criteria.andEqualTo("materielUuid",material.getMaterielUuid());
        }
        if(material.getStatus() != null)
        {
            criteria.andEqualTo("status",material.getStatus());
        }
        criteria.andEqualTo("delFlag",false);
        PageHelper.startPage(pageNum,pageSize);
        List<Material> result = materialMapper.selectByExample(example);

        if(result == null)
        {
            throw new BizException("该面料不存在");
        }
        PageInfo<Material> materialPageInfo = new PageInfo<>(result);

        return materialPageInfo;
    }
    
    @Override
    public int deleteMaterial(String materialUuid)
    {
        Example example = new Example(Material.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid", materialUuid);
        Material material = materialMapper.selectByExample(example).get(0);
        material.setDelFlag(true);
        material.setModifier(getUuid());
        material.setModifyDate(new Date());
        return materialMapper.updateByExample(material, example);
    }
    
    @Override
    public int updateMaterial(Material material)
    {
        if(material.getName() != null)
        {
            Example selectCountexample = new Example(Material.class);
            Criteria selectCountcriteria = selectCountexample.createCriteria();
            selectCountcriteria.andEqualTo("name",material.getName());
            selectCountcriteria.andEqualTo("delFlag",false);
            int count = materialMapper.selectCountByExample(selectCountexample);
            if(count > 1)
            {
                throw new BizException("已存在同名的面料");
            }
        }
        Example example = new Example(Material.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid", material.getUuid());
        material.setModifier(getUuid());
        material.setModifyDate(new Date());
        return materialMapper.updateByExampleSelective(material, example);
    }
    
    @Override
    public List<String> selectColor(String rgb)
    {
        return materialMapper.selectColor(rgb);
    }
    
}
