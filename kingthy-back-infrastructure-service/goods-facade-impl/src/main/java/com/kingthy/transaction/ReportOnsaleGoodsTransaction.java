package com.kingthy.transaction;

import com.kingthy.dto.GoodsPriceRangeNumDto;
import com.kingthy.entity.ReportOnsaleGoods;
import com.kingthy.mapper.ReportOnsaleGoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author chenz
 * @version 1.0.0
 * @class_name ReportOnsaleGoodsTransaction
 * @description 上架商品价格分布事务层
 * @create 2017/8/28
 */
@Component
public class ReportOnsaleGoodsTransaction {
    @Autowired
    private ReportOnsaleGoodsMapper reportOnsaleGoodsMapper;

    @Transactional
    public void add(List<GoodsPriceRangeNumDto> list) {
        reportOnsaleGoodsMapper.deleteAll();
        Date date = new Date();
        for (GoodsPriceRangeNumDto goodsPriceRangeNumDto : list) {
            ReportOnsaleGoods reportOnsaleGoods = new ReportOnsaleGoods();
            reportOnsaleGoods.setNum(goodsPriceRangeNumDto.getNum());
            reportOnsaleGoods.setPricerange(goodsPriceRangeNumDto.getPriceRange());
            reportOnsaleGoods.setCreateDate(date);
            reportOnsaleGoods.setVersion(1);
            reportOnsaleGoods.setDelFlag(false);
            reportOnsaleGoodsMapper.insert(reportOnsaleGoods);
        }
    }
}
