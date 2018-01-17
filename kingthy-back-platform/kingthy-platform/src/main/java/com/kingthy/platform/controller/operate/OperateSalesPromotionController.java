package com.kingthy.platform.controller.operate;

import com.github.pagehelper.PageInfo;
import com.kingthy.platform.dto.operate.OperateSalesPromotionDto;
import com.kingthy.platform.dto.operate.OperateSalesPromotionReq;
import com.kingthy.platform.entity.operate.OperateSalesPromotion;
import com.kingthy.platform.response.WebApiResponse;
import com.kingthy.platform.service.operate.OperateSalesPromotionService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author chenz
 * @version 1.0.0
 * @class_name OperateSalesPromotionController
 * @description 促销控制类
 * @create 2017/8/8
 */
@RestController
@RequestMapping(value = "/salesPromotion")
public class OperateSalesPromotionController {

    private static final Logger LOG = LoggerFactory.getLogger(OperateSalesPromotionController.class);

    @Autowired
    private OperateSalesPromotionService operateSalesPromotionService;

    @ApiOperation(value = "分页查询促销活动信息")
    @RequestMapping(value = "/findByPage", method = RequestMethod.POST)
    public WebApiResponse<?> findByPage(@RequestBody OperateSalesPromotionReq operateSalesPromotionReq){
        try {
            PageInfo<OperateSalesPromotionDto> pageInfo = operateSalesPromotionService.queryPage(operateSalesPromotionReq);
            return WebApiResponse.success(pageInfo);
        } catch (Exception e) {
            LOG.error("/salesPromotion/findByPage:" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
    }

    @ApiOperation(value = "新增促销活动")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public WebApiResponse<?> addPromotion(@RequestBody OperateSalesPromotionReq operateSalesPromotionReq){
        try {
            operateSalesPromotionService.insert(operateSalesPromotionReq);
            return WebApiResponse.success(true);
        } catch (Exception e) {
            LOG.error("/salesPromotion/addPromotion:" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
    }

    @ApiOperation(value = "更新促销活动")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public WebApiResponse<?> updatePromotion(@RequestBody OperateSalesPromotion operateSalesPromotion){
        if (operateSalesPromotion.getUuid() ==null){
            return WebApiResponse.error("uuid不能为空！");
        }
        try {
            if (checkStatus(operateSalesPromotion.getUuid())){
                return WebApiResponse.error("活动正在进行中不能更改信息");
            }
            operateSalesPromotionService.updateByUuid(operateSalesPromotion);
            return WebApiResponse.success(true);
        } catch (Exception e) {
            LOG.error("/salesPromotion/updatePromotion:" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
    }



    @ApiOperation(value = "删除促销活动")
    @RequestMapping(value = "/delete/{uuid}", method = RequestMethod.GET)
    public WebApiResponse<?> deletePromotion(@PathVariable String uuid){
        try {
            if (checkStatus(uuid)){
                return WebApiResponse.error("活动正在进行中不能更改信息");
            }
            operateSalesPromotionService.deleteByUuid(uuid);
            return WebApiResponse.success(true);
        } catch (Exception e) {
            LOG.error("/salesPromotion/deletePromotion/{uuid}:" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }

    }

    @ApiOperation(value = "根据uuid查询活动信息")
    @RequestMapping(value = "/findByUuid/{uuid}", method = RequestMethod.GET)
    public WebApiResponse<?> findByUuid(@PathVariable String uuid){
        try {
            OperateSalesPromotion operateSalesPromotion = operateSalesPromotionService.selectByUuid(uuid);
            return WebApiResponse.success(operateSalesPromotion);
        } catch (Exception e) {
            LOG.error("/salesPromotion/findByUuid/{uuid}:" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
    }

    /**
     * 检查活动状态
     * @param uuid
     * @return
     */
    private boolean checkStatus(String uuid) {
        OperateSalesPromotion operateSalesPromotion1 = operateSalesPromotionService.selectByUuid(uuid);
        if (operateSalesPromotion1.getStatus() == OperateSalesPromotionReq.Status.ongoing.getValue()){
            return true;
        }
        return false;
    }
}
