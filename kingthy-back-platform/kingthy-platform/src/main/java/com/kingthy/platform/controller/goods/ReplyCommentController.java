package com.kingthy.platform.controller.goods;

import com.github.pagehelper.StringUtil;
import com.kingthy.platform.dto.goods.ReplyCommentReq;
import com.kingthy.platform.response.WebApiResponse;
import com.kingthy.platform.service.goods.ReplyCommentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @AUTHORS xumin
 * @Description:
 * @DATE Created by 11:57 on 2017/7/7.
 * @Modified by:
 */

@RestController
@RequestMapping("reply")
public class ReplyCommentController {

    @Autowired
    private ReplyCommentService replyCommentService;

    private static final Logger LOG = LoggerFactory.getLogger(ReplyCommentController.class);

    @ApiOperation(value = "回复买家秀")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public WebApiResponse<?> create(@RequestBody @ApiParam(value = "ReplyCommentReq") ReplyCommentReq req){

        WebApiResponse<?> result = null;

        try {

            if (StringUtil.isEmpty(req.getBuyersUuid())||
                    StringUtil.isEmpty(req.getMemberUuid())||
                    StringUtil.isEmpty(req.getContent())){
                return WebApiResponse.error(WebApiResponse.ResponseMsg.PARAMETER_ERROR.getValue());
            }

            result = replyCommentService.create(req);

        }catch (Exception e){
            e.printStackTrace();
            LOG.error("/reply/create" + e);
        }

        return result;
    }

}
