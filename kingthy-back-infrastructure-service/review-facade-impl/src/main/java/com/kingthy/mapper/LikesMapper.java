package com.kingthy.mapper;

import com.kingthy.dto.BuyersShowListDTO;
import com.kingthy.entity.Likes;
import com.kingthy.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * LikesMapper
 * <p>
 * @author yuanml
 * 2017/8/9
 *
 * @version 1.0.0
 */
public interface LikesMapper extends MyMapper<Likes>
{

    /**
     * 查询动态点赞
     * @param listMomentUuid
     * @return
     */
    List<BuyersShowListDTO.LikesDTO> selectLikeCountByMomentUuidList(@Param("list") List<String> listMomentUuid);

    /**
     * 查询个人动态点赞
     * @param listMomentUuid
     * @param memberUuid
     * @return
     */
    List<BuyersShowListDTO.LikesDTO> selectLikesByMomentUuidAndMemberUuid(@Param("list") List<String> listMomentUuid, @Param("memberUuid") String memberUuid);

    /**
     * 删除动态
     * @param momentUuid
     * @return
     */
    int deleteByMoment(@Param("momentUuid")String momentUuid);

    /**
     * 删除评论
     * @param momentCommentUuid
     * @return
     */
    int deleteByComment(@Param("momentCommentUuid")String momentCommentUuid);

}
