package com.kingthy.platform.service.impl.order;

import com.kingthy.exception.BizException;
import com.kingthy.platform.entity.order.DeliveryCorp;
import com.kingthy.platform.mapper.order.DeliveryCorpMapper;
import com.kingthy.platform.service.order.DeliveryCorpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * DeliveryCorpServiceImpl(描述其作用)
 * <p>
 * 赵生辉 2017年07月17日 15:36
 *
 * @version 1.0.0
 */
@Service("deliveryCorpService")
public class DeliveryCorpServiceImpl implements DeliveryCorpService
{

    @Autowired
    private DeliveryCorpMapper deliveryCorpMapper;

    @Override
    public List<DeliveryCorp> queryAll()
    {
        List<DeliveryCorp> deliveryCorpList = deliveryCorpMapper.selectAll();
        if(null == deliveryCorpList)
        {
            throw new BizException("查询快递公司失败");
        }
        return deliveryCorpList;
    }
}
