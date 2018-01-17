/**
 * 系统项目名称
 * com.kingthy.platform.controller.opus
 * OpusController.java
 * 
 * 2017年4月5日-下午1:50:59
 *  2017金昔科技有限公司-版权所有
 *
 */
package com.kingthy.platform.controller.opus;

import com.github.pagehelper.PageInfo;
import com.kingthy.platform.dto.opus.*;
import com.kingthy.platform.entity.opus.Opus;
import com.kingthy.platform.response.WebApiResponse;
import com.kingthy.platform.service.opus.OpusService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * OpusController
 * 
 * yuanml 2017年4月5日 下午1:50:59
 * 
 * @version 1.0.0
 *
 */
@RestController
@RequestMapping("opus")
public class OpusController
{
    @Autowired
    private OpusService opusService;
    
    private static final Logger LOG = LoggerFactory.getLogger(OpusController.class);

    @ApiOperation(value = "搜索作品", notes = "根据条件查询作品")
    @RequestMapping(value = "/findOpus", method = RequestMethod.POST)
    public WebApiResponse<?> findOpus(@RequestBody @ApiParam(name = "opusReq", value = "搜索作品") OpusSearchReq opusReq)
    {
        if (null != opusReq)
        {
            PageInfo<Opus> opusPageInfo;
            try
            {
                opusPageInfo = opusService.findOpus(opusReq);
            }
            catch (Exception e)
            {
                LOG.error("搜索作品出错，异常信息" + e);
                return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
            }
            if (null != opusPageInfo && opusPageInfo.getSize() > 0)
            {
                return WebApiResponse.success(opusPageInfo);
            }
            else
            {
                return WebApiResponse.error("无符合条件作品");
            }
        }
        else
        {
            return WebApiResponse.error(WebApiResponse.ResponseMsg.PARAMETER_ERROR.getValue());
        }
    }
    
    @ApiOperation(value = "新建作品", notes = "新建作品")
    @RequestMapping(value = "/createOpus", method = RequestMethod.PUT)
    public WebApiResponse<?> createOpus(
        @RequestBody @ApiParam(name = "opusInsertReq", value = "新建作品") OpusInsertReq opusInsertReq)
    {
        if (null != opusInsertReq && !opusInsertReq.getOpusName().isEmpty())
        {
            int result;
            try
            {
                result = opusService.create(opusInsertReq);
            }
            catch (Exception e)
            {
                LOG.error("新建作品出错，异常信息" + e);
                return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
            }
            if (result > 0)
            {
                return WebApiResponse.success(null, WebApiResponse.ResponseMsg.SUCCESS.getValue());
            }
            else
            {
                return WebApiResponse.error(WebApiResponse.ResponseMsg.FAIL.getValue());
            }
        }
        else
        {
            return WebApiResponse.error(WebApiResponse.ResponseMsg.PARAMETER_ERROR.getValue());
        }
    }
    
    @ApiOperation(value = "更新作品", notes = "更新作品")
    @RequestMapping(value = "/updateOpus", method = RequestMethod.POST)
    public WebApiResponse<?> updateOpus(
        @RequestBody @ApiParam(name = "opusInsertReq", value = "更新作品") OpusInsertReq opusInsertReq)
    {
        if (null != opusInsertReq && !opusInsertReq.getOpusName().isEmpty())
        {
            int result;
            try
            {
                result = opusService.updateOpus(opusInsertReq);
            }
            catch (Exception e)
            {
                LOG.error("更新作品出错，异常信息" + e);
                return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
            }
            if (result > 0)
            {
                return WebApiResponse.success(null, WebApiResponse.ResponseMsg.SUCCESS.getValue());
            }
            else
            {
                return WebApiResponse.error(WebApiResponse.ResponseMsg.FAIL.getValue());
            }
        }
        {
            return WebApiResponse.error(WebApiResponse.ResponseMsg.PARAMETER_ERROR.getValue());
        }
    }
    
    @ApiOperation(value = "显示/屏蔽作品", notes = "显示/屏蔽作品")
    @RequestMapping(value = "/isShowOpus/{opusUuid}/{isShow}", method = RequestMethod.POST)
    public WebApiResponse<?> isShowOpus(@PathVariable String opusUuid, @PathVariable Boolean isShow)
    {
        if (null != opusUuid && !opusUuid.isEmpty())
        {
            int result;
            try
            {
                result = opusService.isShowOpus(opusUuid, isShow);
            }
            catch (Exception e)
            {
                LOG.error("显示屏蔽作品出错，异常信息" + e);
                return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
            }
            if (result > 0)
            {
                return WebApiResponse.success(null, WebApiResponse.ResponseMsg.SUCCESS.getValue());
            }
            else
            {
                return WebApiResponse.error(WebApiResponse.ResponseMsg.FAIL.getValue());
            }
        }
        else
        {
            return WebApiResponse.error(WebApiResponse.ResponseMsg.PARAMETER_ERROR.getValue());
        }
    }
    
