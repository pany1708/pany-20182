package com.kingthy.dubbo.user.service;



import com.kingthy.dto.EverydayRegisterNumDto;
import com.kingthy.dto.ReportUserUuidByAgeDto;
import com.kingthy.request.UserDataReq;
import java.util.ArrayList;


/**
 * 用户数据统计接口
 * @author   陈钊
 * @date  2017年7月19日 上午9:50:16
 * @version 1.0.0
 */
public interface UserReportDubboService {

    /**
     * findRegisterUserNum(根据时间段查询全站注册用户数量)
     *
     * @param userDataReq
     * @return <b>创建人：</b>陈钊<br/>
     * int
     * @throws @since 1.0.0
     */
    int findRegisterUserNum(UserDataReq userDataReq);


    /**
     * lastWeekNewUserNum(过去一周每日注册用户数)
     *
     * @param userDataReq
     * @return <b>创建人：</b>陈钊<br/>
     * ArrayList<EverydayRegisterNumDto>
     * @throws @since 1.0.0
     */
    ArrayList<EverydayRegisterNumDto> lastWeekNewUserNum(UserDataReq userDataReq);

    /**
     * 根据年龄段查询用户uuid
     * @return
     */
    ArrayList<ReportUserUuidByAgeDto> findUuidByAge();

}
