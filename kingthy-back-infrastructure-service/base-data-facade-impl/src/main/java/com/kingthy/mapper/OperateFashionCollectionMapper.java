package com.kingthy.mapper;

import com.kingthy.entity.OperateFashionCollection;
import com.kingthy.request.OperateFashionCollectionReq;
import com.kingthy.util.MyMapper;

import java.util.List;

/**
 * @author zhaochen
 * 潮搭映射接口
 */
public interface OperateFashionCollectionMapper extends MyMapper<OperateFashionCollection> {

    /**
     * 分页查询潮搭
     * @param operateFashionCollectionReq
     * @return
     */
    List<OperateFashionCollection> findByPage(OperateFashionCollectionReq operateFashionCollectionReq);

    /**
     * 根据uuid查询潮搭
     * @param uuid
     * @return
     */
    OperateFashionCollection selectByUuid(String uuid);
}