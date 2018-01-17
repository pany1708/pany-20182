package com.kingthy.platform.mapper.goods;

import com.kingthy.platform.entity.goods.ReplyComment;
import com.kingthy.platform.util.MyMapper;

/**
 * 回复评论
 */
public interface ReplyCommentMapper extends MyMapper<ReplyComment> {

    ReplyComment queryReplyCommentByUuid(String buyersUuid);
}