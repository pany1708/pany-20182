package com.kingthy.dto;

import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * @author xumin
 * @Description:
 * @DATE Created by 11:35 on 2018/1/8.
 * @Modified by:
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TreeNode implements java.io.Serializable
{

    private String id;
    private String name;
    private String parentId;
    private List<TreeNode> children;
    //顶级uuid
    private String baseUuid;
}
