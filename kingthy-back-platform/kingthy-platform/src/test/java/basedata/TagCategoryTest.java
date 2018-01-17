package basedata;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.kingthy.platform.KingthyPlatformApplication;
import com.kingthy.platform.entity.basedata.TagCategory;
import com.kingthy.platform.service.basedata.TagCategoryService;

/**
 * TagCategoryTest
 * <p>
 * yuanml 2017/5/27
 *
 * @version 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = KingthyPlatformApplication.class, properties = "eureka.client.enabled=false")
@Rollback
@Transactional
public class TagCategoryTest
{
    @Autowired
    private TagCategoryService tagCategoryService;
    
    @Test
    public void testCreate()
    {
        TagCategory tagCategory = new TagCategory();
        tagCategory.setClassName("tagTest");
        tagCategory.setDescription("testDescription");
        tagCategory.setGrade(-1);
        tagCategory.setStatus(true);
        tagCategory.setParentId("0");
        tagCategory.setDelFlag(false);
        int result = tagCategoryService.create(tagCategory);
        Assert.assertEquals(1, result);
    }
    
    @Test
    public void testUpdate()
    {
        TagCategory tagCategory = new TagCategory();
        PageInfo<TagCategory> list = tagCategoryService.findAllTagCategoryPage(1, 5, tagCategory);
        System.out.println("--------------------test method 'findAllTagCategoryPage'--------------------");
        Assert.assertNotNull(list);
        if (list.getTotal() > 0)
        {
            String uuid = list.getList().get(0).getUuid();
            tagCategory.setUuid(uuid);
            tagCategory = tagCategoryService.findAllTagCategory(tagCategory).get(0);
            System.out.println("--------------------test method 'findAllTagCategory'--------------------");
            Assert.assertEquals(uuid, tagCategory.getUuid());
            boolean flag = tagCategoryService.findTagCategoryName(tagCategory.getClassName());
            System.out.println("--------------------test method 'findTagCategoryName'--------------------");
            Assert.assertEquals(true, flag);
            List<TagCategory> listTags = tagCategoryService.findTags(tagCategory.getClassName().substring(0, 1));
            System.out.println("--------------------test method 'findTags'--------------------");
            Assert.assertNotEquals(0, listTags.size());
            int result = tagCategoryService.updateShowOrHide(uuid,true);
            System.out.println("--------------------test method 'updateShowOrHide'--------------------");
            Assert.assertEquals(1,result);
            tagCategory.setClassName("testNameUpdate");
            result = tagCategoryService.updateSelective(tagCategory);
            System.out.println("--------------------test method 'updateSelective'--------------------");
            Assert.assertEquals(1,result);
            result = tagCategoryService.deleteByUuid(uuid);
            System.out.println("--------------------test method 'deleteByUuid'--------------------");
            Assert.assertEquals(1,result);


        }
    }
}
