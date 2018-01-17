package com.kingthy.dubbo.basedata.service;

import com.kingthy.entity.BaseColourInfo;

import java.util.List;

/**
 * @author zhaochen
 * @Description:
 * @DATE Created by 13:52 on 2017/9/7.
 * @Modified by:
 */
public interface BaseColourInfoDubboService
{
    /**
     * 新增颜色信息
     * @param var
     * @return
     */
    int insert(BaseColourInfo var);

    /**
     * 批量添加颜色信息
     * @param var
     * @return
     */
    int insert(List<BaseColourInfo> var);

    /**
     * 根据code更新颜色信息
     * @param var
     * @return
     */
    int updateByCode(BaseColourInfo var);

    /**
     * 批量更新颜色信息
     * @param vars
     */
    void updates(List<BaseColourInfo> vars);

    /**
     * 根据Code删除颜色信息
     * @param var
     * @return
     */
    int deleteByCode(BaseColourInfo var);

    /**
     * 批量删除
     * @param vars
     */
    void deletes(List<BaseColourInfo> vars);

    /**
     * 根据编码查询数量
     * @param code
     * @return
     */
    int selectColourCountByCode(String code);
}
