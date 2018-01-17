package com.kingthy.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageInfo;
import com.kingthy.dto.MaterialDto;
import com.kingthy.dto.Resource4SamplingDTO;
import com.kingthy.dubbo.basedata.service.MaterialDubboService;
import com.kingthy.dubbo.basedata.service.SourceCategoryDubboService;
import com.kingthy.entity.Material;
import com.kingthy.request.QueryResourceInfoReq;
import com.kingthy.response.QueryResourceInfoResp;
import com.kingthy.response.QuerySubCategoryResp;
import com.kingthy.service.SamplingToolsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author 潘勇
 * @Data 2017/12/25 19:51.
 */
@Service
public class SamplingToolsServiceImpl implements SamplingToolsService
{

    @Reference(version = "1.0.0")
    private MaterialDubboService materialDubboService;

    @Reference(version = "1.0.0")
    private SourceCategoryDubboService sourceCategoryDubboService;

    @Override
    public PageInfo<MaterialDto> showMaterialListByPage(Resource4SamplingDTO materialDTO)
    {
        Material material = new Material();
        material.setCode(materialDTO.getMaterialCode());
        material.setName(materialDTO.getMaterialName());
//        material.setStatus();
        return materialDubboService.findMaterialPage(materialDTO.getPageNum(), materialDTO.getPageSize(), material);
    }

    @Override
    public int updateResourceInfo(String materialListJson)
    {
        List<QueryResourceInfoResp> updateMaterialInfoReqList =
            JSONObject.parseObject(materialListJson, new TypeReference<ArrayList<QueryResourceInfoResp>>()
            {
            });
        return 0;
    }

    @Override
    public List<QueryResourceInfoResp> queryResourceInfoList(QueryResourceInfoReq queryResourceInfoReq)
    {

        return materialDubboService.queryResourceInfoBySubCategory(queryResourceInfoReq);
    }

    @Override
    public List<QuerySubCategoryResp> querySubCategoryList(int categoryType)
    {
        List<QuerySubCategoryResp> result = sourceCategoryDubboService.querySubCategory(categoryType);
        return result;
    }

}