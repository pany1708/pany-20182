package com.kingthy.request;

import com.kingthy.common.BaseReq;
import com.kingthy.dto.*;
import com.kingthy.dto.GoodsAttribute;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * createGoodsReq(创建商品实体bean)
 * <p>
 * @author 赵生辉 2017年05月11日 17:43
 *
 * @version 1.0.0
 */
@Data
@ToString
public class CreateGoodsReq extends BaseReq implements Serializable
{

    //@NotNull(message = "商品名称作品名称不能为空")
    @ApiModelProperty(value = "商品名称")
    private String goodsName;

    //@NotNull(message = "商品卖点不能为空")
    @ApiModelProperty(value = "商品卖点")
    private String goodsFeature;

    @ApiModelProperty(value = "标准价格")
    private BigDecimal standardPrice;

    //@NotNull(message = "关联作品编号不能为空")
    @ApiModelProperty(value = "关联作品编号")
    private String opusSn;

    //@NotNull(message = "设计师UUID不能为空")
    @ApiModelProperty(value = "设计师UUID")
    private String desinger;

    //@NotNull(message = "返还积分不能为空")
    @ApiModelProperty(value = "返还积分")
    private String returnPoint;

    //NotNull(message = "上架状态不能为空")
    @ApiModelProperty(value = "上架状态")
    private Integer status;

    //@NotNull(message = "商品品类主键不能为空")
    @ApiModelProperty(value = "商品品类主键")
    private String goodsCategoryUuid;

    //@NotNull(message = "商品风格主键不能为空")
    @ApiModelProperty(value = "商品风格主键")
    private String goodsStyleUuid;

    //@NotNull(message = "商品详情不能为空")
    @ApiModelProperty(value = "商品详情")
    private String goodsDetails;

   // @NotNull(message = "关联作品的uuid不能为空")
    @ApiModelProperty(value = "关联作品的uuid")
    private String opusUuid;

   // @NotNull(message = "商品图片不能为空")
    @ApiModelProperty(value = "商品图片")
    private List<GoodsImageDto> goodsImage;

    //@NotNull(message = "部件详情不能为空")
    @ApiModelProperty(value = "部件详情")
    private List<PartInfoDto> partInfo;

    //@NotNull(message = "面料详情不能为空")
    @ApiModelProperty(value = "面料详情")
    private List<MaterieDto> materielInfo;

    //@NotNull(message = "辅料详情不能为空")
    @ApiModelProperty(value = "辅料详情")
    private List<AccessorieDto> accessoriesInfo;

    //@NotNull(message = "封面图不能为空")
    @ApiModelProperty(value = "封面图")
    private String cover;

    //@NotNull(message = "商品参数属性不能为空")
    @ApiModelProperty(value = "商品参数属性")
    private GoodsAttribute goodsAttribute;

    //@NotNull(message = "年龄分段参数不能为空")
    @ApiModelProperty(value = "年龄分段参数")
    private String ageSegmentUuid;

    //@NotNull(message = "商品类型不能为空")
    @ApiModelProperty(value = "商品类型")
    private String goodsCategoryType;

    @ApiModelProperty(value = "模型文件地址")
    private String fileUrl;

    private static final long serialVersionUID = 1L;

    /**
     * 类型
     */
    public enum Type
    {

        /** 普通商品 */
        general,

        /** 兑换商品 */
        exchange,

        /** 赠品 */
        gift
    }

    /**
     * 排序类型
     */
    public enum OrderType
    {

        /** 置顶降序 */
        topDesc,

        /** 价格升序 */
        priceAsc,

        /** 价格降序 */
        priceDesc,

        /** 销量降序 */
        salesDesc,

        /** 评分降序 */
        scoreDesc,

        /** 日期降序 */
        dateDesc
    }

    public enum GoodsStatus
    {
        /**
         * @Author:xumin
         * @Description:
         * @Date:16:47 2017/11/2
         */
        goodsUp(1, "已上架"), 
        /**
         * @Author:xumin
         * @Description:
         * @Date:16:47 2017/11/2
         */
        goodsDown(0, "已下架"), 
        /**
         * @Author:xumin
         * @Description:
         * @Date:16:47 2017/11/2
         */
        timingUp(2, "定时上架");
        private int value;

        private String nameValue;

        GoodsStatus(int value, String nameValue)
        {
            this.value = value;
            this.nameValue = nameValue;
        }

        public int getValue()
        {
            return value;
        }

        public void setValue(int value)
        {
            this.value = value;
        }

        public String getNameValue()
        {
            return nameValue;
        }

        public void setNameValue(String nameValue)
        {
            this.nameValue = nameValue;
        }
    }

    /**
     * 排名类型
     */
    public enum RankingType
    {

        /** 评分 */
        score,

        /** 评分数 */
        scoreCount,

        /** 周点击数 */
        weekHits,

        /** 月点击数 */
        monthHits,

        /** 点击数 */
        hits,

        /** 周销量 */
        weekSales,

        /** 月销量 */
        monthSales,

        /** 销量 */
        sales
    }

}
