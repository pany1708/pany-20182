package com.kingthy.dubbo.income.service;

import com.kingthy.base.BaseService;
import com.kingthy.dto.IncomeDetailDTO;
import com.kingthy.entity.MemberIncomeDetail;

import java.util.List;

/**
 * @author xumin
 * @Description:
 * @DATE Created by 13:40 on 2017/8/7.
 * @Modified by:
 */
public interface MemberIncomeDetailDubboService extends BaseService<MemberIncomeDetail>
{
    List<IncomeDetailDTO> selectIncomeList(String membersUuid, Integer offset, Integer pageSize);

    int countIncomeDetailByOrderSn(String orderSn);
}
