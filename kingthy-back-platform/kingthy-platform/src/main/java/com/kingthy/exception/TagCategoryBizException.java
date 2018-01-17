package com.kingthy.exception;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

//import com.jxshop.utils.exception.BizExceptionType;

public class TagCategoryBizException extends BizException
{
    
    private static final long serialVersionUID = 1L;
    
    private static final Log log = LogFactory.getLog(TagCategoryBizException.class);
    
    private static final String prefixException = BizExceptionType.BaseDataException.getValue();
    
    public static final PartsCategoryBizException TAGCATEGORY_lIST = new PartsCategoryBizException("0001", "标签列表查询失败");
    
    public static final PartsCategoryBizException TAGCATEGORY_ONE = new PartsCategoryBizException("0002", "标签详情查询异常");
    
    public static final PartsCategoryBizException TAGCATEGORY_DELEYE =
        new PartsCategoryBizException("0003", "该标签已存在商品不能删除");
    
    public TagCategoryBizException()
    {
    }
    
    public TagCategoryBizException(String code, String msgFormat, Object... args)
    {
        super(prefixException + code, msgFormat, args);
    }
    
    public TagCategoryBizException(String code, String msg)
    {
        super(prefixException + code, msg);
    }
    
    /**
     * 实例化异常
     */
    public TagCategoryBizException newInstance(String msgFormat, Object... args)
    {
        return new TagCategoryBizException(this.code, msgFormat, args);
    }
    
    public TagCategoryBizException print()
    {
        log.info("==>GoodsBizException, code:" + this.code + ", msg:" + this.msg);
        return new TagCategoryBizException(this.code, this.msg);
    }
}
