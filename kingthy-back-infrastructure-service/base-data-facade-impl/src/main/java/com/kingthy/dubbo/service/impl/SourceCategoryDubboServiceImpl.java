package com.kingthy.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingthy.dto.SourceTypeDTO;
import com.kingthy.dubbo.basedata.service.SourceCategoryDubboService;
import com.kingthy.entity.SourceCategory;
import com.kingthy.mapper.SourceCategoryMapper;
import com.kingthy.response.QuerySubCategoryResp;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * Created by kingthy on 2018/1/4.
 */
@Service(version = "1.0.0", timeout = 3000)
public class SourceCategoryDubboServiceImpl implements SourceCategoryDubboService {

    @Autowired
    private transient SourceCategoryMapper sourceCategoryMapper;

    @Override
    public int insert(SourceCategory sourceCategory) {
        sourceCategory.setVersion(1);

        if (null == sourceCategory.getDelFlag()) {
            sourceCategory.setDelFlag(false);
        }

        if (null == sourceCategory.getChangeSize()) {
            sourceCategory.setChangeSize(false);
        }

        if (null == sourceCategory.getCreateDate()) {
            sourceCategory.setCreateDate(new Date());
        }

        if (null == sourceCategory.getModifyDate()) {
            sourceCategory.setModifyDate(new Date());
        }

        int result = sourceCategoryMapper.insert(sourceCategory);

        //修改path
        SourceCategory tempSourceCategory = new SourceCategory();
        tempSourceCategory.setUuid(sourceCategory.getUuid());
        tempSourceCategory.setPath(sourceCategory.getPath() + sourceCategory.getUuid());
        this.updateByUuid(tempSourceCategory);

        return result;
    }

    @Override
    public int updateByUuid(SourceCategory sourceCategory) {
        SourceCategory sourceCategoryResult = selectByUuid(sourceCategory.getUuid());
        Integer currentVersion = sourceCategoryResult.getVersion();
        Example example = new Example(SourceCategory.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid", sourceCategory.getUuid());
        //criteria.andEqualTo("delFlag",false);
        criteria.andEqualTo("version", currentVersion);
        sourceCategory.setVersion(currentVersion + 1);

        return sourceCategoryMapper.updateByExampleSelective(sourceCategory, example);
    }

    @Override
    public List<SourceCategory> selectAll() {
        return sourceCategoryMapper.selectAll();
    }

    @Override
    public SourceCategory selectByUuid(String uuid) {

        SourceCategory var = new SourceCategory();
        var.setUuid(uuid);
        return sourceCategoryMapper.selectOne(var);

    }

    @Override
    public int selectCount(SourceCategory sourceCategory) {
        return sourceCategoryMapper.selectCount(sourceCategory);
    }

    @Override
    public List<SourceCategory> select(SourceCategory var1) {
        return sourceCategoryMapper.select(var1);
    }

    @Override
    public SourceCategory selectOne(SourceCategory var1) {
        return sourceCategoryMapper.selectOne(var1);
    }

    @Override
    public PageInfo<SourceCategory> queryPage(Integer pageNum, Integer pageSize, SourceCategory sourceCategory) {
        PageHelper.startPage(pageNum, pageSize);
        List<SourceCategory> result = sourceCategoryMapper.findByPage(sourceCategory);
        return new PageInfo<>(result);
    }

    @Override
    public List<SourceTypeDTO> querySourceByType(Integer type) {
        return sourceCategoryMapper.querySourceByType(type);
    }

    @Override
    public List<QuerySubCategoryResp> querySubCategory(int categoryType) {
        List<QuerySubCategoryResp> result = sourceCategoryMapper.querySubCategory(categoryType);
        if (result != null && result.size() > 0) {
            return result;
        } else {
            result.add(new QuerySubCategoryResp());
            return result;
        }
    }
}
