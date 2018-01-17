package com.kingthy.transaction;

import com.kingthy.request.CategoryReq;
import com.kingthy.request.TransferCategoryReq;

/**
 * @author zhaochen 2017/8/23.
 */
public interface ClassCategoryTransaction {

    /**
     * 更新品类信息
     * @param categoryReq
     * @return
     */
    int updateClassCategory(CategoryReq categoryReq);

    /**
     * 转移节点
     * @param transferCategoryReq
     * @return
     */
    int transfer(TransferCategoryReq transferCategoryReq);
}
