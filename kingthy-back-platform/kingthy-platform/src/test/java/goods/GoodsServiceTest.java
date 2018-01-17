package goods;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

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
import com.kingthy.platform.dto.goods.DelGoodsPageReq;
import com.kingthy.platform.dto.goods.GoodsDto;
import com.kingthy.platform.dto.goods.GoodsPageDto;
import com.kingthy.platform.dto.goods.GoodsPageReq;
import com.kingthy.platform.dto.goods.GoodsReq;
import com.kingthy.platform.dto.goods.GoodsReq.GoodsOperation;
import com.kingthy.platform.dto.goods.GoodsReq.GoodsPutOnMethod;
import com.kingthy.platform.dto.goods.GoodsUuidArrayReq;
import com.kingthy.platform.service.goods.GoodsService;

/**
 * 
 *
 * GoodsServiceTest(商品接口单元测试)
 * 
 * 陈钊 2017年5月22日 上午9:23:26
 * 
 * @version 1.0.0
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = KingthyPlatformApplication.class)
@WebAppConfiguration
@Rollback
@Transactional
public class GoodsServiceTest
{
    @Autowired
    private GoodsService goodsService;
    
    @Test
    public void findByPage()
    {
        GoodsPageReq goodsPageReq = new GoodsPageReq();
        goodsPageReq.setPageNum(1);
        goodsPageReq.setPageSize(1);
        PageInfo<GoodsPageDto> page = goodsService.findByPage(goodsPageReq);
        assertNotNull(page.getList());
    }
    
    @Test
    public void changeDelFlagBatch()
    {
        String[] uuidArray = {"97100764777807937", "97100764777807939"};
        int result = goodsService.changeDelFlagBatch(uuidArray, false);
        assertSame(2, result);
    }
    
    @Test
    public void add()
    {
        GoodsReq goodsAddParam = new GoodsReq();
        goodsAddParam.setAccessoriesInfo("test");
        goodsAddParam.setDesinger("admin");
        goodsAddParam.setGoodsCategoryUuid("123456");
        goodsAddParam.setGoodsDetails("test");
        goodsAddParam.setGoodsFeature("test");
        goodsAddParam.setGoodsName("test");
        goodsAddParam.setGoodsOperation(GoodsOperation.down);
        goodsAddParam.setGoodsPutOnMethod(GoodsPutOnMethod.immediately);
        goodsAddParam.setOpusUuid("123456");
        String uuid = goodsService.add(goodsAddParam);
        assertNotNull(uuid);
    }
    
    @Test
    public void edit()
    {
        GoodsReq goodsEditParam = new GoodsReq();
        goodsEditParam.setAccessoriesInfo("test");
        goodsEditParam.setDesinger("admin");
        goodsEditParam.setGoodsCategoryUuid("123456");
        goodsEditParam.setGoodsDetails("test");
        goodsEditParam.setGoodsFeature("test");
        goodsEditParam.setGoodsName("test");
        goodsEditParam.setGoodsOperation(GoodsOperation.down);
        goodsEditParam.setGoodsPutOnMethod(GoodsPutOnMethod.immediately);
        goodsEditParam.setOpusUuid("123456");
        goodsEditParam.setUuid("97100764777807937");
        int result = goodsService.edit(goodsEditParam);
        assertSame(1, result);
    }
    
    @Test
    public void findDelByPage()
    {
        DelGoodsPageReq delGoodsPageReq = new DelGoodsPageReq();
        delGoodsPageReq.setPageNum(1);
        delGoodsPageReq.setPageSize(1);
        PageInfo<GoodsPageDto> page = goodsService.findDelByPage(delGoodsPageReq);
        assertNotNull(page.getList());
    }
    
    @Test
    public void delete()
    {
        String[] uuidArray = {"97100764777807937", "97100764777807939"};
        int result = goodsService.delete(uuidArray);
        assertSame(2, result);
    }
    
    @SuppressWarnings("static-access")
    @Test
    public void upOrDownBatch()
    {
        GoodsUuidArrayReq goodsUuidArrayReq = new GoodsUuidArrayReq();
        String[] uuidArray = {"97100764777807937", "97100764777807939"};
        int result = goodsService.upOrDownBatch(uuidArray, goodsUuidArrayReq.getGoodsOperation().down);
        assertSame(2, result);
    }
    
    @Test
    public void findGoodsByUuid()
    {
        GoodsDto goodsDto = goodsService.findGoodsByUuid("97100764777807937");
        assertNotNull(goodsDto);
    }
}
