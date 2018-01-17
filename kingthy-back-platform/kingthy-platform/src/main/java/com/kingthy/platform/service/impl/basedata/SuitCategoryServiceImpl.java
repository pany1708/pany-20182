package com.kingthy.platform.service.impl.basedata;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingthy.exception.BizException;
import com.kingthy.platform.entity.basedata.SkirtCategory;
import com.kingthy.platform.entity.basedata.SuitCategory;
import com.kingthy.platform.mapper.basedata.SuitCategoryMapper;
import com.kingthy.platform.service.basedata.SuitCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * SuitCategoryServiceImpl(描述其作用)
 * <p>
 * 赵生辉 2017年07月10日 10:46
 *
 * @version 1.0.0
 */
@Service("/suitCategory")
public class SuitCategoryServiceImpl implements SuitCategoryService
{
    @Autowired
    private SuitCategoryMapper suitCategoryMapper;

    @Override
    public int createSuitCategory(SuitCategory suitCategory)
    {
        Example example = new Example(SkirtCategory.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("className", suitCategory.getClassName());
        criteria.andEqualTo("delFlag", false);
        /* 判断是否已存在相同名称的数据 */
        if (suitCategoryMapper.selectCountByExample(example) > 0)
        {
            return -1;
        }
        Date currentDate = new Date();
        suitCategory.setCreateDate(currentDate);
        suitCategory.setModifyDate(currentDate);
        suitCategory.setCreator("Creator");
        suitCategory.setModifier("Modifier");
        suitCategory.setDelFlag(false);
        suitCategory.setVersion(1);
        int result = suitCategoryMapper.insertSelective(suitCategory);
        if(result == 0){
            throw new BizException("创建上衣类型失败");
        }
        return result;
    }

    @Override
    public PageInfo<SuitCategory> querySuitCategory(int pageNum, int pageSize, SuitCategory suitCategory)
    {
        Example example = new Example(SuitCategory.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("delFlag",false);
        if(suitCategory.getType() != null)
        {
            criteria.andEqualTo("type",suitCategory.getType());
        }
        if(suitCategory.getParentId() != null)
        {
            criteria.andEqualTo("parentId",suitCategory.getParentId());
        }
        if(suitCategory.getGrade() != null)
        {
            criteria.andEqualTo("grade",suitCategory.getGrade());
        }
        PageHelper.startPage(pageNum,pageSize);
        List<SuitCategory> result = suitCategoryMapper.selectByExample(example);

        if(result == null)
        {
            throw new BizException("没有找到指定的数据");
        }
        PageInfo<SuitCategory> suitCategoryPageInfo = new PageInfo<>(result);
        return suitCategoryPageInfo;
    }

    @Override
    public SuitCategory querySuitCategoryInfo(String uuid)
    {
        Example example = new Example(SuitCategory.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid",uuid);
        criteria.andEqualTo("delFlag",false);
        SuitCategory result = null;
        try
        {
            result = suitCategoryMapper.selectByExample(example).get(0);
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
    public int updateSuitCategory(SuitCategory suitCategory)
    {
        Example example = new Example(SuitCategory.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid",suitCategory.getUuid());
        criteria.andEqualTo("delFlag",false);
        int result = suitCategoryMapper.updateByExampleSelective(suitCategory,example);
        return result;
    }

    @Override
    public int deleteSuitCategory(String uuid)
    {
        Example example = new Example(SuitCategory.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid",uuid);
        int result = suitCategoryMapper.deleteByExample(example);
        if(result == 0)
        {
            throw new BizException("删除数据失败");
        }

        return result;
    }

    @Override
    public int updateGoodsNum()
    {
        return suitCategoryMapper.updateGoodsNum();
    }

}
