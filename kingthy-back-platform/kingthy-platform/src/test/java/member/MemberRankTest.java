package member;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.kingthy.platform.KingthyPlatformApplication;
import com.kingthy.platform.dto.member.MemberRankReq;
import com.kingthy.platform.entity.member.MemberRank;
import com.kingthy.platform.service.member.MemberRankService;

/**
 * MemberRankTest
 * <p>
 * yuanml 2017/5/24
 *
 * @version 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = KingthyPlatformApplication.class, properties = "eureka.client.enabled=false")
@Rollback
@Transactional
public class MemberRankTest
{
    @Autowired
    private MemberRankService memberRankService;
    
    @Test
    public void testAddMemberRank()
    {
        MemberRankReq memberRankReq = new MemberRankReq();
        memberRankReq.setAmountMin(BigDecimal.valueOf(123123));
        memberRankReq.setAmountMax(BigDecimal.valueOf(1231231));
        memberRankReq.setName("测试会员");
        int result = memberRankService.addMemberRank(memberRankReq);
        Assert.assertEquals(1, result);
    }
    
    @Test
    public void testBatch()
    {
        System.out.println("test method 'findMemberRank'");
        List<MemberRank> list = memberRankService.findMemberRank();
        Assert.assertNotNull(list);
        if (list.size() > 0)
        {
            System.out.println("test method 'findOneMemberRank'");
            MemberRank memberRank = memberRankService.findOneMemberRank(list.get(0).getUuid());
            Assert.assertEquals(memberRank.getUuid(), list.get(0).getUuid());
            System.out.println("test method 'editMemberRank'");
            MemberRankReq memberRankReq = new MemberRankReq();
            memberRankReq.setUuid(memberRank.getUuid());
            memberRankReq.setName("测试会员");
            memberRankReq.setAmountMin(BigDecimal.valueOf(123123));
            memberRankReq.setAmountMax(BigDecimal.valueOf(1231231));
            int result = memberRankService.editMemberRank(memberRankReq);
            Assert.assertEquals(1, result);
            System.out.println("test method 'deleteMemberRank'");
            result = memberRankService.deleteMemberRank(memberRank.getUuid());
            Assert.assertEquals(1, result);
        }
    }
}
