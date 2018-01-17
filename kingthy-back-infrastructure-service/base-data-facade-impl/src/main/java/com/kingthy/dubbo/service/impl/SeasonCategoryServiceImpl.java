/**
 * 系统项目名称
 * com.kingthy.platform.service.impl.basedata
 * SeasonServiceImpl.java
 * 
 * 2017年3月29日-下午4:24:19
 *  2017金昔科技有限公司-版权所有
 *
 */
package com.kingthy.dubbo.service.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingthy.dubbo.basedata.service.SeasonCategoryDubboService;
import com.kingthy.entity.SeasonCategory;
import com.kingthy.mapper.SeasonCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 *
 * SeasonServiceImpl
 * 
 * @author zhaochen 2017年3月29日 下午4:24:19
 * 
 * @version 1.0.0
 *
 */
@Service(version = "1.0.0",timeout = 3000)
public class SeasonCategoryServiceImpl implements SeasonCategoryDubboService
{
    @Autowired
    private transient SeasonCategoryMapper seasonCategoryMapper;

    @Override
    public int insert(SeasonCategory seasonCategory) {
        seasonCategory.setVersion(1);
        return seasonCategoryMapper.insert(seasonCategory);
    }

    @Override
    public int updateByUuid(SeasonCategory seasonCategory) {
        SeasonCategory seasonCategoryResult = selectByUuid(seasonCategory.getUuid());
        Integer currentVersion = seasonCategoryResult.getVersion();
        Example example = new Example(SeasonCategory.class);
        Example.Criteria  criteria = example.createCriteria();
        criteria.andEqualTo("uuid",seasonCategory.getUuid());
        criteria.andEqualTo("version",currentVersion);
        seasonCategory.setVersion(currentVersion+1);
        return seasonCategoryMapper.updateByExampleSelective(seasonCategory,example);
    }

    @Override
    public List<SeasonCategory> selectAll() {
        return seasonCategoryMapper.selectAll();
    }

    @Override
    public SeasonCategory selectByUuid(String uuid) {
        Example example = new Example(SeasonCategory.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid",uuid);
        List<SeasonCategory> seasonCategoryList = seasonCategoryMapper.selectByExample(example);
        if (seasonCategoryList!=null && seasonCategoryList.size()>0){
            return seasonCategoryList.get(0);
        }
        return new SeasonCategory();
    }

    @Override
    public int selectCount(SeasonCategory seasonCategory) {
        return seasonCategoryMapper.selectCount(seasonCategory);
    }

    @Override
    public List<SeasonCategory> select(SeasonCategory var1) {
        return seasonCategoryMapper.select(var1);
    }

    @Override
    public SeasonCategory selectOne(SeasonCategory var1) {
        return seasonCategoryMapper.selectOne(var1);
    }

    @Override
    public PageInfo<SeasonCategory> queryPage(Integer pageNum, Integer pageSize, SeasonCategory seasonCategory) {
        PageHelper.startPage(pageNum,pageSize);
        List<SeasonCategory> result = seasonCategoryMapper.fingByPage(seasonCategory);
        return new PageInfo<>(result);
    }
}
