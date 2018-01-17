package com.kingthy.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingthy.dto.GoodsDTO;
import com.kingthy.dubbo.user.service.FigureDubboService;
import com.kingthy.entity.Figure;
import com.kingthy.mapper.FigureMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author  xumin
 * @Description:
 * @DATE Created by 15:18 on 2017/8/10.
 * @Modified by:
 */
@Service(version = "1.0.0", timeout = 10000)
public class FigureDubboServiceImpl implements FigureDubboService {
    private static final Logger LOG = LoggerFactory.getLogger(FigureDubboServiceImpl.class);
    @Autowired
    private FigureMapper figureMapper;

    @Override
    public int insert(Figure figure) {
        try {
            return figureMapper.insertSelective(figure);
        }catch (Exception ex) {
            LOG.error(ex.toString());
            throw ex;
        }
    }

    @Override
    public int updateByUuid(Figure var) {
        try {
            Example example = new Example(Figure.class);
            example.createCriteria().andEqualTo("uuid", var.getUuid());
            return figureMapper.updateByExampleSelective(var, example);
        }catch (Exception ex) {
            LOG.error(ex.toString());
            throw ex;
        }
    }

    @Override
    public List<Figure> selectAll() {
        try {
            return figureMapper.selectAll();
        }catch (Exception ex) {
            LOG.error(ex.toString());
            throw ex;
        }
    }

    @Override
    public Figure selectByUuid(String uuid) {
        try {
            Figure cond=new Figure();
            cond.setUuid(uuid);
            return figureMapper.selectOne(cond);
        }catch (Exception ex) {
            LOG.error(ex.toString());
            throw ex;
        }
    }

    /**
     * 未被调用，若调用需编写SQL查询
     * 
     * @param figure
     * @return
     */
    @Override
    public int selectCount(Figure figure) {
        try {
            return figureMapper.selectCount(figure);
        }catch (Exception ex) {
            LOG.error(ex.toString());
            throw ex;
        }
    }

    @Override
    public List<Figure> select(Figure var1) {
        try {
            return figureMapper.select(var1);
        }catch (Exception ex) {
            LOG.error(ex.toString());
            throw ex;
        }
    }

    /**
     * 未被调用，若调用需编写SQL查询
     * 
     * @param var1
     * @return
     */
    @Override
    public Figure selectOne(Figure var1) {
        try {
            return figureMapper.selectOne(var1);
        }catch (Exception ex) {
            LOG.error(ex.toString());
            throw ex;
        }
    }

    @Override
    public PageInfo<Figure> queryPage(Integer pageNum, Integer pageSize, Figure figure) {
        try {
            PageHelper.startPage(pageNum, pageSize);
            return new PageInfo<>(figureMapper.select(figure));
        }catch (Exception ex) {
            LOG.error(ex.toString());
            throw ex;
        }
    }

    @Override
    public List<Figure> selectInUuid(List<String> listUuid) {
        try {
            return figureMapper.queryBath(listUuid);
        }catch (Exception ex) {
            LOG.error(ex.toString());
            throw ex;
        }
    }

    @Override
    public List<GoodsDTO.FigureInfo> selectFigureByMemberUuid(String memberUuid) {
        try {
            return figureMapper.selectFigureByMemberUuid(memberUuid);
        }catch (Exception ex) {
            LOG.error(ex.toString());
            throw ex;
        }
    }
}
