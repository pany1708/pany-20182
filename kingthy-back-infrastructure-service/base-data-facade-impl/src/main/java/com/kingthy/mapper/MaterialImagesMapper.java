package com.kingthy.mapper;

import com.kingthy.entity.MaterialImages;
import com.kingthy.util.MyMapper;

import java.util.List;

/**
 * @author xumin
 * @Description:
 * @DATE Created by 10:15 on 2018/1/5.
 * @Modified by:
 */
public interface MaterialImagesMapper extends MyMapper<MaterialImages>
{
    List<String> queryUUidByMaterialUuid(String materialUuid);

    Integer delImagesByMaterialUuid(String materialUuid);
}
