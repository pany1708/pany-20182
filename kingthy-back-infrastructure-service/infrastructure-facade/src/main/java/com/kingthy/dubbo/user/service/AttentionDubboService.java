package com.kingthy.dubbo.user.service;


import com.github.pagehelper.PageInfo;
import com.kingthy.dto.AttentionMemberDTO;
import com.kingthy.dto.FansDTO;
import com.kingthy.dto.MomentDto;
import com.kingthy.entity.Attention;
import java.util.List;


/**
 * 关注服务接口
 * @author   likejie  2017/8/7.
 * @since 2.0
 */
public interface AttentionDubboService {

    /***
     * 添加关注
     * @param  entity
     * @return
     */
    int insert(Attention entity);

    /**
     * 获取我关注的用户列表
     * @param memberUuid
     * @return
     */
    List<AttentionMemberDTO> getAttentionMemberList( String memberUuid);

    /**
     * 分页获取我关注的用户列表
     * @param memberUuid
     * @param pageSize
     * @param pageNum
     * @return
     */
    PageInfo<AttentionMemberDTO> pagingSelectAttentionUsers(String memberUuid, int pageSize, int pageNum);
    /**
     * 获取我的粉丝
     * @param memberUuid
     * @return
     */
    List<FansDTO> getFansLilst( String memberUuid);
    /**
     * 分页获取我关注的用户列表
     * @param memberUuid
     * @param pageSize
     * @param pageNum
     * @return
     */
    PageInfo<FansDTO> pagingSelectFans(String memberUuid,int pageSize,int pageNum);
    /**
     * 批量删除我的关注
     * @param memberUuid
     * @param attentionUuids
     * @return
     */
    int batchDeleteAttention( String memberUuid,  List<String> attentionUuids);

    /**
     * 获取关注的用户数量
     * @param memberUuid
     * @param attentionUuid
     * @return
     */
    int getAttentionCount(String memberUuid,  String attentionUuid);

    /**
     * --
     * @param queryMemberUuid
     * @param moments
     * @return
     */
    List<String> isattention(String queryMemberUuid, List<MomentDto> moments);

}
