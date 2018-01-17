package com.kingthy.platform.service.impl.basedata;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingthy.exception.BizException;
import com.kingthy.platform.dto.basedata.AreaDto;
import com.kingthy.platform.entity.basedata.Area;
import com.kingthy.platform.mapper.basedata.AreaMapper;
import com.kingthy.platform.service.basedata.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * AreaServiceImpl(描述其作用)
 * <p>
 * 赵生辉 2017年07月06日 14:42
 *
 * @version 1.0.0
 */
@Service("areaService")
public class AreaServiceImpl implements AreaService
{

    @Autowired
    private AreaMapper areaMapper;

    @Override
    public int createArea(Area area)
    {

        Date currentDate = new Date();
        area.setCreateDate(currentDate);
        area.setModifyDate(currentDate);
        area.setCreator("Creator");
        area.setModifier("Modifier");
        area.setVersion(1);
        int result = areaMapper.insertSelective(area);
        if(result == 0)
        {
            throw new BizException("添加地区失败");
        }
        return result;
    }

    @Override
    public Area queryAreaInfo(int id)
    {
        return areaMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo<Area> queryAreaInfo(int pageNum ,int pageSize ,Area area)
    {
        Example example = new Example(Area.class);
        Example.Criteria criteria = example.createCriteria();
        if(area.getGrade() != null){
            criteria.andEqualTo("grade",area.getGrade());
        }
        if(area.getAreaParentId() != null)
        {
            criteria.andEqualTo("areaParentId",area.getAreaParentId());
        }
        if(area.getName() != null)
        {
            criteria.andEqualTo("name",area.getName());
        }
        PageHelper.startPage(pageNum,pageSize);
        List<Area> areaList = areaMapper.selectByExample(example);

        PageInfo<Area> result = new PageInfo<>(areaList);
        return result;
    }

    @Override
    public List<Area> queryArea(Area area)
    {
        Example example = new Example(Area.class);
        Example.Criteria criteria = example.createCriteria();
        if(area.getGrade() != null){
            criteria.andEqualTo("grade",area.getGrade());
        }
        if(area.getAreaParentId() != null)
        {
            criteria.andEqualTo("areaParentId",area.getAreaParentId());
        }
        if(area.getName() != null)
        {
            criteria.andEqualTo("name",area.getName());
        }
        List<Area> areaList = areaMapper.selectByExample(example);
        if(areaList == null || areaList.size() == 0){
            throw new BizException("查询地址失败");
        }
        return areaList;
    }

    @Override
    public PageInfo<Area> queryParentAreaInfo(int pageNum, int pageSize,int parentId)
    {
        Area area = areaMapper.selectByPrimaryKey(parentId);
        Example example = new Example(Area.class);
        Example.Criteria criteria = example.createCriteria();
        /*if(area.getGrade() == 0)
        {
           throw new BizException("当前地区已经是最上级");
        }
        else*/
        if(area.getGrade() == 0)
        {
            criteria.andEqualTo("grade",0);
        }
        else
        {
            criteria.andEqualTo("areaParentId",area.getAreaParentId());
        }

        PageHelper.startPage(pageNum,pageSize);
        List<Area> areaList = areaMapper.selectByExample(example);
        if(areaList == null || areaList.size() == 0){
        }
        PageInfo<Area> result = new PageInfo<>(areaList);
        return result;
    }

    @Override
    public int updateAreaInfo(Area area)
    {

        Example example = new Example(Area.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id",area.getId());
        int result = areaMapper.updateByExampleSelective(area,example);
        if(result == 0){
            throw new BizException("修改地区失败");
        }
        return result;
    }

    @Override
    public int deleteAreaInfo(int id)
    {
        int result;
        Area area = areaMapper.selectByPrimaryKey(id);
        if(area != null)
        {
            Example example = new Example(Area.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("areaParentId",id);
            List <Area> areaList = areaMapper.selectByExample(example);
            for(Area areaChild :areaList)
            {
                deleteAreaInfo(areaChild.getId());
            }
            areaMapper.deleteByExample(example);
            result = areaMapper.deleteByPrimaryKey(id);
            if(result == 0)
            {
                throw new BizException("删除地区失败");
            }
        }
        else
        {
            throw new BizException("该地区不存在");
        }

        return result;
    }

    @Override
    public List<AreaDto> queryAreaAll()
    {
        return areaMapper.selectArea();
    }
}
