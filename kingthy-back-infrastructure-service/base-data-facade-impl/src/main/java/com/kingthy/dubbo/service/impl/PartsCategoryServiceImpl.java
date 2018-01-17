package com.kingthy.dubbo.service.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingthy.dto.PartsFileDto;
import com.kingthy.dubbo.basedata.service.PartsCategoryDubboService;
import com.kingthy.entity.PartsCategory;
import com.kingthy.exception.BizException;
import com.kingthy.mapper.PartsCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 *
 *
 * PartsCategoryServiceImpl
 *
 * @author zhaochen 2017年3月29日 下午1:56:03
 *
 * @version 1.0.0
 *
 */
@Service(version = "1.0.0",timeout = 3000)
public class PartsCategoryServiceImpl implements PartsCategoryDubboService
{
    @Autowired
    private PartsCategoryMapper partsCategoryMapper;

    @Override
    public int insert(PartsCategory partsCategory) {
        Example example = new Example(PartsCategory.class);
        Example.Criteria criteria = example.createCriteria();
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
        int result = partsCategoryMapper.insertSelective(partsCategory);
        if(result == 0)
        {
            throw new BizException("创建失败");
        }
        return result;
    }

    @Override
    public int updateByUuid(PartsCategory partsCategory) {
        PartsCategory partsCategoryResult = selectByUuid(partsCategory.getUuid());
        Integer currentVersion = partsCategoryResult.getVersion();
        Example example = new Example(PartsCategory.class);
        Example.Criteria  criteria = example.createCriteria();
        criteria.andEqualTo("uuid",partsCategory.getUuid());
        criteria.andEqualTo("version",currentVersion);
        partsCategory.setVersion(currentVersion+1);
        return partsCategoryMapper.updateByExampleSelective(partsCategory,example);
    }

    @Override
    public List<PartsCategory> selectAll() {

        return partsCategoryMapper.selectAllPartsCategory();
    }

    @Override
    public PartsCategory selectByUuid(String uuid) {
        return partsCategoryMapper.selectByUuid(uuid);
    }

    @Override
    public int selectCount(PartsCategory partsCategory) {
        partsCategory.setDelFlag(false);
        return partsCategoryMapper.selectCount(partsCategory);
    }

    @Override
    public List<PartsCategory> select(PartsCategory var1) {
        var1.setDelFlag(false);
        return partsCategoryMapper.select(var1);
    }

    @Override
    public PartsCategory selectOne(PartsCategory var1) {
        var1.setDelFlag(false);
        return partsCategoryMapper.selectOne(var1);
    }

    @Override
    public PageInfo<PartsCategory> queryPage(Integer pageNum, Integer pageSize, PartsCategory partsCategory) {
        PageHelper.startPage(pageNum,pageSize);
        List<PartsCategory> list=partsCategoryMapper.findByPage(partsCategory);
        PageInfo<PartsCategory> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public List<PartsCategory> findPartsCategory(List<String> list)
    {
        return partsCategoryMapper.findPartsCategoryByUuidList(list);
    }

    @Override
    public int create(PartsCategory var) {
        return partsCategoryMapper.insert(var);
    }


    @Override
    public List<PartsFileDto> findFiles() {
        return partsCategoryMapper.findFiles();
    }

    @Override
    public int updateBySn(PartsCategory var) {
        Example example = new Example(PartsCategory.class);
        example.createCriteria().andEqualTo("sn", var.getSn()).andEqualTo("delFlag", false);
        return partsCategoryMapper.updateByExampleSelective(var, example);
    }

    @Override
    public int deleteBySn(PartsCategory var) {
        return updateBySn(var);
    }


}
