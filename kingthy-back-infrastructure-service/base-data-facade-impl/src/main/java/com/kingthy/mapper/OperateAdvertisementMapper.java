package com.kingthy.mapper;


import com.kingthy.entity.OperateAdvertisement;
import com.kingthy.request.OperateAdvertisementReq;
import com.kingthy.util.MyMapper;

import java.util.List;

/**
 * @author zhaochen
 * 广告
 */
public interface OperateAdvertisementMapper extends MyMapper<OperateAdvertisement> {

   /**
    * 分页查询广告信息
    * @param operateAdvertisementReq
    * @return
    */
   List<OperateAdvertisement> findByPage(OperateAdvertisementReq operateAdvertisementReq);

   /**
    * 根据uuid查询广告信息
    * @param uuid
    * @return
    */
   OperateAdvertisement selectByUuid(String uuid);
}