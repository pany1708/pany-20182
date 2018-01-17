package com.kingthy.platform.service.impl.order;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingthy.exception.BizException;
import com.kingthy.platform.entity.order.ShippingMethod;
import com.kingthy.platform.mapper.order.ShippingMethodMapper;
import com.kingthy.platform.service.order.ShippingMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * 
 *
 * ShippingMethodServiceImpl
 * 
 * 赵生辉 2017年4月14日 上午9:52:58
 * 
 * @version 1.0.0
 *
 */
@Service(value = "shippingMethodService")
public class ShippingMethodServiceImpl implements ShippingMethodService
{
    /**
     * 初始版本号
     */
    private static final int DEFAULTVERSION = 1;

    @Autowired
    private transient ShippingMethodMapper shippingMethodMapper;

    @Autowired
    private HttpServletRequest httpServletRequest;

    private String getUuid()
    {
        String uuid = httpServletRequest.getHeader("uuid");
        if (null == uuid)
        {
            uuid = "";
        }
        return uuid;
    }

    @Override
    public int crate(ShippingMethod shippingMethod)
    {
        Date date = new Date();
        shippingMethod.setCreateDate(date);
        shippingMethod.setModifyDate(date);
        shippingMethod.setCreator(getUuid());
        shippingMethod.setModifier(getUuid());
        shippingMethod.setDelFlag(false);
        shippingMethod.setVersion(DEFAULTVERSION);
        int result = shippingMethodMapper.insertSelective(shippingMethod);
        if(result == 0)
        {
            throw new BizException("创建物流失败");
        }
        return result;
    }
    
    @Override
    public int update(ShippingMethod shippingMethod)
    {
        Example example = new Example(ShippingMethod.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid",shippingMethod.getUuid());
        int result = shippingMethodMapper.updateByExampleSelective(shippingMethod,example);
        if(result == 0)
        {
            throw new BizException("修改失败");
        }
        return result;
    }
    
    @Override
    public List<ShippingMethod> findAllShippingMethod()
    {
        Example example = new Example(ShippingMethod.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("delFlag", false);
        List<ShippingMethod> shippingMethodList = shippingMethodMapper.selectByExample(example);
        return shippingMethodList;
    }

    @Override
    public PageInfo<ShippingMethod> findAllShippingMethodPage(int pageNum, int pageSize, ShippingMethod shippingMethod)
    {
        Example example = new Example(ShippingMethod.class);
        Criteria criteria = example.createCriteria();
        if(shippingMethod.getName() != null)
        {
            criteria.andEqualTo("name",shippingMethod.getName());
        }
        PageHelper.startPage(pageNum,pageSize);
        List<ShippingMethod> shippingMethodList = shippingMethodMapper.selectByExample(example);
        PageInfo<ShippingMethod> shippingMethodPageInfo = new PageInfo<>(shippingMethodList);
        return shippingMethodPageInfo;
    }

}
