package com.kingthy.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingthy.dto.GoodsParameterCountDTO;
import com.kingthy.dubbo.basedata.service.CoatCategoryDubboService;
import com.kingthy.entity.CoatCategory;
import com.kingthy.exception.BizException;
import com.kingthy.mapper.CoatCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * CoatCategoryServiceImpl(描述其作用)
 * <p>
 * @author 赵生辉 2017年07月05日 18:33
 *
 * @version 1.0.0
 */
@Service(version = "1.0.0",timeout = 3000)
public class CoatCategoryServiceImpl implements CoatCategoryDubboService
{

    private static String PARENT_ID="0";
    @Autowired
    private CoatCategoryMapper coatCategoryMapper;

    @Override
    public int insert(CoatCategory coatCategory) {


        Example example = new Example(CoatCategory.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("className", coatCategory.getClassName());
        criteria.andEqualTo("delFlag", false);
        /* 判断是否已存在相同名称的数据 */
        if (coatCategoryMapper.selectCountByExample(example) > 0)
        {
            return -1;
        }
        Date currentDate = new Date();
        coatCategory.setCreateDate(currentDate);
        coatCategory.setModifyDate(currentDate);
        coatCategory.setCreator("Creator");
        coatCategory.setModifier("Modifier");
        coatCategory.setDelFlag(false);
        coatCategory.setVersion(1);
        // 判断是否有上级节点来确定当前节点的级别
        if (coatCategory.getParentId() != null)
        {
            Example exampleParent = new Example(CoatCategory.class);
            Example.Criteria criteriaParent = exampleParent.createCriteria();
            criteriaParent.andEqualTo("uuid", coatCategory.getParentId());
            criteriaParent.andEqualTo("delFlag",false);
            int grade = 0;
            if (PARENT_ID.equals(coatCategory.getParentId()))
            {
                grade = -1;
            }
            else
            {
                List<CoatCategory> partsCategories = coatCategoryMapper.selectByExample(exampleParent);
                if (partsCategories.size() == 0)
                {
                    throw new BizException("该父类部件不存在");
                }
                else
                {
                    grade = coatCategoryMapper.selectByExample(exampleParent).get(0).getGrade();
                }
            }
            coatCategory.setGrade(grade + 1);
        }
        int result = coatCategoryMapper.insertSelective(coatCategory);
        if(result == 0){
            throw new BizException("创建上衣类型失败");
        }
        return result;
    }

    @Override
    public int updateByUuid(CoatCategory coatCategory) {
        CoatCategory coatCategoryResult = selectByUuid(coatCategory.getUuid());
        Integer currentVersion = coatCategoryResult.getVersion();
        Example example = new Example(CoatCategory.class);
        Example.Criteria  criteria = example.createCriteria();
        criteria.andEqualTo("uuid",coatCategory.getUuid());
        criteria.andEqualTo("version",currentVersion);
        coatCategory.setVersion(currentVersion+1);
        return coatCategoryMapper.updateByExampleSelective(coatCategory,example);
    }
    @Override
    public int deleteCoatCategory(String uuid)
    {
        Example example = new Example(CoatCategory.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid",uuid);
        int result = coatCategoryMapper.deleteByExample(example);
        if(result == 0)
        {
            throw new BizException("删除数据失败");
        }

        return result;
    }

    @Override
    public int batchUpdateGoodsNum(List<GoodsParameterCountDTO> list) {
        return coatCategoryMapper.batchUpdateGoodsNum( list);
    }

    @Override
    public List<CoatCategory> selectAll() {
        return coatCategoryMapper.findCoatCategoriesAll();
    }
    @Override
    public CoatCategory selectByUuid(String uuid) {
        return coatCategoryMapper.selectByUuid(uuid);
    }

    @Override
    public int selectCount(CoatCategory coatCategory) {
        return 0;
    }

    @Override
    public List<CoatCategory> select(CoatCategory var1) {
        return null;
    }

    @Override
    public CoatCategory selectOne(CoatCategory var1) {
        return null;
    }

    @Override
    public PageInfo<CoatCategory> queryPage(Integer pageNum, Integer pageSize, CoatCategory coatCategory) {

        PageHelper.startPage(pageNum,pageSize);
        List<CoatCategory> result = coatCategoryMapper.findByPage(coatCategory);
        if(result == null)
        {
            throw new BizException("没有找到指定的数据");
        }
        return new PageInfo<>(result);

    }
}
