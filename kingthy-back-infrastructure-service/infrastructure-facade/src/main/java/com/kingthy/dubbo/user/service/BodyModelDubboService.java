package com.kingthy.dubbo.user.service;

import com.kingthy.entity.BodyModel;

import java.util.List;

/**
*
* 人体模型[业务逻辑处理接口]
*
* @author likejie 2017-4-21
*
* @version 1.0.0
*
*/
public interface BodyModelDubboService {

   /**
    * 根据条件获取列表
    * @param bodyModel
    * @return
    */
    List<BodyModel> getBodyModelList(BodyModel bodyModel);

    /**
     * 创建一个人体模型
     * @param bodyModel
     * @return
     */
    int createBodyModel(BodyModel bodyModel);

    /**
     * 删除人体模型
     * @param uuid
     * @return
     */
    int deleteBodyModel(String uuid);

    /**
     * 修改人体模型详情
     * @param bodyModel
     * @return
     */
    int updateBodyModel(BodyModel bodyModel);




}
