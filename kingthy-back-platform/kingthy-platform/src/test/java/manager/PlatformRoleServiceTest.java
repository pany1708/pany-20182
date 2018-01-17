package manager;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;

import java.util.List;

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
import com.kingthy.platform.dto.manager.MenuAssignedReq;
import com.kingthy.platform.dto.manager.PlatformRolePageReq;
import com.kingthy.platform.dto.manager.PlatformRoleReq;
import com.kingthy.platform.entity.manager.PlatformRole;
import com.kingthy.platform.service.manager.PlatformRoleService;

/**
 * 
 *
 * PlatformRoleServiceTest(角色借口单元测试)
 * 
 * 陈钊 2017年5月18日 下午7:48:27
 * 
 * @version 1.0.0
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = KingthyPlatformApplication.class)
@WebAppConfiguration
@Rollback
@Transactional
public class PlatformRoleServiceTest
{
    @Autowired
    private PlatformRoleService platformRoleService;
    
    @Test
    public void findByPage()
    {
        PlatformRolePageReq platformRolePageReq = new PlatformRolePageReq();
        platformRolePageReq.setPageNum(1);
        platformRolePageReq.setPageSize(1);
        PageInfo<PlatformRole> page = platformRoleService.findByPage(platformRolePageReq);
        assertNotNull(page.getList());
    }
    
    @Test
    public void edit()
    {
        PlatformRoleReq platformRoleReq = new PlatformRoleReq();
        platformRoleReq.setDescription("管理");
        platformRoleReq.setRoleName("管理员");
        platformRoleReq.setUuid("123456");
        int result = platformRoleService.edit(platformRoleReq);
        assertSame(1, result);
    }
    
    @Test
    public void add()
    {
        PlatformRoleReq platformRoleReq = new PlatformRoleReq();
        platformRoleReq.setDescription("管理");
        platformRoleReq.setRoleName("管理员");
        int result = platformRoleService.add(platformRoleReq);
        assertSame(1, result);
    }
    
    @Test
    public void delete()
    {
        int result = platformRoleService.delete("123456");
        assertSame(1, result);
    }
    
    @Test
    public void findByUuid()
    {
        PlatformRole platformRole = platformRoleService.findByUuid("123456");
        assertNotNull(platformRole);
    }
    
    @Test
    public void findRolesByUserName()
    {
        List<PlatformRole> list = platformRoleService.findRolesByUserName("admin");
        assertNotNull(list);
    }
    
    @Test
    public void assignedMenu()
    {
        MenuAssignedReq menuAssignedReq = new MenuAssignedReq();
        String[] menuArray = {"clothStyle.shtml", "fabricList.shtml"};
        menuAssignedReq.setMenuArray(menuArray);
        menuAssignedReq.setRoleUuid("123456");
        int result = platformRoleService.assignedMenu(menuAssignedReq);
        assertNotSame(0, result);
    }
    
    @Test
    public void findMenuByRoleUuid()
    {
        List<String> list = platformRoleService.findMenuByRoleUuid("123456");
        assertNotNull(list);
    }
}
