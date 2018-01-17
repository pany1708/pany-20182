package com.kingthy.platform.service.basedata;

import com.github.pagehelper.PageInfo;
import com.kingthy.platform.entity.basedata.BaseData;

/**
 * BaseDataService(描述其作用)
 * <p>
 * 赵生辉 2017年07月05日 10:17
 *
 * @version 1.0.0
 */
public interface BaseDataService
{

    /**
     * 创建新的基础数据
     * @param baseData
     * @return
     */
    int createBaseData(BaseData baseData);

    /**
     * 查询基础数据啊接口
     * @param baseData
     * @return
     */
    PageInfo<BaseData> queryBaseData(int pageNum,int pageSize,BaseData baseData);

    /**
     * 删除基础数据
     * @param uuid
     * @return
     */
    int deleteBaseData(String uuid);

    /**
     * 修改基础数据信息
     * @param baseData
     * @return
     */
    int updateBaseData(BaseData baseData);

    /**s
     * 查询基础数据详情
     * @return
     */
    BaseData queryBaseDataInfo(String uuid);
}
