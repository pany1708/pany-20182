package com.kingthy.dubbo.service.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingthy.dto.GoodsParameterCountDTO;
import com.kingthy.dubbo.basedata.service.DressCategoryDubboService;
import com.kingthy.entity.CoatCategory;
import com.kingthy.entity.DressCategory;
import com.kingthy.exception.BizException;
import com.kingthy.mapper.DressCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * DressCategoryServiceImpl(描述其作用)
 * @author zhaochen 2017年07月10日 10:44
 *
 * @version 1.0.0
 */
@Service(version = "1.0.0",timeout = 3000)
public class DressCategoryServiceImpl implements DressCategoryDubboService
{
    @Autowired
    private DressCategoryMapper dressCategoryMapper;


    @Override
    public int insert(DressCategory dressCategory) {
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
    public int updateByUuid(DressCategory dressCategory) {
        DressCategory dressCategoryResult = selectByUuid(dressCategory.getUuid());
        Integer currentVersion = dressCategoryResult.getVersion();
        Example example = new Example(DressCategory.class);
        Example.Criteria  criteria = example.createCriteria();
        criteria.andEqualTo("uuid",dressCategory.getUuid());
        criteria.andEqualTo("version",currentVersion);
        dressCategory.setVersion(currentVersion+1);
        return dressCategoryMapper.updateByExampleSelective(dressCategory,example);
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
    public int batchUpdateGoodsNum(List<GoodsParameterCountDTO> list) {
        return dressCategoryMapper.batchUpdateGoodsNum(list);
    }

    @Override
    public List<DressCategory> selectAll() {
        return dressCategoryMapper.findAllDressCategory();
    }

    @Override
    public DressCategory selectByUuid(String uuid) {
        return dressCategoryMapper.selectByUuid(uuid);
    }

    @Override
    public int selectCount(DressCategory dressCategory) {
        return 0;
    }

    @Override
    public List<DressCategory> select(DressCategory var1) {
        return null;
    }

    @Override
    public DressCategory selectOne(DressCategory var1) {
        return null;
    }

    @Override
    public PageInfo<DressCategory> queryPage(Integer pageNum, Integer pageSize, DressCategory dressCategory) {
        PageHelper.startPage(pageNum,pageSize);
        List<DressCategory> result = dressCategoryMapper.queryPage(dressCategory);
        if(result == null)
        {
            throw new BizException("该类型数据不存在");
        }
        return new PageInfo<>(result);
    }
}
