package com.kingthy.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingthy.dto.MessageInfoDto;
import com.kingthy.dubbo.message.service.MessageInfoDubboService;
import com.kingthy.entity.MessageInfo;
import com.kingthy.mapper.MessageInfoMapper;
import com.kingthy.request.MessageInfoReq;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author  xumin
 * @Description:
 * @DATE Created by 17:19 on 2017/8/7.
 * @Modified by:
 */

@Service(version = "1.0.0", timeout = 3000)
public class MessageInfoDubboServiceImpl implements MessageInfoDubboService {

    @Autowired
    private MessageInfoMapper messageInfoMapper;

    @Override
    public int insert(MessageInfo var) {
        return messageInfoMapper.insert(var);
    }

    @Override
    public int updateByUuid(MessageInfo var) {

        Example example = new Example(MessageInfo.class);
        example.createCriteria().andEqualTo("uuid", var.getUuid());

        return messageInfoMapper.updateByExampleSelective(var, example);

    }

    @Override
    public List<MessageInfo> selectAll() {
        return messageInfoMapper.selectAll();
    }

    @Override
    public MessageInfo selectByUuid(String uuid) {

        MessageInfo var1 = new MessageInfo();
        var1.setUuid(uuid);
        return selectOne(var1);
    }

    @Override
    public int selectCount(MessageInfo var) {
        return messageInfoMapper.selectCount(var);
    }

    @Override
    public List<MessageInfo> select(MessageInfo var1) {
        return messageInfoMapper.select(var1);
    }

    @Override
    public MessageInfo selectOne(MessageInfo var1) {
        return messageInfoMapper.selectOne(var1);
    }

    @Override
    public PageInfo<MessageInfo> queryPage(Integer pageNum, Integer pageSize, MessageInfo messageInfo) {

        PageHelper.startPage(pageNum, pageSize);

        return new PageInfo<>(messageInfoMapper.select(messageInfo));
    }

    @Override
    public PageInfo<MessageInfoDto> findListMessage(MessageInfoReq req) {
        PageHelper.startPage(req.getPageNum(), req.getPageSize());
        List<MessageInfoDto> list = messageInfoMapper.findListMessage(req);
        return new PageInfo<>(list);
    }
}
