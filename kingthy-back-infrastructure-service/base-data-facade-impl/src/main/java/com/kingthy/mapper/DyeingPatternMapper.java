package com.kingthy.mapper;


import com.kingthy.entity.DyeingPattern;
import com.kingthy.util.MyMapper;

import java.util.List;

/**
 * author:fmq
 */
public interface DyeingPatternMapper extends MyMapper<DyeingPattern>
{
    
    

    /**
     * 分页查询特殊工艺
     * @param DyeingPattern
     * @return
     */
    List<DyeingPattern> findDyeingPatternPage(DyeingPattern DyeingPattern);


    /**
     * 根据编码查询特殊工艺
     * @param code
     * @return
     */
    DyeingPattern selectDyeingPatternByCode(String code);

    /**
     * 根据uuid查询特殊工艺
     * @param uuid
     * @return
     */
    DyeingPattern selectDyeingPatternByUuid(String uuid);

    /**
     * 根据名称查询辅料数量
     * @param name
     * @return
     */
    int selectCountByName(String name);
}