    @ApiOperation(value = "显示作品详情", notes = "显示作品详情")
    @RequestMapping(value = "/findOneOpus/{opusUuid}", method = RequestMethod.GET)
    public WebApiResponse<?> findOneOpus(@PathVariable String opusUuid)
    {
        if (null != opusUuid && !opusUuid.isEmpty())
        {
            OpusDto opusDto;
            try
            {
                opusDto = opusService.findOpusOne(opusUuid);
            }
            catch (Exception e)
            {
                LOG.error("显示作品详情出错，异常信息" + e);
                return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
            }
            if (null != opusDto)
            {
                return WebApiResponse.success(opusDto);
            }
            else
            {
                return WebApiResponse.error(WebApiResponse.ResponseMsg.FAIL.getValue());
            }
        }
        else
        {
            return WebApiResponse.error(WebApiResponse.ResponseMsg.PARAMETER_ERROR.getValue());
        }
    }
    
    /*@ApiOperation(value = "新增作品标签", notes = "新增作品标签")
    @RequestMapping(value = "/addOpusTag", method = RequestMethod.PUT)
    public WebApiResponse<?> addOpusTag(@RequestBody @ApiParam OpusRelationsReq opusRelationsReq)
    {
        if (null != opusRelationsReq)
        {
            int result;
            try
            {
                result = opusService.addOpusRelations(opusRelationsReq, "opus_tag");
            }
            catch (Exception e)
            {
                LOG.error("新增作品标签出错，异常信息" + e);
                return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
            }
            if (0 < result)
            {
                return WebApiResponse.success(null, WebApiResponse.ResponseMsg.SUCCESS.getValue());
            }
            else
            {
                return WebApiResponse.error(WebApiResponse.ResponseMsg.FAIL.getValue());
            }
        }
        else
        {
            return WebApiResponse.error(WebApiResponse.ResponseMsg.PARAMETER_ERROR.getValue());
        }
    }*/
    
    /*@ApiOperation(value = "删除作品标签", notes = "删除作品标签")
    @RequestMapping(value = "/deleteOpusTag", method = RequestMethod.DELETE)
    public WebApiResponse<?> deleteOpusTag(@RequestBody @ApiParam OpusRelationsReq opusRelationsReq)
    {
        if (null != opusRelationsReq)
        {
            int result;
            try
            {
                result = opusService.deleteOpusRelations(opusRelationsReq, "opus_tag");
            }
            catch (Exception e)
            {
                LOG.error("删除作品标签出错，异常信息" + e);
                return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
            }
            if (0 < result)
            {
                return WebApiResponse.success(null, WebApiResponse.ResponseMsg.SUCCESS.getValue());
            }
            else
            {
                return WebApiResponse.error(WebApiResponse.ResponseMsg.FAIL.getValue());
            }
        }
        else
        {
            return WebApiResponse.error(WebApiResponse.ResponseMsg.PARAMETER_ERROR.getValue());
        }
    }*/
    
    /*@ApiOperation(value = "查询作品标签", notes = "查询作品标签")
    @RequestMapping(value = "/findOpusTag/{opusUuid}", method = RequestMethod.GET)
    public WebApiResponse<?> findOpusTag(@PathVariable String opusUuid)
    {
        if (null != opusUuid && !opusUuid.isEmpty())
        {
            List<OpusRelationsDto> tags;
            try
            {
                tags = opusService.findOpusTag(opusUuid);
            }
            catch (Exception e)
            {
                LOG.error("查询作品标签出错，异常信息" + e);
                return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
            }
            if (0 <= tags.size())
            {
                return WebApiResponse.success(tags);
            }
            else
            {
                return WebApiResponse.error(WebApiResponse.ResponseMsg.FAIL.getValue());
            }
        }
        else
        {
            return WebApiResponse.error(WebApiResponse.ResponseMsg.PARAMETER_ERROR.getValue());
        }
    }*/
    
