package com.kingthy.platform.controller.message;

import com.kingthy.platform.dto.message.MessageInfoReq;
import com.kingthy.platform.response.WebApiResponse;
import com.kingthy.platform.service.message.MessageService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @AUTHORS xumin
 * @Description:消息管理
 * @DATE Created by 16:23 on 2017/7/13.
 * @Modified by:
 */

@RestController
@RequestMapping("msg")
public class MessageController {

    @Autowired
    private MessageService messageService;

    private static final Logger LOG = LoggerFactory.getLogger(MessageController.class);

    @ApiOperation(value = "消息列表", notes = "消息管理")
    @RequestMapping(value = "/msg/list", method = RequestMethod.POST)
    public WebApiResponse<?> list(@RequestBody @ApiParam(name = "MessageInfoReq", value = "查询条件", required = true) MessageInfoReq req){

        WebApiResponse<?> result = null;

        try {

            result = messageService.list(req);

        }catch (Exception e){

            LOG.error("/msg/list 消息列表出错,异常信息" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }

        return result;
    }

    @ApiOperation(value = "新增消息", notes = "消息管理")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public WebApiResponse<?> createMessage(@RequestBody @ApiParam(name = "CreateMessageDto", value = "查询条件", required = true) MessageInfoReq.CreateMessageDto req){

        WebApiResponse<?> result = null;

        try {

            result = messageService.createMessage(req);

        }catch (Exception e){
            LOG.error("/msg/create 新增消息出错,异常信息" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }

        return result;
    }

    @ApiOperation(value = "编辑消息", notes = "消息管理")
    @RequestMapping(value = "/edit", method = RequestMethod.PUT)
    public WebApiResponse<?> editMessage(@RequestBody @ApiParam(name = "EditMessageDto", value = "查询条件", required = true) MessageInfoReq.EditMessageDto req){

        WebApiResponse<?> result = null;

        try{
            result = messageService.editMessage(req);

        }catch (Exception e){
            LOG.error("/msg/edit 编辑消息出错,异常信息" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }

        return result;
    }

    @ApiOperation(value = "删除消息", notes = "消息管理")
    @RequestMapping(value = "/del/{uuid}/{membersUuid}", method = RequestMethod.DELETE)
    public WebApiResponse<?> delMessage(@PathVariable @ApiParam(name = "uuid", value = "uuid", required = true) String uuid,
                                        @PathVariable @ApiParam(name = "membersUuid", value = "membersUuid", required = true) String membersUuid){

        WebApiResponse<?> result = null;

        try {

            result = messageService.delMessage(uuid, membersUuid);

        }catch (Exception e){
            LOG.error("/msg/del/{uuid}/{membersUuid} 删除消息出错,异常信息" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }

        return result;
    }

    @ApiOperation(value = "查看消息", notes = "消息管理")
    @RequestMapping(value = "/show/{uuid}", method = RequestMethod.GET)
    public WebApiResponse<?> showMessage(@PathVariable @ApiParam(name = "uuid", value = "uuid", required = true) String uuid){

        WebApiResponse<?> result = null;

        try {

            result = messageService.showMessage(uuid);

        }catch (Exception e){
            LOG.error("/msg/show/{uuid} 查看消息出错,异常信息" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }

        return result;
    }

    @ApiOperation(value = "发布", notes = "消息管理")
    @RequestMapping(value = "/push/{uuid}", method = RequestMethod.PUT)
    public WebApiResponse<?> pushMessage(@PathVariable @ApiParam(name = "uuid", value = "uuid", required = true) String uuid,
                                         @PathVariable @ApiParam(name = "membersUuid", value = "membersUuid", required = true) String membersUuid){

        WebApiResponse<?> result = null;

        try {

            result = messageService.pushMessage(uuid, membersUuid);

        }catch (Exception e){
            LOG.error("/msg/push/{uuid} 查看消息出错,异常信息" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }

        return result;
    }
}
