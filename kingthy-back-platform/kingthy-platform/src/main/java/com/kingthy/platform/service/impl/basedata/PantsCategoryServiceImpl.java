package com.kingthy.platform.service.impl.basedata;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingthy.exception.BizException;
import com.kingthy.platform.entity.basedata.PantsCategory;
import com.kingthy.platform.mapper.basedata.PantsCategoryMapper;
import com.kingthy.platform.service.basedata.PantsCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * PantsCategoryServiceImpl(描述其作用)
 * <p>
 * 赵生辉 2017年07月10日 10:01
 *
 * @version 1.0.0
 */
@Service("/pantsCategory")
public class PantsCategoryServiceImpl implements PantsCategoryService
{

    @Autowired
    private PantsCategoryMapper pantsCategoryMapper;

    @Override
    public int createPantsCategory(PantsCategory pantsCategory)
    {
        Example example = new Example(PantsCategory.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("className", pantsCategory.getClassName());
        criteria.andEqualTo("delFlag", false);
        /* 判断是否已存在相同名称的数据 */
        if (pantsCategoryMapper.selectCountByExample(example) > 0)
        {
            return -1;
        }
        Date currentDate = new Date();
        pantsCategory.setCreateDate(currentDate);
        pantsCategory.setModifyDate(currentDate);
        pantsCategory.setCreator("Creator");
        pantsCategory.setModifier("Modifier");
        pantsCategory.setDelFlag(false);
        pantsCategory.setVersion(1);
        int result = pantsCategoryMapper.insertSelective(pantsCategory);
        if(result == 0){
            throw new BizException("创建上衣类型失败");
        }
        return result;
    }

    @Override
    public PageInfo<PantsCategory> queryPantsCategory(int pageNum, int pageSize, PantsCategory pantsCategory)
    {
        Example example = new Example(PantsCategory.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("delFlag",false);
        if(pantsCategory.getType() != null)
        {
            criteria.andEqualTo("type",pantsCategory.getType());
        }
        if(pantsCategory.getParentId() != null)
        {
            criteria.andEqualTo("parentId",pantsCategory.getParentId());
        }
        if(pantsCategory.getGrade() != null)
        {
            criteria.andEqualTo("grade",pantsCategory.getGrade());
        }
        PageHelper.startPage(pageNum,pageSize);
        List<PantsCategory> result = pantsCategoryMapper.selectByExample(example);

        if(result == null)
        {
            throw new BizException("没有找到指定的数据");
        }
        PageInfo<PantsCategory> pantsCategoryPageInfo = new PageInfo<>(result);
        return pantsCategoryPageInfo;
    }

    @Override
    public PantsCategory queryPantsCategoryInfo(String uuid)
    {
        Example example = new Example(PantsCategory.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid",uuid);
        criteria.andEqualTo("delFlag",false);
        PantsCategory result = null;
        try
        {
            result = pantsCategoryMapper.selectByExample(example).get(0);
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
    public int updatePantsCategory(PantsCategory pantsCategory)
    {
        Example example = new Example(PantsCategory.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid",pantsCategory.getUuid());
        criteria.andEqualTo("delFlag",false);
        int result = pantsCategoryMapper.updateByExampleSelective(pantsCategory,example);
        return result;
    }

    @Override
    public int deletePantsCategory(String uuid)
    {
        Example example = new Example(PantsCategory.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid",uuid);
        int result = pantsCategoryMapper.deleteByExample(example);
        if(result == 0)
        {
            throw new BizException("删除数据失败");
        }

        return result;
    }

    @Override
    public int updateGoodsNum()
    {
        return pantsCategoryMapper.updateGoodsNum();
    }

}
