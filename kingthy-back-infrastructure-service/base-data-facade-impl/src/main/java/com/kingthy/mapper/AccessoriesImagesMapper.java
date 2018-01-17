package com.kingthy.mapper;

import com.kingthy.entity.AccessoriesImages;
import com.kingthy.util.MyMapper;
import io.swagger.models.auth.In;

import java.util.List;

/**
 * @author xumin
 * @Description:
 * @DATE Created by 14:11 on 2018/1/11.
 * @Modified by:
 */
public interface AccessoriesImagesMapper extends MyMapper<AccessoriesImages>
{
    List<String> queryUUidByMaterialUuid(String accessoriesUuid);

    Integer delImagesByAccessoriesUuid(String accessoriesUuid);
}
