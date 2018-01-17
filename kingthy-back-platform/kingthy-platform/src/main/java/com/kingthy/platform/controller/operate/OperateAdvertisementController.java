package com.kingthy.platform.controller.operate;

import com.github.pagehelper.PageInfo;
import com.kingthy.platform.dto.operate.OperateAdvertisementReq;
import com.kingthy.platform.entity.operate.OperateAdvertisement;
import com.kingthy.platform.response.WebApiResponse;
import com.kingthy.platform.service.operate.OperateAdvertisementService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author chenz
 * @version 1.0.0
 * @class_name OperateAdvertisementController
 * @description 广告控制层
 * @create 2017/8/9
 */
@RestController
@RequestMapping(value = "/advertisement")
public class OperateAdvertisementController {

    private static final Logger LOG = LoggerFactory.getLogger(OperateAdvertisementController.class);

    @Autowired
    private OperateAdvertisementService operateAdvertisementService;

    @ApiOperation(value = "分页查询广告信息")
    @RequestMapping(value = "/findByPage", method = RequestMethod.POST)
    public WebApiResponse<?> findByPage(@RequestBody OperateAdvertisementReq operateAdvertisementReq){
        try {
            PageInfo<OperateAdvertisement> pageInfo = operateAdvertisementService.queryPage(operateAdvertisementReq);
            return WebApiResponse.success(pageInfo);
        } catch (Exception e) {
            LOG.error("/advertisement/findByPage:" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
    }

    @ApiOperation(value = "新增广告")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public WebApiResponse<?> addAdvertisement(@RequestBody OperateAdvertisementReq operateAdvertisementReq){
        try {
            operateAdvertisementService.insert(operateAdvertisementReq);
            return WebApiResponse.success(true);
        } catch (Exception e) {
            LOG.error("/advertisement/addAdvertisement:" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
    }

    @ApiOperation(value = "更新广告信息")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public WebApiResponse<?> updateAdvertisement(@RequestBody OperateAdvertisement operateAdvertisement){
        if (operateAdvertisement.getUuid() ==null){
            return WebApiResponse.error("uuid不能为空！");
        }
        try {
            operateAdvertisementService.updateByUuid(operateAdvertisement);
            return WebApiResponse.success(true);
        } catch (Exception e) {
            LOG.error("/advertisement/update:" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
    }



    @ApiOperation(value = "删除广告")
    @RequestMapping(value = "/delete/{uuid}", method = RequestMethod.GET)
    public WebApiResponse<?> deleteAdvertisement(@PathVariable String uuid){
        try {
            operateAdvertisementService.deleteByUuid(uuid);
            return WebApiResponse.success(true);
        } catch (Exception e) {
            LOG.error("/advertisement/delete/{uuid}:" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }

    }

    @ApiOperation(value = "根据uuid查询广告信息")
    @RequestMapping(value = "/findByUuid/{uuid}", method = RequestMethod.GET)
    public WebApiResponse<?> findByUuid(@PathVariable String uuid){
        try {
            OperateAdvertisement operateAdvertisement = operateAdvertisementService.selectByUuid(uuid);
            return WebApiResponse.success(operateAdvertisement);
        } catch (Exception e) {
            LOG.error("/advertisement/findByUuid/{uuid}:" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
    }

}
