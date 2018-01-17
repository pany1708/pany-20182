package com.kingthy.platform.service.moments;

import com.github.pagehelper.PageInfo;

import com.kingthy.platform.dto.moments.QueryMomentPageReq;
import com.kingthy.platform.dto.moments.UpdateMomentReq;
import com.kingthy.platform.entity.moments.Moments;

/**
 * momentService(描述其作用)
 * <p>
 * 赵生辉 2017年05月17日 15:53
 *
 * @version 1.0.0
 */
public interface MomentService
{
    /**
     * 发布动态
     * @param moments
     * @return
     */
    int publishMoment(Moments moments);

    /**
     * 分页查询动态
     * @param queryMomentPageReq
     * @return
     */
    PageInfo<Moments> queryMomentPage(QueryMomentPageReq queryMomentPageReq);

    /**
     * 更新动态
     * @param uuid
     * @param review
     * @param reason
     * @return
     */
    int update(UpdateMomentReq updateMomentReq);

    /**
     * 删除动态
     * @param uuid
     * @return
     */
    Moments queryInfo(String uuid);
}
