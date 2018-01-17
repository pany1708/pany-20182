package com.kingthy.platform.service.impl.basedata;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingthy.exception.BizException;
import com.kingthy.platform.entity.basedata.BaseData;
import com.kingthy.platform.entity.basedata.SegmentCategory;
import com.kingthy.platform.mapper.basedata.SegmentCategoryMapper;
import com.kingthy.platform.service.basedata.SegmentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * SegmentCategoryServiceImpl(描述其作用)
 * <p>
 * 赵生辉 2017年07月11日 11:00
 *
 * @version 1.0.0
 */
@Service("/segmentCategoryService")
public class SegmentCategoryServiceImpl implements SegmentCategoryService
{

    @Autowired
    private SegmentCategoryMapper segmentCategoryMapper;

    @Override
    public int createSegmentCategory(SegmentCategory segmentCategory)
    {
        Example example = new Example(SegmentCategory.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("className", segmentCategory.getClassName());
        criteria.andEqualTo("delFlag", false);
        /* 判断是否已存在相同名称的数据 */
        if (segmentCategoryMapper.selectCountByExample(example) > 0)
        {
            return -1;
        }
        Date currentDate = new Date();
        segmentCategory.setCreateDate(currentDate);
        segmentCategory.setModifyDate(currentDate);
        segmentCategory.setCreator("Creator");
        segmentCategory.setModifier("Modifier");
        segmentCategory.setDelFlag(false);
        segmentCategory.setVersion(1);
        int result = segmentCategoryMapper.insertSelective(segmentCategory);
        if(result == 0)
        {
            throw new BizException("创建分段数据失败");
        }
        return result;
    }

    @Override
    public PageInfo<SegmentCategory> querySegmentCategory(int pageNum, int pageSize, SegmentCategory segmentCategory)
    {

        Example example = new Example(SegmentCategory.class);
        Example.Criteria criteria = example.createCriteria();
        if(segmentCategory.getClassName() != null)
        {
            criteria.andEqualTo("className",segmentCategory.getClassName());
        }
        if(segmentCategory.getType() != null)
        {
            criteria.andEqualTo("type",segmentCategory.getType());
        }
        if(segmentCategory.getStatus() != null)
        {
            criteria.andEqualTo("status",segmentCategory.getStatus());
        }
        if(segmentCategory.getOpusNum() != null)
        {
            criteria.andEqualTo("opusNum",segmentCategory.getOpusNum());
        }
        PageHelper.startPage(pageNum,pageSize);
        List<SegmentCategory> result = segmentCategoryMapper.selectByExample(example);

        if(result == null)
        {
            throw new BizException("该类型数据不存在");
        }
        PageInfo<SegmentCategory> segmentCategoryPageInfo = new PageInfo<>(result);
        return segmentCategoryPageInfo;
    }

    @Override
    public SegmentCategory querySegmentCategory(String uuid)
    {
        Example example = new Example(BaseData.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid",uuid);
        SegmentCategory result = null;
        try
        {
            result = segmentCategoryMapper.selectByExample(example).get(0);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        if(result == null){
            throw new BizException("没有找到基础数据");
        }
        return result;
    }

    @Override
    public int updateSegmentCategory(SegmentCategory segmentCategory)
    {
        Example example = new Example(SegmentCategory.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid",segmentCategory.getUuid());
        int result = segmentCategoryMapper.updateByExampleSelective(segmentCategory,example);
        return result;
    }

    @Override
    public int deleteSegmentCategory(String uuid)
    {
        Example example = new Example(SegmentCategory.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid",uuid);
        int result = segmentCategoryMapper.deleteByExample(example);
        if(result == 0){
            throw new BizException("删除失败");
        }
        return result;
    }

    @Override
    public int updateGoodsNum()
    {
        return segmentCategoryMapper.updateGoodsNum();
    }

    @Override
    public int updateGoodsNumPrice()
    {
        return segmentCategoryMapper.updateGoodsNumPrice();
    }
}
