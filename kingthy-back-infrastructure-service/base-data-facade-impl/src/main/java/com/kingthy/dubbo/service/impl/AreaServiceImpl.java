package com.kingthy.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingthy.dto.CityInfoDTO;
import com.kingthy.dubbo.basedata.service.AreaDubboService;
import com.kingthy.entity.Area;
import com.kingthy.exception.BizException;
import com.kingthy.mapper.AreaMapper;
import com.kingthy.dto.AreaDto;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * AreaServiceImpl(描述其作用)
 * @author zhaochen 2017年07月06日 14:42
 *
 * @version 1.0.0
 */
@Service(version = "1.0.0",timeout = 3000)
public class AreaServiceImpl implements AreaDubboService
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
        return areaMapper.findById(id);
    }

    @Override
    public PageInfo<Area> queryAreaInfo(int pageNum ,int pageSize ,Area area)
    {
        PageHelper.startPage(pageNum,pageSize);
        List<Area> areaList = areaMapper.queryAreaInfoList(area);
        return new PageInfo<>(areaList);
    }

    @Override
    public List<Area> queryArea(Area area)
    {
        List<Area> areaList = areaMapper.queryAreaInfoList(area);
        if(areaList == null || areaList.size() == 0){
            throw new BizException("查询地址失败");
        }
        return areaList;
    }

    @Override
    public PageInfo<Area> queryParentAreaInfo(int pageNum, int pageSize,int parentId)
    {
        Area area = areaMapper.findById(parentId);
        PageHelper.startPage(pageNum,pageSize);
        List<Area> areaList = areaMapper.queryParentAreaInfoList(area);
        return new PageInfo<>(areaList);
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

    @Override
    public CityInfoDTO selectCityInfoByAreaId(Integer areaUuid) {
        return areaMapper.selectCityInfoByAreaId(areaUuid);
    }
}