    /*@ApiOperation(value = "查询作品部件", notes = "查询作品部件")
    @RequestMapping(value = "/findOpusPart/{opusUuid}", method = RequestMethod.GET)
    public WebApiResponse<?> findOpusPart(@PathVariable String opusUuid)
    {
        if (null != opusUuid && !opusUuid.isEmpty())
        {
            List<OpusPartSubDto> opusPartSubDtos;
            try
            {
                opusPartSubDtos = opusService.findOpusPart(opusUuid);
            }
            catch (Exception e)
            {
                LOG.error("查询作品部件出错，异常信息" + e);
                return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
            }
            if (null != opusPartSubDtos && 0 <= opusPartSubDtos.size())
            {
                return WebApiResponse.success(opusPartSubDtos);
            }
            else
            {
                return WebApiResponse.error(WebApiResponse.ResponseMsg.FAIL.getValue());
            }
        }
        else
        {
            return WebApiResponse.error(WebApiResponse.ResponseMsg.PARAMETER_ERROR.getValue());
        }
    }*/
    
    /*@ApiOperation(value = "查询作品面料", notes = "查询作品面料")
    @RequestMapping(value = "/findOpusMaterial/{opusUuid}", method = RequestMethod.GET)
    public WebApiResponse<?> findOpusMaterial(@PathVariable String opusUuid)
    {
        if (null != opusUuid && !opusUuid.isEmpty())
        {
            List<OpusRelationsDto> opusMaterials;
            try
            {
                opusMaterials = opusService.findOpusRelations(opusUuid, "material");
            }
            catch (Exception e)
            {
                LOG.error("查询作品面料出错，异常信息" + e);
                return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
            }
            if (null != opusMaterials && 0 <= opusMaterials.size())
            {
                return WebApiResponse.success(opusMaterials);
            }
            else
            {
                return WebApiResponse.error(WebApiResponse.ResponseMsg.FAIL.getValue());
            }
        }
        else
        {
            return WebApiResponse.error(WebApiResponse.ResponseMsg.PARAMETER_ERROR.getValue());
        }
    }*/
    
    /*@ApiOperation(value = "查询作品辅料", notes = "查询作品辅料")
    @RequestMapping(value = "/findOpusAccessories/{opusUuid}", method = RequestMethod.GET)
    public WebApiResponse<?> findOpusAccessories(@PathVariable String opusUuid)
    {
        if (null != opusUuid && !opusUuid.isEmpty())
        {
            List<OpusRelationsDto> opusAccessories;
            try
            {
                opusAccessories = opusService.findOpusRelations(opusUuid, "accessories");
            }
            catch (Exception e)
            {
                LOG.error("查询作品辅料出错，异常信息" + e);
                return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
            }
            if (null != opusAccessories && 0 <= opusAccessories.size())
            {
                return WebApiResponse.success(opusAccessories);
            }
            else
            {
                return WebApiResponse.error(WebApiResponse.ResponseMsg.FAIL.getValue());
            }
        }
        else
        {
            return WebApiResponse.error(WebApiResponse.ResponseMsg.PARAMETER_ERROR.getValue());
        }
    }*/
    
    /*@ApiOperation(value = "新建作品面料", notes = "新建作品面料")
    @RequestMapping(value = "/addOpusMaterials", method = RequestMethod.PUT)
    public WebApiResponse<?> addOpusMaterials(@RequestBody @ApiParam() OpusRelationsReq opusRelationsReq)
    {
        if (null != opusRelationsReq)
        {
            int result;
            try
            {
                result = opusService.addOpusRelations(opusRelationsReq, "opus_material");
            }
            catch (Exception e)
            {
                LOG.error("新建作品面料出错，异常信息" + e);
                return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
            }
            if (0 < result)
            {
                return WebApiResponse.success(null, WebApiResponse.ResponseMsg.SUCCESS.getValue());
            }
            else
            {
                return WebApiResponse.error(WebApiResponse.ResponseMsg.FAIL.getValue());
            }
        }
        else
        {
            return WebApiResponse.error(WebApiResponse.ResponseMsg.PARAMETER_ERROR.getValue());
        }
    }*/
    
    /*@ApiOperation(value = "刪除作品面料", notes = "刪除作品面料")
    @RequestMapping(value = "/deleteOpusMaterials", method = RequestMethod.DELETE)
    public WebApiResponse<?> deleteOpusMaterials(@RequestBody @ApiParam() OpusRelationsReq opusRelationsReq)
    {
        if (null != opusRelationsReq)
        {
            int result;
            try
            {
                result = opusService.deleteOpusRelations(opusRelationsReq, "opus_material");
            }
            catch (Exception e)
            {
                LOG.error("删除作品面料出错，异常信息" + e);
                return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
            }
            if (0 < result)
            {
                return WebApiResponse.success(null, WebApiResponse.ResponseMsg.SUCCESS.getValue());
            }
            else
            {
                return WebApiResponse.error(WebApiResponse.ResponseMsg.FAIL.getValue());
            }
        }
        else
        {
            return WebApiResponse.error(WebApiResponse.ResponseMsg.PARAMETER_ERROR.getValue());
        }
    }*/
    
