package com.kingthy.mapper;

import com.kingthy.dto.AttentionMemberDTO;
import com.kingthy.dto.FansDTO;
import com.kingthy.entity.Attention;
import com.kingthy.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 *
 * @author   likejie  2017/8/7.
 *
 */
public interface AttentionMapper  extends MyMapper<Attention> {
    /**
     *
     * 获取我关注的用户列表
     * @param memberUuid
     * @return List<AttentionMemberDTO>
     *
     */
    List<AttentionMemberDTO> getAttentionMemberList(@Param("memberUuid") String memberUuid);

    /**
     * 获取关注我的用户列表（粉丝）
     * @param list
     * @return
     */
    List<FansDTO> getFansLilst(@Param("list") List<String> list);

    /**
     *  获取粉丝的uuid列表
     *  @param  attentionUuid 关注的会员uuid
     *  @return
     */
    List<String> selectFansUuidList(@Param("attentionUuid") String attentionUuid);
    /**
     * 批量删除我的关注
     *  @param memberUuid
     *  @param attentionUuids
     *  @return
     */
    int batchDeleteAttention(@Param("memberUuid") String memberUuid, @Param("attentionUuids") List<String> attentionUuids);

    /**
     * 获取关注的数量
     * @param memberUuid
     * @param attentionUuid
     * @return
     */
    int getAttentionCount(@Param("memberUuid") String memberUuid, @Param("attentionUuid") String attentionUuid);

    /**
     * 获取关注的数量
     * @param paramMap
     * @return
     */
    List<String> isattention(Map<String, Object> paramMap);
}
