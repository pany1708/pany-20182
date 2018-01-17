package com.kingthy.dubbo.basedata.service;

import com.github.pagehelper.PageInfo;
import com.kingthy.base.BaseService;
import com.kingthy.entity.OperateFashionCollection;
import com.kingthy.request.OperateFashionCollectionReq;

/**
 * @author chenz
 * @version 1.0.0
 * @class_name OperateFashionCollectionDubboService
 * @description 潮搭接口
 * @create 2017/8/25
 */
public interface OperateFashionCollectionDubboService extends BaseService<OperateFashionCollection> {
    /**
     * 分页查询潮搭
     * @param operateFashionCollectionReq
     * @return
     */
    PageInfo<OperateFashionCollection> queryPage(OperateFashionCollectionReq operateFashionCollectionReq);
}
