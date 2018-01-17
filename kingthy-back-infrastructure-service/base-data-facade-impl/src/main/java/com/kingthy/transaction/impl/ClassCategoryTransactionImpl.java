package com.kingthy.transaction.impl;

import com.kingthy.entity.ClassCategory;
import com.kingthy.mapper.ClassCategoryMapper;
import com.kingthy.request.CategoryReq;
import com.kingthy.request.TransferCategoryReq;
import com.kingthy.transaction.ClassCategoryTransaction;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhaochen 2017/8/23.
 */
@Service
public class ClassCategoryTransactionImpl  implements ClassCategoryTransaction {

    /**
     * 节点修改动作
     */
    private static final String edit = "edit";
    /**
     * 禁止转移标识
     */
    private static final int transForbidden = -2;
    /**
     * 节点转移动作
     */
    private static final String transfer = "transfer";

    @Autowired
    private ClassCategoryMapper classCategoryMapper;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public int updateClassCategory(CategoryReq categoryReq) {
        int result = 0;
        Example example = new Example(ClassCategory.class);
        Example.Criteria criteria = example.createCriteria();
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
                Map<String, Object> parentChangeMap = new HashMap<>(16);
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

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int transfer(TransferCategoryReq transferCategoryReq) {
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
        Map<String, Object> parentChangeMap = new HashMap<>(16);
        parentChangeMap.put("source", sourceUuid);
        parentChangeMap.put("target", targetUuid);
        parentChangeMap.put("modifyDate", new Date());
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
        Map<String, Object> gradeChangeMap = new HashMap<>(16);
        gradeChangeMap.put("gradeChange", gradeChange);
        if (uuidString.length > 0)
        {
            gradeChangeMap.put("uuidString", uuidString);
        }
        return gradeChangeMap;
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
        classCategory.setModifyDate(new Date());
        classCategory.setClassName(categoryReq.getClassName());
        classCategory.setDescription(categoryReq.getDescription());
        classCategory.setUuid(categoryReq.getUuid());
        return classCategoryMapper.updateCategory(classCategory);
    }
    private int editStatus(String classCategoryUuid, boolean status)
    {
        String uuids = classCategoryMapper.findChildUuidsByParentId(classCategoryUuid);
        String[] uuidString = uuids.split(",");
        Map<String, Object> paramMap = new HashMap<>(16);
        paramMap.put("uuidString", uuidString);
        paramMap.put("modifyDate", new Date());
        paramMap.put("status", status);
        return classCategoryMapper.updateStatusBatch(paramMap);
    }
}
