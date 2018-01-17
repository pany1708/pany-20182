package com.kingthy.mapper;

import com.kingthy.dto.GoodsDTO;
import com.kingthy.entity.Figure;
import com.kingthy.util.MyMapper;

import java.util.List;

/**
 * @author  xumin
 * @Description:
 * @DATE Created by 15:18 on 2017/8/10.
 * @Modified by:
 */
public interface FigureMapper extends MyMapper<Figure>
{
    /**
     * 通过用户id查询体型数据
     * @param memberUuid
     * @return List<GoodsDTO.FigureInfo>
     */
    List<GoodsDTO.FigureInfo> selectFigureByMemberUuid(String memberUuid);

    /**
     * 批量查询
     * 
     * @param listUuid
     * @return
     */
    List<Figure> queryBath(List<String> listUuid);
}
