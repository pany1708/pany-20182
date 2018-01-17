package com.kingthy.dubbo.user.service;

import com.kingthy.base.BaseService;
import com.kingthy.dto.GoodsDTO;
import com.kingthy.entity.Figure;

import java.util.List;

/**
 * @author  xumin
 * @Description:
 * @DATE Created by 15:11 on 2017/8/10.
 * @Modified by:
 */
public interface FigureDubboService extends BaseService<Figure>{

    /**
     * ...
     * @param listUuid
     * @return List<GoodsDTO.FigureInfo>
     */
    List<Figure> selectInUuid(List<String> listUuid);

    /**
     * 通过用户id查询体型数据
     * @param memberUuid
     * @return List<GoodsDTO.FigureInfo>
     */
    List<GoodsDTO.FigureInfo> selectFigureByMemberUuid(String memberUuid);
}
