package com.kingthy.dubbo.basedata.service;

import com.kingthy.base.BaseService;
import com.kingthy.entity.SensitiveWord;

import java.util.List;

/**
 * @author zhaochen
 * @Description:
 * @DATE Created by 17:24 on 2017/8/15.
 * @Modified by:
 */
public interface SensitiveWordService extends BaseService<SensitiveWord>
{
    /**
     * 查询所有敏感词
     * @return
     */
    List<String> selectWordAll();
}
