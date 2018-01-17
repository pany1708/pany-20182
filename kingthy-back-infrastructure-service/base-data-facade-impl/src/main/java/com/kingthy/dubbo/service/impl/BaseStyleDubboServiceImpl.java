package com.kingthy.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingthy.dto.BaseStyleFileDto;
import com.kingthy.dubbo.basedata.service.BaseStyleDubboService;
import com.kingthy.entity.BaseStyle;
import com.kingthy.mapper.BaseStyleMapper;
import com.kingthy.request.BaseStylePageReq;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author zhaochen
 * @Description: 款式类别
 * @DATE Created by 10:48 on 2017/9/12.
 * @Modified by:
 */
@Service(version = "1.0.0", timeout = 3000, interfaceClass = BaseStyleDubboService.class)
public class BaseStyleDubboServiceImpl implements BaseStyleDubboService {

    @Autowired
    private BaseStyleMapper baseStyleMapper;

    @Override
    public int insert(BaseStyle baseStyle) {
        return baseStyleMapper.insert(baseStyle);
    }

    @Override
    public int updateByUuid(BaseStyle var) {
        Example example = new Example(BaseStyle.class);
        example.createCriteria().andEqualTo("uuid", var.getUuid());
        return baseStyleMapper.updateByExampleSelective(var, example);
    }

    @Override
    public List<BaseStyle> selectAll() {
        return baseStyleMapper.findAllStyle();
    }

    @Override
    public BaseStyle selectByUuid(String uuid) {
        return baseStyleMapper.selectByUuid(uuid);
    }

    @Override
    public PageInfo<BaseStyle> queryPage(BaseStylePageReq baseStylePageReq) {
        PageHelper.startPage(baseStylePageReq.getPageNum(), baseStylePageReq.getPageSize());
        List<BaseStyle> baseStyleList = baseStyleMapper.findByPage(baseStylePageReq);
        return  new PageInfo<>(baseStyleList);
    }

    @Override
    public List<BaseStyleFileDto> findFiles() {
        return baseStyleMapper.findFiles();
    }

    @Override
    public int selectCount(BaseStyle var) {
        return baseStyleMapper.selectCount(var);
    }

    @Override
    public List<BaseStyle> select(BaseStyle var1) {
        return baseStyleMapper.select(var1);
    }

    @Override
    public BaseStyle selectOne(BaseStyle var1) {
        return baseStyleMapper.selectOne(var1);
    }

    @Override
    public PageInfo<BaseStyle> queryPage(Integer pageNum, Integer pageSize, BaseStyle baseStyle) {

        PageHelper.startPage(pageNum, pageSize);

        return new PageInfo<>(baseStyleMapper.select(baseStyle));
    }

    @Override
    public int updateByStyleSn(BaseStyle var) {
        Example example = new Example(BaseStyle.class);
        example.createCriteria().andEqualTo("styleSn", var.getStyleSn()).andEqualTo("delFlag", false);
        return baseStyleMapper.updateByExampleSelective(var, example);
    }

    @Override
    public int deleteByStyleSn(BaseStyle var) {
        return updateByStyleSn(var);
    }
}
