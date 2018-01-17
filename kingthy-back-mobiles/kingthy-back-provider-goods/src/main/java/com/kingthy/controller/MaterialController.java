package com.kingthy.controller;

import com.kingthy.response.WebApiResponse;
import com.kingthy.service.MaterialService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xumin
 * @Description:
 * @DATE Created by 10:29 on 2017/12/22.
 * @Modified by:
 */

@Api
@RestController
@RequestMapping("/material")
public class MaterialController
{

    private static final Logger LOG = LoggerFactory.getLogger(MaterialController.class);

    @Autowired
    private MaterialService materialService;

    @ApiOperation(value = "面料列表", notes = "面料列表")
    @PostMapping("/list")
    public WebApiResponse listMaterial(){

        try {

            return WebApiResponse.success(materialService.listMaterial());

        }catch (Exception e){
            e.printStackTrace();
            LOG.error("/buyer/{goodsUuid}/{token}:" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }

    }
}
