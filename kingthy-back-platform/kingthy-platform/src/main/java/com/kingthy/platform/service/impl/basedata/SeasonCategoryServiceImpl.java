/**
 * 系统项目名称
 * com.kingthy.platform.service.impl.basedata
 * SeasonServiceImpl.java
 * 
 * 2017年3月29日-下午4:24:19
 *  2017金昔科技有限公司-版权所有
 *
 */
package com.kingthy.platform.service.impl.basedata;

import com.kingthy.platform.dto.basedata.MaterielSeasonReq;
import com.kingthy.platform.dto.basedata.SeasonCategoryDto;
import com.kingthy.platform.entity.basedata.SeasonCategory;
import com.kingthy.platform.mapper.basedata.SeasonCategoryMapper;
import com.kingthy.platform.service.basedata.SeasonCategoryService;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.BeanUtils;
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
 * SeasonServiceImpl
 * 
 * yuanml 2017年3月29日 下午4:24:19
 * 
 * @version 1.0.0
 *
 */
@Service("seasonCategoryService")
public class SeasonCategoryServiceImpl implements SeasonCategoryService
{
    @Autowired
    private transient SeasonCategoryMapper seasonCategoryMapper;
    
    @Autowired
    private HttpServletRequest httpServletRequest;
    
    private Map<String, Object> parameterMap;
    
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
    public int createSeasonCategory(MaterielSeasonReq materielSeasonReq)
    {
        SeasonCategoryDto seasonCategoryDto = new SeasonCategoryDto();
        BeanUtils.copyProperties(materielSeasonReq, seasonCategoryDto);
        seasonCategoryDto.setCreator(getUuid());
        seasonCategoryDto.setCreateDate(new Date());
        seasonCategoryDto.setModifier(seasonCategoryDto.getCreator());
        seasonCategoryDto.setModifyDate(seasonCategoryDto.getCreateDate());
        seasonCategoryDto.setVersion(DEFAULTVERSION);
        seasonCategoryDto.setDelFlag(false);
        seasonCategoryDto.setGrade(Integer.parseInt(seasonCategoryDto.getParentGrade()) + 1);
        SeasonCategory seasonCategory = seasonCategoryDto;
        return seasonCategoryMapper.insert(seasonCategory);
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
    public List<SeasonCategory> findSeasonCategoryByParentId(String parentId)
    {
        Example example = new Example(SeasonCategory.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("parentId", parentId);
        criteria.andEqualTo("delFlag", false);
        List<SeasonCategory> seasonCategories = seasonCategoryMapper.selectByExample(example);
        return seasonCategories;
    }
    
    @Transactional
    @Override
    public int updateSeasonCategoryByUuid(MaterielSeasonReq materielSeasonReq)
    {
        SeasonCategoryDto seasonCategoryDto = new SeasonCategoryDto();
        BeanUtils.copyProperties(materielSeasonReq, seasonCategoryDto);
        Example example = new Example(SeasonCategory.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid", seasonCategoryDto.getUuid());
        SeasonCategory seasonCategoryOld = seasonCategoryMapper.selectByExample(example).get(0);
        String seasonCategoryOldParentId = seasonCategoryOld.getParentId();
        seasonCategoryDto.setModifier(getUuid());
        seasonCategoryDto.setModifyDate(new Date());
        int result = 0;
        int diffGrade = 0;
        String[] seasonCategoryUuidChilds = getChildSeasonCategoryUuids(seasonCategoryOld.getUuid(), true);
        if (!(seasonCategoryDto.getParentId().equals(seasonCategoryOldParentId)
            && seasonCategoryDto.getStatus().equals(seasonCategoryOld.getStatus())))
        {
            if (!seasonCategoryDto.getParentId().equals(seasonCategoryOldParentId))
            {
                diffGrade =
                    (int)(Integer.parseInt(seasonCategoryDto.getParentGrade()) - seasonCategoryOld.getGrade()) + 1;
                if (0 != diffGrade)
                {
                    if (ArrayUtils.contains(seasonCategoryUuidChilds, seasonCategoryDto.getParentId()))
                    {
                        return ISCHILDNODE;
                    }
                    parameterMap = new ConcurrentHashMap<>();
                    parameterMap.put("seasonCategoryChildUuids", seasonCategoryUuidChilds);
                    parameterMap.put("diffInt", diffGrade);
                    parameterMap.put("modifier", seasonCategoryDto.getModifier());
                    result = seasonCategoryMapper.updateSeasonCategoryGradeBatch(parameterMap);
                }
            }
            if (!seasonCategoryDto.getStatus().equals(seasonCategoryOld.getStatus()))
            {
                if (result >= 0)
                    result = updateSeasonCategoryStatusBatch(seasonCategoryDto.getStatus().toString(),
                        seasonCategoryUuidChilds);
            }
        }
        if (result >= 0)
        {
            seasonCategoryOld.setClassName(seasonCategoryDto.getClassName());
            seasonCategoryOld.setStatus(seasonCategoryDto.getStatus());
            seasonCategoryOld.setGrade(seasonCategoryOld.getGrade() + diffGrade);
            seasonCategoryOld.setDescription(seasonCategoryDto.getDescription());
            seasonCategoryOld.setParentId(seasonCategoryDto.getParentId());
            seasonCategoryOld.setModifier(seasonCategoryDto.getModifier());
            seasonCategoryOld.setModifyDate(seasonCategoryDto.getModifyDate());
            result = seasonCategoryMapper.updateByExample(seasonCategoryOld, example);
        }
        return result;
    }
    
    @Override
    public int updateSeasonCategoryStatusByUuid(String seasonCategoryUuid, String seasonCategoryStatus)
    {
        // 调用数据库函数查询出所有需修改的记录
        String seasonCategoryChildUuid = seasonCategoryMapper.getSeasonCategoryChildUuid(seasonCategoryUuid);
        String[] seasonCategoryChildUuids = seasonCategoryChildUuid.split(",");
        return updateSeasonCategoryStatusBatch(seasonCategoryStatus, seasonCategoryChildUuids);
    }
    
    /**
     * 批量更新状态 (这里描述这个方法适用条件 – 可选)
     *
     * yuanml
     * 
     * @param seasonCategoryStatus
     * @param seasonCategoryChildUuids
     * @return int
     * @exception @since 1.0.0
     */
    private int updateSeasonCategoryStatusBatch(String seasonCategoryStatus, String[] seasonCategoryChildUuids)
    {
        parameterMap = new ConcurrentHashMap<>();
        parameterMap.put("modifier", getUuid());
        parameterMap.put("seasonCategoryStatus", Boolean.parseBoolean(seasonCategoryStatus));
        parameterMap.put("seasonCategoryChildUuids", seasonCategoryChildUuids);
        return seasonCategoryMapper.updateSeasonCategoryStatus(parameterMap);
    }
    
    @Override
    public int deleteSeasonCategory(String seasonCategoryUuid)
    {
        parameterMap = new ConcurrentHashMap<>();
        parameterMap.put("seasonCategoryUuid", seasonCategoryUuid);
        parameterMap.put("modifier", getUuid());
        Example example = new Example(SeasonCategory.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("parentId", seasonCategoryUuid);
        criteria.andEqualTo("delFlag", false);
        if (0 < seasonCategoryMapper.selectByExample(example).size())
        {
            return NOLEAFNODE;
        }
        else
        {
            return seasonCategoryMapper.deleteSeasonCategory(parameterMap);
        }
    }
    
    @Transactional
    @Override
    public int transferSeasonCategory(String seasonCategoryUuidOld, String seasoncategoryUuidNew, String sourceGrade,
        String targetGrade)
    {
        parameterMap = new ConcurrentHashMap<>();
        parameterMap.put("modifier", getUuid());
        parameterMap.put("seasonCategoryUuidOld", seasonCategoryUuidOld);
        parameterMap.put("seasoncategoryUuidNew", seasoncategoryUuidNew);
        int result = -1;
        int diffInt = Integer.parseInt(targetGrade) - Integer.parseInt(sourceGrade) + 1;
        if (0 != diffInt)
        {
            String[] seasonCategoryChildUuids = getChildSeasonCategoryUuids(seasonCategoryUuidOld, false);
            if (ArrayUtils.contains(seasonCategoryChildUuids, seasoncategoryUuidNew))
            {
                return ISCHILDNODE;
            }
            if (0 < seasonCategoryChildUuids.length)
            {
                parameterMap.put("seasonCategoryChildUuids", seasonCategoryChildUuids);
                parameterMap.put("diffInt", diffInt);
                result = seasonCategoryMapper.updateSeasonCategoryGradeBatch(parameterMap);
                if (0 <= result)
                {
                    result = seasonCategoryMapper.transferSeasonCategory(parameterMap);
                }
            }
        }
        else
        {
            result = seasonCategoryMapper.transferSeasonCategory(parameterMap);
        }
        return result;
        
    }
    
    /**
     * 
     * 获取下级节点集合 (这里描述这个方法适用条件 – 可选)
     *
     * yuanml
     * 
     * @param seasonCategoryUuidOld
     * @param isRootUuid true 保存rootUuid false 不保存
     * @return String[]
     * @exception @since 1.0.0
     */
    private String[] getChildSeasonCategoryUuids(String seasonCategoryUuidOld, boolean isRootUuid)
    {
        String seasonCategoryChildUuid = seasonCategoryMapper.getSeasonCategoryChildUuid(seasonCategoryUuidOld);
        if (!isRootUuid)
        {
            if (seasonCategoryChildUuid.indexOf(seasonCategoryUuidOld + ",") >= 0)
            {
                seasonCategoryChildUuid = seasonCategoryChildUuid.replaceAll(seasonCategoryUuidOld + ",", "");
            }
            else
            {
                seasonCategoryChildUuid = seasonCategoryChildUuid.replaceAll(seasonCategoryUuidOld, "");
            }
        }
        String[] seasonCategoryChildUuids = seasonCategoryChildUuid.split(",", -1);
        return seasonCategoryChildUuids;
    }
    
    @Override
    public SeasonCategory findSeasonCategoryByUuid(String seasonCategoryUuid)
    {
        Example example = new Example(SeasonCategory.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid", seasonCategoryUuid);
        criteria.andEqualTo("delFlag", false);
        return seasonCategoryMapper.selectByExample(example).get(0);
    }
    
    @Override
    public List<SeasonCategoryDto> findAllSeasoncategoryTree()
    {
        return getTree(seasonCategoryMapper.selectAllSeasoncategoryTree());
    }
    
    /**
     * 
     * 将结果集进行层级封装 (这里描述这个方法适用条件 – 可选)
     *
     * yuanml
     * 
     * @param trees
     * @return List<SeasonCategoryDto>
     * @exception @since 1.0.0
     */
    private List<SeasonCategoryDto> getTree(List<SeasonCategoryDto> trees)
    {
        List<SeasonCategoryDto> rootTrees = new ArrayList<SeasonCategoryDto>();
        for (SeasonCategoryDto tree : trees)
        {
            if (tree.getParentId().equals(ROOTNODE))
            {
                rootTrees.add(tree);
            }
            for (SeasonCategoryDto t : trees)
            {
                if (t.getParentId().equals(tree.getUuid()))
                {
                    if (tree.getSeasonCategoryDtos() == null)
                    {
                        List<SeasonCategoryDto> myChildrens = new ArrayList<SeasonCategoryDto>();
                        myChildrens.add(t);
                        tree.setSeasonCategoryDtos(myChildrens);
                    }
                    else
                    {
                        tree.getSeasonCategoryDtos().add(t);
                    }
                }
            }
        }
        return rootTrees;
    }
    
    @Override
    public List<SeasonCategory> findAllSeasoncategoryTop()
    {
        return seasonCategoryMapper.findAllSeasoncategoryTop();
    }
    
    @Override
    public Boolean findSeasonCategoryName(String seasonCategoryName)
    {
        Example example = new Example(SeasonCategory.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("className", seasonCategoryName);
        criteria.andEqualTo("delFlag", false);
        List<SeasonCategory> classCategories = seasonCategoryMapper.selectByExample(example);
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
