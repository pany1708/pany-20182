package com.kingthy.platform.service.member;

import com.github.pagehelper.PageInfo;
import com.kingthy.platform.dto.member.IntegralReq;
import com.kingthy.platform.entity.member.Integral;

/**
 * 
 *
 * IntegralService(积分设置业务层接口)
 * 
 * 陈钊 2017年4月18日 上午9:50:32
 * 
 * @version 1.0.0
 *
 */
public interface IntegralService
{
    /**
     * 
     * findByPage(分页查询积分设置信息)
     * 
     * @param PageNum
     * @param pageSize
     * @return <b>创建人：</b>陈钊<br/>
     *         PageInfo<Integral>
     * @exception @since 1.0.0
     */
    PageInfo<Integral> findByPage(int pageNum, int pageSize);
    
    /**
     * 
     * addIntegral(添加积分设置)
     * 
     * @param integralReq
     * @return <b>创建人：</b>陈钊<br/>
     *         int
     * @exception @since 1.0.0
     */
    int addIntegral(IntegralReq integralReq);
    
    /**
     * 
     * editIntegral(修改积分设置)
     * 
     * @param integralReq
     * @return <b>创建人：</b>陈钊<br/>
     *         int
     * @exception @since 1.0.0
     */
    int editIntegral(IntegralReq integralReq);
    
    /**
     * 
     * deleteIntegral(删除积分设置)
     * 
     * @param uuid
     * @return <b>创建人：</b>陈钊<br/>
     *         int
     * @exception @since 1.0.0
     */
    int deleteIntegral(String uuid);
    
    /**
     * 
     * findByUuid(根据uuid查询详细信息)
     * 
     * @param uuid
     * @return <b>创建人：</b>陈钊<br/>
     *         Integral
     * @exception @since 1.0.0
     */
    Integral findByUuid(String uuid);
}
