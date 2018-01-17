package com.kingthy.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingthy.dto.OperateSalesPromotionDto;
import com.kingthy.dubbo.basedata.service.OperateSalesPromotionDubboService;
import com.kingthy.entity.OperateSalesPromotion;
import com.kingthy.mapper.OperateSalesPromotionMapper;
import com.kingthy.request.OperateSalesPromotionReq;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author chenz
 * @version 1.0.0
 * @class_name OperateSalesPromotionServiceImpl
 * @description 促销接口实现类
 * @create 2017/8/25
 */
@Service(version = "1.0.0",timeout = 3000)
public class OperateSalesPromotionServiceImpl implements OperateSalesPromotionDubboService {

    @Autowired
    private OperateSalesPromotionMapper operateSalesPromotionMapper;

    @Override
    public int insert(OperateSalesPromotion operateSalesPromotion) {
        operateSalesPromotion.setVersion(1);
        return operateSalesPromotionMapper.insert(operateSalesPromotion);
    }

    @Override
    public int updateByUuid(OperateSalesPromotion operateSalesPromotion) {
        OperateSalesPromotion operateSalesPromotionResult = selectByUuid(operateSalesPromotion.getUuid());
        Integer currentVersion = operateSalesPromotionResult.getVersion();
        Example example = new Example(OperateSalesPromotion.class);
        Example.Criteria  criteria = example.createCriteria();
        criteria.andEqualTo("uuid",operateSalesPromotion.getUuid());
        criteria.andEqualTo("version",currentVersion);
        operateSalesPromotion.setVersion(currentVersion+1);
        return operateSalesPromotionMapper.updateByExampleSelective(operateSalesPromotion,example);
    }

    @Override
    public List<OperateSalesPromotion> selectAll() {
        return null;
    }

    @Override
    public OperateSalesPromotion selectByUuid(String uuid) {
        return operateSalesPromotionMapper.selectByUuid(uuid);
    }

    @Override
    public int selectCount(OperateSalesPromotion operateSalesPromotion) {
        return 0;
    }

    @Override
    public List<OperateSalesPromotion> select(OperateSalesPromotion var1) {
        return null;
    }

    @Override
    public OperateSalesPromotion selectOne(OperateSalesPromotion var1) {
        return null;
    }

    @Override
    public PageInfo<OperateSalesPromotion> queryPage(Integer pageNum, Integer pageSize, OperateSalesPromotion operateSalesPromotion) {
        return null;
    }

    @Override
    public PageInfo<OperateSalesPromotionDto> queryPage(OperateSalesPromotionReq operateSalesPromotionReq) {
        PageHelper.startPage(operateSalesPromotionReq.getPageNum(),operateSalesPromotionReq.getPageSize());
        List<OperateSalesPromotionDto> result = operateSalesPromotionMapper.findByPage(operateSalesPromotionReq);
        return new PageInfo<>(result);
    }
}
