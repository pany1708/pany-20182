package com.kingthy.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingthy.dto.AfterSaleServiceDetailDto;
import com.kingthy.dubbo.order.service.AfterSaleServiceDubboService;
import com.kingthy.entity.AfterSaleSchedule;
import com.kingthy.entity.AfterSaleService;
import com.kingthy.mapper.AfterSaleScheduleMapper;
import com.kingthy.mapper.AfterSaleServiceMapper;
import com.kingthy.request.AfterSaleServiceReq;
import com.kingthy.response.AfterSaleServiceResp;
import com.kingthy.service.AfterSaleTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * @author xumin
 * @Description:
 * @DATE Created by 18:18 on 2017/8/4.
 * @Modified by:
 */

@Service(version = "1.0.0", timeout = 10000)
public class AfterSaleDubboServiceImpl implements AfterSaleServiceDubboService
{

    @Autowired
    private AfterSaleServiceMapper afterSaleServiceMapper;

    @Autowired
    private AfterSaleScheduleMapper afterSaleScheduleMapper;

    @Autowired
    private AfterSaleTransaction afterSaleTransaction;

    @Override
    public int insert(AfterSaleService saleService) {
        return afterSaleServiceMapper.insert(saleService);
    }

    @Override
    public int updateByUuid(AfterSaleService var) {

        Example example = new Example(AfterSaleService.class);
        example.createCriteria().andEqualTo("uuid", var.getUuid());
        return afterSaleServiceMapper.updateByExampleSelective(var, example);
    }
    @Override
    public List<AfterSaleService> selectAll() {
        return afterSaleServiceMapper.selectAll();
    }
    @Override
    public AfterSaleService selectByUuid(String uuid) {
        AfterSaleService var1 = new AfterSaleService();
        var1.setUuid(uuid);
        return selectOne(var1);
    }
    @Override
    public int selectCount(AfterSaleService var) {
        return afterSaleServiceMapper.selectCount(var);
    }
    @Override
    public List<AfterSaleService> select(AfterSaleService var1) {
        return afterSaleServiceMapper.select(var1);
    }
    @Override
    public AfterSaleService selectOne(AfterSaleService var1) {
        return afterSaleServiceMapper.selectOne(var1);
    }
    @Override
    public PageInfo<AfterSaleService> queryPage(Integer pageNum, Integer pageSize, AfterSaleService var) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(select(var));
    }
    @Override
    public int updateAfterSaleServiceByOrderSn(AfterSaleService var) {
        return afterSaleServiceMapper.updateAfterSaleServiceByOrderSn(var);
    }
    @Override
    public List<AfterSaleServiceResp> selectSaleServiceList(int offset, int pageSize, String memberUuid) {
        return afterSaleServiceMapper.selectSaleServiceList(offset, pageSize, memberUuid);
    }
    @Override
    public PageInfo<AfterSaleServiceDetailDto> pageingQueryAfterSaleService(AfterSaleServiceReq var) {
        PageHelper.startPage(var.getPageNum(), var.getPageSize());
        List<AfterSaleServiceDetailDto>  list =afterSaleServiceMapper.selectListByConditon(var);
        return new PageInfo<>(list);
    }
    @Override
    public int updateStatus(AfterSaleServiceReq.EditStatusReq req) {
       return  afterSaleTransaction.updateStatus(req);
    }
    @Override
    public int rejectAuditing(AfterSaleServiceReq.RejectReq req) {
       return afterSaleTransaction.rejectAuditing(req);
    }

}
