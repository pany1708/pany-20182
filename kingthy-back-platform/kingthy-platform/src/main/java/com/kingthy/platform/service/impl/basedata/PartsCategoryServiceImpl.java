package com.kingthy.platform.service.impl.basedata;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingthy.exception.BizException;
import com.kingthy.platform.entity.basedata.PartsCategory;
import com.kingthy.platform.entity.material.Accessories;
import com.kingthy.platform.mapper.basedata.PartsCategoryMapper;
import com.kingthy.platform.service.basedata.PartsCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 *
 *
 * PartsCategoryServiceImpl
 *
 * 赵生辉 2017年3月29日 下午1:56:03
 *
 * @version 1.0.0
 *
 */
@Service(value = "partsCategoryService")
public class PartsCategoryServiceImpl implements PartsCategoryService
{
    // 默认版本号
    private static final int VERSION = 1;

    @Autowired
    private transient PartsCategoryMapper partsCategoryMapper;

    @Autowired
    private HttpServletRequest httpServletRequest;

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
    public int create(PartsCategory partsCategory)
    {
        Example example = new Example(PartsCategory.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("className",partsCategory.getClassName());
        int count = partsCategoryMapper.selectCountByExample(example);
        if(count > 0)
        {
            throw new BizException("已存在同名的辅料");
        }
        Date currentDate = new Date();
        partsCategory.setCreateDate(currentDate);
        partsCategory.setModifyDate(currentDate);
        partsCategory.setDelFlag(false);
        partsCategory.setCreator(getUuid());
        partsCategory.setModifier(getUuid());
        partsCategory.setVersion(VERSION);
        int result = partsCategoryMapper.insertSelective(partsCategory);
        if(result == 0)
        {
            throw new BizException("创建失败");
        }
        return result;
    }

    @Override
    public int deleteById(String uuid)
    {
        Example example = new Example(PartsCategory.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid", uuid);
        criteria.andEqualTo("delFlag",false);
        PartsCategory partsCategory = null;
        try
        {
            partsCategory = partsCategoryMapper.selectByExample(example).get(0);
        }
        catch (Exception e)
        {
            throw new BizException("该部件不存在");
        }
        partsCategory.setDelFlag(true);
        partsCategory.setModifier(getUuid());
        partsCategory.setModifyDate(new Date());
        int result = partsCategoryMapper.updateByExample(partsCategory, example);
        if(result == 0)
        {
            throw new BizException("删除失败");
        }
        return result;
    }

    @Override
    public int updateSelective(PartsCategory partsCategory)
    {
        Example selectCountexample = new Example(PartsCategory.class);
        Criteria selectCountcriteria = selectCountexample.createCriteria();

        if(partsCategory.getClassName() != null)
        {
            selectCountcriteria.andEqualTo("className",partsCategory.getClassName());
            selectCountcriteria.andEqualTo("delFlag",false);
            int count = partsCategoryMapper.selectCountByExample(selectCountexample);
            if(count > 1)
            {
                throw new BizException("已存在同名的部件");
            }
        }

        Example example = new Example(PartsCategory.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid", partsCategory.getUuid());
        partsCategory.setModifier(getUuid());
        partsCategory.setModifyDate(new Date());
        int result = partsCategoryMapper.updateByExampleSelective(partsCategory, example);
        if(result == 0)
        {
            throw new BizException("更新失败");
        }
        return result;
    }

    @Override
    public PageInfo<PartsCategory> findAllPartsCategoryPage(int pageNum, int pageSize, PartsCategory partsCategory)
    {
        Example example = new Example(PartsCategory.class);
        Example.Criteria criteria = example.createCriteria();
        if(partsCategory.getSn() != null)
        {
            criteria.andEqualTo("sn",partsCategory.getSn());
        }
        if(partsCategory.getClassName() != null)
        {
            criteria.andLike("className",partsCategory.getClassName());
        }
        if(partsCategory.getType() != null)
        {
            criteria.andEqualTo("type",partsCategory.getType());
        }
        if(partsCategory.getStatus() != null)
        {
            criteria.andEqualTo("status",partsCategory.getStatus());
        }
        criteria.andEqualTo("delFlag",false);
        PageHelper.startPage(pageNum,pageSize);
        List<PartsCategory> result = partsCategoryMapper.selectByExample(example);

        if(result == null)
        {
            throw new BizException("该辅料不存在");
        }
        PageInfo<PartsCategory> materialPageInfo = new PageInfo<>(result);

        return materialPageInfo;
    }

    @Override
    public PartsCategory findPartsCategory(String uuid)
    {
        Example example = new Example(PartsCategory.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid", uuid);
        criteria.andEqualTo("delFlag",false);
        List<PartsCategory> accessoriesList = partsCategoryMapper.selectByExample(example);
        if(accessoriesList.size() == 0)
        {
            throw new BizException("该辅料不存在");
        }
        return accessoriesList.get(0);
    }

    @Override
    public List<PartsCategory> findAllPartsCategory()
    {
        return partsCategoryMapper.selectAll();
    }
}
