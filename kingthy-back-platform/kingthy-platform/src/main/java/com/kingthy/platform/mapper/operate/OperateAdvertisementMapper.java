package com.kingthy.platform.mapper.operate;

import com.kingthy.platform.dto.operate.OperateAdvertisementReq;
import com.kingthy.platform.entity.operate.OperateAdvertisement;
import com.kingthy.platform.util.MyMapper;

import java.util.List;

/**
 * 广告映射接口
 */
public interface OperateAdvertisementMapper extends MyMapper<OperateAdvertisement>{

   List<OperateAdvertisement> findByPage(OperateAdvertisementReq operateAdvertisementReq);
}