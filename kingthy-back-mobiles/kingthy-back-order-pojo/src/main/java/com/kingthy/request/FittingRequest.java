package com.kingthy.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author xumin
 * @Description:
 * @DATE Created by 9:58 on 2017/12/19.
 * @Modified by:
 */
@Data
@ToString
public class FittingRequest implements Serializable {

    @ApiModelProperty("用户uuid")
    private String membersUuid;

    @ApiModelProperty("商品uuid")
    private String goodsUuid;

    @ApiModelProperty("人体模型uuid")
    private String figureUuid;

    @ApiModelProperty("客户端标识 APP WEB MIRROR")
    private String client;

    @Data
    @ToString
    public static class Query implements Serializable {

        @ApiModelProperty("用户uuid")
        private String membersUuid;

        @ApiModelProperty("人体模型uuid")
        private String figureUuid;

        private Integer pageNo;

        private Integer pageSize;

        @ApiModelProperty("客户端标识 APP WEB MIRROR")
        private String client;

    }

    @Data
    @ToString
    public static class CheckQuery implements Serializable {

        @ApiModelProperty("用户uuid")
        private String membersUuid;

        @ApiModelProperty("人体模型uuid")
        private String figureUuid;

        @ApiModelProperty("客户端标识 APP WEB MIRROR")
        private String client;

    }

    /**
     * 客户端标识
     */
    public enum ClientType{
        APP, WEB, MIRROR
    }
}
