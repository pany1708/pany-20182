package com.kingthy.mapper;

import com.kingthy.entity.MemberFavorite;
import com.kingthy.request.MemberFavoritePageReq;
import com.kingthy.request.MemberFavoriteReq;
import com.kingthy.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * 会员收藏
 * @author   likejie  2017/8/7.
 *
 */
public interface MemberFavoriteMapper extends MyMapper<MemberFavorite> {

    /**
     * 分页获取收藏列表
     * @param req
     * @return
     */
    List<MemberFavorite> pageGetFavoriteListByTimesapn(MemberFavoritePageReq req);
    /**
     * 更新所有收藏
     * @param memberFavoriteReq
     * @return
     */
    int updateAllFavorite(@Param("memberFavoriteReq") MemberFavoriteReq memberFavoriteReq);
    /**
     * 更新部分收藏
     * @param memberFavoriteReq
     * @return
     */
    int updateSomeFavorite(@Param("memberFavoriteReq") MemberFavoriteReq memberFavoriteReq);
    /**
     * 查询收藏数量
     * @param goodsUuid
     * @param membersUuid
     * @return
     */
    Long selectFavoriteCountByGoodsUuidAndMembersUuid(@Param("goodsUuid") String goodsUuid, @Param("membersUuid") String membersUuid);


}
