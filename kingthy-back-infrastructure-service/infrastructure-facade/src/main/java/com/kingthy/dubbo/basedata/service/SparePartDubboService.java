package com.kingthy.dubbo.basedata.service;

import com.kingthy.base.BaseService;
import com.kingthy.entity.SparePart;

/**
 * @author zhaochen
 * @Description:
 * @DATE Created by 18:38 on 2017/9/13.
 * @Modified by:
 */
public interface SparePartDubboService extends BaseService<SparePart>
{
    /**
     * 更新
     * @param var
     * @return
     */
    int updateBySn(SparePart var);

    /**
     * 更新
     * @param var
     * @return
     */
    int deleteBySn(SparePart var);
}
