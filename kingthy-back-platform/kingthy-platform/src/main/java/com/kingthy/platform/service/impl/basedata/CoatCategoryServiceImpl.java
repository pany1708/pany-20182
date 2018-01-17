package com.kingthy.platform.service.impl.basedata;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingthy.exception.BizException;
import com.kingthy.platform.entity.basedata.CoatCategory;
import com.kingthy.platform.entity.basedata.DressCategory;
import com.kingthy.platform.mapper.basedata.CoatCategoryMapper;
import com.kingthy.platform.service.basedata.CoatCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * CoatCategoryServiceImpl(描述其作用)
 * <p>
 * 赵生辉 2017年07月05日 18:33
 *
 * @version 1.0.0
 */
@Service("coatCategoryService")
public class CoatCategoryServiceImpl implements CoatCategoryService
{

    @Autowired
    private CoatCategoryMapper coatCategoryMapper;

    @Override
    public int createCoatCategory(CoatCategory coatCategory)
    {
        Example example = new Example(CoatCategory.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("className", coatCategory.getClassName());
        criteria.andEqualTo("delFlag", false);
        /* 判断是否已存在相同名称的数据 */
        if (coatCategoryMapper.selectCountByExample(example) > 0)
        {
            return -1;
        }
        Date currentDate = new Date();
        coatCategory.setCreateDate(currentDate);
        coatCategory.setModifyDate(currentDate);
        coatCategory.setCreator("Creator");
        coatCategory.setModifier("Modifier");
        coatCategory.setDelFlag(false);
        coatCategory.setVersion(1);
        if (coatCategory.getParentId() != null)// 判断是否有上级节点来确定当前节点的级别
        {
            Example exampleParent = new Example(CoatCategory.class);
            Example.Criteria criteriaParent = exampleParent.createCriteria();
            criteriaParent.andEqualTo("uuid", coatCategory.getParentId());
            criteriaParent.andEqualTo("delFlag",false);
            int grade = 0;
            if (coatCategory.getParentId().equals("0"))
            {
                grade = -1;
            }
            else
            {
                List<CoatCategory> partsCategories = coatCategoryMapper.selectByExample(exampleParent);
                if (partsCategories.size() == 0)
                {
                    throw new BizException("该父类部件不存在");
                }
                else
                {
                    grade = coatCategoryMapper.selectByExample(exampleParent).get(0).getGrade();
                }
            }
            coatCategory.setGrade(grade + 1);
        }
        int result = coatCategoryMapper.insertSelective(coatCategory);
        if(result == 0){
            throw new BizException("创建上衣类型失败");
        }
        return result;
    }

    @Override
    public PageInfo<CoatCategory> queryCoatCategory(int pageNum, int pageSize, CoatCategory coatCategory)
    {
        Example example = new Example(CoatCategory.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("delFlag",false);
        if(coatCategory.getType() != null)
        {
            criteria.andEqualTo("type",coatCategory.getType());
        }
        if(coatCategory.getParentId() != null)
        {
            criteria.andEqualTo("parentId",coatCategory.getParentId());
        }
        if(coatCategory.getGrade() != null)
        {
            criteria.andEqualTo("grade",coatCategory.getGrade());
        }
        PageHelper.startPage(pageNum,pageSize);
        List<CoatCategory> result = coatCategoryMapper.selectByExample(example);

        if(result == null)
        {
            throw new BizException("没有找到指定的数据");
        }
        PageInfo<CoatCategory> coatCategoryPageInfo = new PageInfo<>(result);
        return coatCategoryPageInfo;
    }

    @Override
    public CoatCategory queryCoatCategoryInfo(String uuid)
    {
        Example example = new Example(CoatCategory.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid",uuid);
        criteria.andEqualTo("delFlag",false);
        CoatCategory result = null;
        try
        {
            result = coatCategoryMapper.selectByExample(example).get(0);
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
    public int updateCoatCategory(CoatCategory coatCategory)
    {
        Example example = new Example(DressCategory.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid",coatCategory.getUuid());
        criteria.andEqualTo("delFlag",false);
        int result = coatCategoryMapper.updateByExampleSelective(coatCategory,example);
        return result;
    }

    @Override
    public int deleteCoatCategory(String uuid)
    {
        Example example = new Example(CoatCategory.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid",uuid);
        /*CoatCategory coatCategory = coatCategoryMapper.selectByExample(example).get(0);
        if(coatCategory.getGrade() == 0)//清除子类的数据
        {
            Example childExample = new Example(CoatCategory.class);
            Example.Criteria childCriteria = childExample.createCriteria();
            childCriteria.andEqualTo("parentId",coatCategory.getUuid());
            coatCategoryMapper.deleteByExample(childExample);
        }*/
        int result = coatCategoryMapper.deleteByExample(example);
        if(result == 0)
        {
            throw new BizException("删除数据失败");
        }

        return result;
    }

    @Override
    public int updateGoodsNum()
    {
        return coatCategoryMapper.updateGoodsNum();
    }

}
