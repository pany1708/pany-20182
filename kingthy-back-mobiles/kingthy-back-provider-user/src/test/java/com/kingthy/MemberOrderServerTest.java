package com.kingthy;

import com.github.pagehelper.PageInfo;
import com.kingthy.dto.MemberDTO;
import com.kingthy.dto.MemberOrderDTO;
import com.kingthy.request.MemberOrderReq;
import com.kingthy.request.MemberPageReq;
import com.kingthy.service.MemberOrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * 描述：----------
 *
 * @author likejie
 * @date 2017/12/13
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = KingthyProviderUserApplication.class)
@WebAppConfiguration
public class MemberOrderServerTest {

    @Autowired
    private MemberOrderService memberOrderService;

    @Test
    public void pageGetOrderList()
    {
        try
        {
            MemberOrderReq req=new MemberOrderReq();
            req.setPageSize(10);
            req.setPageNum(1);
            req.setMemberUuid("97071061572518195");
            PageInfo<MemberOrderDTO> pageInfo= memberOrderService.pageGetOrderList(req);

        }
        catch (Exception e)
        {
            fail();
            System.out.println("MemberOrderServerTest.pageGetOrderList error:"+e.toString());
        }
    }
}
