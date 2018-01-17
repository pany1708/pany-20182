package opus;

import com.kingthy.platform.KingthyPlatformApplication;
import com.kingthy.platform.dto.opus.OpusPartSubDto;
import com.kingthy.platform.service.opus.OpusPartSubService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * OpusPartsTest
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
public class OpusPartsSubTest
{
    @Autowired
    private OpusPartSubService opusPartSubService;

    @Test
    public void findPartSub(){
        List<String> listUuids =new ArrayList<>();
        listUuids.add("012345678");
        List<OpusPartSubDto> partsSub = opusPartSubService.findPartSub(listUuids);
        Assert.assertNotNull(partsSub);
    }

    @Test
    public void findPartSubMaterialAccessories(){
        String uuid = "012345678";
        Map<?, ?> result = opusPartSubService.findPartSubMaterialAccessories(uuid,"opus_partsub_material");
        Assert.assertNull(result);
        result = opusPartSubService.findPartSubMaterialAccessories(uuid,"opus_partsub_accessories");
        Assert.assertNull(result);
    }
}
