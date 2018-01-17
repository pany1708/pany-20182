package com.kingthy.platform.dto.opus;

import lombok.Data;

/**
 *
 * OpusRelationsReq(作品关联)
 * 
 * yuanml 2017年4月11日 下午7:47:10
 * 
 * @version 1.0.0
 *
 */
@Data
public class OpusRelationsReq
{
    private String opusUuid;
    
    private String[] relationUuids;
}