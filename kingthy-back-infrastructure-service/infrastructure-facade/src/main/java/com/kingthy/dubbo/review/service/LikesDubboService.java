package com.kingthy.dubbo.review.service;

import com.kingthy.base.BaseService;
import com.kingthy.dto.BuyersShowListDTO;
import com.kingthy.entity.Likes;

import java.util.List;

/**
 * LikesDubboService
 * <p>
 * yuanml
 * 2017/8/9
 *
 * @version 1.0.0
 */
public interface LikesDubboService extends BaseService<Likes>
{
    List<BuyersShowListDTO.LikesDTO> selectLikeCountByMomentUuidList(List<String> listMomentUuid);

    List<BuyersShowListDTO.LikesDTO> selectLikesByMomentUuidAndMemberUuid(List<String> listMomentUuid, String memberUuid);

    int insertSelective(Likes likes);

    int selectCountByExample(Likes likes);

    int deleteByExample(Likes likes);

    int deleteByMoment(String momentUuid);

    int deleteByComment(String momentCommentUuid);

    int createLike(Likes likes);

    int deleteLike(Likes likes);
}
