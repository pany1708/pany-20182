package com.kingthy.dubbo.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingthy.dto.BuyersShowDTO;
import com.kingthy.dto.BuyersShowListDTO;
import com.kingthy.dto.BuyersShowReviewDTO;
import com.kingthy.dubbo.review.service.BuyersShowDubboService;
import com.kingthy.entity.BuyersShow;
import com.kingthy.mapper.BuyersShowMapper;
import com.kingthy.request.BuyersShowReq;
import com.kingthy.request.ReviewReq;

import tk.mybatis.mapper.entity.Example;

/**
 * @author yuanml
 * @Description:
 * @DATE Created by 14:33 on 2017/8/2.
 * @Modified by:
 */
@Service(version = "1.0.0", timeout = 10000)
public class BuyersShowDubboServiceImpl implements BuyersShowDubboService
{
    @Autowired
    private BuyersShowMapper buyersShowMapper;
    
    @Override
    public int insert(BuyersShow buyersShow)
    {
        return buyersShowMapper.insert(buyersShow);
    }
    
    @Override
    public int updateByUuid(BuyersShow buyersShow)
    {
        Example example = new Example(BuyersShow.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid", buyersShow.getUuid());
        return buyersShowMapper.updateByExampleSelective(buyersShow, example);
    }
    
    /**
     * 未被调用，若调用需编写SQL查询
     * 
     * @return
     */
    @Override
    public List<BuyersShow> selectAll()
    {
        return buyersShowMapper.selectAll();
    }
    
    @Override
    public BuyersShow selectByUuid(String uuid)
    {
        BuyersShow cond=new BuyersShow();
        cond.setUuid(uuid);
        return buyersShowMapper.selectOne(cond);
    }
    
    /**
     * 未被调用，若调用需编写SQL查询
     * 
     * @param buyersShow
     * @return
     */
    @Override
    public int selectCount(BuyersShow buyersShow)
    {
        return buyersShowMapper.selectCount(buyersShow);
    }
    
    /**
     * 未被调用，若调用需编写SQL查询
     * 
     * @param buyersShow
     * @return
     */
    @Override
    public List<BuyersShow> select(BuyersShow buyersShow)
    {
        return buyersShowMapper.select(buyersShow);
    }
    
    /**
     * 未被调用，若调用需编写SQL查询
     * 
     * @param buyersShow
     * @return
     */
    @Override
    public BuyersShow selectOne(BuyersShow buyersShow)
    {
        return buyersShowMapper.selectOne(buyersShow);
    }
    
    @Override
    public PageInfo<BuyersShow> queryPage(Integer pageNum, Integer pageSize, BuyersShow buyersShow)
    {
        PageHelper.startPage(pageNum, pageSize);
        List<BuyersShow> buyersShowList = buyersShowMapper.select(buyersShow);
        return new PageInfo<>(buyersShowList);
    }
    
    @Override
    public List<BuyersShowDTO> selectBuyersShowByGoodsUuid(String goodsUuid)
    {
        return buyersShowMapper.selectBuyersShowByGoodsUuid(goodsUuid);
    }
    
    @Override
    public Long selectBuyerShowCountByGoodUuid(String goodsUuid)
    {
        return buyersShowMapper.selectBuyerShowCountByGoodUuid(goodsUuid);
    }
    
    /**
     * 分页查询商品的买家秀
     * @param req
     * @return
     */
    @Override
    public List<BuyersShowListDTO> selectBuyersShowList(BuyersShowReq req)
    {
        return buyersShowMapper.selectBuyersShowList(req);
    }
    
    @Override
    public PageInfo<BuyersShowReviewDTO> selectReviewList(ReviewReq req)
    {
        if (req.getPageSize() > 0)
        {
            PageHelper.startPage(req.getPageNum(), req.getPageSize());
        }
        List<BuyersShowReviewDTO> list = buyersShowMapper.selectReviewList(req);
        return new PageInfo<>(list);
    }
    
    @Override
    public int updateReview(Map map)
    {
        return buyersShowMapper.updateReview(map);
    }
    
    @Override
    public String insertReturnUuid(BuyersShow buyersShow)
    {
        int result = buyersShowMapper.insert(buyersShow);
        return result == 0 ? null : buyersShow.getUuid();
    }
    
    @Override
    public BuyersShowReviewDTO findBuyersShowReviewDTOByUuid(String uuid)
    {
        BuyersShowReviewDTO buyersShowReviewDTO = buyersShowMapper.findBuyersShowReviewDTOByUuid(uuid);
        if (buyersShowReviewDTO != null)
        {
            return buyersShowReviewDTO;
        }
        return new BuyersShowReviewDTO();
    }
}
