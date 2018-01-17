package com.kingthy.dubbo.service.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingthy.dto.GoodsParameterCountDTO;
import com.kingthy.dubbo.basedata.service.PantsCategoryDubboService;
import com.kingthy.entity.PantsCategory;
import com.kingthy.exception.BizException;
import com.kingthy.mapper.PantsCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * PantsCategoryServiceImpl(描述其作用)
 * @author zhaochen 2017年07月10日 10:01
 *
 * @version 1.0.0
 */
@Service(version = "1.0.0",timeout = 3000)
public class PantsCategoryServiceImpl implements PantsCategoryDubboService
{
    @Autowired
    private PantsCategoryMapper pantsCategoryMapper;

    @Override
    public int insert(PantsCategory pantsCategory) {
        Example example = new Example(PantsCategory.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("className", pantsCategory.getClassName());
        criteria.andEqualTo("delFlag", false);
        /* 判断是否已存在相同名称的数据 */
        if (pantsCategoryMapper.selectCountByExample(example) > 0)
        {
            return -1;
        }
        Date currentDate = new Date();
        pantsCategory.setCreateDate(currentDate);
        pantsCategory.setModifyDate(currentDate);
        pantsCategory.setCreator("Creator");
        pantsCategory.setModifier("Modifier");
        pantsCategory.setDelFlag(false);
        pantsCategory.setVersion(1);
        int result = pantsCategoryMapper.insertSelective(pantsCategory);
        if(result == 0){
            throw new BizException("创建上衣类型失败");
        }
        return result;
    }

    @Override
    public int updateByUuid(PantsCategory pantsCategory) {
        PantsCategory pantsCategoryResult = selectByUuid(pantsCategory.getUuid());
        Integer currentVersion = pantsCategoryResult.getVersion();
        Example example = new Example(PantsCategory.class);
        Example.Criteria  criteria = example.createCriteria();
        criteria.andEqualTo("uuid",pantsCategory.getUuid());
        criteria.andEqualTo("version",currentVersion);
        pantsCategory.setVersion(currentVersion+1);
        return pantsCategoryMapper.updateByExampleSelective(pantsCategory,example);
    }
    @Override
    public int deletePantsCategory(String uuid)
    {
        Example example = new Example(PantsCategory.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid",uuid);
        int result = pantsCategoryMapper.deleteByExample(example);
        if(result == 0)
        {
            throw new BizException("删除数据失败");
        }

        return result;
    }

    @Override
    public int batchUpdateGoodsNum(List<GoodsParameterCountDTO> list) {
        return pantsCategoryMapper.batchUpdateGoodsNum(list);
    }

    @Override
    public List<PantsCategory> selectAll() {
        return pantsCategoryMapper.selectAllPantsCategoryUuids();
    }

    @Override
    public PantsCategory selectByUuid(String uuid) {
        return pantsCategoryMapper.selectByUuid(uuid);
    }

    @Override
    public int selectCount(PantsCategory pantsCategory) {
        return 0;
    }

    @Override
    public List<PantsCategory> select(PantsCategory var1) {
        return null;
    }

    @Override
    public PantsCategory selectOne(PantsCategory var1) {
        return null;
    }

    @Override
    public PageInfo<PantsCategory> queryPage(Integer pageNum, Integer pageSize, PantsCategory pantsCategory) {
        PageHelper.startPage(pageNum,pageSize);
        List<PantsCategory> result = pantsCategoryMapper.findByPage(pantsCategory);
        return new PageInfo<>(result);
    }
}
