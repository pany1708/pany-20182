package basedata;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.kingthy.platform.KingthyPlatformApplication;
import com.kingthy.platform.dto.basedata.MaterielSeasonReq;
import com.kingthy.platform.entity.basedata.MaterielCategory;
import com.kingthy.platform.service.basedata.MaterielCategoryService;

/**
 * MaterielCategoryTest
 * <p>
 * yuanml 2017/5/17
 *
 * @version 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = KingthyPlatformApplication.class, properties = {"eureka.client.enabled=false"})
@Rollback
@Transactional
public class MaterielCategoryTest
{
    @Autowired
    private MaterielCategoryService materielCategoryService;
    
   /* @Test
    public void createTest()
    {
        MaterielSeasonReq materielSeasonReq = new MaterielSeasonReq();
        materielSeasonReq.setClassName("junitTest");
        materielSeasonReq.setDescription("junitTest");
        materielSeasonReq.setParentGrade("-1");
        materielSeasonReq.setStatus(true);
        materielSeasonReq.setParentId("-1");
        int result = materielCategoryService.createMaterielCategory(materielSeasonReq);
        Assert.assertEquals(1, result);
    }
    
    @Test
    public void updateDeleteTest()
    {
        MaterielSeasonReq materielSeasonReq = new MaterielSeasonReq();
        List<MaterielCategory> allMaterielcategoryTop = materielCategoryService.findAllMaterielcategoryTop();
        Assert.assertNotNull(allMaterielcategoryTop);
        materielSeasonReq.setUuid(allMaterielcategoryTop.get(0).getUuid());
        Assert.assertNotNull(materielSeasonReq.getUuid());
        MaterielCategory materielCategory =
            materielCategoryService.findMaterielCategoryByUuid(materielSeasonReq.getUuid());
        Assert.assertNotNull(materielCategory);
        BeanUtils.copyProperties(materielCategory, materielSeasonReq);
        materielSeasonReq.setClassName("JunitTest");
        int result = materielCategoryService.updateMaterielCategoryByUuid(materielSeasonReq);
        Assert.assertEquals(1, result);
        result = materielCategoryService.updateMaterielCategoryStatusByUuid(materielSeasonReq.getUuid(), "1");
        Assert.assertNotEquals(0, result);
        List<MaterielCategory> lists =
            materielCategoryService.findMaterielCategoryByParentId(materielSeasonReq.getUuid());
        Assert.assertNotNull(lists);
        if (lists.size() == 0)
        {
            result = materielCategoryService.deleteMaterielCategory(materielSeasonReq.getUuid());
            Assert.assertEquals(1, result);
        }
    }*/
    
}
