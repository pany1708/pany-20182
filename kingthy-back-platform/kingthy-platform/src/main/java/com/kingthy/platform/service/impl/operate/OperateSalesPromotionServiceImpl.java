package com.kingthy.platform.service.impl.operate;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingthy.platform.dto.operate.OperateSalesPromotionDto;
import com.kingthy.platform.dto.operate.OperateSalesPromotionReq;
import com.kingthy.platform.entity.operate.OperateSalesPromotion;
import com.kingthy.platform.mapper.operate.OperateSalesPromotionMapper;
import com.kingthy.platform.service.operate.OperateSalesPromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * @author chenz
 * @version 1.0.0
 * @class_name OperateSalesPromotionServiceImpl
 * @description 促销实现类
 * @create 2017/8/8
 */
@Service(value = "operateSalesPromotionService")
public class OperateSalesPromotionServiceImpl implements OperateSalesPromotionService {


    @Autowired
    private OperateSalesPromotionMapper operateSalesPromotionMapper;

    @Override
    public int insert(OperateSalesPromotionReq operateSalesPromotionReq) {
        OperateSalesPromotion operateSalesPromotion = new OperateSalesPromotion();
        operateSalesPromotion.setActivityName(operateSalesPromotionReq.getActivityName());
        operateSalesPromotion.setCreateDate(new Date());
        operateSalesPromotion.setCreator("User");
        operateSalesPromotion.setDelFlag(false);
        operateSalesPromotion.setDescription(operateSalesPromotionReq.getDescription());
        operateSalesPromotion.setEndTime(operateSalesPromotionReq.getEndTime());
        operateSalesPromotion.setJoinGoods(operateSalesPromotionReq.getJoinGoods());
        operateSalesPromotion.setPolicy(operateSalesPromotionReq.getPolicy());
        operateSalesPromotion.setPolicyType(operateSalesPromotionReq.getPolicyType().getValue());
        operateSalesPromotion.setStartTime(operateSalesPromotionReq.getStartTime());
        operateSalesPromotion.setStatus(operateSalesPromotionReq.getStatus().getValue());
        operateSalesPromotion.setVersion(1);
        return operateSalesPromotionMapper.insert(operateSalesPromotion);
    }

    @Override
    public int updateByUuid(OperateSalesPromotion operateSalesPromotion) {
        Example example = new Example(OperateSalesPromotion.class);
        Example.Criteria  criteria = example.createCriteria();
        criteria.andEqualTo("uuid",operateSalesPromotion.getUuid());
        return operateSalesPromotionMapper.updateByExampleSelective(operateSalesPromotion,example);
    }

    @Override
    public OperateSalesPromotion selectByUuid(String uuid) {
        Example example = new Example(OperateSalesPromotion.class);
        Example.Criteria  criteria = example.createCriteria();
        criteria.andEqualTo("uuid",uuid);
        List<OperateSalesPromotion> list = operateSalesPromotionMapper.selectByExample(example);
        if (list !=null && list.size()>0){
            return list.get(0);
        }
        return new OperateSalesPromotion();
    }

    @Override
    public PageInfo<OperateSalesPromotionDto> queryPage(OperateSalesPromotionReq operateSalesPromotionReq) {
        PageHelper.startPage(operateSalesPromotionReq.getPageNum(),operateSalesPromotionReq.getPageSize());
        List<OperateSalesPromotionDto> result = operateSalesPromotionMapper.findByPage(operateSalesPromotionReq);
        PageInfo<OperateSalesPromotionDto> pageInfo = new PageInfo<>(result);
        return pageInfo;
    }

    @Override
    public int deleteByUuid(String uuid) {
        Example example = new Example(OperateSalesPromotion.class);
        Example.Criteria  criteria = example.createCriteria();
        criteria.andEqualTo("uuid",uuid);
        OperateSalesPromotion operateSalesPromotion = new OperateSalesPromotion();
        operateSalesPromotion.setDelFlag(true);
        operateSalesPromotion.setUuid(uuid);
        return operateSalesPromotionMapper.updateByExampleSelective(operateSalesPromotion,example);
    }
}
