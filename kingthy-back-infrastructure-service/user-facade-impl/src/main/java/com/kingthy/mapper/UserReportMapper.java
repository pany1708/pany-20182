package com.kingthy.mapper;


import com.kingthy.dto.AgeBuyDataDto;
import com.kingthy.dto.EverydayRegisterNumDto;
import com.kingthy.dto.ReportUserUuidByAgeDto;
import com.kingthy.request.UserDataReq;

import java.util.List;

/**
 *
 *
 * UserReportMapper(统计报表映射接口)
 *
 * @author 陈钊
 * @date 2017年7月24日 上午9:49:34
 *
 * @version 1.0.0
 *
 */
public interface UserReportMapper {

    /**
     *
     * findRegisterUserNum(根据时间段查询全站注册用户数量)
     *
     * @param userDataReq
     * @return <b>创建人：</b>陈钊<br/>
     *         int
     * @exception @since 1.0.0
     */
    int findRegisterUserNum(UserDataReq userDataReq);

    /**
     *
     * lastWeekNewUserNum(过去一周每日注册用户数)
     * @param userDataReq
     * @return <b>创建人：</b>陈钊<br/>
     *         List<EverydayRegisterNumDto>
     * @exception @since 1.0.0
     */
    List<EverydayRegisterNumDto> lastWeekNewUserNum(UserDataReq userDataReq);


    /**
     * 不同年龄分段购买情况
     * @return List<AgeBuyDataDto>
     */
    List<AgeBuyDataDto> findBuyDataByAge();

    /**
     * 根据年龄段查询用户uuid
     * @return
     */
    List<ReportUserUuidByAgeDto> findUuidByAge();
}
