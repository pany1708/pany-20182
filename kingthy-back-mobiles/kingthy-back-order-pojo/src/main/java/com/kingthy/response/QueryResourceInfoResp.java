package com.kingthy.response;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Author 潘勇
 * @Data 2018/1/8 14:49.
 */
@Data
@ToString
public class QueryResourceInfoResp implements Serializable
{
    private int id;

    //面料名称
    private String name;

    //面料的业务主键
    private String uuid;

    //颜色的RGB值
    private String colourRgb;

    //面料编码
    private String code;

    //颜色索引
    private String colourIdx;

    //采样图片预览
    private String imagePath;

    //采样模型文件
    private String filePath;
}
