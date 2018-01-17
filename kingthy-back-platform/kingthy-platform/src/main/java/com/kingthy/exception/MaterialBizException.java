package com.kingthy.exception;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

//import com.jxshop.utils.exception.BizExceptionType;

public class MaterialBizException extends BizException
{
    
    private static final long serialVersionUID = 1L;
    
    private static final Log log = LogFactory.getLog(MaterialBizException.class);
    
    private static final String prefixException = BizExceptionType.BaseDataException.getValue();
    
    public static final PartsCategoryBizException MATERIAL_lIST = new PartsCategoryBizException("0001", "面料列表查询失败");
    
    public static final PartsCategoryBizException MATERIAL_ONE = new PartsCategoryBizException("0002", "面料详情查询异常");
    
    public MaterialBizException()
    {
    }
    
    public MaterialBizException(String code, String msgFormat, Object... args)
    {
        super(prefixException + code, msgFormat, args);
    }
    
    public MaterialBizException(String code, String msg)
    {
        super(prefixException + code, msg);
    }
    
    /**
     * 实例化异常
     */
    public MaterialBizException newInstance(String msgFormat, Object... args)
    {
        return new MaterialBizException(this.code, msgFormat, args);
    }
    
    public MaterialBizException print()
    {
        log.info("==>GoodsBizException, code:" + this.code + ", msg:" + this.msg);
        return new MaterialBizException(this.code, this.msg);
    }
}
