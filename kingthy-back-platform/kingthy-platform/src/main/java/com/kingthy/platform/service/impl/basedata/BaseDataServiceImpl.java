package com.kingthy.platform.service.impl.basedata;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingthy.exception.BizException;
import com.kingthy.platform.entity.basedata.BaseData;
import com.kingthy.platform.mapper.basedata.BaseDataMapper;
import com.kingthy.platform.service.basedata.BaseDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * BaseDataServiceImpl(描述其作用)
 * <p>
 * 赵生辉 2017年07月05日 10:20
 *
 * @version 1.0.0
 */
@Service(value = "baseDataService")
public class BaseDataServiceImpl implements BaseDataService
{

    @Autowired
    private BaseDataMapper baseDataMapper;

    @Override
    public PageInfo<BaseData> queryBaseData(int pageNum,int pageSize,BaseData baseData)
    {
        Example example = new Example(BaseData.class);
        Example.Criteria criteria = example.createCriteria();
        if(baseData.getType() != null)
        {
            criteria.andEqualTo("type",baseData.getType());
        }
        if(baseData.getParentId() != null)
        {
            criteria.andEqualTo("parentId",baseData.getParentId());
        }
        if(baseData.getGrade() != null)
        {
            criteria.andEqualTo("grade",baseData.getGrade());
        }
        PageHelper.startPage(pageNum,pageSize);
        List<BaseData> result = baseDataMapper.selectByExample(example);

        if(result == null)
        {
            throw new BizException("该类型数据不存在");
        }
        PageInfo<BaseData> baseDataPageInfo = new PageInfo<>(result);
        return baseDataPageInfo;
    }

    @Transactional
    @Override
    public int createBaseData(BaseData baseData)
    {
        Example example = new Example(BaseData.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("className", baseData.getClassName());
        criteria.andEqualTo("type", baseData.getType());
        criteria.andEqualTo("delFlag", false);
        /* 判断是否已存在相同名称的数据 */
        if (baseDataMapper.selectCountByExample(example) > 0)
        {
            throw new BizException("已存在相同名称的数据");
        }
        Date currentDate = new Date();
        baseData.setCreateDate(currentDate);
        baseData.setModifyDate(currentDate);
        baseData.setCreator("Creator");
        baseData.setModifier("Modifier");
        baseData.setDelFlag(false);
        baseData.setVersion(1);
        if (baseData.getParentId() != null)// 判断是否有上级节点来确定当前节点的级别
        {
            Example exampleParent = new Example(BaseData.class);
            Example.Criteria criteriaParent = exampleParent.createCriteria();
            criteriaParent.andEqualTo("uuid", baseData.getParentId());
            criteriaParent.andEqualTo("delFlag",false);
            int grade = 0;
            if (baseData.getParentId().equals("0"))
            {
                grade = -1;
            }
            else
            {
                List<BaseData> partsCategories = baseDataMapper.selectByExample(exampleParent);
                if (partsCategories.size() == 0)
                {
                    throw new BizException("该父类部件不存在");
                }
                else
                {
                    grade = baseDataMapper.selectByExample(exampleParent).get(0).getGrade();
                }
            }
            baseData.setGrade(grade + 1);
        }
        int result = baseDataMapper.insertSelective(baseData);
        return result;
    }

    @Override
    public int deleteBaseData(String uuid)
    {
        Example example = new Example(BaseData.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid",uuid);
        BaseData baseData = baseDataMapper.selectByExample(example).get(0);
        if(baseData.getGrade() == 0)//清除子类的数据
        {
            Example childExample = new Example(BaseData.class);
            Example.Criteria childCriteria = childExample.createCriteria();
            childCriteria.andEqualTo("parentId",baseData.getUuid());
            baseDataMapper.deleteByExample(childExample);
        }
        int result = baseDataMapper.deleteByExample(example);
        if(result == 0)
        {
            throw new BizException("删除数据失败");
        }
        return result;
    }

    @Override
    public int updateBaseData(BaseData baseData)
    {
        Example example = new Example(BaseData.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid",baseData.getUuid());
        int result = baseDataMapper.updateByExampleSelective(baseData,example);
        return result;
    }

    @Override
    public BaseData queryBaseDataInfo(String uuid)
    {
        Example example = new Example(BaseData.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid",uuid);
        BaseData result = null;
        try
        {
            result = baseDataMapper.selectByExample(example).get(0);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        if(result == null){
            throw new BizException("没有找到基础数据");
        }
        return result;
    }
}
