package com.kingthy.mapper;

import com.kingthy.dto.ShippingInfoDTO;
import com.kingthy.entity.ShippingMethod;
import com.kingthy.util.MyMapper;

import java.util.List;

/**
 * @author zhaochen
 */
public interface ShippingMethodMapper extends MyMapper<ShippingMethod>
{
    /**
     * 根据地区id查询物流信息
     * @param areaUuid
     * @return
     */
    List<ShippingInfoDTO> selectShippingInfoByAreaUuid(Long areaUuid);
    
}
