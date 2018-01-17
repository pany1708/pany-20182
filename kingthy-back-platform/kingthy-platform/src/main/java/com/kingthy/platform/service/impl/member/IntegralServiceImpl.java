package com.kingthy.platform.service.impl.member;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingthy.platform.dto.member.IntegralReq;
import com.kingthy.platform.entity.member.Integral;
import com.kingthy.platform.mapper.member.IntegralMapper;
import com.kingthy.platform.service.member.IntegralService;
import org.springframework.beans.BeanUtils;
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
 * IntegralServiceImpl(积分设置业务层实现类)
 * 
 * 陈钊 2017年4月18日 上午9:53:40
 * 
 * @version 1.0.0
 *
 */
@Service(value = "integralService")
public class IntegralServiceImpl implements IntegralService
{
    /**
     * 版本号
     */
    private static final Integer version = 1;
    
    @Autowired
    private HttpServletRequest request;
    
    @Autowired
    private IntegralMapper integralMapper;
    
    /**
     * 
     * getCurrentUser(得到当前操作用户的uuid)
     * 
     * @return <b>创建人：</b>陈钊<br/>
     *         String
     * @exception @since 1.0.0
     */
    private String getCurrentUser()
    {
        String currentUser = request.getHeader("uuid");
        if (currentUser == null)
        {
            return "";
        }
        return currentUser;
    }
    
    @Override
    public PageInfo<Integral> findByPage(int pageNum, int pageSize)
    {
        PageHelper.startPage(pageNum, pageSize);
        Example example = new Example(Integral.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("delFlag", false);
        List<Integral> integralList = integralMapper.selectByExample(example);
        PageInfo<Integral> pageInfo = new PageInfo<Integral>(integralList);
        return pageInfo;
    }
    
    @Override
    public int addIntegral(IntegralReq integralReq)
    {
        Integral integral = new Integral();
        BeanUtils.copyProperties(integralReq, integral);
        integral.setCreateDate(new Date());
        integral.setCreator(getCurrentUser());
        integral.setVersion(version);
        integral.setDelFlag(false);
        int result = integralMapper.insert(integral);
        return result;
    }
    
    @Override
    public int editIntegral(IntegralReq integralReq)
    {
        int result = 0;
        Example example = new Example(Integral.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid", integralReq.getUuid());
        List<Integral> integralList = integralMapper.selectByExample(example);
        if (integralList != null && integralList.size() > 0)
        {
            Integral oldIntegral = integralList.get(0);
            oldIntegral.setScore(integralReq.getScore());
            oldIntegral.setSource(integralReq.getSource());
            oldIntegral.setDescription(integralReq.getDescription());
            oldIntegral.setModifier(getCurrentUser());
            oldIntegral.setModifyDate(new Date());
            result = integralMapper.updateByExample(oldIntegral, example);
        }
        return result;
    }
    
    @Override
    public int deleteIntegral(String uuid)
    {
        int result = 0;
        Example example = new Example(Integral.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid", uuid);
        List<Integral> integralList = integralMapper.selectByExample(example);
        if (integralList != null && integralList.size() > 0)
        {
            Integral oldIntegral = integralList.get(0);
            oldIntegral.setModifier(getCurrentUser());
            oldIntegral.setModifyDate(new Date());
            oldIntegral.setDelFlag(true);
            result = integralMapper.updateByExample(oldIntegral, example);
        }
        return result;
    }
    
    @Override
    public Integral findByUuid(String uuid)
    {
        Example example = new Example(Integral.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid", uuid);
        List<Integral> integralList = integralMapper.selectByExample(example);
        if (integralList != null && integralList.size() > 0)
        {
            return integralList.get(0);
        }
        return null;
    }
    
}
