package com.kingthy;

import com.kingthy.KingthyProviderUserApplication;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * BodyModelServiceTest(描述其作用)
 * <p>
 * 赵生辉 2017年06月22日 14:17
 *
 * @version 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = KingthyProviderUserApplication.class)
@WebAppConfiguration
@Rollback
@Transactional
public class BodyModelServiceTest
{

}
