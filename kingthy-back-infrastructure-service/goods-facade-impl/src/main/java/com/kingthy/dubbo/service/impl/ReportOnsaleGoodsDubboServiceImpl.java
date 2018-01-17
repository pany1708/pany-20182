package com.kingthy.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.kingthy.dto.GoodsPriceRangeNumDto;
import com.kingthy.dubbo.goods.service.ReportOnsaleGoodsDubboService;
import com.kingthy.entity.ReportOnsaleGoods;
import com.kingthy.mapper.ReportOnsaleGoodsMapper;
import com.kingthy.transaction.ReportOnsaleGoodsTransaction;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenz
 * @version 1.0.0
 * @class_name ReportOnsaleGoodsDubboServiceImpl
 * @description 上架商品价格分布接口实现类
 * @create 2017/8/28
 */
@Service(version = "1.0.0", timeout = 10000)
public class ReportOnsaleGoodsDubboServiceImpl implements ReportOnsaleGoodsDubboService {

    @Autowired
    private ReportOnsaleGoodsMapper reportOnsaleGoodsMapper;

    @Autowired
    private ReportOnsaleGoodsTransaction reportOnsaleGoodsTransaction;

    @Override
    public void add(List<GoodsPriceRangeNumDto> list) {
        reportOnsaleGoodsTransaction.add(list);
    }

    @Override
    public ArrayList<ReportOnsaleGoods> findAll() {
        List<ReportOnsaleGoods> list = reportOnsaleGoodsMapper.selectAll();
        ArrayList<ReportOnsaleGoods> arrayList = new ArrayList<>();
        if (list != null) {
            arrayList = (ArrayList<ReportOnsaleGoods>) list;
        }
        return arrayList;
    }
}
