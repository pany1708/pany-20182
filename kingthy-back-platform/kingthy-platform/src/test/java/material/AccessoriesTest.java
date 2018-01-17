package material;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.kingthy.platform.KingthyPlatformApplication;
import com.kingthy.platform.dto.material.AccessoriesDto;
import com.kingthy.platform.dto.material.AddUpdateAccessoriesReq;
import com.kingthy.platform.dto.material.FindPage;
import com.kingthy.platform.service.material.AccessoriesService;

/**
 * AccessoriesTest
 * <p>
 * yuanml
 * 2017/5/24
 *
 * @version 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = KingthyPlatformApplication.class, properties = "eureka.client.enabled=false")
@Rollback
@Transactional
public class AccessoriesTest
{
    @Autowired
    private AccessoriesService accessoriesService;

    /*@Test
    public void testCreate()
    {
        AddUpdateAccessoriesReq addUpdateAccessoriesReq = new AddUpdateAccessoriesReq();
        addUpdateAccessoriesReq.setCode("test001");
        addUpdateAccessoriesReq.setColor("AAFF00");
        addUpdateAccessoriesReq.setIsshrink(false);
        addUpdateAccessoriesReq.setFaxnum("0076");
        addUpdateAccessoriesReq.setImage("http://test-image.jpg");
        addUpdateAccessoriesReq.setSpecification("specification");
        addUpdateAccessoriesReq.setTexture("texture");
        addUpdateAccessoriesReq.setLinkman("yiming");
        addUpdateAccessoriesReq.setLinkphone("13888888888");
        addUpdateAccessoriesReq.setLinktel("0755666666");
        addUpdateAccessoriesReq.setName("test-");
        addUpdateAccessoriesReq.setRemark("test-remark");
        addUpdateAccessoriesReq.setStatus(0);
        addUpdateAccessoriesReq.setSupplier("supplier");
        addUpdateAccessoriesReq.setMaterielUuid("123456");
        int result = accessoriesService.create(addUpdateAccessoriesReq);
        System.out.println("---------------------test method 'create'-------------------");
        Assert.assertEquals(1,result);
    }

    @Test
    public void testBatch()
    {
        FindPage findPage = new FindPage();
        findPage.setPageSize(1);
        findPage.setPageNum(5);
        PageInfo<AccessoriesDto> list =
            accessoriesService.findAccessoriesPage(findPage);
        Assert.assertNotNull(list);
        if(list.getList().size()>0){
            String uuid = list.getList().get(0).getUuid();
            AccessoriesDto accessoriesDto = accessoriesService.findAccessories(uuid);
            System.out.println("---------------------test method 'findAccessories'--------------");
            Assert.assertEquals(accessoriesDto.getUuid(),uuid);
            AddUpdateAccessoriesReq addUpdateAccessoriesReq = new AddUpdateAccessoriesReq();
            BeanUtils.copyProperties(list.getList().get(0),addUpdateAccessoriesReq);
            addUpdateAccessoriesReq.setUuid(uuid);
            addUpdateAccessoriesReq.setName("test-12345");
            int result = accessoriesService.updateAccessories(addUpdateAccessoriesReq);
            System.out.println("----------------------test method 'updateAccessories'-----------");
            Assert.assertEquals(1,result);
            result = accessoriesService.deleteAccessories(uuid);
            System.out.println("----------------------test method 'deleteAccessories'------------");
            Assert.assertEquals(1,result);
        }
    }*/
}
