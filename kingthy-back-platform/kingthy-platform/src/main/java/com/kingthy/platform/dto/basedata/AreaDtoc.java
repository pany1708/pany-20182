package com.kingthy.platform.dto.basedata;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * AreaDto1(描述其作用)
 * <p>
 * 赵生辉 2017年07月21日 11:51
 *
 * @version 1.0.0
 */
@Data
@ToString
public class AreaDtoc implements Serializable
{
    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer grade;

    private String name;

    private Long areaParentId;

    private String fullName;

    private List<AreaDtoa> areaDto2List;
}
