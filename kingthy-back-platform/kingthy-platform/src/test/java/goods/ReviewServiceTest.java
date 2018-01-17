package goods;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.kingthy.platform.KingthyPlatformApplication;
import com.kingthy.platform.dto.goods.ReviewReq;
import com.kingthy.platform.dto.goods.ReviewUpdateReq;
import com.kingthy.platform.service.goods.ReviewService;

/**
 * goods.ReviewServiceTest
 * <p>
 * yuanml 2017/5/16
 *
 * @version 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = KingthyPlatformApplication.class, properties = {"eureka.client.enabled=false"})
@Rollback
@Transactional
public class ReviewServiceTest
{
    @Autowired
    private ReviewService reviewService;
    
    @Test
    public void listReviewTest()
    {
        ReviewReq reviewReq = new ReviewReq();
        reviewReq.setPageNum(1);
        reviewReq.setPageSize(5);
        Assert.assertNotNull(reviewService.listReview(reviewReq).getList());
    }
    
    @Test
    public void selectReview()
    {
        String reviewUuid = "97130857868820508";
        Assert.assertNotNull(reviewService.selectReview(reviewUuid));
    }
    
    @Test
    public void updateReview()
    {
        String reviewUuid = "97130857868820508";
        ReviewUpdateReq reviewUpdateReq = new ReviewUpdateReq();
        reviewUpdateReq.setReviewUuids(reviewUuid);
        int result = reviewService.updateReview(reviewUpdateReq);
        Assert.assertEquals(1, result);
    }
}
