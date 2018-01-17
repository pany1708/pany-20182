package com.kingthy.platform.dto.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @AUTHORS xumin
 * @Description:
 * @DATE Created by 16:16 on 2017/7/24.
 * @Modified by:
 */

@Data
@ToString
public class OrderLogDto implements Serializable {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date createDate;

    private String content;

    private String userName;
}
