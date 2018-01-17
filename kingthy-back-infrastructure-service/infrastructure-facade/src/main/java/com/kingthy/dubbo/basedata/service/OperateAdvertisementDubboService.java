package com.kingthy.dubbo.basedata.service;

import com.github.pagehelper.PageInfo;
import com.kingthy.base.BaseService;
import com.kingthy.entity.OperateAdvertisement;
import com.kingthy.request.OperateAdvertisementReq;


/**
 * OperateAdvertisementDubboService
 * @author zhaochen 2017年08月27日 10:00
 *
 * @version 1.0.0
 */
public interface OperateAdvertisementDubboService extends BaseService<OperateAdvertisement> {
    /**
     * 分页查询广告
     * @param operateAdvertisementReq
     * @return
     */
    PageInfo<OperateAdvertisement> queryPage(OperateAdvertisementReq operateAdvertisementReq);
}
