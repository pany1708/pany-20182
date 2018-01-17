package com.kingthy.dubbo.basedata.service;

import com.github.pagehelper.PageInfo;
import com.kingthy.base.BaseService;
import com.kingthy.dto.OperateSalesPromotionDto;
import com.kingthy.entity.OperateSalesPromotion;
import com.kingthy.request.OperateSalesPromotionReq;

/**
 * @author chenz
 * @version 1.0.0
 * @class_name OperateSalesPromotionDubboService
 * @description 促销接口
 * @create 2017/8/25
 */
public interface OperateSalesPromotionDubboService extends BaseService<OperateSalesPromotion> {
    /**
     * 分页查询
     * @param operateSalesPromotionReq
     * @return
     */
    PageInfo<OperateSalesPromotionDto> queryPage(OperateSalesPromotionReq operateSalesPromotionReq);
}
