package com.kingthy.platform.service.impl.basedata;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingthy.exception.BizException;
import com.kingthy.platform.entity.basedata.StyleCategory;
import com.kingthy.platform.mapper.basedata.StyleCategoryMapper;
import com.kingthy.platform.service.basedata.StyleCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Service(value = "styleCategoryService")
public class StyleCategoryServiceImpl implements StyleCategoryService
{
    /**
     * 顶级节点的父节点id为0
     */
    private static final String topNode = "0";
    
    /**
     * 未被删除的数据delFlag为true
     */
    private static final boolean exist = false;
    
    /**
     * 已删除数据为false
     */
    private static final boolean notexist = true;
    
    /**
     * 节点转移动作
     */
    private static final String transfer = "transfer";
    
    /**
     * 节点修改动作
     */
    private static final String edit = "edit";
    
    /**
     * 禁止转移标识
     */
    private static final int transForbidden = -2;
    
    /**
     * 版本
     */
    private static final int version = 1;
    
    @Autowired
    private StyleCategoryMapper styleCategoryMapper;
    
    @Autowired
    private HttpServletRequest request;

    @Override
    public int create(StyleCategory styleCategory)
    {
        Date currentDate = new Date();
        styleCategory.setCreateDate(currentDate);
        styleCategory.setModifyDate(currentDate);
        styleCategory.setCreator("Creator");
        styleCategory.setModifier("Modifier");
        styleCategory.setDelFlag(false);
        styleCategory.setVersion(1);
        int result = styleCategoryMapper.insertSelective(styleCategory);
        if(result == 0)
        {
            throw new BizException("创建新的风格");
        }
        return result;
    }

    @Override
    public int deleteByUuid(String uuid)
    {
        Example example = new Example(StyleCategory.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid",uuid);
        int result = styleCategoryMapper.deleteByExample(example);
        if(result == 0)
        {
            throw new BizException("删除失败");
        }
        return result;
    }

    @Override
    public int updateSelective(StyleCategory styleCategory)
    {
        Example example = new Example(StyleCategory.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid",styleCategory.getUuid());
        int result = styleCategoryMapper.updateByExampleSelective(styleCategory,example);
        if(result == 0)
        {
            throw new BizException("修改失败");
        }
        return result;
    }

    @Override
    public List<StyleCategory> findAllStyleCategory(StyleCategory styleCategory)
    {

        return null;
    }

    @Override
    public StyleCategory findStyleCategoryInfo(String uuid)
    {
        Example example = new Example(StyleCategory.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid",uuid);
        StyleCategory result = null;
        try
        {
            result = styleCategoryMapper.selectByExample(example).get(0);
        }
        catch (Exception e)
        {
            throw new BizException("没有找到该风格");
        }
        if(result == null)
        {
            throw new BizException("查询风格详情失败");
        }
        return result;
    }

    @Override
    public PageInfo<StyleCategory> findAllTagCategoryPage(int pageNum, int pageSize, StyleCategory styleCategory)
    {
        Example example = new Example(StyleCategory.class);
        Criteria criteria = example.createCriteria();
        if(styleCategory.getClassName() != null)
        {
            criteria.andEqualTo("className",styleCategory.getClassName());
        }
        criteria.andEqualTo("uuid",styleCategory.getUuid());
        PageHelper.startPage(pageNum,pageSize);
        List<StyleCategory> styleCategories = styleCategoryMapper.selectByExample(example);
        PageInfo<StyleCategory> styleCategoryPageInfo = new PageInfo<>(styleCategories);
        return styleCategoryPageInfo;
    }

    @Override
    public int updateGoodsNum()
    {
        return styleCategoryMapper.updateGoodsNum();
    }

}
