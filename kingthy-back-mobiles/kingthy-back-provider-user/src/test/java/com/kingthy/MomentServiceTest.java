package com.kingthy;

import com.github.pagehelper.PageInfo;
import com.kingthy.KingthyProviderUserApplication;
import com.kingthy.dto.MomentDto;
import com.kingthy.entity.Moments;
import com.kingthy.request.QueryMomentPageReq;
import com.kingthy.response.WebApiResponse;
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

import java.util.ArrayList;
import java.util.List;

/**
 * comment(描述其作用)
 * <p>
 * 赵生辉 2017年06月22日 14:10
 *
 * @version 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = KingthyProviderUserApplication.class)
@WebAppConfiguration
@Rollback
@Transactional
public class MomentServiceTest
{

    @Autowired
    private MomentService momentService;

    @Test
    public void publishMoment()
    {
        Moments moments = new Moments();
        moments.setImage("[\"http://192.168.1.217/group1/M00/00/17/wKgB2lkKg56ACYyqAAfvbjKjzIA414.jpg\"]");
        moments.setContext("红红火火恍恍惚惚");
        moments.setMemberUuid("97100764777809442");
        moments.setMemberNick("考拉");
        moments.setMemberHead("http://192.168.1.217/group1/M00/00/15/wKgB21jztL-AITUGAAvqH_kipG8997.jpg");


        String orderSn = "97100764777808193";try {

        //发布成功
        int result = momentService.publishMoment(moments);
        Assert.assertTrue("发布成功", result == 1);

    } catch (Exception e) {
        e.printStackTrace();
        Assert.fail(e.getMessage());
    }

    }

    @Test
    public void deleteMoment()
    {
        Moments moments = new Moments();
        moments.setUuid("97137901346757309");
        moments.setMemberUuid("97071061572518194");

        try {

            //删除成功
            int result = momentService.delete("97137901346757309","97071061572518194");
            Assert.assertTrue("删除成功", result == 1);

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void updateMoment()
    {
        QueryMomentPageReq queryMomentPageReq = new QueryMomentPageReq();
        queryMomentPageReq.setMemberUuid("97071061572518195");
        queryMomentPageReq.setPageNum(0);
        queryMomentPageReq.setPageSize(5);
        queryMomentPageReq.setOrder("time");
        queryMomentPageReq.setMembers(new ArrayList<String>());
        try {
            //查询成功
            PageInfo<MomentDto> result = momentService.queryMomentPage(queryMomentPageReq);
            Assert.assertTrue("查询成功", result.getList().size() != 0);

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }
}
