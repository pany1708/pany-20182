package com.kingthy.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingthy.dubbo.goods.service.GoodsFittingDubboService;
import com.kingthy.entity.GoodsFitting;
import com.kingthy.mapper.GoodsFittingMapper;
import com.kingthy.request.FittingRequest;
import com.kingthy.response.FittingResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author  xumin
 * @Description:
 * @DATE Created by 19:38 on 2017/8/15.
 * @Modified by:
 */
@Service(version = "1.0.0", timeout = 10000)
public class GoodsFittingDubboServiceImpl  implements GoodsFittingDubboService {

    private static final Logger LOG= LoggerFactory.getLogger(GoodsFittingDubboServiceImpl.class);
    @Autowired
    private GoodsFittingMapper goodsFittingMapper;

    @Override
    public int insert(GoodsFitting var) {
        try {
            return goodsFittingMapper.insert(var);
        }catch (Exception ex){
            LOG.error(ex.toString());
            throw ex;
        }
    }

    @Override
    public int updateByUuid(GoodsFitting var) {
        try {
            Example example = new Example(GoodsFitting.class);
            example.createCriteria().andEqualTo("uuid", var.getUuid());
            return goodsFittingMapper.updateByExampleSelective(var, example);
        }catch (Exception ex){
            LOG.error(ex.toString());
            throw ex;
        }
    }

    /**
     * 未被调用，若调用需编写SQL查询
     * 
     * @return
     */
    @Override
    public List<GoodsFitting> selectAll() {
        try {
            return goodsFittingMapper.selectAll();
        }catch (Exception ex){
            LOG.error(ex.toString());
            throw ex;
        }
    }

    /**
     * 未被调用，若调用需编写SQL查询
     * 
     * @param uuid
     * @return
     */
    @Override
    public GoodsFitting selectByUuid(String uuid) {
        try {
            GoodsFitting var = new GoodsFitting();
            var.setUuid(uuid);
            return goodsFittingMapper.selectOne(var);
        }catch (Exception ex){
            LOG.error(ex.toString());
            throw ex;
        }
    }

    /**
     * 未被调用，若调用需编写SQL查询
     * 
     * @param var
     * @return
     */
    @Override
    public int selectCount(GoodsFitting var) {
        try {
            return goodsFittingMapper.selectCount(var);
        }catch (Exception ex){
            LOG.error(ex.toString());
            throw ex;
        }
    }

    /**
     * 未被调用，若调用需编写SQL查询
     * 
     * @param var1
     * @return
     */
    @Override
    public List<GoodsFitting> select(GoodsFitting var1) {
        try {
            return goodsFittingMapper.select(var1);
        }catch (Exception ex){
            LOG.error(ex.toString());
            throw ex;
        }
    }

    /**
     * 未被调用，若调用需编写SQL查询
     * 
     * @param var1
     * @return
     */
    @Override
    public GoodsFitting selectOne(GoodsFitting var1) {
        try {
            return goodsFittingMapper.selectOne(var1);
        }catch (Exception ex){
            LOG.error(ex.toString());
            throw ex;
        }
    }

    /**
     * 未被调用，若调用需编写SQL查询
     * 
     * @param pageNum
     * @param pageSize
     * @param var
     * @return
     */
    @Override
    public PageInfo<GoodsFitting> queryPage(Integer pageNum, Integer pageSize, GoodsFitting var) {
        try {
            PageHelper.startPage(pageNum, pageSize);
            return new PageInfo<>(goodsFittingMapper.select(var));
        }catch (Exception ex){
            LOG.error(ex.toString());
            throw ex;
        }
    }

    @Override
    public Long selectFittingCountByGoodsUuid(String goodsUuid) {
        try {
            return goodsFittingMapper.selectFittingCountByGoodsUuid(goodsUuid);
        }catch (Exception ex){
            LOG.error(ex.toString());
            throw ex;
        }
    }

    @Override
    public List<FittingResp> selectGoodsUuidList(FittingRequest.Query query) {
        return goodsFittingMapper.selectGoodsUuidList(query);
    }

    @Override
    public String selectGoodsFittingUuid(FittingRequest var) {
        return goodsFittingMapper.selectGoodsFittingUuid(var);
    }
}
