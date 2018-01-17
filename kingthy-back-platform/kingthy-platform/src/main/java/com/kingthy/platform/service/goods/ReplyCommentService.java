package com.kingthy.platform.service.goods;

import com.kingthy.platform.dto.goods.ReplyCommentReq;
import com.kingthy.platform.response.WebApiResponse;

/**
 * @AUTHORS xumin
 * @Description:
 * @DATE Created by 14:28 on 2017/7/7.
 * @Modified by:
 */
public interface ReplyCommentService {

    WebApiResponse<?> create(ReplyCommentReq req) throws Exception;
}
