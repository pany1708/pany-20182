package com.kingthy;

import com.github.pagehelper.PageInfo;
import com.kingthy.dto.MemberFootmarkDTO;
import com.kingthy.request.MemberFootmarkPageReq;
import com.kingthy.service.MemberFootmarkService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

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
public class MemberFootmarkServiceTest {

    @Autowired
    private MemberFootmarkService memberFootmarkService;

    public void pageGetMemberFootmarkList(){

        try {
            MemberFootmarkPageReq req = new MemberFootmarkPageReq();
            req.setPageNum(1);
            req.setPageSize(10);
            memberFootmarkService.pageGetMemberFootmarkList(req);
        }catch (Exception ex){
            fail();
            System.out.println(ex.toString());
        }
    }
}
