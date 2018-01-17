package com.kingthy.platform.service.operate;

import com.github.pagehelper.PageInfo;
import com.kingthy.platform.dto.operate.OperateAdvertisementReq;
import com.kingthy.platform.entity.operate.OperateAdvertisement;

/**
 * @author chenz
 * @version 1.0.0
 * @class_name OperateAdvertisementService
 * @description 广告接口
 * @create 2017/8/9
 */
public interface OperateAdvertisementService {
    int insert(OperateAdvertisementReq operateAdvertisementReq);

    int updateByUuid(OperateAdvertisement operateAdvertisement);

    int deleteByUuid(String uuid);

    OperateAdvertisement selectByUuid(String uuid);


    PageInfo<OperateAdvertisement> queryPage(OperateAdvertisementReq operateAdvertisementReq);
}
