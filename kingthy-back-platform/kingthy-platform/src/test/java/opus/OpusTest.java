package opus;

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
import com.kingthy.platform.dto.opus.OpusDto;
import com.kingthy.platform.dto.opus.OpusInsertReq;
import com.kingthy.platform.dto.opus.OpusSearchReq;
import com.kingthy.platform.service.opus.OpusService;

/**
 * OpusTest
 * <p>
 * yuanml
 * 2017/5/22
 *
 * @version 1.0.0
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = KingthyPlatformApplication.class,properties = "eureka.client.enabled=false")
@Rollback
@Transactional
public class OpusTest
{
    @Autowired
    private OpusService opusService;

    @Test
    public void createOpus(){
        OpusInsertReq opusInsertReq= new OpusInsertReq();
        opusInsertReq.setIsShow(true);
        opusInsertReq.setMemberUuid("testUuid");
        opusInsertReq.setOpusDetails("details");
        opusInsertReq.setOpusName("test");
        opusInsertReq.setOpusImage("http://localhost:/test.jpg");
        opusInsertReq.setOpusCategoryUuid("opusCategoryUuid");
        opusInsertReq.setOpusCategoryUuid("opusCategoryUuid");
        opusInsertReq.setOpusSeasonUuid("opusSeasonUuid");
        opusInsertReq.setRemark("remark");
        int result = opusService.create(opusInsertReq);
        Assert.assertEquals(1,result);
    }

    /*@Test
    public void updateOpus(){
        OpusSearchReq searchReq = new OpusSearchReq();
        searchReq.setPageNum(1);
        searchReq.setPageSize(1);
        PageInfo<OpusDto> resultLists =
            opusService.findOpus(searchReq);
        Assert.assertNotNull(resultLists);
        if(resultLists.getTotal() > 0){
            System.out.println("test the method  'findOpusOne'..");
            String uuid = resultLists.getList().get(0).getUuid();
            OpusDto opusDto = opusService.findOpusOne(uuid);
            Assert.assertNotNull(opusDto);
            System.out.println("test the method  'updateOpus'..");
            OpusInsertReq opusParam = new OpusInsertReq();
            opusParam.setUuid(uuid);
            opusParam.setOpusImage("htp://localhost:/test.jpg");
            int result = opusService.updateOpus(opusParam);
            Assert.assertEquals(1,result);
        }
    }*/

}
