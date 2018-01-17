package com.kingthy.platform.dto.goods;

import com.kingthy.platform.entity.goods.BuyersShow;
import com.kingthy.platform.entity.goods.BuyersShowImg;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * BuyersShowDto
 * <p>
 * yuanml
 * 2017/5/16
 *
 * @version 1.0.0
 */
@Data
@ToString
public class BuyersShowDto extends BuyersShow
{
    private String goodsName;

    private List<BuyersShowImg> buyersShowImgList;

    private String replyContent;
}
