package com.kingthy.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.kingthy.dubbo.opus.service.OpusDubboService;
import com.kingthy.entity.Opus;
import com.kingthy.request.CreateOpusReq;
import com.kingthy.service.OpusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * OpusServiceImpl(描述其作用)
 * <p>
 * 赵生辉 2017年11月21日 15:08
 *
 * @version 1.0.0
 */
@Service(value="opusService")
public class OpusServiceImpl implements OpusService
{

    @Reference(version ="1.0.0")
    private OpusDubboService opusDubboService;

    @Override
    public int createOpus(CreateOpusReq createOpusReq)
    {
        return 0;
    }

    @Override
    public int deleteOpus(List<String> list)
    {
        return 0;
    }

    @Override
    public int updateOpus(String uuid, Integer status,String memberUuid)
    {
        Opus opus = new Opus();
        opus.setUuid(uuid);
        opus.setOpusStatus(status);
        opus.setMemberUuid(memberUuid);
        return opusDubboService.updateByUuid(opus);
    }
}
