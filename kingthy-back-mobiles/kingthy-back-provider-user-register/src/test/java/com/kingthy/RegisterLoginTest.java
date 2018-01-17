package com.kingthy;

import com.kingthy.dto.UserDTO;
import com.kingthy.dto.UserRegDTO;
import com.kingthy.entity.Member;
import com.kingthy.response.WebApiResponse;
import com.kingthy.service.LoginService;
import com.kingthy.service.RegisterService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

/**
 * 描述：----------
 *
 * @author likejie
 * @date 2017/12/12
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = KingthyProviderUserRegisterApplication.class)
@WebAppConfiguration
public class RegisterLoginTest {
    private static final Logger LOG = LoggerFactory.getLogger(RegisterLoginTest.class);

    @Autowired
    private RegisterService service;

    @Autowired
    private LoginService loginService;

    private static final String CLIENTID="098f6bcd4621d373cade4e832627b4f6";
    private static final String IP="192.168.1.100";
    private String phone;

    @Before
    public void setUp()
    {
        //动态生成手机号
        phone = "158" + getRandNum(8);
    }
    private static String getRandNum(int max)
    {
        Random random = new Random();
        String result = "";
        for (int i = 0; i < max; i++)
        {
            result += random.nextInt(10);
        }
        return result;
    }
    @Test
    public void userTest(){

        try {
            //根据手机号获取验证码
            String code = service.getVerificationCode(phone);
            String pwd="12345678a";
            //创建用户
            UserRegDTO dto = new UserRegDTO();
            dto.setPhone(phone);
            dto.setPwd(pwd);
            dto.setRegIp(IP);
            dto.setVerificationCode(code);
            //1.注册测试
            WebApiResponse res= service.createUser(dto);
            assertEquals(res.getCode(), 0);
            //2.重复注册测试
            res= service.createUser(dto);
            assertEquals(res.getCode(), 1);

            //3.密码登录测试
            UserDTO userDTO=new UserDTO();
            userDTO.setPhone(phone);
            userDTO.setPwd(pwd);
            userDTO.setClientId(CLIENTID);
            res = loginService.toLogin(userDTO);
            assertEquals(res.getCode(), 0);

            //验证码登录测试
            userDTO.setPwd(null);
            userDTO.setCode(code);
            userDTO.setLoginMode(UserDTO.LoginModel.PHONE_VERIFY_CODE.getValue());
            res = loginService.toLogin(userDTO);
            assertEquals(res.getCode(), 0);

            //4.修改密码测试
            pwd="12345678b";
            dto.setPwd(pwd);
            service.updateUserPwd(dto);

            //5.新密码登陆测试
            WebApiResponse<String> response= loginService.toLogin(userDTO);
            assertEquals(response.getCode(), 0);

            //6.登出测试
            res=loginService.toLogout(response.getData());
            assertEquals(res.getCode(), 0);

        }catch (Exception ex){
            fail();
            LOG.error("userTest error:", ex.toString());
        }
    }
    @Test
    public void getVerificationCode(){
        try {
            String code = service.getVerificationCode(phone);
            assertNotEquals(code, null);
        }catch (Exception ex){
            fail();
            LOG.error("updateUserPwd error:", ex.toString());
        }
    }
    @Test
    public void loginPwd(){
        try {
            UserDTO userDTO=new UserDTO();
            userDTO.setPhone("15889363167");
            userDTO.setPwd("12345678b111111");
            userDTO.setClientId("098f6bcd4621d373cade4e832627b4f6");
            //密码错误测试
            for (int i = 1; i <= 5; i++) {
                WebApiResponse<String> res1 = loginService.toLogin(userDTO);
            }
            Member member = loginService.findUserByLoginName(userDTO.getPhone());
            //测试锁定
            assertEquals(member.getIsLocked(), true);
            System.out.println("账户锁定");
            //解锁
            int res = loginService.unlockUser(userDTO.getPhone());
            member = loginService.findUserByLoginName(userDTO.getPhone());
            assertEquals(member.getIsLocked(), false);
            System.out.println("账户解除锁定");
        }catch (Exception e){
            fail();
            System.out.println(e.toString());
        }

    }
}
