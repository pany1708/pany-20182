package com.kingthy.dubbo.basedata.service;

import com.github.pagehelper.PageInfo;
import com.kingthy.entity.DyeingPattern;

/**
 * @author fmq
 */
public interface DyeingPatternDubboService
{
    /**
     * 
     * create(创建一个特殊工艺) (创建一个特殊工艺)
     * 
     * @param dyeingPattern
     * @return  int
     */
    int create(DyeingPattern dyeingPattern);

    /**
     * 创建特殊工艺并返回结果
     * @param dyeingPattern
     * @return
     */
    String insertReturnUuid(DyeingPattern dyeingPattern);
    
    /**
     * 
     * findDyeingPattern(查询一个特殊工艺的详情) (查询一个特殊工艺的详情)
     * 
     * @param dyeingPatternUuid
     * @return  DyeingPattern
     */
    DyeingPattern findDyeingPattern(String dyeingPatternUuid);
    
    /**
     * 分页查询特殊工艺表
     * 
     * @param pageNum
     * @param pageSize
     * @param dyeingPattern
     * @return
     */
    PageInfo<DyeingPattern> findDyeingPatternPage(int pageNum, int pageSize, DyeingPattern dyeingPattern);
    
    /**
     * 删除特殊工艺详情
     *
     * @author yuanml
     *
     * @param dyeingPatternUuid
     * @return
     */
    int deleteDyeingPattern(String dyeingPatternUuid);
    
    /**
     * 更新特殊工艺信息
     * @param DyeingPattern
     * @author yuanml
     *
     * @return
     */
    int updateDyeingPattern(DyeingPattern DyeingPattern);


    /**
     * 根据编码查询
     * @param code
     * @return
     */
    DyeingPattern selectDyeingPatternByCode(String code);

    /**
     * 根据uuid查询
     * @param uuid
     * @return
     */
    DyeingPattern selectDyeingPatternByUuid(String uuid);

    /**
     * 根据名称查询
     * @param name
     * @return
     */
    int selectCountByExample(String name);

}
