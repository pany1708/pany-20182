package com.kingthy.dto;

import com.kingthy.entity.Material;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author:xumin
 * @Description:
 * @Date:18:04 2017/11/2
 */
@Data
@ToString
public class MaterialDto extends Material
{

    /**
     * 查询返回面料分类名称
     */
    private String categoryName;

    //array 详情页面用
    private List<ImageDTO> images;

    //json 列表用
    private String img;

    //array 详情页面用
//    private List<String> color;
    private List<ColorDTO> color;

    //json 列表用
    private String colors;

    @Data
    @ToString
    public static class ImageDTO implements java.io.Serializable
    {
        private String img;
        private String imgNo;
        private Integer status;
        private String imgUuid;

    }


    @Data
    @ToString
    public static class ColorDTO implements java.io.Serializable
    {
        //颜色
        private String color;
        //是否可以删除颜色 一旦完成采样，则不允许修改颜色；未完成采样的可以去除或修改颜色
        private Boolean delFlag;
    }
}
