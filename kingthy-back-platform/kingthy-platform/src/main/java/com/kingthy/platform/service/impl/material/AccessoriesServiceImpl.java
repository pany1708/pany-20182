package com.kingthy.platform.service.impl.material;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingthy.exception.BizException;
import com.kingthy.platform.dto.material.AccessoriesDto;
import com.kingthy.platform.entity.basedata.MaterielCategory;
import com.kingthy.platform.entity.material.Accessories;
import com.kingthy.platform.mapper.basedata.MaterielCategoryMapper;
import com.kingthy.platform.mapper.material.AccessoriesMapper;
import com.kingthy.platform.service.material.AccessoriesService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service(value = "accessoriesService")
public class AccessoriesServiceImpl implements AccessoriesService
{
    // 默认版本号
    private static final int VERSION = 1;
    
    @Autowired
    private AccessoriesMapper accessoriesMapper;
    
    @Autowired
    private MaterielCategoryMapper materielCategoryMapper;
    
    @Autowired
    private HttpServletRequest httpServletRequest;
    
    private Map parameterMap;
    
    @Override
    public int create(Accessories accessories)
    {
        Example example = new Example(Accessories.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("name",accessories.getName());
        int count = accessoriesMapper.selectCountByExample(example);
        if(count > 0)
        {
            throw new BizException("已存在同名的辅料");
        }
        Date currentDate = new Date();
        accessories.setCreateDate(currentDate);
        accessories.setModifyDate(currentDate);
        accessories.setDelFlag(false);
        accessories.setCreator(getUuid());
        accessories.setModifier(getUuid());
        accessories.setVersion(VERSION);
        int result = accessoriesMapper.insertSelective(accessories);
        if(result == 0)
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
    public AccessoriesDto findAccessories(String accessoriesUuid)
    {
        Example example = new Example(Accessories.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid", accessoriesUuid);
        criteria.andEqualTo("delFlag",false);
        List<Accessories> accessoriesList = accessoriesMapper.selectByExample(example);
        if(accessoriesList.size() == 0)
        {
            throw new BizException("该辅料不存在");
        }
        AccessoriesDto accessoriesDto = new AccessoriesDto();
        BeanUtils.copyProperties(accessoriesList.get(0), accessoriesDto);
        MaterielCategory materielCategory = new MaterielCategory();
        materielCategory.setUuid(accessoriesList.get(0).getMaterielUuid());
        materielCategory = materielCategoryMapper.selectOne(materielCategory);
        accessoriesDto.setMaterielName(null == materielCategory ? null : materielCategory.getClassName());
        if (accessoriesDto != null && null != accessoriesDto.getUuid())
        {
            return accessoriesDto;
        }
        else
        {
            return null;
        }
    }

    @Override
    public PageInfo<Accessories> findAccessoriesPage(int pageNum,int pageSize , Accessories accessories)
    {

        Example example = new Example(Accessories.class);
        Example.Criteria criteria = example.createCriteria();
        if(accessories.getCode() != null)
        {
            criteria.andEqualTo("code",accessories.getCode());
        }
        if(accessories.getName() != null)
        {
            criteria.andLike("name",accessories.getName());
        }
        if(accessories.getMaterielUuid() != null)
        {
            criteria.andEqualTo("materielUuid",accessories.getMaterielUuid());
        }
        if(accessories.getStatus() != null)
        {
            criteria.andEqualTo("status",accessories.getStatus());
        }
        criteria.andEqualTo("delFlag",false);
        PageHelper.startPage(pageNum,pageSize);
        List<Accessories> result = accessoriesMapper.selectByExample(example);

        if(result == null)
        {
            throw new BizException("该辅料不存在");
        }
        PageInfo<Accessories> materialPageInfo = new PageInfo<>(result);

        return materialPageInfo;
    }
    
    @Override
    public int deleteAccessories(String accessoriesUuid)
    {
        Example example = new Example(Accessories.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid", accessoriesUuid);
        criteria.andEqualTo("delFlag",false);
        Accessories accessories = accessoriesMapper.selectByExample(example).get(0);
        accessories.setDelFlag(true);
        accessories.setModifier(getUuid());
        accessories.setModifyDate(new Date());
        return accessoriesMapper.updateByExample(accessories, example);
    }
    
    @Override
    public int updateAccessories(Accessories accessories)
    {
        if(accessories.getName() != null)
        {
            Example selectCountexample = new Example(Accessories.class);
            Criteria selectCountcriteria = selectCountexample.createCriteria();
            selectCountcriteria.andEqualTo("name",accessories.getName());
            selectCountcriteria.andEqualTo("delFlag",false);
            int count = accessoriesMapper.selectCountByExample(selectCountexample);
            if(count > 1)
            {
                throw new BizException("已存在同名的辅料");
            }
        }

        Example example = new Example(Accessories.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid", accessories.getUuid());
        accessories.setModifier(getUuid());
        accessories.setModifyDate(new Date());
        return accessoriesMapper.updateByExampleSelective(accessories, example);
    }


    
}
