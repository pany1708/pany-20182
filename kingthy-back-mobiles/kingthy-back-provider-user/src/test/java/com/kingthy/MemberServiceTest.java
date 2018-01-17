/**
 * 系统项目名称
 * com.kingthy.test
 * MemberServiceTest.java
 * 
 * 2017年5月16日-下午5:29:57
 *  2017金昔科技有限公司-版权所有
 *
 */
package com.kingthy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import com.kingthy.dto.MemberDTO;
import com.kingthy.response.WebApiResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.kingthy.service.MemberService;

import java.util.Random;

/**
 *
 * MemberServiceTest
 * 
 * 李克杰 2017年5月16日 下午5:29:57
 * 
 * @version 1.0.0
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = KingthyProviderUserApplication.class)
@WebAppConfiguration
public class MemberServiceTest
{
    @Autowired
    private MemberService service;

    private String memberUuid="97187413512519828";
    private String password="12345678a";
    private String pay_password="123456a";

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
    public void updateMember()
    {
        try
        {
            //1.更新用户测试
            String  userName="测试用户"+getRandNum(3);
            MemberDTO dto=new MemberDTO();
            dto.setUuid(memberUuid);
            dto.setUserName(userName);
            int res=service.updateMember(dto);
            assertEquals(res,1);
            //2.缓存更新测试
            MemberDTO member= service.getMemberByUuId(memberUuid);
            assertEquals(userName,member.getUserName());
        }
        catch (Exception e)
        {
            fail();
            System.out.println("MemberServiceTest.updateMember error:"+e.toString());
        }
    }
    @Test
    public void updateName()
    {
        try
        {
            //1.修改用户昵称测试
            String  nickName="测试用户"+getRandNum(3);
            WebApiResponse res=service.updateName(memberUuid,nickName);
            assertEquals(res.getCode(),0);
            //2.昵称重复测试
            res=service.updateName("97182768756175132",nickName);
            assertEquals(res.getCode(),1);
            //2.缓存更新测试
            MemberDTO member= service.getMemberByUuId(memberUuid);
            assertEquals(nickName,member.getNickName());
        }
        catch (Exception e)
        {
            fail();
            System.out.println("MemberServiceTest.updateNickName error:"+e.toString());
        }
    }
    @Test
    public void unlocked(){
        try {
            service.unlocked("15889363167");
        }catch (Exception e){
            fail();
            System.out.println("MemberServiceTest.unlocked error:"+e.toString());
        }
    }
    @Test
    public void modifyMobile(){

        try {

            String phone="15889659857";
            String memberUuid="97187413512519823";
            //1.修改手机号测试
            WebApiResponse response = service.modifyMobile(memberUuid,phone);
            assertEquals(response.getCode(), 0);

            //2.缓存更新测试
            MemberDTO member= service.getMemberByUuId("97187413512519823");
            assertEquals(phone,member.getPhone());

            //3.修改手机号-手机号重复测试
            memberUuid="97187413512519822";
            response = service.modifyMobile(memberUuid,phone);
            assertEquals(response.getCode(), 1);

        }catch (Exception e){
            fail();
            System.out.println("MemberServiceTest.modifyMobile error:"+e.toString());
        }
    }
    @Test
    public void modifyPassword(){

        try {
            WebApiResponse res= service.modifyPassword(memberUuid,password);
            assertEquals(res.getCode(), 0);
        }catch (Exception e){
            fail();
            System.out.println("MemberServiceTest.modifyPassword error:"+e.toString());
        }
    }
    @Test
    public void modifyPaymentPassword() {

        try {
            WebApiResponse res = service.modifyPaymentPassword(memberUuid,pay_password);
            assertEquals(res.getCode(), 0);
            MemberDTO memberDTO = service.getMemberByUuId(memberUuid);
            assertEquals(memberDTO.getIsSetPaymentPwd(), true);
        } catch (Exception e) {
            fail();
            System.out.println("MemberServiceTest.modifyPaymentPassword error:" + e.toString());
        }
    }
    @Test
    public  void verifyPaymentPassword(){
        try {
            WebApiResponse response=service.verifyPaymentPassword(memberUuid,pay_password);
            assertEquals(response.getCode(),0);
        }catch (Exception e){
            fail();
            System.out.println("MemberServiceTest.verifyPaymentPassword error:" + e.toString());
        }
    }
    public void verifyLoginPassword(){
        try {
            WebApiResponse response=service.verifyLoginPassword(memberUuid,password);
            assertEquals(response.getCode(),0);
        }catch (Exception e){
            fail();
            System.out.println("MemberServiceTest.verifyLoginPassword error:" + e.toString());
        }
    }
}
