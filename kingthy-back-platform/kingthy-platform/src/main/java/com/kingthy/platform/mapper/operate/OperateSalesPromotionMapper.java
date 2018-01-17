package com.kingthy.platform.mapper.operate;

import com.kingthy.platform.dto.operate.OperateSalesPromotionDto;
import com.kingthy.platform.dto.operate.OperateSalesPromotionReq;
import com.kingthy.platform.entity.operate.OperateSalesPromotion;
import com.kingthy.platform.util.MyMapper;

import java.util.List;

/**
 * 促销映射接口
 */
public interface OperateSalesPromotionMapper extends MyMapper<OperateSalesPromotion> {
    /**
     * 分页查询促销活动
     * @param operateSalesPromotionReq
     * @return List<OperateSalesPromotion>
     */
    List<OperateSalesPromotionDto> findByPage(OperateSalesPromotionReq operateSalesPromotionReq);
}