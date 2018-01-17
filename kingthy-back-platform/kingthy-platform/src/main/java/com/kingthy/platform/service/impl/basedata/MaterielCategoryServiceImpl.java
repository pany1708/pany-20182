/**
 * 系统项目名称
 * com.kingthy.platform.service.impl.basedata
 * MaterielServiceImpl.java
 * 
 * 2017年3月29日-下午4:24:19
 *  2017金昔科技有限公司-版权所有
 *
 */
package com.kingthy.platform.service.impl.basedata;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingthy.exception.BizException;
import com.kingthy.platform.dto.basedata.MaterielCategoryDto;
import com.kingthy.platform.entity.basedata.MaterielCategory;
import com.kingthy.platform.mapper.basedata.MaterielCategoryMapper;
import com.kingthy.platform.service.basedata.MaterielCategoryService;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * MaterielServiceImpl
 * 
 * yuanml 2017年3月29日 下午4:24:19
 * 
 * @version 1.0.0
 *
 */
@Service("materielCategoryService")
public class MaterielCategoryServiceImpl implements MaterielCategoryService
{
    @Autowired
    private transient MaterielCategoryMapper materielCategoryMapper;
    
    private Map<String, Object> parameterMap;
    
    @Autowired
    private HttpServletRequest httpServletRequest;
    
    /**
     * 初始版本号
     */
    private static final int DEFAULTVERSION = 1;
    
    /**
     * 当前节点不是末节点 返回结果
     */
    private static final int NOLEAFNODE = -2;
    
    /**
     * 转移分类时禁止将节点转移到子节点下
     */
    private static final int ISCHILDNODE = -3;
    
    /**
     * 顶级根节点
     */
    private static final String ROOTNODE = "0";
    
