package com.kingthy.platform.service.impl.order;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingthy.platform.dto.order.AfterSaleServiceDto;
import com.kingthy.platform.dto.order.AfterSaleServiceReq;
import com.kingthy.platform.entity.order.AfterSaleSchedule;
import com.kingthy.platform.entity.order.AfterSaleService;
import com.kingthy.platform.mapper.goods.BuyersShowMapper;
import com.kingthy.platform.mapper.order.AfterSaleScheduleMapper;
import com.kingthy.platform.mapper.order.AfterSaleServiceMapper;
import com.kingthy.platform.response.WebApiResponse;
import com.kingthy.platform.service.order.AfterSaleServiceService;
import com.kingthy.platform.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @AUTHORS xumin
 * @Description:
 * @DATE Created by 15:27 on 2017/7/11.
 * @Modified by:
 */
@Service
public class AfterSaleServiceServiceImpl implements AfterSaleServiceService {

    @Autowired
    private AfterSaleServiceMapper afterSaleServiceMapper;

    @Autowired
    private BuyersShowMapper buyersShowMapper;

    @Autowired
    private AfterSaleScheduleMapper afterSaleScheduleMapper;

    /**
     * 售后列表
     * @param req
     * @return
     * @throws Exception
     */
    @Override
    public WebApiResponse<?> afterSaleList(AfterSaleServiceReq req) throws Exception {

        PageHelper.startPage(req.getPageNum(), req.getPageSize());

        req.setEndDate(DateUtil.putEndTime(req.getEndDate()));

        List<AfterSaleServiceDto> list = afterSaleServiceMapper.selectSaleServiceList(req);


        /**
         * 调用工厂提供的接口 查询商品是否生成完成
         *
         * 生产完成 则可以发送, 未完成则可以查看生产进度
         */
        list.parallelStream().forEach(dto -> {
            if (dto.getStatus().intValue() == 3){

                //等待工厂提供接口...

                Random random = new Random();
                dto.setProducingFlag(random.nextBoolean());
            }
        });

        PageInfo<AfterSaleServiceDto> pageInfo = new PageInfo<>(list);

        return WebApiResponse.success(pageInfo);
    }

    /**
     * 修改售后状态
     * @param req
     * @return
     * @throws Exception
     */
    @Override
    public WebApiResponse<?> editStatus(AfterSaleServiceReq.EditStatusReq req) throws Exception {

        AfterSaleService afterSaleService = new AfterSaleService();

        afterSaleService.setModifier(req.getMemberUuid());
        afterSaleService.setModifyDate(new Date());
        afterSaleService.setUuid(req.getUuid());
        afterSaleService.setStatus(req.getStatus());
        afterSaleService.setShippingNumber(req.getShippingNumber());
        afterSaleService.setDelFlag(req.getDelFlag());
        afterSaleService.setRefundsAddrUuid(req.getRefundsAddrUuid());

        int result = afterSaleServiceMapper.updateStatus(afterSaleService);

        if(result > 0){

            AfterSaleService af = afterSaleServiceMapper.selectServiceInfoByUuid(req.getUuid());

            String memo = "";
            AfterSaleSchedule afterSaleSchedule = new AfterSaleSchedule();
            afterSaleSchedule.setGoodsUuid(af.getGoodsUuid());

            afterSaleSchedule.setOrderSn(af.getOrderSn());
            afterSaleSchedule.setSaleServiceUuid(req.getUuid());
            afterSaleSchedule.setStatus(req.getStatus());
            afterSaleSchedule.setCreateDate(new Date());
            afterSaleSchedule.setCreator(req.getMemberUuid());
            afterSaleSchedule.setDelFlag(false);
            afterSaleSchedule.setVersion(0);

            //记录售后进度 售后状态 0:发起售后 1:-审核通过 2:厂家确认收货,待生产 3:生产中 4:已包装发货，等待收货 5:已收货 6:已评价
            switch (req.getStatus()){
                case 1:
                    memo = "审核通过";

                    break;
                case 2:

                    memo = "厂家确认收货,待生产";
                    break;
                case 3:

                    memo = "生产中";
                    break;
                case 4:

                    memo = "已包装发货，等待收货";
                    break;
                case 5:

                    memo = "已收货";
                    break;
                case 6:

                    memo = "已评价";
                    break;
            }

            afterSaleSchedule.setMemo(memo);
            afterSaleScheduleMapper.insert(afterSaleSchedule);
        }

        return  result > 0 ? WebApiResponse.success(WebApiResponse.ResponseMsg.SUCCESS.getValue())
                : WebApiResponse.error(WebApiResponse.ResponseMsg.FAIL.getValue());
    }

    /**
     * 审核不通过
     * @param req
     * @return
     * @throws Exception
     */
    @Override
    public WebApiResponse<?> rejectAuditing(AfterSaleServiceReq.RejectReq req) throws Exception {

        int result = afterSaleServiceMapper.updateRejectAuditing(req);

        if (result > 0 ){

            AfterSaleService af = afterSaleServiceMapper.selectServiceInfoByUuid(req.getUuid());

            AfterSaleSchedule afterSaleSchedule = new AfterSaleSchedule();
            afterSaleSchedule.setGoodsUuid(af.getGoodsUuid());

            afterSaleSchedule.setOrderSn(af.getOrderSn());
            afterSaleSchedule.setSaleServiceUuid(req.getUuid());
            afterSaleSchedule.setStatus(1);
            afterSaleSchedule.setCreateDate(new Date());
            afterSaleSchedule.setCreator(req.getMemberUuid());
            afterSaleSchedule.setDelFlag(false);
            afterSaleSchedule.setVersion(0);
            afterSaleSchedule.setMemo("审核不通过");
            afterSaleScheduleMapper.insert(afterSaleSchedule);
        }

        return result > 0 ? WebApiResponse.success(WebApiResponse.ResponseMsg.SUCCESS.getValue())
                : WebApiResponse.error(WebApiResponse.ResponseMsg.FAIL.getValue());
    }

    /**
     * 查看评论
     * @param uuid
     * @return
     * @throws Exception
     */
    @Override
    public WebApiResponse<?> queryComment(String uuid) throws Exception {

        return WebApiResponse.success(buyersShowMapper.selectCommentByUuid(uuid));

    }

}
