package com.kingthy.mapper;

import com.kingthy.entity.DeliveryCorp;
import com.kingthy.util.MyMapper;

import java.util.List;

/**
 * @author zhaochen 2017/8/25.
 */
public interface DeliveryCorpMapper  extends MyMapper<DeliveryCorp>  {
    /**
     * 查询全部快递公司
     * @return
     */
    List<DeliveryCorp> selectAllDeliveryCorp();
}
