package com.kingthy.dubbo.review.service;

import com.github.pagehelper.PageInfo;
import com.kingthy.base.BaseService;
import com.kingthy.dto.MomentCommentDto;
import com.kingthy.entity.MomentComment;

import java.util.List;

/**
 * MomentsDubboService
 * <p>
 * yuanml
 * 2017/8/4
 *
 * @version 1.0.0
 */
public interface MomentCommentDubboService extends BaseService<MomentComment>
{
    int updateLikes(String uuid, Integer likes);

    /**
     * 物理删除
     * @param uuid
     * @return
     */
    int deleteMomentComment(String uuid);

    PageInfo<MomentCommentDto> selectComment(Integer pageNo, Integer pageSize, String momentUuid, String memberUuid);

    int deleteByUuidAndMemberUuid(MomentComment momentComment);

    int deleteByParentUuid(MomentComment momentComment);

    int publishComment(MomentComment momentComment);

    int deleteMomentComment(MomentComment momentComment);
}
