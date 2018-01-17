package com.kingthy;

import com.github.pagehelper.PageInfo;
import com.kingthy.KingthyProviderUserApplication;
import com.kingthy.dto.MomentCommentDto;
import com.kingthy.entity.MomentComment;
import com.kingthy.service.MomentCommentService;
import com.kingthy.service.MomentService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * MomentCommentServiceTest(描述其作用)
 * <p>
 * 赵生辉 2017年06月22日 14:14
 *
 * @version 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = KingthyProviderUserApplication.class)
@WebAppConfiguration
@Rollback
@Transactional
public class MomentCommentServiceTest
{
    @Autowired
    private MomentCommentService momentCommentService;

    @Test
    public void publishComment(){

        MomentComment momentComment = new MomentComment();
        momentComment.setContext("呵呵哒");
        momentComment.setMemberHead("string");
        momentComment.setMemberNick("string");
        momentComment.setParentUuid("0");
        momentComment.setParentNick("王大锤");
        momentComment.setMemberUuid("97100764777809442");
        momentComment.setMomentUuid("97137901346757309");
        try {
            //发布成功
            int result = momentCommentService.publishComment(momentComment);
            Assert.assertTrue("发布成功", result == 1);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void deleteComment(){
        MomentComment momentComment = new MomentComment();
        momentComment.setUuid("97137901346757414");
        momentComment.setMemberUuid("97071061572518195");
        momentComment.setMomentUuid("97137901346757412");
        try {
            //删除成功
            int result = momentCommentService.delete(momentComment);
            Assert.assertTrue("删除成功", result == 1);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void queryComment(){
        try {
            //查询成功
            PageInfo<MomentCommentDto> result = momentCommentService.queryMomentCommentPage( 0, 2, "97137901346757412", "97071061572518195");
            Assert.assertTrue("删除成功", result.getList().size() != 0);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

}
