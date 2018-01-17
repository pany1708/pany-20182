package com.kingthy.dubbo.service.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingthy.dubbo.basedata.service.SegmentCategoryDubboService;
import com.kingthy.entity.BaseData;
import com.kingthy.entity.SegmentCategory;
import com.kingthy.mapper.SegmentCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * SegmentCategoryServiceImpl(描述其作用)
 * @author zhaochen 2017年07月11日 11:00
 *
 * @version 1.0.0
 */
@Service(version = "1.0.0",timeout = 3000)
public class SegmentCategoryServiceImpl implements SegmentCategoryDubboService
{

    @Autowired
    private SegmentCategoryMapper segmentCategoryMapper;

    @Override
    public int insert(SegmentCategory segmentCategory) {
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
        return segmentCategoryMapper.insertSelective(segmentCategory);
    }

    @Override
    public int updateByUuid(SegmentCategory segmentCategory) {
        SegmentCategory segmentCategoryResult = selectByUuid(segmentCategory.getUuid());
        Integer currentVersion = segmentCategoryResult.getVersion();
        Example example = new Example(SegmentCategory.class);
        Example.Criteria  criteria = example.createCriteria();
        criteria.andEqualTo("uuid",segmentCategory.getUuid());
        criteria.andEqualTo("version",currentVersion);
        segmentCategory.setVersion(currentVersion+1);
        return segmentCategoryMapper.updateByExampleSelective(segmentCategory,example);
    }

    @Override
    public List<SegmentCategory> selectAll() {
        return segmentCategoryMapper.selectAll();
    }

    @Override
    public SegmentCategory selectByUuid(String uuid) {
        return segmentCategoryMapper.selectByUuid(uuid);
    }

    @Override
    public int selectCount(SegmentCategory segmentCategory) {
        return segmentCategoryMapper.selectCount(segmentCategory);
    }

    @Override
    public List<SegmentCategory> select(SegmentCategory var1) {
        return segmentCategoryMapper.select(var1);
    }

    @Override
    public SegmentCategory selectOne(SegmentCategory var1) {
        return segmentCategoryMapper.selectOne(var1);
    }

    @Override
    public PageInfo<SegmentCategory> queryPage(Integer pageNum, Integer pageSize, SegmentCategory segmentCategory) {
        PageHelper.startPage(pageNum,pageSize);
        List<SegmentCategory> result = segmentCategoryMapper.findByPage(segmentCategory);
        return new PageInfo<>(result);
    }

    @Override
    public int deleteSegmentCategory(String uuid) {
        Example example = new Example(SegmentCategory.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid",uuid);
        return segmentCategoryMapper.deleteByExample(example);
    }
}
