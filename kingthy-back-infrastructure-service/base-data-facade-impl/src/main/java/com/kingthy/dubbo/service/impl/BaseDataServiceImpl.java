package com.kingthy.dubbo.service.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingthy.dubbo.basedata.service.BaseDataDubboService;
import com.kingthy.entity.BaseData;
import com.kingthy.exception.BizException;
import com.kingthy.mapper.BaseDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import java.util.Date;
import java.util.List;

/**
 * BaseDataServiceImpl(描述其作用)
 * @author zhaochen 2017年07月05日 10:20
 *
 * @version 1.0.0
 */
@Service(version = "1.0.0",timeout = 3000)
public class BaseDataServiceImpl implements BaseDataDubboService
{

    @Autowired
    private BaseDataMapper baseDataMapper;

    @Override
    public int insert(BaseData baseData) {
        return baseDataMapper.insert(baseData);
    }

    @Override
    public int updateByUuid(BaseData baseData) {
        BaseData baseDataResult = selectByUuid(baseData.getUuid());
        Integer currentVersion = baseDataResult.getVersion();
        Example example = new Example(BaseData.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid",baseData.getUuid());
        criteria.andEqualTo("version",currentVersion);
        baseData.setVersion(currentVersion+1);
        int result = baseDataMapper.updateByExampleSelective(baseData,example);
        if(result == 0)
        {
            throw new BizException("更新数据失败");
        }
        return result;
    }

    @Override
    public List<BaseData> selectAll() {
        return baseDataMapper.selectAll();
    }

    @Override
    public BaseData selectByUuid(String uuid) {
        return baseDataMapper.selectByUuid(uuid);
    }

    @Override
    public int selectCount(BaseData baseData) {
        return 0;
    }

    @Override
    public List<BaseData> select(BaseData var1) {
        return null;
    }

    @Override
    public BaseData selectOne(BaseData var1) {
        return null;
    }

    @Override
    public PageInfo<BaseData> queryPage(Integer pageNum, Integer pageSize, BaseData baseData) {
        PageHelper.startPage(pageNum,pageSize);
        List<BaseData> result = baseDataMapper.findByPage(baseData);
        if(result == null)
        {
            throw new BizException("该类型数据不存在");
        }
        PageInfo<BaseData> baseDataPageInfo = new PageInfo<>(result);
        return baseDataPageInfo;
    }

    @Override
    public List<String> queryExchangeReason() {
        return baseDataMapper.queryExchangeReason();
    }
    @Override
    public List<BaseData> queryBaseData(BaseData baseData)
    {
        List<BaseData> result =baseDataMapper.findByPage(baseData);
        if(result == null)
        {
            throw new BizException("该类型数据不存在");
        }
        return result;
    }
    @Override
    public int createBaseData(BaseData baseData)
    {
        Example example = new Example(BaseData.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("className", baseData.getClassName());
        criteria.andEqualTo("delFlag", false);
        /* 判断是否已存在相同名称的部件 */
        if (baseDataMapper.selectCountByExample(example) > 0)
        {
            return -1;
        }
        Date currentDate = new Date();
        baseData.setCreateDate(currentDate);
        baseData.setModifyDate(currentDate);
        baseData.setCreator("Creator");
        baseData.setModifier("Modifier");
        baseData.setDelFlag(false);
        baseData.setVersion(1);
        // 判断是否有上级节点来确定当前节点的级别
        if (baseData.getParentId() != null)
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
        //清除子类的数据
        if(baseData.getGrade() == 0)
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

}
