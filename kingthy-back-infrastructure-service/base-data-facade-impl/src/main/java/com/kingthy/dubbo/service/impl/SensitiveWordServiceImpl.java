package com.kingthy.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingthy.dubbo.basedata.service.SensitiveWordService;
import com.kingthy.entity.SensitiveWord;
import com.kingthy.mapper.SensitiveWordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author zhaochen
 * @Description:
 * @DATE Created by 17:29 on 2017/8/15.
 * @Modified by:
 */
@Service(version = "1.0.0",timeout = 3000)
public class SensitiveWordServiceImpl implements SensitiveWordService {


    @Autowired
    private SensitiveWordMapper sensitiveWordMapper;

    @Override
    public int insert(SensitiveWord var) {
        var.setVersion(1);
        return sensitiveWordMapper.insert(var);
    }

    @Override
    public int updateByUuid(SensitiveWord var) {
        SensitiveWord sensitiveWord = selectByUuid(var.getUuid());
        Integer currentVersion = sensitiveWord.getVersion();
        Example example = new Example(SensitiveWord.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid", var.getUuid());
        criteria.andEqualTo("version",currentVersion);
        var.setVersion(currentVersion+1);
        return sensitiveWordMapper.updateByExampleSelective(var, example);
    }

    @Override
    public List<SensitiveWord> selectAll() {
        return sensitiveWordMapper.selectAll();
    }

    @Override
    public SensitiveWord selectByUuid(String uuid) {
        Example example = new Example(SensitiveWord.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid",uuid);
        List<SensitiveWord> sensitiveWordList = sensitiveWordMapper.selectByExample(example);
        if (sensitiveWordList!=null && sensitiveWordList.size()>0){
            return sensitiveWordList.get(0);
        }
        return new SensitiveWord();
    }

    @Override
    public int selectCount(SensitiveWord var) {
        return sensitiveWordMapper.selectCount(var);
    }

    @Override
    public List<SensitiveWord> select(SensitiveWord var1) {
        return sensitiveWordMapper.select(var1);
    }

    @Override
    public SensitiveWord selectOne(SensitiveWord var1) {
        return sensitiveWordMapper.selectOne(var1);
    }

    @Override
    public PageInfo<SensitiveWord> queryPage(Integer pageNum, Integer pageSize, SensitiveWord var) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(sensitiveWordMapper.select(var));
    }

    @Override
    public List<String> selectWordAll() {
        return sensitiveWordMapper.selectWordAll();
    }
}
