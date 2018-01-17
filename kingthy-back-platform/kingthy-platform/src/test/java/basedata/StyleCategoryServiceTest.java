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
import com.kingthy.platform.entity.basedata.StyleCategory;
import com.kingthy.platform.service.basedata.StyleCategoryService;

/**
 * 
 *
 * StyleCategoryServiceTest()
 * 
 * 陈钊 2017年5月19日 下午2:11:42
 * 
 * @version 1.0.0
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = KingthyPlatformApplication.class)
@WebAppConfiguration
@Rollback
@Transactional
public class StyleCategoryServiceTest
{
    @Autowired
    private StyleCategoryService styleCategoryService;
    
}
