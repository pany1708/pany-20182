/**
 * 系统项目名称
 * com.kingthy
 * AttentionControllerTest.java
 * 
 * 2017年5月15日-下午4:35:26
 *  2017金昔科技有限公司-版权所有
 *
 */
package com.kingthy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import com.github.pagehelper.PageInfo;
import com.kingthy.request.AttentionPageReq;
import com.kingthy.request.AttentionReq;
import com.kingthy.response.WebApiResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.kingthy.KingthyProviderUserApplication;
import com.kingthy.dto.AttentionMemberDTO;
import com.kingthy.dto.FansDTO;
import com.kingthy.entity.Attention;
import com.kingthy.service.AttentionService;

/**
 *
 * AttentionController测试类
 * 
 * 李克杰 2017年5月15日 下午4:35:26
 * 
 * @version 1.0.0
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = KingthyProviderUserApplication.class)
@WebAppConfiguration
public class AttentionServiceTest
{
    private static final Logger LOG = LoggerFactory.getLogger(AttentionServiceTest.class);
    
    @Autowired
    private AttentionService service;

    private String memberUuid;
    
    @Before
    public void setUp()
    {
        memberUuid = "97071061572518195";
    }
    
    @Test
    public void getAttentionList()
    {
        
        try
        {
            List<AttentionMemberDTO> list = service.getAttentionList(memberUuid);
            assertNotEquals(list, null);
            
        }
        catch (Exception e)
        {
            fail();
            LOG.error("AttentionServiceTest.getAttentionList", e.toString());
        }
        
    }
    @Test
    public void pageGetAttentionList(){
        try
        {
            AttentionPageReq req=new AttentionPageReq();
            req.setMemberUuid(memberUuid);
            req.setPageNum(1);
            req.setPageSize(10);
            PageInfo<AttentionMemberDTO> pageInfo = service.pageGetAttentionList(req);
        }
        catch (Exception e)
        {
            fail();
            LOG.error("AttentionServiceTest.getAttentionList", e.toString());
        }
    }
    @Test
    public void addAndDel()
    {
        try
        {
            // 新增测试
            WebApiResponse response=WebApiResponse.success();
            AttentionReq req = new AttentionReq();
            req.setMemberUuid(memberUuid);
            req.setAttentionUuid("97071061572518195");
            response = service.addAttention(req);
            assertEquals("不能关注自己", 1, response.getCode());


            req.setMemberUuid(memberUuid);
            req.setAttentionUuid("1111111111111111");
            response = service.addAttention(req);
            assertEquals("用户不存在",1, response.getCode());

            req.setMemberUuid(memberUuid);
            req.setAttentionUuid("97130857868820571");
            response = service.addAttention(req);


            // 批量删除测试
            List<String> attentionUuids = new ArrayList<String>();
            attentionUuids.add("97130857868820571");
            int count = service.batchDeleteAttention(memberUuid, attentionUuids);
            assertEquals(count, attentionUuids.size());
            
        }
        catch (Exception e)
        {
            fail();
            LOG.error("AttentionServiceTest.addAttention", e.toString());
        }
        
    }
    
    @Test
    public void getFansLilst()
    {
        try
        {
            List<FansDTO> list = service.getFansLilst(memberUuid);
            assertNotNull(list);
        }
        catch (Exception e)
        {
            fail();
            LOG.error("AttentionServiceTest.getFansLilst", e.toString());
        }
        
    }
    
    @Test
    public void getFansCount()
    {
        
        try
        {
            service.getFansCount(memberUuid);
        }
        catch (Exception e)
        {
            fail();
            LOG.error("AttentionServiceTest.getFansCount", e.toString());
        }
        
    }
    
    @Test
    public void getAttentionMemberCount()
    {
        
        try
        {
            service.getAttentionMemberCount(memberUuid);
        }
        catch (Exception e)
        {
            fail();
            LOG.error("AttentionServiceTest.getAttentionMemberCount", e.toString());
        }
        
    }
    @Test
    public void pageGetFansList(){
        try
        {
            AttentionPageReq req=new AttentionPageReq();
            req.setAttentionUuid(memberUuid);
            req.setPageNum(1);
            req.setPageSize(10);
            PageInfo<FansDTO> pageInfo = service.pageGetFansList(req);
        }
        catch (Exception e)
        {
            fail();
            LOG.error("AttentionServiceTest.getAttentionList", e.toString());
        }
    }
}
