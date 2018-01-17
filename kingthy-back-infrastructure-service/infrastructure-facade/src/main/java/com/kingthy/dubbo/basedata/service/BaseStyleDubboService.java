package com.kingthy.dubbo.basedata.service;

import com.github.pagehelper.PageInfo;
import com.kingthy.base.BaseService;
import com.kingthy.dto.BaseStyleFileDto;
import com.kingthy.entity.BaseStyle;
import com.kingthy.request.BaseStylePageReq;

import java.util.List;

/**
 * @author chenz
 * @version 1.0.0
 * @class_name BaseStyleDubboService
 * @description 服装款式接口
 * @create 2017/9/7
 */
public interface BaseStyleDubboService extends BaseService<BaseStyle> {

    /**
     * 根据Sn更新款式信息
     * @param var
     * @return
     */
    int updateByStyleSn(BaseStyle var);

    /**
     * 根据Sn删除款式信息
     * @param var
     * @return
     */
    int deleteByStyleSn(BaseStyle var);

    /**
     * 全部查询
     * @return
     */
    List<BaseStyle> selectAll();

    /**
     * 根据uuid查询
     * @param uuid
     * @return
     */
    BaseStyle selectByUuid(String uuid);

    /**
     * 分页查询
     * @param baseStylePageReq
     * @return
     */
    PageInfo<BaseStyle> queryPage(BaseStylePageReq baseStylePageReq);

    /**
     * 查询文件
     * @return
     */
    List<BaseStyleFileDto> findFiles();
}

