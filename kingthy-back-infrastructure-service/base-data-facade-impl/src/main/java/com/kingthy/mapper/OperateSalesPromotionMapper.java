package com.kingthy.mapper;

import com.kingthy.dto.OperateSalesPromotionDto;
import com.kingthy.entity.OperateSalesPromotion;
import com.kingthy.request.OperateSalesPromotionReq;
import com.kingthy.util.MyMapper;

import java.util.List;

/**
 * @author zhaochen
 * 促销映射接口
 */
public interface OperateSalesPromotionMapper extends MyMapper<OperateSalesPromotion> {
    /**
     * 分页查询促销活动
     * @param operateSalesPromotionReq
     * @return List<OperateSalesPromotion>
     */
    List<OperateSalesPromotionDto> findByPage(OperateSalesPromotionReq operateSalesPromotionReq);

    /**
     * 根据uuid查询促销信息
     * @param uuid
     * @return
     */
    OperateSalesPromotion selectByUuid(String uuid);
}