package com.kingthy.mapper;

import com.kingthy.dto.GoodsParameterCountDTO;
import com.kingthy.entity.GoodsParameter;
import com.kingthy.util.MyMapper;

import java.util.List;

/**
 * @author  likejie on 2017/8/22.
 */
public interface GoodsParameterMapper  extends MyMapper<GoodsParameter> {

    List<GoodsParameterCountDTO> selectCountByParameterIds(List<String> list);
}