    @Override
    public int createMaterielCategory(MaterielCategory materielCategory)
    {

        Date date = new Date();
        materielCategory.setCreateDate(date);
        materielCategory.setModifyDate(date);
        materielCategory.setCreator(getUuid());
        materielCategory.setModifier(getUuid());
        materielCategory.setDelFlag(false);
        materielCategory.setVersion(DEFAULTVERSION);
        int result = materielCategoryMapper.insertSelective(materielCategory);
        if(result == 0)
        {
            throw new BizException("创建材质分类失败");
        }
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
    
    @Override
    public List<MaterielCategory> findMaterielCategoryByParentId(String parentId)
    {
        Example example = new Example(MaterielCategory.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("parentId", parentId);
        criteria.andEqualTo("delFlag", false);
        List<MaterielCategory> materielCategories = materielCategoryMapper.selectByExample(example);
        return materielCategories;
    }
    
    @Transactional
    @Override
    public int updateMaterielCategoryByUuid(MaterielCategory materielCategory)
    {

        Example example = new Example(MaterielCategory.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid",materielCategory.getUuid());
        criteria.andEqualTo("delFlag",false);
        int result = materielCategoryMapper.updateByExampleSelective(materielCategory,example);
        if(result == 0)
        {
            throw new BizException("更新物料分类失败");
        }
        return result;
    }

    @Override
    public PageInfo findMaterielCategory(int pageNum, int pageSize, MaterielCategory materielCategory)
    {

        Example example = new Example(MaterielCategory.class);
        Criteria criteria = example.createCriteria();
        if(materielCategory.getClassName() != null)
        {
            criteria.andLike("className",materielCategory.getClassName());
        }
        if(materielCategory.getStatus() != null)
        {
            criteria.andEqualTo("status",materielCategory.getStatus());
        }
        criteria.andEqualTo("delFlag",false);
        PageHelper.startPage(pageNum,pageSize);
        List<MaterielCategory> materielCategories = materielCategoryMapper.selectByExample(example);
        if(materielCategories == null)
        {
            throw new BizException("分页查询材料失败");
        }
        PageInfo<MaterielCategory> result = new PageInfo<>(materielCategories);
        return result;
    }



    @Override
    public int updateMaterielCategoryStatusByUuid(String materielCategoryUuid, String materielCategoryStatus)
    {
        // 调用数据库函数查询出所有需修改的记录
        String materielCategoryChildUuid = materielCategoryMapper.getMaterielCategoryChildUuid(materielCategoryUuid);
        String[] materielCategoryChildUuids = materielCategoryChildUuid.split("");
        return updateMaterielCategoryStatusBatch(materielCategoryStatus, materielCategoryChildUuids);
    }
    
    /**
     * 批量更新状态 (这里描述这个方法适用条件 – 可选)
     *
     * yuanml
     * 
     * @param materielCategoryStatus
     * @param materielCategoryChildUuids
     * @return int
     * @exception @since 1.0.0
     */
    private int updateMaterielCategoryStatusBatch(String materielCategoryStatus, String[] materielCategoryChildUuids)
    {
        parameterMap = new ConcurrentHashMap<>();
        parameterMap.put("modifier", getUuid());
        parameterMap.put("materielCategoryStatus", Boolean.parseBoolean(materielCategoryStatus));
        parameterMap.put("materielCategoryChildUuids", materielCategoryChildUuids);
        return materielCategoryMapper.updateMaterielCategoryStatus(parameterMap);
    }
    
    @Override
    public int deleteMaterielCategory(String materielCategoryUuid)
    {
        Example example = new Example(MaterielCategory.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid",materielCategoryUuid);
        int result = materielCategoryMapper.deleteByExample(example);
        if(result == 0)
        {
            throw new BizException("删除材质失败");
        }
        return result;
    }
    
    @Transactional
    @Override
    public int transferMaterielCategory(String materielCategoryUuidOld, String materielcategoryUuidNew,
        String sourceGrade, String targetGrade)
    {
        parameterMap = new ConcurrentHashMap<>();
        parameterMap.put("modifier", getUuid());
        parameterMap.put("materielCategoryUuidOld", materielCategoryUuidOld);
        parameterMap.put("materielcategoryUuidNew", materielcategoryUuidNew);
        int result = -1;
        int diffInt = Integer.parseInt(targetGrade) - Integer.parseInt(sourceGrade);
        if (0 != diffInt)
        {
            String[] materielCategoryChildUuids = getChildMaterielCategoryUuids(materielCategoryUuidOld, false);
            if (ArrayUtils.contains(materielCategoryChildUuids, materielcategoryUuidNew))
            {
                return ISCHILDNODE;
            }
            
            if (0 < materielCategoryChildUuids.length)
            {
                parameterMap.put("materielCategoryChildUuids", materielCategoryChildUuids);
                parameterMap.put("diffInt", diffInt);
                
                result = materielCategoryMapper.updateMaterielCategoryGradeBatch(parameterMap);
                if (0 <= result)
                {
                    result = materielCategoryMapper.transferMaterielCategory(parameterMap);
                }
            }
        }
        else
        {
            result = materielCategoryMapper.transferMaterielCategory(parameterMap);
        }
        return result;
        
    }
    
    /**
     * 
     * 获取下级节点集合 (这里描述这个方法适用条件 – 可选)
     *
     * yuanml
     * 
     * @param materielCategoryUuidOld
     * @param isRootUuid true 保存rootUuid false 不保存
     * @return String[]
     * @exception @since 1.0.0
     */
    private String[] getChildMaterielCategoryUuids(String materielCategoryUuidOld, boolean isRootUuid)
    {
        String materielCategoryChildUuid = materielCategoryMapper.getMaterielCategoryChildUuid(materielCategoryUuidOld);
        if (!isRootUuid)
        {
            if (materielCategoryChildUuid.indexOf(materielCategoryUuidOld + ",") >= 0)
            {
                materielCategoryChildUuid = materielCategoryChildUuid.replaceAll(materielCategoryUuidOld + ",", "");
            }
            else
            {
                materielCategoryChildUuid = materielCategoryChildUuid.replaceAll(materielCategoryUuidOld, "");
            }
        }
        String[] materielCategoryChildUuids = materielCategoryChildUuid.split(",", -1);
        return materielCategoryChildUuids;
    }
    
    @Override
    public MaterielCategory findMaterielCategoryByUuid(String materielCategoryUuid)
    {
        Example example = new Example(MaterielCategory.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid", materielCategoryUuid);
        criteria.andEqualTo("delFlag", false);
        MaterielCategory materielCategory = null;
        try
        {
            materielCategory = materielCategoryMapper.selectByExample(example).get(0);
            if(materielCategory == null)
            {
                throw new BizException("查询材质失败");
            }
        }
        catch (BizException e)
        {
            throw new BizException("查询材质失败");
        }
        return materielCategory;
    }
    
    @Override
    public List<MaterielCategoryDto> findAllMaterielcategoryTree()
    {
        return getTree(materielCategoryMapper.selectAllMaterielcategoryTree());
    }
    
    /**
     * 将结果集进行层级封装 (这里描述这个方法适用条件 – 可选)
     *
     * yuanml
     * 
     * @param trees
     * @return List<MaterielCategoryDto>
     * @exception @since 1.0.0
     */
    private List<MaterielCategoryDto> getTree(List<MaterielCategoryDto> trees)
    {
        List<MaterielCategoryDto> rootTrees = new ArrayList<>();
        for (MaterielCategoryDto tree : trees)
        {
            if (tree.getParentId().equals(ROOTNODE))
            {
                rootTrees.add(tree);
            }
            for (MaterielCategoryDto t : trees)
            {
                if (t.getParentId().equals(tree.getUuid()))
                {
                    if (tree.getMaterielCategoryDtos() == null)
                    {
                        List<MaterielCategoryDto> myChildrens = new ArrayList<MaterielCategoryDto>();
                        myChildrens.add(t);
                        tree.setMaterielCategoryDtos(myChildrens);
                    }
                    else
                    {
                        tree.getMaterielCategoryDtos().add(t);
                    }
                }
            }
        }
        return rootTrees;
    }
    
    @Override
    public List<MaterielCategory> findAllMaterielcategoryTop()
    {
        return materielCategoryMapper.findAllMaterielcategoryTop();
    }
    
    @Override
    public Boolean findMaterielCategoryName(String materielCategoryName)
    {
        Example example = new Example(MaterielCategory.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("className", materielCategoryName);
        criteria.andEqualTo("delFlag", false);
        List<MaterielCategory> materielCategories = materielCategoryMapper.selectByExample(example);
        if (materielCategories.size() > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public int updateGoodsNum()
    {
        return materielCategoryMapper.updateGoodsNum();
    }

    @Override
    public List<MaterielCategory> findAllMaterielcategory()
    {
        return materielCategoryMapper.selectAll();
    }

}
