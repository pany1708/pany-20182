package com.kingthy.platform.controller.order;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kingthy.platform.dto.order.AfterSaleServiceReq;
import com.kingthy.platform.dto.order.ProducingInfoDto;
import com.kingthy.platform.response.WebApiResponse;
import com.kingthy.platform.service.order.AfterSaleServiceService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @AUTHORS xumin
 * @Description:售后管理
 * @DATE Created by 15:19 on 2017/7/11.
 * @Modified by:
 */
@RestController
@RequestMapping("afterSale")
public class AfterSaleServiceController {

    private static final Logger LOG = LoggerFactory.getLogger(AfterSaleServiceController.class);

    @Autowired
    private AfterSaleServiceService afterSaleServiceService;

    @ApiOperation(value = "售后列表", notes = "售后管理")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public WebApiResponse<?> afterSaleList(@RequestBody @ApiParam(name = "AfterSaleServiceReq", value = "查询条件", required = true) AfterSaleServiceReq req){

        WebApiResponse<?> result = null;

        try {

            result = afterSaleServiceService.afterSaleList(req);

        }catch (Exception e){
            LOG.error("/afterSale/list 发票列表出错,异常信息" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }

        return result;
    }

    @ApiOperation(value = "修改售后状态", notes = "售后管理")
    @RequestMapping(value = "/edit/status", method = RequestMethod.PUT)
    public WebApiResponse<?> editStatus(@RequestBody AfterSaleServiceReq.EditStatusReq req){

        WebApiResponse<?> result = null;

        try {

            result = afterSaleServiceService.editStatus(req);

        }catch (Exception e){
            LOG.error("/afterSale/edit/status 修改售后状态出错,异常信息" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }

        return result;
    }

    @ApiOperation(value = "审核不通过", notes = "售后管理")
    @RequestMapping(value = "/reject", method = RequestMethod.PUT)
    public WebApiResponse<?> rejectAuditing(@RequestBody AfterSaleServiceReq.RejectReq req){

        WebApiResponse<?> result = null;

        try {

            result = afterSaleServiceService.rejectAuditing(req);

        }catch (Exception e){
            LOG.error("/afterSale/reject/{memberUuid} 审核不通过出错,异常信息" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }

        return result;
    }

    @ApiOperation(value = "查看评价", notes = "售后管理")
    @RequestMapping(value = "/comment/{uuid}", method = RequestMethod.GET)
    public WebApiResponse<?> queryComment(@PathVariable @ApiParam(name = "uuid", value = "查询条件", required = true) String uuid){

        WebApiResponse<?> result = null;

        try {

            result = afterSaleServiceService.queryComment(uuid);

        }catch (Exception e){
            LOG.error("/afterSale/comment/{uuid} 查看评价出错,异常信息" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }

        return result;
    }

    /**
     * 查看生产进度
     * @param orderSn
     * @return
     */
    @ApiOperation(value = "查看生产进度", notes = "售后管理")
    @RequestMapping(value = "/producing/{orderSn}", method = {RequestMethod.GET, RequestMethod.POST})
    public WebApiResponse<?> queryProducingInfo(@PathVariable @ApiParam(name = "orderSn", value = "查询条件") String orderSn){

        WebApiResponse<?> result = null;

        try {

            ProducingInfoDto producingDto = new ProducingInfoDto();
            producingDto.setProducingDate(new Date());
            producingDto.setMemo("物料已出库");

            ProducingInfoDto producingDto1 = new ProducingInfoDto();
            producingDto.setProducingDate(new Date());
            producingDto.setMemo("裁剪");

            ProducingInfoDto producingDto2 = new ProducingInfoDto();
            producingDto.setProducingDate(new Date());
            producingDto.setMemo("特殊工艺");

            ProducingInfoDto producingDto3 = new ProducingInfoDto();
            producingDto.setProducingDate(new Date());
            producingDto.setMemo("车缝");

            ProducingInfoDto producingDto4 = new ProducingInfoDto();
            producingDto.setProducingDate(new Date());
            producingDto.setMemo("熨烫,质检");

            List<ProducingInfoDto> list = new ArrayList<>();

            list.add(producingDto);
            list.add(producingDto1);
            list.add(producingDto2);
            list.add(producingDto3);
            list.add(producingDto4);

            result = WebApiResponse.success(list);

            //调用工厂提供接口
            //...


        }catch (Exception e){
            LOG.error("/afterSale/producing/{orderSn} 查看生产进度出错,异常信息" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }

        return result;
    }

    @ApiOperation(value = "查看物流进度", notes = "售后管理")
    @RequestMapping(value = "/shipping/{orderSn}", method = RequestMethod.GET)
    public WebApiResponse<?> queryShippingInfo(@PathVariable @ApiParam(name = "orderSn", value = "查询条件") String orderSn){

        WebApiResponse<?> result = null;

        try{

            //调用sdk查询物流进度

            OkHttpClient client = new OkHttpClient();
            //测试key 只能调用100次
            Request request = new Request.Builder()
                    .url("http://v.juhe.cn/exp/index?com=sf&no=114750081239&dtype=&key=84503fc457189696a8b112191f8c7154")
                    .get()
                    .build();

            Response response = client.newCall(request).execute();

            Gson gson = new Gson();

            Map<String, Object> map = gson.fromJson(response.body().string(), new TypeToken<Map<String, Object>>(){}.getType());

            result = WebApiResponse.success(map.get("result"));

//            result = WebApiResponse.success(map);

        }catch (Exception e){
            LOG.error("/afterSale/shipping/{orderSn} 查看生产进度出错,异常信息" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }

        return result;
    }
}
