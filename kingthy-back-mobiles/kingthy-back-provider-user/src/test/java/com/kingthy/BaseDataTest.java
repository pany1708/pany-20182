package com.kingthy;
import com.kingthy.KingthyProviderUserApplication;
import com.kingthy.entity.BaseData;
import com.kingthy.service.BaseDataService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * BaseDataTest(描述其作用)
 * <p>
 * 赵生辉 2017年05月11日 14:37
 *
 * @version 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = KingthyProviderUserApplication.class)
public class BaseDataTest
{

    @Autowired
    private BaseDataService baseDataService;

    @Test
    public void queryBaseData(){
        BaseData baseData = new BaseData();
        List<BaseData> result = baseDataService.queryBaseData(baseData);
        System.out.println("查询结果为："+result.toString());
    }

    @Test
    public void queryBaseDataByParentId(){
        BaseData baseData = new BaseData();
        baseData.setParentId("97100764777809443");
        List<BaseData> result = baseDataService.queryBaseData(baseData);
        System.out.println("查询结果为："+result.toString());
    }

    @Test
    public void queryBaseDataByGrade(){
        BaseData baseData = new BaseData();
        baseData.setGrade(1);
        List<BaseData> result = baseDataService.queryBaseData(baseData);
        System.out.println("查询结果为："+result.toString());
    }

    @Test
    public void queryBaseDataByType(){
        BaseData baseData = new BaseData();
        baseData.setType(0);
        List<BaseData> result = baseDataService.queryBaseData(baseData);
        System.out.println("查询结果为："+result.toString());
    }

}

