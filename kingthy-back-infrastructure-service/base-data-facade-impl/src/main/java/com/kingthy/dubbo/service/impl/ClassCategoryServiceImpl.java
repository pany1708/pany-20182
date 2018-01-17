package com.kingthy.dubbo.service.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingthy.dto.CategoryTreeDto;
import com.kingthy.dto.GoodsParameterCountDTO;
import com.kingthy.dubbo.basedata.service.ClassCategoryDubboService;
import com.kingthy.entity.ClassCategory;
import com.kingthy.mapper.ClassCategoryMapper;
import com.kingthy.request.CategoryReq;
import com.kingthy.request.TransferCategoryReq;
import com.kingthy.response.ClassCategoryResp;
import com.kingthy.transaction.ClassCategoryTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

/**
 * @author zhaochen
 */
@Service(version = "1.0.0",timeout = 3000)
public class ClassCategoryServiceImpl implements ClassCategoryDubboService
{


    /**
     * 顶级节点的父节点id为"0"
     */
    private static final String topNode = "0";


    @Autowired
    private ClassCategoryMapper classCategoryMapper;

    @Autowired
    private ClassCategoryTransaction classCategoryTransaction;

    @Override
    public int insert(ClassCategory classCategory) {
        return classCategoryMapper.insert(classCategory);
    }

    @Override
    public int updateByUuid(ClassCategory classCategory) {
        ClassCategory classCategoryResult = selectByUuid(classCategory.getUuid());
        int currentVersion = classCategoryResult.getVersion();
        Example example = new Example(ClassCategory.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid",classCategory.getUuid());
        criteria.andEqualTo("version",currentVersion);
        classCategory.setVersion(currentVersion+1);
        return classCategoryMapper.updateByExampleSelective(classCategory,example);
    }
    @Override
    public int  update(CategoryReq categoryReq){
        return  classCategoryTransaction.updateClassCategory(categoryReq);
    }
    @Override
    public List<ClassCategory> selectAll() {
        return classCategoryMapper.selectAll();
    }

    @Override
    public ClassCategory selectByUuid(String uuid) {
        return classCategoryMapper.selectByUuid(uuid);
    }

    @Override
    public int selectCount(ClassCategory classCategory) {
        return classCategoryMapper.selectCount(classCategory);
    }

    @Override
    public List<ClassCategory> select(ClassCategory var1) {
        return classCategoryMapper.select(var1);
    }

    @Override
    public ClassCategory selectOne(ClassCategory var1) {
        return classCategoryMapper.selectOne(var1);
    }

    @Override
    public PageInfo<ClassCategory> queryPage(Integer pageNum, Integer pageSize, ClassCategory classCategory) {
        PageHelper.startPage(pageNum, pageSize);
        List<ClassCategory> list = classCategoryMapper.findByPage(classCategory);
        return new PageInfo<>(list);
    }

    @Override
    public int transfer(TransferCategoryReq transferCategoryReq) {
        return classCategoryTransaction.transfer(transferCategoryReq);
    }
    @Override
    public int editStatus(String classCategoryUuid, boolean status)
    {
        String uuids = classCategoryMapper.findChildUuidsByParentId(classCategoryUuid);
        String[] uuidString = uuids.split(",");
        Map<String, Object> paramMap = new HashMap<>(16);
        paramMap.put("uuidString", uuidString);
        paramMap.put("modifyDate", new Date());
        paramMap.put("status", status);
        return classCategoryMapper.updateStatusBatch(paramMap);
    }


    @Override
    public List<CategoryTreeDto> getTree() {

        List<CategoryTreeDto> classCategoryTreeList = classCategoryMapper.getTree();
        return treeProduce(classCategoryTreeList);
    }

    @Override
    public int batchUpdateGoodsNum(List<GoodsParameterCountDTO> list) {
        return classCategoryMapper.batchUpdateGoodsNum(list);
    }

    @Override
    public List<ClassCategory> selectNameByUuids(List<String> uuids) {
        return classCategoryMapper.selectNameByUuids(uuids);
    }

    @Override
    public List<ClassCategoryResp> queryClassCategory() {
        return classCategoryMapper.queryClassCategory();
    }

    @Override
    public int updateByStyleCode(ClassCategory var) {
        Example example = new Example(Override.class);
        example.createCriteria().andEqualTo("code", var.getCode()).andEqualTo("delFlag", false);
        return classCategoryMapper.updateByExampleSelective(var, example);
    }

    @Override
    public int delByStyleCode(ClassCategory var) {
        return updateByStyleCode(var);
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
        List<CategoryTreeDto> rootTrees = new ArrayList<>();
        for (CategoryTreeDto tree : trees)
        {
            if (topNode.equals(tree.getParentId()))
            {
                rootTrees.add(tree);
            }
            for (CategoryTreeDto t : trees)
            {
                if (tree.getUuid().equals(t.getParentId()))
                {
                    if (tree.getChildrens() == null)
                    {
                        List<CategoryTreeDto> myChildrens = new ArrayList<>();
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




}
