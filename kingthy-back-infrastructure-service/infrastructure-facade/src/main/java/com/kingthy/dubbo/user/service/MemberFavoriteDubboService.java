package com.kingthy.dubbo.user.service;

import com.kingthy.base.BaseService;
import com.kingthy.entity.MemberFavorite;
import com.kingthy.page.PageT;
import com.kingthy.request.MemberFavoritePageReq;
import com.kingthy.request.MemberFavoriteReq;

/**
 * 收藏dubbo服务接口
 * @author   likejie
 * @date  2017/8/7
 */
public interface MemberFavoriteDubboService extends BaseService<MemberFavorite> {

    /**
     * 更新所有收藏
     * @param memberFavoriteReq
     * @return
     **/
    int updateAllFavorite( MemberFavoriteReq memberFavoriteReq);
    /**
     * 更新收藏
     * @param memberFavoriteReq
     * @return
     */
    int updateSomeFavorite( MemberFavoriteReq memberFavoriteReq);
    /**
     * 查询商品收藏数量
     * @param goodsUuid
     * @param membersUuid
     * @return
     */
    Long selectFavoriteCountByGoodsUuidAndMembersUuid(String goodsUuid,String membersUuid);
    /**
     * 分页获取收藏列表
     * @param req
     * @return
     */
    PageT<MemberFavorite> pageGetFavoriteListByTimesapn(MemberFavoritePageReq req);

}
