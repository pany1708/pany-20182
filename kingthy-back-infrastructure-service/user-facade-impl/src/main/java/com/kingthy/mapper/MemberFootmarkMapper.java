package com.kingthy.mapper;

import com.kingthy.entity.MemberFootmark;
import com.kingthy.request.MemberFootmarkPageReq;
import com.kingthy.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * @author   likejie  2017/8/7.
 */
public interface MemberFootmarkMapper  extends MyMapper<MemberFootmark> {

    /**
     *
     * 批量删除我到足迹
     * @param uuids
     * @return
     */
    int batchDeleteFootmark(@Param("uuids") List<String> uuids);

    /**
     *
     * 根据用户ID，获取足迹
     * @param uuid
     * @return
     */
    List<MemberFootmark> getFootmarkByMemberUuid(@Param("memberUuid") String uuid);


    /**
     *
     * 分页获取数据
     * @param req
     * @return
     */
    List<MemberFootmark>  pageGetFootmarkListByTimesapn(MemberFootmarkPageReq req);
    

}
