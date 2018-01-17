package com.kingthy.dubbo.basedata.service;

import com.kingthy.base.BaseService;
import com.kingthy.entity.MeasureUnit;

/**
 * @author  xumin
 * @Description:
 * @DATE Created by 9:32 on 2017/9/27.
 * @Modified by:
 */
public interface MeasureUnitDubboService  extends BaseService<MeasureUnit>
{
    /**
     * 更新
     * @param var
     * @return
     */
    int updateByCode(MeasureUnit var);

    /**
     * 删除
     * @param var
     * @return
     */
    int deleteByCode(MeasureUnit var);
}
