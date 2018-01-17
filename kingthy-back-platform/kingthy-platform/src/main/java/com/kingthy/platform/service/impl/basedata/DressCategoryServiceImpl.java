package com.kingthy.platform.service.impl.basedata;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingthy.exception.BizException;
import com.kingthy.platform.entity.basedata.CoatCategory;
import com.kingthy.platform.entity.basedata.DressCategory;
import com.kingthy.platform.mapper.basedata.DressCategoryMapper;
import com.kingthy.platform.service.basedata.DressCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * DressCategoryServiceImpl(描述其作用)
 * <p>
 * 赵生辉 2017年07月10日 10:44
 *
 * @version 1.0.0
 */
@Service("/dressCategory")
public class DressCategoryServiceImpl implements DressCategoryService
{
    @Autowired
    private DressCategoryMapper dressCategoryMapper;

    @Override
    public int createDressCategory(DressCategory dressCategory)
    {
        Example example = new Example(DressCategory.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("className", dressCategory.getClassName());
        criteria.andEqualTo("delFlag", false);
        /* 判断是否已存在相同名称的数据 */
        if (dressCategoryMapper.selectCountByExample(example) > 0)
        {
            return -1;
        }
        Date currentDate = new Date();
        dressCategory.setCreateDate(currentDate);
        dressCategory.setModifyDate(currentDate);
        dressCategory.setCreator("Creator");
        dressCategory.setModifier("Modifier");
        dressCategory.setDelFlag(false);
        dressCategory.setVersion(1);

        int result = dressCategoryMapper.insertSelective(dressCategory);
        if(result == 0){
            throw new BizException("创建上衣类型失败");
        }
        return result;
    }

    @Override
    public PageInfo<DressCategory> queryDressCategory(int pageNum, int pageSize, DressCategory dressCategory)
    {
        Example example = new Example(DressCategory.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("delFlag",false);
        if(dressCategory.getType() != null)
        {
            criteria.andEqualTo("type",dressCategory.getType());
        }
        if(dressCategory.getParentId() != null)
        {
            criteria.andEqualTo("parentId",dressCategory.getParentId());
        }
        if(dressCategory.getGrade() != null)
        {
            criteria.andEqualTo("grade",dressCategory.getGrade());
        }
        PageHelper.startPage(pageNum,pageSize);
        List<DressCategory> result = dressCategoryMapper.selectByExample(example);

        if(result == null)
        {
            throw new BizException("没有找到指定的数据");
        }
        PageInfo<DressCategory> coatCategoryPageInfo = new PageInfo<>(result);
        return coatCategoryPageInfo;
    }

    @Override
    public DressCategory queryDressCategoryInfo(String uuid)
    {
        Example example = new Example(DressCategory.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid",uuid);
        criteria.andEqualTo("delFlag",false);
        DressCategory result = null;
        try
        {
            result = dressCategoryMapper.selectByExample(example).get(0);
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
    public int updateDressCategory(DressCategory dressCategory)
    {
        Example example = new Example(DressCategory.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid",dressCategory.getUuid());
        criteria.andEqualTo("delFlag",false);
        int result = dressCategoryMapper.updateByExampleSelective(dressCategory,example);
        return result;
    }

    @Override
    public int deleteDressCategory(String uuid)
    {
        Example example = new Example(CoatCategory.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid",uuid);
        int result = dressCategoryMapper.deleteByExample(example);
        if(result == 0)
        {
            throw new BizException("删除数据失败");
        }

        return result;
    }

    @Override
    public int updateGoodsNum()
    {
        return dressCategoryMapper.updateGoodsNum();
    }

}
