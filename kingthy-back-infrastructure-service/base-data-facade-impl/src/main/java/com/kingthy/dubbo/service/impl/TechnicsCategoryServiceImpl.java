package com.kingthy.dubbo.service.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingthy.dubbo.basedata.service.TechnicsCategoryDubboService;
import com.kingthy.entity.PantsCategory;
import com.kingthy.entity.TechnicsCategory;
import com.kingthy.mapper.TechnicsCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * TechnicsServiceImpl(描述其作用)
 * @author zhaochen 2017年07月28日 17:29
 *
 * @version 1.0.0
 */
@Service(version = "1.0.0", timeout = 3000)
public class TechnicsCategoryServiceImpl implements TechnicsCategoryDubboService {

    @Autowired
    private TechnicsCategoryMapper technicsCategoryMapper;

    @Override
    public int insert(TechnicsCategory technicsCategory) {
        Example example = new Example(TechnicsCategory.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("name", technicsCategory.getName());
        criteria.andEqualTo("delFlag", false);
        /* 判断是否已存在相同名称的数据 */
        if (technicsCategoryMapper.selectCountByExample(example) > 0) {
            return -1;
        }
        Date currentDate = new Date();
        technicsCategory.setCreateDate(currentDate);
        technicsCategory.setModifyDate(currentDate);
        technicsCategory.setCreator("Creator");
        technicsCategory.setModifier("Modifier");
        technicsCategory.setDelFlag(false);
        technicsCategory.setVersion(1);
        return technicsCategoryMapper.insertSelective(technicsCategory);
    }

    @Override
    public int updateByUuid(TechnicsCategory technicsCategory) {
        TechnicsCategory technicsCategoryResult = selectByUuid(technicsCategory.getUuid());
        Integer currentVersion = technicsCategoryResult.getVersion();
        Example example = new Example(TechnicsCategory.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid", technicsCategory.getUuid());
        criteria.andEqualTo("version", currentVersion);
        technicsCategory.setModifyDate(new Date());
        technicsCategory.setVersion(currentVersion+1);
        return technicsCategoryMapper.updateByExampleSelective(technicsCategory, example);
    }

    @Override
    public List<TechnicsCategory> selectAll() {
        return technicsCategoryMapper.selectAllTechnicsCategory();
    }

    @Override
    public TechnicsCategory selectByUuid(String uuid) {
        return technicsCategoryMapper.selectByUuid(uuid);
    }

    @Override
    public int selectCount(TechnicsCategory technicsCategory) {
        return technicsCategoryMapper.selectCount(technicsCategory);
    }

    @Override
    public List<TechnicsCategory> select(TechnicsCategory var1) {
        return technicsCategoryMapper.select(var1);
    }

    @Override
    public TechnicsCategory selectOne(TechnicsCategory var1) {
        return technicsCategoryMapper.selectOne(var1);
    }

    @Override
    public PageInfo<TechnicsCategory> queryPage(Integer pageNum, Integer pageSize, TechnicsCategory technicsCategory) {
        PageHelper.startPage(pageNum, pageSize);
        List<TechnicsCategory> result = technicsCategoryMapper.findByPage(technicsCategory);
        return new PageInfo<>(result);
    }

    @Override
    public int delete(String uuid) {
        Example example = new Example(PantsCategory.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid", uuid);
        return  technicsCategoryMapper.deleteByExample(example);
    }

    @Override
    public int selectCountByExample(TechnicsCategory technicsCategory) {
        return technicsCategoryMapper.selectCountByName(technicsCategory.getName());
    }
}
