package com.kingthy.platform.service.impl.basedata;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingthy.exception.TagCategoryBizException;
import com.kingthy.platform.entity.basedata.TagCategory;
import com.kingthy.platform.mapper.basedata.TagCategoryMapper;
import com.kingthy.platform.service.basedata.TagCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * 
 *
 * TagCategoryServiceImpl
 * 
 * 赵生辉 2017年4月1日 上午9:20:53
 * 
 * @version 1.0.0
 *
 */
@Service(value = "tagCategoryService")
public class TagCategoryServiceImpl implements TagCategoryService
{
    // 默认版本号
    private static final int VERSION = 1;
    
    // 默认父类id
    private static final String PARENTID = "0";
    
    // 默认父类id
    private static final int GRADE = 0;

    //分类名重复时返回结果
    public static final int ISEXIST = -2;
    
    @Autowired
    private transient TagCategoryMapper tagCategoryMapper;
    
    @Autowired
    private HttpServletRequest httpServletRequest;
    
    @Transactional
    @Override
    public int create(TagCategory tagCategory)
    {
        /* 因用户登陆未做修改人和创建人未添加 */
        Example example = new Example(TagCategory.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("className", tagCategory.getClassName());
        criteria.andEqualTo("delFlag", false);
        /* 判断是否已存在相同名称的标签 */
        if (tagCategoryMapper.selectCountByExample(example) > 0)
        {
            return ISEXIST;
        }
        Date currentDate = new Date();
        tagCategory.setCreateDate(currentDate);
        tagCategory.setCreator(getUuid());
        tagCategory.setModifier(getUuid());
        tagCategory.setModifyDate(currentDate);
        tagCategory.setVersion(VERSION);
        tagCategory.setParentId(PARENTID);
        tagCategory.setDelFlag(false);
        tagCategory.setGrade(GRADE);
        int result = tagCategoryMapper.insertSelective(tagCategory);
        return result;
    }
    
    /**
     * @desc 获取请求用户
     *
     * @author yuanml
     *
     * @return
     */
    private String getUuid()
    {
        String uuid = httpServletRequest.getHeader("uuid");
        if (null == uuid)
        {
            uuid = "";
        }
        return uuid;
    }
    
    @Transactional
    @Override
    public int deleteByUuid(String uuid)
    {
        /* 因用户登陆未做修改人和创建人未添加 */
        TagCategory tagCategory = new TagCategory();
        Date currentDate = new Date();
        tagCategory.setDelFlag(true);
        tagCategory.setModifyDate(currentDate);
        tagCategory.setModifier(getUuid());
        Example example = new Example(TagCategory.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid", uuid);
        criteria.andEqualTo("delFlag", false);
        int result = tagCategoryMapper.updateByExampleSelective(tagCategory, example);
        if (result < 1)
        {
            throw new TagCategoryBizException(TagCategoryBizException.DB_UPDATE_RESULT_0.getCode(), "标签列表删除失败");
        }
        return result;
    }
    
    @Transactional
    @Override
    public int updateSelective(TagCategory tagCategory)
    {
        /* 因用户登陆未做修改人和创建人未添加 */
        Date currentDate = new Date();
        tagCategory.setModifier(getUuid());
        tagCategory.setModifyDate(currentDate);
        Example example = new Example(TagCategory.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("delFlag", false);
        criteria.andEqualTo("uuid", tagCategory.getUuid());
        int result = tagCategoryMapper.updateByExampleSelective(tagCategory, example);
        if (result < 1)
        {
            throw TagCategoryBizException.DB_UPDATE_RESULT_0;
        }
        return result;
    }
    
    @Override
    public List<TagCategory> findAllTagCategory(TagCategory tagCategory)
    {
        Example example = new Example(TagCategory.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("delFlag", false);
        List<TagCategory> countries = tagCategoryMapper.selectByExample(example);
        if (countries.size() > 0)
        {
            return countries;
        }
        else
        {
            throw TagCategoryBizException.DB_LIST_IS_NULL;
        }
        
    }
    
    @Override
    public PageInfo<TagCategory> findAllTagCategoryPage(int pageNo, int pageSize, TagCategory tagCategory)
    {
        Example example = new Example(TagCategory.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("className", tagCategory.getClassName());
        criteria.andEqualTo("description", tagCategory.getDescription());
        criteria.andEqualTo("delFlag", false);
        PageHelper.startPage(pageNo, pageSize);
        List<TagCategory> countries = tagCategoryMapper.selectByExample(example);
        if (countries.size() < 1)
        {
            throw TagCategoryBizException.DB_LIST_IS_NULL;
        }
        PageInfo<TagCategory> pageInfo = new PageInfo<TagCategory>(countries);
        pageInfo = new PageInfo<TagCategory>(countries);
        return pageInfo;
    }
    
    @Transactional
    @Override
    public int updateShowOrHide(String uuid, boolean status)
    {
        /* 因用户登陆未做修改人和创建人未添加 */
        Example example = new Example(TagCategory.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid", uuid);
        criteria.andEqualTo("delFlag", false);
        TagCategory tagCategory = new TagCategory();
        tagCategory.setStatus(status);
        tagCategory.setModifier(getUuid());
        tagCategory.setModifyDate(new Date());
        int result = tagCategoryMapper.updateByExampleSelective(tagCategory, example);
        return result;
    }
    
    @Override
    public List<TagCategory> findTags(String tagName)
    {
        Example example = new Example(TagCategory.class);
        Criteria criteria = example.createCriteria();
        criteria.andLike("className", "%" + tagName + "%");
        criteria.andEqualTo("delFlag", false);
        return tagCategoryMapper.selectByExample(example);
    }
    
    @Override
    public Boolean findTagCategoryName(String tagCategoryName)
    {
        Example example = new Example(TagCategory.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("className", tagCategoryName);
        criteria.andEqualTo("delFlag", false);
        List<TagCategory> classCategories = tagCategoryMapper.selectByExample(example);
        if (classCategories.size() > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
}
