package com.kingthy.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingthy.dubbo.message.service.MessagePushRecordDubboService;
import com.kingthy.entity.MessagePushRecord;
import com.kingthy.mapper.MessagePushRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author  xumin
 * @Description:
 * @DATE Created by 11:51 on 2017/8/8.
 * @Modified by:
 */

@Service(version = "1.0.0", timeout = 3000)
public class MessagePushRecordDubboServiceImpl implements MessagePushRecordDubboService
{
    @Autowired
    private MessagePushRecordMapper messagePushRecordMapper;

    @Override
    public int insert(MessagePushRecord var) {
        return messagePushRecordMapper.insert(var);
    }

    @Override
    public int updateByUuid(MessagePushRecord var) {

        Example example = new Example(MessagePushRecord.class);
        example.createCriteria().andEqualTo("uuid", var.getUuid());

        return messagePushRecordMapper.updateByExampleSelective(var, example);
    }

    @Override
    public List<MessagePushRecord> selectAll() {
        return messagePushRecordMapper.selectAll();
    }

    @Override
    public MessagePushRecord selectByUuid(String uuid) {

        MessagePushRecord var1 = new MessagePushRecord();
        var1.setUuid(uuid);
        return selectOne(var1);
    }

    @Override
    public int selectCount(MessagePushRecord var) {
        return messagePushRecordMapper.selectCount(var);
    }

    @Override
    public List<MessagePushRecord> select(MessagePushRecord var1) {
        return messagePushRecordMapper.select(var1);
    }

    @Override
    public MessagePushRecord selectOne(MessagePushRecord var1) {
        return messagePushRecordMapper.selectOne(var1);
    }

    @Override
    public PageInfo<MessagePushRecord> queryPage(Integer pageNum, Integer pageSize, MessagePushRecord var) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(messagePushRecordMapper.select(var));
    }
}
