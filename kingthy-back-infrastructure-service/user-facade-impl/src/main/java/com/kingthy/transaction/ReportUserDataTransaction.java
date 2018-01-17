package com.kingthy.transaction;

import com.kingthy.entity.ReportUserData;
import com.kingthy.mapper.ReportUserDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author chenz
 * @version 1.0.0
 * @class_name ReportUserDataTransaction
 * @description 全站注册用户表事务
 * @create 2017/8/29
 */
@Component
public class ReportUserDataTransaction {
    @Autowired
    private ReportUserDataMapper reportUserDataMapper;

    @Transactional(rollbackFor = Exception.class)
    public void add(ReportUserData reportUserData) {
        //删除原来的记录
        reportUserDataMapper.deleteByDataType(reportUserData);
        //添加新记录
        reportUserDataMapper.insert(reportUserData);
    }
}
