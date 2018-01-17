/**
 * 系统项目名称
 * com.kingthy.test
 * ReceiverServiceTest.java
 * 
 * 2017年5月16日-下午1:59:43
 *  2017金昔科技有限公司-版权所有
 *
 */
package com.kingthy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

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
import com.kingthy.entity.Receiver;
import com.kingthy.service.ReceiverService;

/**
 *
 * ReceiverServiceTest
 * 
 * 李克杰 2017年5月16日 下午1:59:43
 * 
 * @version 1.0.0
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = KingthyProviderUserApplication.class)
@WebAppConfiguration
public class ReceiverServiceTest
{
    private static final Logger logger = LoggerFactory.getLogger(ReceiverServiceTest.class);
    
    @Autowired
    private ReceiverService service;

    private String memberUuid;
    
    @Before
    public void setUp()
    {
        memberUuid = "97071061572518195";
    }
    
    @Test
    public void getMemberReceiverList()
    {
        try
        {
            service.getMemberReceiverList(memberUuid);
        }
        catch (Exception e)
        {
            fail();
            logger.error("ReceiverServiceTest.getMemberReceiverList error:", e.toString());
        }
    }
    
    @Test
    public void mainTest()
    {
        try
        {
            // 获取默认收获地址
            Receiver defaultReceiver = service.getDefaultReceiver(memberUuid);
            if (defaultReceiver != null)
            {
                assertEquals(defaultReceiver.getIsDefault(), true);
            }
            
            // 新增
            Receiver receiver = new Receiver();
            receiver.setMemberUuid(memberUuid);
            receiver.setIsDefault(true);
            receiver.setConsignee("李四");
            receiver.setPhone("15887454747");
            receiver.setAreaId(2);
            receiver.setAreaName("北京市东城区");
            receiver.setAddress("聊斋胡同1003号");
            service.addReceiver(receiver);
            boolean isDefault = receiver.getIsDefault();
            
            Receiver receive1 = new Receiver();
            receive1.setMemberUuid(memberUuid);
            receive1.setIsDefault(true);
            receive1.setConsignee("wangwuddd");
            receive1.setPhone("15889858536");
            receive1.setAreaId(2);
            receive1.setAreaName("北京市东城区");
            receive1.setAddress("聊斋胡同1002号");
            service.addReceiver(receive1);
            boolean isDefault1 = receive1.getIsDefault();
            
            receiver = service.getReceiverByUuid(receiver.getUuid());
            boolean isDefault2 = receiver.getIsDefault();
            
            // 测试默认收获地址变化值
            assertEquals(isDefault, true);
            assertEquals(isDefault1, true);
            assertEquals(isDefault2, false);
            
            // 删除收获地址
            service.deleteReceiver(receiver.getUuid(),memberUuid);
            service.deleteReceiver(receive1.getUuid(),memberUuid);
            
            // 设置默认收获地址
            
            int res = service.setDefaultReceiver(memberUuid, "");
            assertEquals(0, res);// 事务回滚
            if (defaultReceiver != null)
            {
                res = service.setDefaultReceiver(memberUuid, defaultReceiver.getUuid());
                assertEquals(1, res);// 设置成功
            }
            
        }
        catch (Exception e)
        {
            fail();
            logger.error("ReceiverServiceTest.addReceiver error:", e.toString());
        }
    }
    
}
