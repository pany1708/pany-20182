package com.kingthy.mapper;

import com.kingthy.dto.IncomeDetailDTO;
import com.kingthy.entity.MemberIncomeDetail;
import com.kingthy.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author  xumin
 * @Description:
 * @DATE Created by 11:53 on 2017/8/7.
 * @Modified by:
 */
public interface MemberIncomeDetailMapper extends MyMapper<MemberIncomeDetail>
{

    List<IncomeDetailDTO> selectIncomeList(@Param("membersUuid") String membersUuid, @Param("offset") Integer offset, @Param("pageSize") Integer pageSize);

    int countIncomeDetailByOrderSn(String orderSn);
}
