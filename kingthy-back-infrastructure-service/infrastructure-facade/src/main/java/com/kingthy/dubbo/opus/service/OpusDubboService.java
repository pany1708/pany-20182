package com.kingthy.dubbo.opus.service;

import com.github.pagehelper.PageInfo;
import com.kingthy.base.BaseService;
import com.kingthy.entity.Opus;
import com.kingthy.request.OpusSearchReq;

import java.util.List;

/**
 * OpusServiceImpl(描述其作用)
 * <p>
 * 赵生辉 2017年08月02日 15:36
 *
 * @version 1.0.0
 */
public interface OpusDubboService extends BaseService<Opus>
{

    String insertReturn(Opus opus);

    int deleteByUuids(List<String> list, String memberUuid);

    long findNumByDesignerUuid(String designerUuid);

    PageInfo<Opus> findByPage(OpusSearchReq opusReq);
}
