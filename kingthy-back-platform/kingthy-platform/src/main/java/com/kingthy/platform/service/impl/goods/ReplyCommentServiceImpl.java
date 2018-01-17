package com.kingthy.platform.service.impl.goods;

import com.kingthy.platform.dto.goods.ReplyCommentReq;
import com.kingthy.platform.entity.goods.ReplyComment;
import com.kingthy.platform.mapper.goods.ReplyCommentMapper;
import com.kingthy.platform.response.WebApiResponse;
import com.kingthy.platform.service.goods.ReplyCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @AUTHORS xumin
 * @Description:
 * @DATE Created by 14:34 on 2017/7/7.
 * @Modified by:
 */

@Service
public class ReplyCommentServiceImpl implements ReplyCommentService{

    @Autowired
    private ReplyCommentMapper replyCommentMapper;

    @Override
    public WebApiResponse<?> create(ReplyCommentReq req) throws Exception {

        ReplyComment replyComment = new ReplyComment();
        replyComment.setBuyersUuid(req.getBuyersUuid());
        replyComment.setContent(req.getContent());
        replyComment.setCreateDate(new Date());
        replyComment.setCreator(req.getMemberUuid());
        replyComment.setVersion(0);
        replyComment.setDelFlag(false);

        return replyCommentMapper.insert(replyComment) > 0 ? WebApiResponse.success(WebApiResponse.ResponseMsg.SUCCESS.getValue())
                : WebApiResponse.error(WebApiResponse.ResponseMsg.FAIL.getValue());
    }
}
