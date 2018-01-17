package com.kingthy.platform.service.impl.basedata;

import com.kingthy.platform.dto.basedata.CategoryReq;
import com.kingthy.platform.dto.basedata.CategoryTreeDto;
import com.kingthy.platform.dto.basedata.TransferCategoryReq;
import com.kingthy.platform.entity.basedata.ClassCategory;
import com.kingthy.platform.mapper.basedata.ClassCategoryMapper;
import com.kingthy.platform.service.basedata.ClassCategoryService;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service(value = "classCategoryService")
public class ClassCategoryServiceImpl implements ClassCategoryService
{
    /**
     * 顶级节点的父节点id为"0"
     */
    private static final String topNode = "0";
    
    /**
     * 未被删除的数据delFlag为true
     */
    private static final boolean exist = false;
    
    /**
     * 已删除数据为false
     */
    private static final boolean notexist = true;
    
    /**
     * 节点转移动作
     */
    private static final String transfer = "transfer";
    
    /**
     * 节点修改动作
     */
    private static final String edit = "edit";
    
    /**
     * 禁止转移标识
     */
    private static final int transForbidden = -2;
    
    /**
     * 版本
     */
    private static final int version = 1;
    
    @Autowired
    private ClassCategoryMapper classCategoryMapper;
    
    @Autowired
    private HttpServletRequest request;
    
    /**
     * 
     * getCurrentUser(得到当前操作用户的uuid)
     * 
     * @return <b>创建人：</b>陈钊<br/>
     *         String
     * @exception @since 1.0.0
     */
    private String getCurrentUser()
    {
        String currentUser = request.getHeader("uuid");
        if (currentUser == null)
        {
            return "";
        }
        return currentUser;
    }
    
