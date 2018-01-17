package com.kingthy;

import com.kingthy.KingthyProviderUserApplication;
import com.kingthy.entity.Likes;
import com.kingthy.service.LikesService;
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
 * LikesServiceTest(描述其作用)
 * <p>
 * 赵生辉 2017年06月22日 14:16
 *
 * @version 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = KingthyProviderUserApplication.class)
@WebAppConfiguration
@Rollback
@Transactional
public class LikesServiceTest
{

    @Autowired
    private LikesService likesService;

    @Test
    public void createLike(){
        Likes likes = new Likes();
        likes.setMomentUuid("97071061572518196");
        likes.setType(0);
        likes.setMemberUuid("97071061572518195");
            try {
                //点赞成功
                int result = likesService.createLike(likes);
                Assert.assertTrue("点赞成功", result == 1);
            } catch (Exception e) {
                e.printStackTrace();
                Assert.fail(e.getMessage());
            }

    }

    @Test
    public void deleteLike(){
        Likes likes = new Likes();
        likes.setMomentUuid("97137901346761725");
        likes.setType(2);
        likes.setMemberUuid("97137901346750495");
        try {
            //取消赞成功
            int result = likesService.deleteLike(likes);
            Assert.assertTrue("取消赞成功", result == 1);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

    }
}
