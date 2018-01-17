package com.kingthy.platform.service.impl.basedata;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingthy.exception.BizException;
import com.kingthy.platform.entity.basedata.PantsCategory;
import com.kingthy.platform.entity.basedata.SkirtCategory;
import com.kingthy.platform.mapper.basedata.SkirtCategoryMapper;
import com.kingthy.platform.service.basedata.SkirtCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * SkirtCategoryServiceImpl(描述其作用)
 * <p>
 * 赵生辉 2017年07月10日 10:46
 *
 * @version 1.0.0
 */
@Service("/skirtCategory")
public class SkirtCategoryServiceImpl implements SkirtCategoryService
{
    @Autowired
    private SkirtCategoryMapper skirtCategoryMapper;

    @Override
    public int createSkirtCategory(SkirtCategory skirtCategory)
    {
        Example example = new Example(SkirtCategory.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("className", skirtCategory.getClassName());
        criteria.andEqualTo("delFlag", false);
        /* 判断是否已存在相同名称的数据 */
        if (skirtCategoryMapper.selectCountByExample(example) > 0)
        {
            return -1;
        }
        Date currentDate = new Date();
        skirtCategory.setCreateDate(currentDate);
        skirtCategory.setModifyDate(currentDate);
        skirtCategory.setCreator("Creator");
        skirtCategory.setModifier("Modifier");
        skirtCategory.setDelFlag(false);
        skirtCategory.setVersion(1);
        int result = skirtCategoryMapper.insertSelective(skirtCategory);
        if(result == 0){
            throw new BizException("创建上衣类型失败");
        }
        return result;
    }

    @Override
    public PageInfo<SkirtCategory> querySkirtCategory(int pageNum, int pageSize, SkirtCategory skirtCategory)
    {
        Example example = new Example(SkirtCategory.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("delFlag",false);
        if(skirtCategory.getType() != null)
        {
            criteria.andEqualTo("type",skirtCategory.getType());
        }
        if(skirtCategory.getParentId() != null)
        {
            criteria.andEqualTo("parentId",skirtCategory.getParentId());
        }
        if(skirtCategory.getGrade() != null)
        {
            criteria.andEqualTo("grade",skirtCategory.getGrade());
        }
        PageHelper.startPage(pageNum,pageSize);
        List<SkirtCategory> result = skirtCategoryMapper.selectByExample(example);

        if(result == null)
        {
            throw new BizException("没有找到指定的数据");
        }
        PageInfo<SkirtCategory> pantsCategoryPageInfo = new PageInfo<>(result);
        return pantsCategoryPageInfo;
    }

    @Override
    public SkirtCategory querySkirtCategoryInfo(String uuid)
    {
        Example example = new Example(SkirtCategory.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid",uuid);
        criteria.andEqualTo("delFlag",false);
        SkirtCategory result = null;
        try
        {
            result = skirtCategoryMapper.selectByExample(example).get(0);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        if(result == null){
            throw new BizException("没有找到数据");
        }
        return result;
    }

    @Override
    public int updateSkirtCategory(SkirtCategory skirtCategory)
    {
        Example example = new Example(PantsCategory.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid",skirtCategory.getUuid());
        criteria.andEqualTo("delFlag",false);
        int result = skirtCategoryMapper.updateByExampleSelective(skirtCategory,example);
        return result;
    }

    @Override
    public int deleteSkirtCategory(String uuid)
    {
        Example example = new Example(PantsCategory.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid",uuid);
        int result = skirtCategoryMapper.deleteByExample(example);
        if(result == 0)
        {
            throw new BizException("删除数据失败");
        }

        return result;
    }

    @Override
    public int updateGoodsNum()
    {
        return skirtCategoryMapper.updateGoodsNum();
    }

}
