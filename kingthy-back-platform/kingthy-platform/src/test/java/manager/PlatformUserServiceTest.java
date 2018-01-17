package manager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import java.util.Date;

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
import com.kingthy.platform.dto.manager.PlatformUserEditReq;
import com.kingthy.platform.dto.manager.PlatformUserInfoDto;
import com.kingthy.platform.dto.manager.PlatformUserPageReq;
import com.kingthy.platform.dto.manager.PlatformUserReq;
import com.kingthy.platform.dto.manager.ResetPasswordReq;
import com.kingthy.platform.entity.manager.PlatformUser;
import com.kingthy.platform.service.manager.PlatformUserService;

/**
 * 
 *
 * PlatformUserServiceTest(平台用户接口单元测试)
 * 
 * 陈钊 2017年5月18日 下午7:51:45
 * 
 * @version 1.0.0
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = KingthyPlatformApplication.class)
@WebAppConfiguration
@Rollback
@Transactional
public class PlatformUserServiceTest
{
    @Autowired
    private PlatformUserService platformUserService;
    
    @Test
    public void findBaseInfoByUuid()
    {
        PlatformUserInfoDto platformUserInfoDto = platformUserService.findBaseInfoByUuid("97100764777809293");
        assertNotNull(platformUserInfoDto);
    }
    
    @Test
    public void findByUserNameTest()
    {
        String userName = "admin";
        PlatformUser platformUser = platformUserService.findByUserName(userName);
        assertEquals("admin", platformUser.getUserName());
    }
    
    @Test
    public void checkPasswordTest()
    {
        String password = "654321";
        String uuid = "97100764777809293";
        boolean result = platformUserService.checkPassword(password, uuid);
        assertEquals(true, result);
    }
    
    @Test
    public void addUserTest()
    {
        PlatformUserReq platformUserReq = new PlatformUserReq();
        platformUserReq.setEmail("1111@163.com");
        platformUserReq.setOfficePhone("88889999");
        platformUserReq.setPassword("111111");
        platformUserReq.setPhone("15129280751");
        platformUserReq.setRoleUuid("123456");
        platformUserReq.setUserName("xxxx");
        int result = platformUserService.add(platformUserReq);
        assertSame(1, result);
    }
    
    @Test
    public void findUserByPage()
    {
        PlatformUserPageReq platformUserPageReq = new PlatformUserPageReq();
        platformUserPageReq.setPageNum(1);
        platformUserPageReq.setPageSize(1);
        PageInfo<PlatformUserInfoDto> page = platformUserService.findUserByPage(platformUserPageReq);
        assertNotNull(page.getList());
    }
    
    @Test
    public void editBaseInfo()
    {
        PlatformUserEditReq platformUserEditReq = new PlatformUserEditReq();
        platformUserEditReq.setEmail("123456");
        platformUserEditReq.setOfficePhone("123456");
        platformUserEditReq.setPhone("123456");
        platformUserEditReq.setRoleUuid("");
        platformUserEditReq.setUserName("xxx");
        platformUserEditReq.setUuid("97100764777809293");
        int result = platformUserService.editBaseInfo(platformUserEditReq);
        assertSame(1, result);
    }
    
    @Test
    public void delete()
    {
        int result = platformUserService.delete("97100764777809293");
        assertSame(1, result);
    }
    
    @Test
    public void findCountByName()
    {
        int result = platformUserService.findCountByName("admin");
        assertSame(1, result);
    }
    
    @Test
    public void resetPassword()
    {
        ResetPasswordReq resetPasswordReq = new ResetPasswordReq();
        resetPasswordReq.setModifier("admin");
        resetPasswordReq.setModifyDate(new Date());
        resetPasswordReq.setPassword("111111");
        resetPasswordReq.setSalt("154874354564");
        resetPasswordReq.setUuid("97100764777809293");
        String result = platformUserService.resetPassword(resetPasswordReq);
        assertNotNull(result);
    }
    
    @Test
    public void adminEditUser()
    {
        PlatformUserEditReq platformUserEditReq = new PlatformUserEditReq();
        platformUserEditReq.setEmail("123456");
        platformUserEditReq.setOfficePhone("123456");
        platformUserEditReq.setPhone("123456");
        platformUserEditReq.setRoleUuid("123456");
        platformUserEditReq.setUserName("admin");
        platformUserEditReq.setUuid("97100764777809293");
        int result = platformUserService.adminEditUser(platformUserEditReq);
        assertSame(1, result);
    }
}
