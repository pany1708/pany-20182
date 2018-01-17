package com.kingthy.platform.service.impl.order;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingthy.exception.BizException;
import com.kingthy.exception.MaterialBizException;
import com.kingthy.platform.dto.material.MaterialDto;
import com.kingthy.platform.dto.order.QueryShopPageReq;
import com.kingthy.platform.entity.basedata.BaseData;
import com.kingthy.platform.entity.basedata.DressCategory;
import com.kingthy.platform.entity.basedata.MaterielCategory;
import com.kingthy.platform.entity.material.Material;
import com.kingthy.platform.entity.order.Area;
import com.kingthy.platform.entity.order.Shop;
import com.kingthy.platform.mapper.order.ShopMapper;
import com.kingthy.platform.service.order.ShopService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.joda.convert.ToString;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * ShopServiceimpl(描述其作用)
 * <p>
 * 赵生辉 2017年07月21日 9:54
 *
 * @version 1.0.0
 */
@Service("shopService")
public class ShopServiceimpl implements ShopService
{
    @Autowired
    private ShopMapper shopMapper;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Override
    public int create(Shop shop)
    {
        Example example = new Example(Shop.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("name", shop.getName());
        criteria.andEqualTo("delFlag", false);
        /* 判断是否已存在相同名称的数据 */
        if (shopMapper.selectCountByExample(example) > 0)
        {
            throw new BizException("已存在相同名的数据");
        }
        Date currentDate = new Date();
        shop.setCreateDate(currentDate);
        shop.setModifyDate(currentDate);
        shop.setCreator("Creator");
        shop.setModifier("Modifier");
        shop.setDelFlag(false);
        shop.setVersion(1);

        int result = shopMapper.insertSelective(shop);
        if(result == 0){
            throw new BizException("创建类型失败");
        }
        return result;
    }

    @Override
    public PageInfo<Shop> queryShopPage(QueryShopPageReq queryBaseDataReq)
    {
        Example example = new Example(Shop.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("delFlag",false);
        if(queryBaseDataReq.getStatus() != null)
        {
            criteria.andEqualTo("status",queryBaseDataReq.getStatus());
        }
        if(queryBaseDataReq.getName() != null)
        {
            criteria.andEqualTo("name",queryBaseDataReq.getName());
        }
        if(queryBaseDataReq.getCity()  != null)
        {
            criteria.andEqualTo("city",queryBaseDataReq.getCity());
        }
        if(queryBaseDataReq.getBeginDate() == null && queryBaseDataReq.getEndDate()!=null)
        {
            criteria.andBetween("createDate",queryBaseDataReq.getBeginDate(),queryBaseDataReq.getEndDate());
        }

        PageHelper.startPage(queryBaseDataReq.getPageNum(),queryBaseDataReq.getPageSize());
        List<Shop> result = shopMapper.selectByExample(example);

        if(result == null)
        {
            throw new BizException("没有找到指定的数据");
        }
        PageInfo<Shop> shopPageInfo = new PageInfo<>(result);
        return shopPageInfo;
    }

    @Override
    public Shop queryShopInfo(String uuid)
    {
        Example example = new Example(Material.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid", uuid);
        criteria.andEqualTo("delFlag",false);
        List<Shop> materials = shopMapper.selectByExample(example);
        if(materials != null || materials.size() > 0)
        {
            return materials.get(0);
        }
        else
        {
            throw new BizException("查询体验店失败");
        }
    }

    @Override
    public int update(Shop shop)
    {
        Example selectCountexample = new Example(Shop.class);
        Example.Criteria selectCountcriteria = selectCountexample.createCriteria();
        selectCountcriteria.andEqualTo("name",shop.getName());
        selectCountcriteria.andEqualTo("delFlag",false);
        int count = shopMapper.selectCountByExample(selectCountexample);
        if(count > 1)
        {
            throw new BizException("已存在同名的面料");
        }
        Example example = new Example(Material.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid",shop.getUuid());
        shop.setModifier(getUuid());
        shop.setModifyDate(new Date());
        return shopMapper.updateByExampleSelective(shop, example);
    }

    @Override
    public int delete(String uuid)
    {
        Example example = new Example(Shop.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid", uuid);
        Shop shop = shopMapper.selectByExample(example).get(0);
        shop.setDelFlag(true);
        shop.setModifier(getUuid());
        shop.setModifyDate(new Date());
        return shopMapper.updateByExample(shop, example);
    }

    /**
     * @desc 获取请求用户
     *
     * @author yuanml
     *
     * @return
     */
    private String getUuid()
    {
        String uuid = httpServletRequest.getHeader("uuid");
        if (null == uuid)
        {
            uuid = "";
        }
        return uuid;
    }
}
