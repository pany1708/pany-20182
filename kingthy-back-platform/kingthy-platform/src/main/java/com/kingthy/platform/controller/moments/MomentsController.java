package com.kingthy.platform.controller.moments;

import com.github.pagehelper.PageInfo;
import com.kingthy.platform.dto.moments.MomentDto;
import com.kingthy.platform.dto.moments.QueryMomentPageReq;
import com.kingthy.platform.dto.moments.UpdateMomentReq;
import com.kingthy.platform.entity.moments.Moments;
import com.kingthy.platform.response.WebApiResponse;
import com.kingthy.platform.service.moments.MomentService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * MomentsController(描述其作用)
 * <p>
 * 赵生辉 2017年08月07日 17:27
 *
 * @version 1.0.0
 */
@RestController
@RequestMapping(value = "moments")
public class MomentsController
{
    @Autowired
    private MomentService momentService;

    private static final Logger LOG = LoggerFactory.getLogger(MomentsController.class);

    @ApiOperation(value = "新增消息", notes = "消息管理")
    @PostMapping("/queryPage")
    public WebApiResponse<?> queryMomentPage(@RequestBody QueryMomentPageReq queryMomentPageReq)
    {
        /*String memberUuid = redisManager.get(queryMomentPageReq.getToken());
        queryMomentPageReq.setMemberUuid(memberUuid);*/
        PageInfo<Moments> result;
        try
        {
            result = momentService.queryMomentPage(queryMomentPageReq);
        }
        catch (Exception e)
        {
            LOG.error("/moment/queryPage:" + e);
            return WebApiResponse.error(e.getMessage());
        }
        return WebApiResponse.success(0);
    }

    @ApiOperation(value = "新增消息", notes = "消息管理")
    @PostMapping("/queryinfo")
    public WebApiResponse<?> queryMomentInfo(@PathVariable (value="uuid") String uuid)
    {
        /*String memberUuid = redisManager.get(queryMomentPageReq.getToken());
        queryMomentPageReq.setMemberUuid(memberUuid);*/
        Moments result;
        try
        {
            result = momentService.queryInfo(uuid);
        }
        catch (Exception e)
        {
            LOG.error("/moment/queryinfo:" + e);
            return WebApiResponse.error(e.getMessage());
        }
        return WebApiResponse.success(0);
    }

    @ApiOperation(value = "修改动态", notes = "消息管理")
    @PostMapping("/update")
    public WebApiResponse<?> updateMoment(@RequestBody UpdateMomentReq updateMomentReq)
    {
        /*String memberUuid = redisManager.get(queryMomentPageReq.getToken());
        queryMomentPageReq.setMemberUuid(memberUuid);*/
        Integer result;
        try
        {
            result = momentService.update(updateMomentReq);
        }
        catch (Exception e)
        {
            LOG.error("/moment/queryinfo:" + e);
            return WebApiResponse.error(e.getMessage());
        }
        return WebApiResponse.success(0);
    }

}
