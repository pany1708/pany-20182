package com.kingthy.transaction;

import com.kingthy.entity.Accessories;
import com.kingthy.request.AddUpdateAccessoriesReq;
import com.kingthy.request.UpdateAccessoriesReq;

/**
 * @author xumin
 * @Description:
 * @DATE Created by 18:28 on 2018/1/10.
 * @Modified by:
 */
public interface AccessoriesServiceTransaction
{
    void saveAccessories(AddUpdateAccessoriesReq req);

    String insertReturnUuid(Accessories accessories);

    void updateAccessories(UpdateAccessoriesReq req);

    int updateAccessories(Accessories accessories);

    void deleteAccessories(String accessoriesUuid);
}