    /*@ApiOperation(value = "新建作品辅料", notes = "新建作品辅料")
    @RequestMapping(value = "/addOpusAccessories", method = RequestMethod.PUT)
    public WebApiResponse<?> addOpusAccessories(@RequestBody @ApiParam() OpusRelationsReq opusRelationsReq)
    {
        if (null != opusRelationsReq)
        {
            int result;
            try
            {
                result = opusService.addOpusRelations(opusRelationsReq, "opus_accessories");
            }
            catch (Exception e)
            {
                LOG.error("新建作品辅料出错，异常信息" + e);
                return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
            }
            if (0 < result)
            {
                return WebApiResponse.success(null, WebApiResponse.ResponseMsg.SUCCESS.getValue());
            }
            else
            {
                return WebApiResponse.error(WebApiResponse.ResponseMsg.FAIL.getValue());
            }
        }
        else
        {
            return WebApiResponse.error(WebApiResponse.ResponseMsg.PARAMETER_ERROR.getValue());
        }
    }*/
    
    /*@ApiOperation(value = "刪除作品辅料", notes = "刪除作品辅料")
    @RequestMapping(value = "/deleteOpusAccessories", method = RequestMethod.DELETE)
    public WebApiResponse<?> deleteOpusAccessories(@RequestBody @ApiParam() OpusRelationsReq opusRelationsReq)
    {
        if (null != opusRelationsReq)
        {
            int result;
            try
            {
                result = opusService.deleteOpusRelations(opusRelationsReq, "opus_accessories");
            }
            catch (Exception e)
            {
                LOG.error("删除作品辅料出错，异常信息" + e);
                return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
            }
            if (0 < result)
            {
                return WebApiResponse.success(null, WebApiResponse.ResponseMsg.SUCCESS.getValue());
            }
            else
            {
                return WebApiResponse.error(WebApiResponse.ResponseMsg.FAIL.getValue());
            }
        }
        else
        {
            return WebApiResponse.error(WebApiResponse.ResponseMsg.PARAMETER_ERROR.getValue());
        }
    }*/
    
    /*@ApiOperation(value = "新建作品部件", notes = "新建作品部件")
    @RequestMapping(value = "/addOpusPart", method = RequestMethod.PUT)
    public WebApiResponse<?> addOpusPart(@RequestBody @ApiParam() OpusRelationsReq opusRelationsReq)
    {
        if (null != opusRelationsReq)
        {
            int result;
            try
            {
                result = opusService.addOpusRelations(opusRelationsReq, "opus_part");
            }
            catch (Exception e)
            {
                LOG.error("新建作品部件出错，异常信息" + e);
                return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
            }
            if (0 < result)
            {
                return WebApiResponse.success(null, WebApiResponse.ResponseMsg.SUCCESS.getValue());
            }
            else
            {
                return WebApiResponse.error(WebApiResponse.ResponseMsg.FAIL.getValue());
            }
        }
        else
        {
            return WebApiResponse.error(WebApiResponse.ResponseMsg.PARAMETER_ERROR.getValue());
        }
    }*/
    
    /*@ApiOperation(value = "刪除作品部件", notes = "刪除作品部件")
    @RequestMapping(value = "/deleteOpusPart", method = RequestMethod.DELETE)
    public WebApiResponse<?> deleteOpusPart(@RequestBody @ApiParam() OpusRelationsReq opusRelationsReq)
    {
        if (null != opusRelationsReq)
        {
            int result;
            try
            {
                result = opusService.deleteOpusRelations(opusRelationsReq, "opus_part");
            }
            catch (Exception e)
            {
                LOG.error("删除作品部件出错，异常信息" + e);
                return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
            }
            if (0 < result)
            {
                return WebApiResponse.success(null, WebApiResponse.ResponseMsg.SUCCESS.getValue());
            }
            else
            {
                return WebApiResponse.error(WebApiResponse.ResponseMsg.FAIL.getValue());
            }
        }
        else
        {
            return WebApiResponse.error(WebApiResponse.ResponseMsg.PARAMETER_ERROR.getValue());
        }
    }*/
}
