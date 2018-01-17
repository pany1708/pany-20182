package member;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.kingthy.platform.KingthyPlatformApplication;
import com.kingthy.platform.dto.member.IntegralReq;
import com.kingthy.platform.entity.member.Integral;
import com.kingthy.platform.service.member.IntegralService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = KingthyPlatformApplication.class)
@WebAppConfiguration
@Rollback
@Transactional
public class IntegralServiceTest
{
    @Autowired
    private IntegralService integralService;
    
    @Test
    public void findByPage()
    {
        PageInfo<Integral> page = integralService.findByPage(1, 1);
        assertNotNull(page.getList());
    }
    
    @Test
    public void addIntegral()
    {
        IntegralReq integralReq = new IntegralReq();
        integralReq.setDescription("test");
        integralReq.setScore(1);
        integralReq.setSource("test");
        int result = integralService.addIntegral(integralReq);
        assertSame(1, result);
    }
    
    @Test
    public void editIntegral()
    {
        IntegralReq integralReq = new IntegralReq();
        integralReq.setDescription("test");
        integralReq.setScore(1);
        integralReq.setSource("test");
        integralReq.setUuid("97071061572518190");
        int result = integralService.editIntegral(integralReq);
        assertSame(1, result);
    }
    
    @Test
    public void deleteIntegral()
    {
        int result = integralService.deleteIntegral("97071061572518190");
        assertSame(1, result);
    }
    
    @Test
    public void findByUuid()
    {
        Integral integral = integralService.findByUuid("97071061572518190");
        assertNotNull(integral);
    }
}