    @Override
    public List<ClassCategory> findAllTopNodes()
    {
        Example example = new Example(ClassCategory.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("parentId", topNode);
        criteria.andEqualTo("delFlag", exist);
        List<ClassCategory> classCategoryList = classCategoryMapper.selectByExample(example);
        return classCategoryList;
    }

    @Override
    public List<ClassCategory> findAllNodes()
    {
        Example example = new Example(ClassCategory.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("delFlag", exist);
        List<ClassCategory> classCategoryList = classCategoryMapper.selectByExample(example);
        return classCategoryList;
    }
    
    @Override
    public List<ClassCategory> findAllChildNodes(String classCategoryUuid)
    {
        Example example = new Example(ClassCategory.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("parentId", classCategoryUuid);
        criteria.andEqualTo("delFlag", exist);
        List<ClassCategory> classCategoryList = classCategoryMapper.selectByExample(example);
        return classCategoryList;
    }
    
    @Override
    public int addNode(CategoryReq categoryReq)
    {
        ClassCategory classCategory = new ClassCategory();
        BeanUtils.copyProperties(categoryReq, classCategory);
        int parentGrade = Integer.valueOf(categoryReq.getParentGrade());
        // 子节点的等级等于父节点等级+1,父节点等级从-1开始
        int grade = parentGrade + 1;
        Date currentDate = new Date();
        classCategory.setCreateDate(currentDate);
        classCategory.setModifyDate(currentDate);
        classCategory.setGrade(grade);
        classCategory.setCreator(getCurrentUser());
        classCategory.setVersion(version);
        classCategory.setDelFlag(false);
        int result = classCategoryMapper.insert(classCategory);
        return result;
    }
    
    @Override
    public int delete(String classCategoryUuid)
    {
        Example example = new Example(ClassCategory.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid", classCategoryUuid);
        criteria.andEqualTo("delFlag", exist);
        List<ClassCategory> classCategoryList = classCategoryMapper.selectByExample(example);
        int result = 0;
        if (classCategoryList != null && classCategoryList.size() > 0)
        {
            ClassCategory oldClassCategory = classCategoryList.get(0);
            oldClassCategory.setDelFlag(notexist);
            oldClassCategory.setModifyDate(new Date());
            oldClassCategory.setModifier(getCurrentUser());
            result = classCategoryMapper.updateByExample(oldClassCategory, example);
        }
        
        return result;
    }
    
    @Override
    public int findAllChildNodesNum(String classCategoryUuid)
    {
        Example example = new Example(ClassCategory.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("parentId", classCategoryUuid);
        criteria.andEqualTo("delFlag", exist);
        int childrenNum = classCategoryMapper.selectCountByExample(example);
        return childrenNum;
    }
    
    @Transactional
    @Override
    public int transfer(TransferCategoryReq transferCategoryReq)
    {
        int result = 0;
        // 原父节点的uuid
        String sourceUuid = transferCategoryReq.getSourceUuid();
        // 新父节点的uuid
        String targetUuid = transferCategoryReq.getTargetUuid();
        // 原父节点的级别编号
        int sourceGrade = transferCategoryReq.getSourceGrade();
        // 新父节点的级别编号
        int targetGrade = transferCategoryReq.getTargetGrade();
        // 判断级别是否有变化，没变化则直接执行转移操作，否则要验证新的父节点是不是原父节点的子孙节点，如果是则不允许转移。
        if (sourceGrade != targetGrade)
        {
            if (checkNodes(sourceUuid, targetUuid) == transForbidden)
            {
                return transForbidden;
            }
        }
        Map<String, Object> parentChangeMap = new HashMap<String, Object>();
        parentChangeMap.put("source", sourceUuid);
        parentChangeMap.put("target", targetUuid);
        parentChangeMap.put("modifyDate", new Date());
        parentChangeMap.put("modifier", getCurrentUser());
        if (sourceGrade != targetGrade)
        {
            Map<String, Object> gradeChangeMap = changeGrade(sourceUuid, sourceGrade, targetGrade, transfer);
            if (gradeChangeMap.get("uuidString") != null)
            {
                // 存在子孙节点，则对所有的子孙节点做变级别处理
                classCategoryMapper.updateGradeBatch(gradeChangeMap);
            }
        }
        // 改变直接子节点的父节点
        // 改变完层级，再进行父节点改变，因为层级改变需要查询数据库，如果先改变父节点会出现脏读
        result = classCategoryMapper.updateParentIdBatch(parentChangeMap);
        return result;
    }
    
    @Override
    public ClassCategory findClassCategoryByUuid(String classCategoryUuid)
    {
        Example example = new Example(ClassCategory.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid", classCategoryUuid);
        List<ClassCategory> classCategoryList = classCategoryMapper.selectByExample(example);
        if (classCategoryList != null && classCategoryList.size() > 0)
        {
            return classCategoryList.get(0);
        }
        
        return null;
    }
    
    @Transactional
    @Override
    public int edit(CategoryReq categoryReq)
    {
        int result = 0;
        Example example = new Example(ClassCategory.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid", categoryReq.getUuid());
        List<ClassCategory> classCategoryList = classCategoryMapper.selectByExample(example);
        if (classCategoryList != null && classCategoryList.size() > 0)
        {
            ClassCategory oldClassCategory = classCategoryList.get(0);
            // 改变层级
            int sourceGrade = oldClassCategory.getGrade() - 1;
            int targetGrade = Integer.valueOf(categoryReq.getParentGrade());
            // 判断级别是否有变化，没变化则直接执行转移操作，否则要验证新的父节点是不是原节点的子孙节点，如果是则不允许转移。
            if (sourceGrade != targetGrade)
            {
                if (checkNodes(oldClassCategory.getUuid(), categoryReq.getParentId()) == transForbidden)
                {
                    return transForbidden;
                }
            }
            if (!oldClassCategory.getParentId().equals(categoryReq.getParentId()))
            {// 父节点不同，则进行父节点改变，以及层级改变
                Map<String, Object> parentChangeMap = new HashMap<String, Object>();
                parentChangeMap.put("uuid", categoryReq.getUuid());
                parentChangeMap.put("newParentId", categoryReq.getParentId());
                // 改变层级
                if (sourceGrade != targetGrade)
                {
                    Map<String, Object> gradeChangeMap =
                        changeGrade(oldClassCategory.getUuid(), sourceGrade, targetGrade, edit);
                    classCategoryMapper.updateGradeBatch(gradeChangeMap);
                }
                // 改变完层级，再进行父节点改变，因为层级改变需要查询数据库，如果先改变父节点会出现脏读
                classCategoryMapper.updateParentId(parentChangeMap);
            }
            if (oldClassCategory.getStatus() != categoryReq.getStatus())
            {// 状态改变了，调用改变状态方法
                editStatus(categoryReq.getUuid(), categoryReq.getStatus());
            }
            result = editBaseInfo(categoryReq);
        }
        return result;
    }
    
    @Override
    public int editStatus(String classCategoryUuid, boolean status)
    {
        String uuids = classCategoryMapper.findChildUuidsByParentId(classCategoryUuid);
        String[] uuidString = uuids.split(",");
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("uuidString", uuidString);
        paramMap.put("modifier", getCurrentUser());
        paramMap.put("modifyDate", new Date());
        paramMap.put("status", status);
        int result = classCategoryMapper.updateStatusBatch(paramMap);
        return result;
    }
    
    /**
     * changeGrade(生成层级改变所需参数)
     * 
     * @param sourceUuid
     * @param sourceGrade
     * @param targetGrade
     * @return <b>创建人：</b>陈钊<br/>
     *         Map<String,Object>
     * @exception @since 1.0.0
     */
    private Map<String, Object> changeGrade(String sourceUuid, int sourceGrade, int targetGrade, String type)
    {
        // 等级变动值
        int gradeChange = targetGrade - sourceGrade;
        String uuids = classCategoryMapper.findChildUuidsByParentId(sourceUuid);
        // 转移则排除掉父节点本身，修改将父节点一起修改
        if (transfer.equals(type))
        {
            if (uuids.contains(sourceUuid + ","))
            {
                uuids = uuids.replaceAll(sourceUuid + ",", "");
            }
            else
            {
                uuids = uuids.replaceAll(sourceUuid, "");
            }
        }
        String[] uuidString = uuids.split(",");
        Map<String, Object> gradeChangeMap = new HashMap<String, Object>();
        gradeChangeMap.put("gradeChange", gradeChange);
        if (uuidString.length > 0)
        {
            gradeChangeMap.put("uuidString", uuidString);
        }
        return gradeChangeMap;
    }
    
    @Override
    public List<ClassCategory> findAll()
    {
        Example example = new Example(ClassCategory.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("delFlag", exist);
        List<ClassCategory> classCategoryList = classCategoryMapper.selectByExample(example);
        return classCategoryList;
    }
    
    @Override
    public List<CategoryTreeDto> getTree()
    {
        List<CategoryTreeDto> classCategoryTreeList = classCategoryMapper.getTree();
        List<CategoryTreeDto> treeList = treeProduce(classCategoryTreeList);
        return treeList;
    }
    
    /**
     * getTree(根据查询出来的数据生成树结构)
     * 
     * @param trees
     * @return <b>创建人：</b>陈钊<br/>
     *         List<ClassCategoryTree>
     * @exception @since 1.0.0
     */
    private List<CategoryTreeDto> treeProduce(List<CategoryTreeDto> trees)
    {
        List<CategoryTreeDto> rootTrees = new ArrayList<CategoryTreeDto>();
        for (CategoryTreeDto tree : trees)
        {
            if (tree.getParentId().equals(topNode))
            {
                rootTrees.add(tree);
            }
            for (CategoryTreeDto t : trees)
            {
                if (t.getParentId().equals(tree.getUuid()))
                {
                    if (tree.getChildrens() == null)
                    {
                        List<CategoryTreeDto> myChildrens = new ArrayList<CategoryTreeDto>();
                        myChildrens.add(t);
                        tree.setChildrens(myChildrens);
                    }
                    else
                    {
                        tree.getChildrens().add(t);
                    }
                }
            }
        }
        return rootTrees;
    }
    
    /**
     * 
     * checkNodes(判断目标节点是否属于原节点的子孙)
     * 
     * @param sourceUuid
     * @param targetUuid
     * @return <b>创建人：</b>陈钊<br/>
     *         int
     * @exception @since 1.0.0
     */
    private int checkNodes(String sourceUuid, String targetUuid)
    {
        String uuids = classCategoryMapper.findChildUuidsByParentId(sourceUuid);
        String[] uuidArray = uuids.split(",");
        // 如果目标节点属于原节点的子孙，则返回false
        if (ArrayUtils.contains(uuidArray, targetUuid))
        {
            return transForbidden;
        }
        return 0;
    }
    
    /**
     * 
     * editBaseInfo(编辑基本信息)
     * 
     * @param categoryReq
     * @return <b>创建人：</b>陈钊<br/>
     *         int
     * @exception @since 1.0.0
     */
    private int editBaseInfo(CategoryReq categoryReq)
    {
        ClassCategory classCategory = new ClassCategory();
        classCategory.setModifier(getCurrentUser());
        classCategory.setModifyDate(new Date());
        classCategory.setClassName(categoryReq.getClassName());
        classCategory.setDescription(categoryReq.getDescription());
        classCategory.setUuid(categoryReq.getUuid());
        int result = classCategoryMapper.updateCategory(classCategory);
        return result;
    }
    
    @Override
    public Boolean findClassCategoryName(String classCategoryName)
    {
        Example example = new Example(ClassCategory.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("className", classCategoryName);
        criteria.andEqualTo("delFlag", false);
        List<ClassCategory> classCategories = classCategoryMapper.selectByExample(example);
        if (classCategories.size() > 0)
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
        return classCategoryMapper.updateGoodsNum();
    }
}
