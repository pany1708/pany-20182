package com.kingthy.dubbo.review.service;

import com.github.pagehelper.PageInfo;
import com.kingthy.base.BaseService;
import com.kingthy.dto.MomentDto;
import com.kingthy.entity.Moments;
import com.kingthy.request.QueryMomentPageReq;

import java.util.List;

/**
 * MomentsDubboService
 * <p>
 * yuanml
 * 2017/8/4
 *
 * @version 1.0.0
 */
public interface MomentsDubboService extends BaseService<Moments>
{
    int updateLikes(String uuid, Integer likes, Integer comments);

    int publishMoment(Moments moments);

    PageInfo<MomentDto> selectMoment(QueryMomentPageReq queryMomentPageReq);

    int deleteMoment(String uuid, String memberUuid);
}
