package com.kingthy.platform.service.impl.basedata;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingthy.exception.BizException;
import com.kingthy.platform.entity.basedata.PantsCategory;
import com.kingthy.platform.entity.basedata.TechnicsCategory;
import com.kingthy.platform.mapper.basedata.TechnicsCategoryMapper;
import com.kingthy.platform.service.basedata.TechnicsCategoryService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * TechnicsServiceImpl(描述其作用)
 * <p>
 * 赵生辉 2017年07月28日 17:29
 *
 * @version 1.0.0
 */
@Service("technicsCategoryService")
public class TechnicsCategoryServiceImpl implements TechnicsCategoryService
{

    @Autowired
    private TechnicsCategoryMapper technicsCategoryMapper;

    @Autowired
    private HttpServletRequest httpServletRequest;

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
    public int create(TechnicsCategory technicsCategory)
    {
        Example example = new Example(TechnicsCategory.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("name", technicsCategory.getName());
        criteria.andEqualTo("delFlag", false);
        /* 判断是否已存在相同名称的数据 */
        if (technicsCategoryMapper.selectCountByExample(example) > 0)
        {
            return -1;
        }
        Date currentDate = new Date();
        technicsCategory.setCreateDate(currentDate);
        technicsCategory.setModifyDate(currentDate);
        technicsCategory.setCreator("Creator");
        technicsCategory.setModifier("Modifier");
        technicsCategory.setDelFlag(false);
        technicsCategory.setVersion(1);
        int result = technicsCategoryMapper.insertSelective(technicsCategory);
        if(result == 0){
            throw new BizException("创建上衣类型失败");
        }
        return result;
    }

    @Override
    public int delete(String uuid)
    {
        Example example = new Example(PantsCategory.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid",uuid);
        int result = technicsCategoryMapper.deleteByExample(example);
        if(result == 0)
        {
            throw new BizException("删除数据失败");
        }

        return result;
    }

    @Override
    public int update(TechnicsCategory technicsCategory)
    {
        if(technicsCategory.getName() != null)
        {
            Example selectCountexample = new Example(TechnicsCategory.class);
            Example.Criteria selectCountcriteria = selectCountexample.createCriteria();
            selectCountcriteria.andEqualTo("name",technicsCategory.getName());
            selectCountcriteria.andEqualTo("delFlag",false);
            int count = technicsCategoryMapper.selectCountByExample(selectCountexample);
            if(count > 1)
            {
                throw new BizException("已存在同名的面料");
            }
        }
        Example example = new Example(TechnicsCategory.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid", technicsCategory.getUuid());
        technicsCategory.setModifier(getUuid());
        technicsCategory.setModifyDate(new Date());
        return technicsCategoryMapper.updateByExampleSelective(technicsCategory, example);
    }

    @Override
    public TechnicsCategory queryInfo(String uuid)
    {
        Example example = new Example(TechnicsCategory.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid",uuid);
        criteria.andEqualTo("delFlag",false);
        TechnicsCategory result = null;
        try
        {
            result = technicsCategoryMapper.selectByExample(example).get(0);
        }
        catch (Exception e)
        {
            return null;
        }
        if(result == null){
            throw new BizException("没有找到数据");
        }
        return result;
    }

    @Override
    public PageInfo<TechnicsCategory> queryPage(int pageNum, int pageSize, TechnicsCategory technicsCategory)
    {
        Example example = new Example(TechnicsCategory.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("delFlag",false);
        if(technicsCategory.getType() != null)
        {
            criteria.andEqualTo("type",technicsCategory.getType());
        }
        if(technicsCategory.getName() != null)
        {
            criteria.andEqualTo("name",technicsCategory.getName());
        }
        if(technicsCategory.getStatus() != null)
        {
            criteria.andEqualTo("status",technicsCategory.getStatus());
        }
        PageHelper.startPage(pageNum,pageSize);
        List<TechnicsCategory> result = technicsCategoryMapper.selectByExample(example);

        if(result == null)
        {
            throw new BizException("没有找到指定的数据");
        }
        PageInfo<TechnicsCategory> technicsCategoryPageInfo = new PageInfo<>(result);
        return technicsCategoryPageInfo;
    }

    @Override
    public List<TechnicsCategory> queryAll()
    {
        return technicsCategoryMapper.selectAll();
    }
}
