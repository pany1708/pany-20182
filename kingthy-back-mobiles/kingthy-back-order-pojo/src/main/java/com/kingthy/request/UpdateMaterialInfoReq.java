package com.kingthy.request;

import com.kingthy.response.QueryResourceInfoResp;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: 潘勇
 * @Description: 用于接收json数据转为List对象
 * @Date: 2018/1/10 18:59
 */
@Data
@ToString
public class UpdateMaterialInfoReq implements Serializable
{
    private List<QueryResourceInfoResp> queryResourceInfoRespList;
}
