package com.kingthy.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingthy.dto.MessagePlatformDto;
import com.kingthy.dubbo.message.service.MessagePlatformDubboService;
import com.kingthy.entity.MessagePlatform;
import com.kingthy.mapper.MessagePlatformMapper;
import com.kingthy.request.MessagePlatformReq;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author  xumin
 * @Description:
 * @DATE Created by 17:52 on 2017/8/7.
 * @Modified by:
 */
@Service(version = "1.0.0", timeout = 3000)
public class MessagePlatformDubboServiceImpl implements MessagePlatformDubboService {
    @Autowired
    private MessagePlatformMapper messagePlatformMapper;

    @Override
    public int insert(MessagePlatform var) {
        return messagePlatformMapper.insert(var);
    }

    @Override
    public int updateByUuid(MessagePlatform var) {

        Example example = new Example(MessagePlatform.class);
        example.createCriteria().andEqualTo("uuid", var.getUuid());

        return messagePlatformMapper.updateByExampleSelective(var, example);
    }

    @Override
    public List<MessagePlatform> selectAll() {
        return messagePlatformMapper.selectAll();
    }

    @Override
    public MessagePlatform selectByUuid(String uuid) {

        MessagePlatform var1 = new MessagePlatform();
        var1.setUuid(uuid);
        return selectOne(var1);
    }

    @Override
    public int selectCount(MessagePlatform var) {
        return messagePlatformMapper.selectCount(var);
    }

    @Override
    public List<MessagePlatform> select(MessagePlatform var1) {
        return messagePlatformMapper.select(var1);
    }

    @Override
    public MessagePlatform selectOne(MessagePlatform var1) {
        return messagePlatformMapper.selectOne(var1);
    }

    @Override
    public PageInfo<MessagePlatform> queryPage(Integer pageNum, Integer pageSize, MessagePlatform var) {

        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(messagePlatformMapper.select(var));
    }

    @Override
    public PageInfo<MessagePlatformDto> findListPage(MessagePlatformReq req) {
        PageHelper.startPage(req.getPageNum(), req.getPageSize());
        List<MessagePlatformDto> list = messagePlatformMapper.findListPage(req);
        return new PageInfo<>(list);
    }

    @Override
    public int updateMessagePlatFromByUuid(MessagePlatform messagePlatform) {
        return messagePlatformMapper.updateMessagePlatFromByUuid(messagePlatform);
    }
}
