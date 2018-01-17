package com.kingthy.platform.mapper.operate;

import com.kingthy.platform.dto.operate.OperateFashionCollectionReq;
import com.kingthy.platform.entity.operate.OperateFashionCollection;
import com.kingthy.platform.util.MyMapper;

import java.util.List;

/**
 * 潮搭映射接口
 */
public interface OperateFashionCollectionMapper extends MyMapper<OperateFashionCollection> {

    List<OperateFashionCollection> findByPage(OperateFashionCollectionReq operateFashionCollectionReq);
}