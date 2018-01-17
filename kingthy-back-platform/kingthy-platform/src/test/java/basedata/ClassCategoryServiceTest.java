package basedata;

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

import com.kingthy.platform.KingthyPlatformApplication;
import com.kingthy.platform.dto.basedata.CategoryReq;
import com.kingthy.platform.dto.basedata.CategoryTreeDto;
import com.kingthy.platform.dto.basedata.TransferCategoryReq;
import com.kingthy.platform.entity.basedata.ClassCategory;
import com.kingthy.platform.service.basedata.ClassCategoryService;

/**
 * 
 *
 * ClassCategoryServiceTest(品类分类接口单元测试)
 * 
 * 陈钊 2017年5月19日 上午10:18:18
 * 
 * @version 1.0.0
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = KingthyPlatformApplication.class)
@WebAppConfiguration
@Rollback
@Transactional
public class ClassCategoryServiceTest
{
    @Autowired
    private ClassCategoryService classCategoryService;
    
    @Test
    public void findAllTopNodes()
    {
        List<ClassCategory> list = classCategoryService.findAllTopNodes();
        assertNotNull(list);
        assertNotSame(0, list.size());
    }
    
    @Test
    public void findAllChildNodes()
    {
        List<ClassCategory> list = classCategoryService.findAllChildNodes("0");
        assertNotSame(0, list.size());
    }
    
    @Test
    public void addNode()
    {
        CategoryReq categoryReq = new CategoryReq();
        categoryReq.setClassName("xxx");
        categoryReq.setDescription("xxx");
        categoryReq.setParentGrade("-1");
        categoryReq.setParentId("0");
        categoryReq.setStatus(true);
        int result = classCategoryService.addNode(categoryReq);
        assertSame(1, result);
    }
    
    @Test
    public void delete()
    {
        int result = classCategoryService.delete("97130857868820526");
        assertSame(1, result);
    }
    
    @Test
    public void findAllChildNodesNum()
    {
        int result = classCategoryService.findAllChildNodesNum("0");
        assertNotSame(0, result);
    }
    
    @Test
    public void transfer()
    {
        TransferCategoryReq transferCategoryReq = new TransferCategoryReq();
        transferCategoryReq.setSourceGrade(0);
        transferCategoryReq.setSourceUuid("97071061572518316");
        transferCategoryReq.setTargetGrade(0);
        transferCategoryReq.setTargetUuid("97071061572518319");
        int result = classCategoryService.transfer(transferCategoryReq);
        assertNotSame(0, result);
    }
    
    @Test
    public void findClassCategoryByUuid()
    {
        ClassCategory classCategory = classCategoryService.findClassCategoryByUuid("97071061572518319");
        assertNotNull(classCategory);
    }
    
    @Test
    public void edit()
    {
        CategoryReq categoryReq = new CategoryReq();
        categoryReq.setClassName("xxx");
        categoryReq.setDescription("xzz");
        categoryReq.setParentGrade("-1");
        categoryReq.setParentId("0");
        categoryReq.setStatus(true);
        categoryReq.setUuid("97071061572518319");
        int result = classCategoryService.edit(categoryReq);
        assertSame(1, result);
    }
    
    @Test
    public void editStatus()
    {
        int result = classCategoryService.editStatus("97071061572518319", true);
        assertNotSame(0, result);
    }
    
    @Test
    public void findAll()
    {
        List<ClassCategory> list = classCategoryService.findAll();
        assertNotNull(list);
    }
    
    @Test
    public void getTree()
    {
        List<CategoryTreeDto> list = classCategoryService.getTree();
        assertNotNull(list);
        assertNotSame(0, list.size());
    }
    
    @Test
    public void findClassCategoryName()
    {
        boolean result = classCategoryService.findClassCategoryName("外套");
        assertSame(true, result);
    }
    
}
