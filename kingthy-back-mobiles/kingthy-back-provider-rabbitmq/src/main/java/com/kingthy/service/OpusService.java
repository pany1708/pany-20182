package com.kingthy.service;

import com.kingthy.request.CreateOpusReq;

import java.util.List;

/**
 * OpusService(描述其作用)
 * <p>
 * @author 赵生辉 2017年08月23日 14:18
 *
 * @version 1.0.0
 */
public interface OpusService
{

    /**
     * 创建一个新的作品
     * @param createOpusReq
     * @return
     */
    int createOpus(CreateOpusReq createOpusReq);

    /**
     * 删除指定的作品
     * @param list
     * @return
     */
    int deleteOpus(List<String> list);

    /**
     * 修改作品的状态
     * @param status
     * @param uuid
     * @return
     */
    int updateOpus(String uuid, Integer status,String memberUuid);

}
