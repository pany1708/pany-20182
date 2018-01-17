package com.kingthy.platform.dto.report;

import lombok.Data;
import lombok.ToString;

/**
 * @author chenz
 * @version 1.0.0
 * @class_name GoodsPriceRangeNumDto
 * @description 上架商品价格分布出参封装类
 * @create 2017/7/27
 */
@Data
@ToString
public class GoodsPriceRangeNumDto {

    private int num;
    private String priceRange;
}
