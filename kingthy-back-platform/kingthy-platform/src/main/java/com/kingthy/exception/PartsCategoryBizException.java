package com.kingthy.exception;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

//import com.jxshop.utils.exception.BizExceptionType;

public class PartsCategoryBizException extends BizException
{
    
    private static final long serialVersionUID = 1L;
    
    private static final Log log = LogFactory.getLog(PartsCategoryBizException.class);
    
    private static final String prefixException = BizExceptionType.BaseDataException.getValue();
    
    public static final PartsCategoryBizException PARTSCATEGORY_lIST =
        new PartsCategoryBizException("0001", "部件列表查询异常");
    
    public static final PartsCategoryBizException PARTSCATEGORY_ONE = new PartsCategoryBizException("0002", "部件详情查询异常");
    
    public static final PartsCategoryBizException PARTSCATEGORY_DELEYE =
        new PartsCategoryBizException("0003", "该部件存在子类别不能删除");

    public static final PartsCategoryBizException PARTSCATEGORY_UPDATE =
        new PartsCategoryBizException("0004", "分类等级不能超过两级");
    
    public PartsCategoryBizException()
    {
    }
    
    public PartsCategoryBizException(String code, String msgFormat, Object... args)
    {
        super(prefixException + code, msgFormat, args);
    }
    
    public PartsCategoryBizException(String code, String msg)
    {
        super(prefixException + code, msg);
    }
    
    /**
     * 实例化异常
     */
    public PartsCategoryBizException newInstance(String msgFormat, Object... args)
    {
        return new PartsCategoryBizException(this.code, msgFormat, args);
    }
    
    public PartsCategoryBizException print()
    {
        log.info("==>GoodsBizException, code:" + this.code + ", msg:" + this.msg);
        return new PartsCategoryBizException(this.code, this.msg);
    }
}
