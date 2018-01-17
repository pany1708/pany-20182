package com.kingthy.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingthy.dubbo.basedata.service.DyeingPatternDubboService;
import com.kingthy.entity.DyeingPattern;
import com.kingthy.mapper.DyeingPatternMapper;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import java.util.Date;
import java.util.List;

/**
 * @author fmq
 */
@Service(version = "1.0.0", timeout = 3000)
public class DyeingPatternServiceImpl implements DyeingPatternDubboService
{
    /**
     * 默认版本号
     */
    private static final int VERSION = 1;
    
    @Autowired
    private DyeingPatternMapper dyeingPatternMapper;

    
    @Override
    public int create(DyeingPattern dyeingPattern)
    {
        Date currentDate = new Date();
        dyeingPattern.setCreateDate(currentDate);
        dyeingPattern.setModifyDate(currentDate);
        dyeingPattern.setDelFlag(false);
        //dyeingPattern.setUuid(null);
        dyeingPattern.setVersion(VERSION);
        int result = dyeingPatternMapper.insertSelective(dyeingPattern);
        return result;
    }
    
    @Override
    public String insertReturnUuid(DyeingPattern dyeingPattern)
    {
        Date currentDate = new Date();
        dyeingPattern.setCreateDate(currentDate);
        dyeingPattern.setModifyDate(currentDate);
        dyeingPattern.setDelFlag(false);
        //String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
        //dyeingPattern.setUuid(uuid.substring(7, 24));
        dyeingPattern.setVersion(VERSION);

        int result = dyeingPatternMapper.insertSelective(dyeingPattern);
        return result == 0 ? null : dyeingPattern.getUuid();
    }
    
    @Override
    public DyeingPattern findDyeingPattern(String dyeingPatternUuid)
    {
        DyeingPattern dyeingPattern = dyeingPatternMapper.selectDyeingPatternByUuid(dyeingPatternUuid);
        if (dyeingPattern != null && null != dyeingPattern.getUuid())
        {
            return dyeingPattern;
        }
        else
        {
            return null;
        }
    }
    
    @Override
    public PageInfo<DyeingPattern> findDyeingPatternPage(int pageNum, int pageSize, DyeingPattern dyeingPattern)
    {
        PageHelper.startPage(pageNum, pageSize);
        List<DyeingPattern> result = dyeingPatternMapper.findDyeingPatternPage(dyeingPattern);
        return new PageInfo<>(result);
    }
    
    @Override
    public int deleteDyeingPattern(String dyeingPatternUuid)
    {
        Example example = new Example(DyeingPattern.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid", dyeingPatternUuid);
        criteria.andEqualTo("delFlag", false);
        DyeingPattern dyeingPattern = dyeingPatternMapper.selectByExample(example).get(0);
        dyeingPattern.setDelFlag(true);
        dyeingPattern.setModifyDate(new Date());
        return dyeingPatternMapper.updateByExample(dyeingPattern, example);
    }
    
    @Override
    public int updateDyeingPattern(DyeingPattern dyeingPattern)
    {
        Example example = new Example(DyeingPattern.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid", dyeingPattern.getUuid());
        dyeingPattern.setModifyDate(new Date());
        return dyeingPatternMapper.updateByExampleSelective(dyeingPattern, example);
    }
    
    @Override
    public DyeingPattern selectDyeingPatternByCode(String code)
    {
        return dyeingPatternMapper.selectDyeingPatternByCode(code);
    }
    
    @Override
    public DyeingPattern selectDyeingPatternByUuid(String uuid)
    {
        return dyeingPatternMapper.selectDyeingPatternByUuid(uuid);
    }
    
    @Override
    public int selectCountByExample(String name)
    {
        return dyeingPatternMapper.selectCountByName(name);
    }

}
