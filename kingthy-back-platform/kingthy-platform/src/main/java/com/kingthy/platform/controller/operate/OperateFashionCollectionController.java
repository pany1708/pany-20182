package com.kingthy.platform.controller.operate;

import com.github.pagehelper.PageInfo;
import com.kingthy.platform.dto.operate.OperateFashionCollectionReq;
import com.kingthy.platform.entity.operate.OperateFashionCollection;
import com.kingthy.platform.response.WebApiResponse;
import com.kingthy.platform.service.operate.OperateFashionCollectionService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author chenz
 * @version 1.0.0
 * @class_name OperateFashionCollectionController
 * @description 潮搭控制层
 * @create 2017/8/9
 */
@RestController
@RequestMapping(value = "/fashion")
public class OperateFashionCollectionController {

    private static final Logger LOG = LoggerFactory.getLogger(OperateFashionCollectionController.class);

    @Autowired
    private OperateFashionCollectionService operateFashionCollectionService;

    @ApiOperation(value = "分页查询潮搭信息")
    @RequestMapping(value = "/findByPage", method = RequestMethod.POST)
    public WebApiResponse<?> findByPage(@RequestBody OperateFashionCollectionReq operateFashionCollectionReq){
        try {
            PageInfo<OperateFashionCollection> pageInfo = operateFashionCollectionService.queryPage(operateFashionCollectionReq);
            return WebApiResponse.success(pageInfo);
        } catch (Exception e) {
            LOG.error("/fashion/findByPage:" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
    }

    @ApiOperation(value = "新增潮搭")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public WebApiResponse<?> addPromotion(@RequestBody OperateFashionCollectionReq operateFashionCollectionReq){
        try {
            operateFashionCollectionService.insert(operateFashionCollectionReq);
            return WebApiResponse.success(true);
        } catch (Exception e) {
            LOG.error("/fashion/addFashion:" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
    }

    @ApiOperation(value = "更新潮搭信息")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public WebApiResponse<?> updatePromotion(@RequestBody OperateFashionCollection operateFashionCollection){
        if (operateFashionCollection.getUuid() ==null){
            return WebApiResponse.error("uuid不能为空！");
        }
        try {
            operateFashionCollectionService.updateByUuid(operateFashionCollection);
            return WebApiResponse.success(true);
        } catch (Exception e) {
            LOG.error("/fashion/updateFashion:" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
    }



    @ApiOperation(value = "删除潮搭")
    @RequestMapping(value = "/delete/{uuid}", method = RequestMethod.GET)
    public WebApiResponse<?> deletePromotion(@PathVariable String uuid){
        try {
            operateFashionCollectionService.deleteByUuid(uuid);
            return WebApiResponse.success(true);
        } catch (Exception e) {
            LOG.error("/fashion/deleteFashion/{uuid}:" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }

    }

    @ApiOperation(value = "根据uuid查询潮搭信息")
    @RequestMapping(value = "/findByUuid/{uuid}", method = RequestMethod.GET)
    public WebApiResponse<?> findByUuid(@PathVariable String uuid){
        try {
            OperateFashionCollection operateFashionCollection = operateFashionCollectionService.selectByUuid(uuid);
            return WebApiResponse.success(operateFashionCollection);
        } catch (Exception e) {
            LOG.error("/fashion/findByUuid/{uuid}:" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
    }
}
