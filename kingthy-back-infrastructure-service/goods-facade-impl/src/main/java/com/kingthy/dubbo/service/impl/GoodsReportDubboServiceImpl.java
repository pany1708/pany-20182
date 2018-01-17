package com.kingthy.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.kingthy.dto.GoodsPriceRangeNumDto;
import com.kingthy.dubbo.goods.service.GoodsReportDubboService;
import com.kingthy.mapper.GoodsReportMapper;
import com.kingthy.request.ReportGoodsDataReq;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chenz
 * @version 1.0.0
 * @class_name GoodsReportDubboServiceImpl
 * @description 商品统计数据接口实现类
 * @create 2017/8/28
 */
@Service(version = "1.0.0",timeout = 10000)
public class GoodsReportDubboServiceImpl implements GoodsReportDubboService {

    @Autowired
    private GoodsReportMapper goodsReportMapper;

    @Override
    public int findGoodsDataByDataType(ReportGoodsDataReq reportGoodsDataReq) {
        return goodsReportMapper.findGoodsDataByDataType(reportGoodsDataReq);
    }

    @Override
    public ArrayList<GoodsPriceRangeNumDto> findNumByPriceRange() {
        List<GoodsPriceRangeNumDto> list = goodsReportMapper.findNumByPriceRange();
        ArrayList<GoodsPriceRangeNumDto> arrayList =new ArrayList<>();
        if (list !=null){
            arrayList = (ArrayList<GoodsPriceRangeNumDto>) list;
        }
        return arrayList;
    }
}
