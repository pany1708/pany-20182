package com.kingthy.dubbo.user.service;

import com.kingthy.base.BaseService;
import com.kingthy.entity.MemberFootmark;
import com.kingthy.page.PageT;
import com.kingthy.request.MemberFootmarkPageReq;

import java.util.List;

/**
 * 会员足迹 服务接口
 * @author   likejie
 * @date  2017/8/7
 */
public interface MemberFootmarkDubboService extends BaseService<MemberFootmark> {

    /**
     * 批量删除足迹
     * @param uuids
     * @return
     */
    int batchDeleteFootmark(List<String> uuids);
    /**
     * 查询会员足迹
     * @param memberUuid
     * @return
     */
    List<MemberFootmark> getFootmarkByMemberUuid( String memberUuid);
    /**
     * 分页查询会员足迹
     * @param req
     * @return
     */
    PageT<MemberFootmark> pageGetFootmarkListByTimesapn(MemberFootmarkPageReq req);

}
