package com.kingthy.platform.service.operate;

import com.github.pagehelper.PageInfo;
import com.kingthy.platform.dto.operate.OperateSalesPromotionDto;
import com.kingthy.platform.dto.operate.OperateSalesPromotionReq;
import com.kingthy.platform.entity.operate.OperateSalesPromotion;

/**
 * @author chenz
 * @version 1.0.0
 * @class_name OperateSalesPromotionService
 * @description 促销接口
 * @create 2017/8/8
 */
public interface OperateSalesPromotionService {

    int insert(OperateSalesPromotionReq operateSalesPromotionReq);

    int updateByUuid(OperateSalesPromotion operateSalesPromotion);

    int deleteByUuid(String uuid);

    OperateSalesPromotion selectByUuid(String uuid);


    PageInfo<OperateSalesPromotionDto> queryPage(OperateSalesPromotionReq operateSalesPromotionReq);
}
