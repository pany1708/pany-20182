package com.kingthy.transaction;

import com.kingthy.entity.Material;
import com.kingthy.request.AddUpdateMaterialReq;
import com.kingthy.request.UpdateMaterialReq;

/**
 * @author xumin
 * @Description:
 * @DATE Created by 14:56 on 2018/1/9.
 * @Modified by:
 */
public interface MaterialServiceTransaction
{
    void saveMaterial(AddUpdateMaterialReq req);
    void updateMaterial(UpdateMaterialReq req);

    String insertReturnUuid(Material material);

    int updateMaterial(Material material);

    void deleteMaterial(String materialUuid);
}
