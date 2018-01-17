/**
 * 系统项目名称
 * com.kingthy.platform.service.impl.basedata
 * MaterielServiceImpl.java
 * 
 * 2017年3月29日-下午4:24:19
 *  2017金昔科技有限公司-版权所有
 *
 */
package com.kingthy.dubbo.service.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingthy.dto.GoodsParameterCountDTO;
import com.kingthy.dto.MaterieDto;
import com.kingthy.dubbo.basedata.service.MaterielCategoryDubboService;
import com.kingthy.entity.MaterielCategory;
import com.kingthy.mapper.MaterielCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 *
 * MaterielServiceImpl
 * 
 * yuanml 2017年3月29日 下午4:24:19
 * 
 * @version 1.0.0
 *
 */
@Service(version = "1.0.0",timeout = 3000)
public class MaterielCategoryServiceImpl implements MaterielCategoryDubboService
{
    @Autowired
    private transient MaterielCategoryMapper materielCategoryMapper;

    @Override
    public int insert(MaterielCategory materielCategory) {
        materielCategory.setVersion(1);
        return materielCategoryMapper.insert(materielCategory);
    }

    @Override
    public int updateByUuid(MaterielCategory materielCategory) {
        MaterielCategory materielCategoryResult = selectByUuid(materielCategory.getUuid());
        Integer currentVersion = materielCategoryResult.getVersion();
        Example example = new Example(MaterielCategory.class);
        Example.Criteria  criteria = example.createCriteria();
        criteria.andEqualTo("uuid",materielCategory.getUuid());
        criteria.andEqualTo("version",currentVersion);
        materielCategory.setVersion(currentVersion+1);
        return materielCategoryMapper.updateByExampleSelective(materielCategory,example);
    }

    @Override
    public List<MaterielCategory> selectAll() {
        return materielCategoryMapper.selectAllUuid();
    }

    @Override
    public MaterielCategory selectByUuid(String uuid) {
        return materielCategoryMapper.selectByUuid(uuid);
    }

    @Override
    public int selectCount(MaterielCategory materielCategory) {
        return materielCategoryMapper.selectCount(materielCategory);
    }

    @Override
    public List<MaterielCategory> select(MaterielCategory var1) {
        return materielCategoryMapper.select(var1);
    }

    @Override
    public MaterielCategory selectOne(MaterielCategory var1) {
        return materielCategoryMapper.selectOne(var1);
    }

    @Override
    public PageInfo<MaterielCategory> queryPage(Integer pageNum, Integer pageSize, MaterielCategory materielCategory) {
        PageHelper.startPage(pageNum,pageSize);
        List<MaterielCategory> result = materielCategoryMapper.findByPage(materielCategory);
        return new PageInfo<>(result);
    }

    @Override
    public int deleteMaterielCategory(String uuid) {
        MaterielCategory materielCategory = new MaterielCategory();
        materielCategory.setUuid(uuid);
        materielCategory.setDelFlag(true);
        materielCategory.setModifyDate(new Date());
        return updateByUuid(materielCategory);
    }
    @Override
    public int batchUpdateGoodsNum(List<GoodsParameterCountDTO> list) {
        return materielCategoryMapper.batchUpdateGoodsNum(list);
    }

    @Override
    public String insertReturnUuid(MaterielCategory materielCategory) {
        materielCategory.setVersion(1);
        int result = materielCategoryMapper.insert(materielCategory);

        return result == 0 ? null : materielCategory.getUuid();
    }

    @Override
    public int updateByCode(MaterielCategory var){
        Example example = new Example(MaterielCategory.class);
        example.createCriteria().andEqualTo("code", var.getCode()).andEqualTo("delFlag", false);
        return materielCategoryMapper.updateByExampleSelective(var, example);
    }

    @Override
    public int deleteByCode(MaterielCategory var) {
        return updateByCode(var);
    }

    @Override
    public List<MaterieDto> findMaterielNameByMaterielUuid(List<String> materielUuidList) {
        return materielCategoryMapper.findMaterielNameByMaterielUuid(materielUuidList);
    }
}
