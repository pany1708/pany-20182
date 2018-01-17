package com.kingthy.platform.controller.message;

import com.kingthy.platform.dto.message.MessagePlatformReq;
import com.kingthy.platform.response.WebApiResponse;
import com.kingthy.platform.service.message.MessagePlatformService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @AUTHORS xumin
 * @Description: 短信平台
 * @DATE Created by 9:42 on 2017/7/17.
 * @Modified by:
 */

@RestController
@RequestMapping("/msgPlat")
public class MessagePlatformController {

    private static final Logger LOG = LoggerFactory.getLogger(MessagePlatformController.class);

    @Autowired
    private MessagePlatformService messagePlatformService;

    @ApiOperation(value = "消息平台列表", notes = "消息平台管理")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public WebApiResponse<?> list(@RequestBody @ApiParam(name = "MessageInfoReq", value = "查询条件", required = true) MessagePlatformReq req){

        WebApiResponse<?> result = null;

        try {

            result = messagePlatformService.list(req);

        }catch (Exception e){

            LOG.error("/msgPlat/list 消息平台列表出错,异常信息" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }

        return result;
    }

    @ApiOperation(value = "查看消息平台明细", notes = "消息平台管理")
    @RequestMapping(value = "/show/{uuid}", method = RequestMethod.GET)
    public WebApiResponse<?> showPlat(@PathVariable @ApiParam(name = "uuid", value = "查询条件", required = true) String uuid){

        WebApiResponse<?> result = null;

        try {

            result = messagePlatformService.showPlat(uuid);

        }catch (Exception e){
            LOG.error("/msgPlat/show 查看消息平台明细出错,异常信息" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }

        return result;
    }

    @ApiOperation(value = "编辑", notes = "消息平台管理")
    @RequestMapping(value = "/edit", method = RequestMethod.PUT)
    public WebApiResponse<?> edit(@RequestBody @ApiParam(name = "UpdateStatusReq", value = "查询条件", required = true) MessagePlatformReq.EditReq req){

        WebApiResponse<?> result = null;

        try {

            result = messagePlatformService.edit(req);

        }catch (Exception e){
            LOG.error("/msgPlat/edit 编辑出错,异常信息" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }

        return result;
    }

    @ApiOperation(value = "启用/禁用", notes = "消息平台管理")
    @RequestMapping(value = "/updateStatus", method = RequestMethod.PUT)
    public WebApiResponse<?> updateStatus(@RequestBody @ApiParam(name = "UpdateStatusReq", value = "查询条件", required = true) MessagePlatformReq.UpdateStatusReq req){

        WebApiResponse<?> result = null;

        try {

            result = messagePlatformService.updateStatus(req);

        }catch (Exception e){
            LOG.error("/msgPlat/updateStatus 启用/禁用出错,异常信息" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }

        return result;
    }

    @ApiOperation(value = "删除", notes = "消息平台管理")
    @RequestMapping(value = "/del/{uuid}/{membersUuid}", method = RequestMethod.DELETE)
    public WebApiResponse<?> del(@PathVariable @ApiParam(name = "uuid", value = "查询条件", required = true) String uuid,
                                 @PathVariable @ApiParam(name = "membersUuid", value = "查询条件", required = true)String membersUuid){

        WebApiResponse<?> result = null;

        try {

            result = messagePlatformService.del(uuid, membersUuid);

        }catch (Exception e){
            LOG.error("/msgPlat/del/{uuid}/{membersUuid} 查看消息平台明细出错,异常信息" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }

        return result;
    }
}
