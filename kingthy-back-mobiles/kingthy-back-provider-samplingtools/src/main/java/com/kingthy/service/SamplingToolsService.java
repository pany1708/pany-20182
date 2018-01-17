package com.kingthy.service;

import com.github.pagehelper.PageInfo;
import com.kingthy.dto.Resource4SamplingDTO;
import com.kingthy.entity.Material;
import com.kingthy.request.QueryResourceInfoReq;
import com.kingthy.response.QueryResourceInfoResp;
import com.kingthy.response.QuerySubCategoryResp;

import java.util.List;

/**
 * @Author 潘勇
 * @Data 2017/12/25 9:49.
 */
public interface SamplingToolsService
{

    //获取面料列表
    PageInfo<? extends Material> showMaterialListByPage(Resource4SamplingDTO materialDTO);

    //获取面料子分类列表
    List<QuerySubCategoryResp> querySubCategoryList(int categoryType);

    List<QueryResourceInfoResp> queryResourceInfoList(QueryResourceInfoReq queryResourceInfoReq);

    int updateResourceInfo(String materialListJson);

}
