package com.kingthy.dubbo.goods.service;

import com.kingthy.base.BaseService;
import com.kingthy.dto.GoodsDTO;
import com.kingthy.dto.GoodsOrderDTO;
import com.kingthy.dto.GoodsParameterCountDTO;
import com.kingthy.entity.Goods;
import com.kingthy.entity.GoodsParameter;

import java.util.List;

/**
 * @author xumin
 * @Description:
 * @DATE Created by 15:38 on 2017/8/7.
 * @Modified by:
 */
public interface GoodsParameterDubboService extends BaseService<GoodsParameter>
{
    List<GoodsParameterCountDTO> selectCountByParameterIds(List<String> list);
}
