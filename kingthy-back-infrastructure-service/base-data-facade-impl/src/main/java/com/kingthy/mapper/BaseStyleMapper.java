package com.kingthy.mapper;

import com.kingthy.dto.BaseStyleFileDto;
import com.kingthy.entity.BaseStyle;
import com.kingthy.request.BaseStylePageReq;
import com.kingthy.util.MyMapper;

import java.util.List;

/**
 *  @author zhaochen
 */
public interface BaseStyleMapper extends MyMapper<BaseStyle>{
    /**
     * 分页查询款式
     * @param baseStylePageReq
     * @return
     */
    List<BaseStyle> findByPage(BaseStylePageReq baseStylePageReq);

    /**
     * 查询款式文件
     * @return
     */
    List<BaseStyleFileDto> findFiles();

    /**
     * 查询全部款式信息
     * @return
     */
    List<BaseStyle> findAllStyle();

    /**
     * 根据uuid查询款式信息
     * @param uuid
     * @return
     */
    BaseStyle selectByUuid(String uuid);
}