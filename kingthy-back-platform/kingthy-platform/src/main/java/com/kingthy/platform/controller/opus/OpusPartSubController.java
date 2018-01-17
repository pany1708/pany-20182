/**
 * 系统项目名称
 * com.kingthy.platform.controller.opus
 * OpusPartSub.java
 * 
 * 2017年4月7日-下午3:45:22
 *  2017金昔科技有限公司-版权所有
 *
 */
package com.kingthy.platform.controller.opus;

import com.kingthy.platform.dto.opus.OpusPartSubDto;
import com.kingthy.platform.response.WebApiResponse;
import com.kingthy.platform.service.opus.OpusPartSubService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 *
 * OpusPartSub
 * 
 * yuanml 2017年4月7日 下午3:45:22
 * 
 * @version 1.0.0
 *
 */
@RestController
@RequestMapping("partSub")
public class OpusPartSubController
{
    @Autowired
    private OpusPartSubService opusPartSubService;
    
    private static final Logger LOG = LoggerFactory.getLogger(OpusPartSubController.class);
    
    @ApiOperation(value = "查找部件", notes = "根据部件业务uuid得到部件")
    @RequestMapping(value = "/getPartSub", method = RequestMethod.POST)
    public WebApiResponse<?> getPartSub(
        @RequestBody @ApiParam(name = "partSubUuids", value = "根据部件业务uuid得到部件") List<String> partSubUuids)
    {
        if (partSubUuids.size() > 0)
        {
            List<OpusPartSubDto> opusPartSubs;
            try
            {
                opusPartSubs = opusPartSubService.findPartSub(partSubUuids);
            }
            catch (Exception e)
            {
                LOG.error("查找部件出错，异常信息" + e);
                return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
            }
            return WebApiResponse.success(opusPartSubs);
        }
        else
        {
            return WebApiResponse.error(WebApiResponse.ResponseMsg.PARAMETER_ERROR.getValue());
        }
    }
    
    @ApiOperation(value = "查找部件面料", notes = "根据部件uuid得到部件面料")
    @RequestMapping(value = "/getPartSubMaterial/{partSubUuid}", method = RequestMethod.GET)
    public WebApiResponse<?> getPartSubMaterial(@PathVariable String partSubUuid)
    {
        if (null != partSubUuid && !partSubUuid.isEmpty())
        {
            Map<?, ?> opusPartSubMaterials;
            try
            {
                opusPartSubMaterials =
                    opusPartSubService.findPartSubMaterialAccessories(partSubUuid, "opus_partsub_material");
            }
            catch (Exception e)
            {
                LOG.error("查找部件面料出错，异常信息" + e);
                return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
            }
            return WebApiResponse.success(opusPartSubMaterials);
        }
        else
        {
            return WebApiResponse.error(WebApiResponse.ResponseMsg.PARAMETER_ERROR.getValue());
        }
    }
    
    @ApiOperation(value = "查找部件辅料", notes = "根据部件uuid得到部件辅料")
    @RequestMapping(value = "/getPartSubAccessories/{partSubUuid}", method = RequestMethod.GET)
    public WebApiResponse<?> getPartSubAccessories(@PathVariable String partSubUuid)
    {
        if (null != partSubUuid && !partSubUuid.isEmpty())
        {
            Map<?, ?> opusPartSubAccessoriess;
            try
            {
                opusPartSubAccessoriess =
                    opusPartSubService.findPartSubMaterialAccessories(partSubUuid, "opus_partsub_accessories");
            }
            catch (Exception e)
            {
                LOG.error("查找部件辅料出错，异常信息" + e);
                return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
            }
            return WebApiResponse.success(opusPartSubAccessoriess);
        }
        else
        {
            return WebApiResponse.error(WebApiResponse.ResponseMsg.PARAMETER_ERROR.getValue());
        }
    }
}
