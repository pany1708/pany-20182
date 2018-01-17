package com.kingthy.response;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Author: 潘勇
 * @Description: 返回子分类信息(用于采样工具对接)
 * @Date: 2018/1/5 15:17
 */
@Data
@ToString
public class QuerySubCategoryResp implements Serializable
{
    //子分类业务主键
    private String uuid;

    //子分类名称
    private String name;
}
