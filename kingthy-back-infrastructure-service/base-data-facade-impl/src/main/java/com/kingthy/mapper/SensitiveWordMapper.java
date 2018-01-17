package com.kingthy.mapper;

import com.kingthy.entity.SensitiveWord;
import com.kingthy.util.MyMapper;

import java.util.List;

/**
 * @author zhaochen
 * @Description:
 * @DATE Created by 17:34 on 2017/5/11.
 * @Modified by:
 */
public interface SensitiveWordMapper extends MyMapper<SensitiveWord> {

    /**
     * 查询全部敏感词
     * @return
     */
    List<String> selectWordAll();
}
