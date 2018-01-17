package com.kingthy.platform.service.operate;

import com.github.pagehelper.PageInfo;
import com.kingthy.platform.dto.operate.OperateFashionCollectionReq;
import com.kingthy.platform.entity.operate.OperateFashionCollection;

/**
 * @author chenz
 * @version 1.0.0
 * @class_name OperateFashionCollectionService
 * @description 潮搭接口
 * @create 2017/8/9
 */
public interface OperateFashionCollectionService {

    int insert(OperateFashionCollectionReq operateFashionCollectionReq);

    int updateByUuid(OperateFashionCollection operateFashionCollection);

    int deleteByUuid(String uuid);

    OperateFashionCollection selectByUuid(String uuid);


    PageInfo<OperateFashionCollection> queryPage(OperateFashionCollectionReq operateFashionCollectionReq);
}
