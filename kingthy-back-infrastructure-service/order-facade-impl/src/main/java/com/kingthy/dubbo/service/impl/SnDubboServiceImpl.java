package com.kingthy.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingthy.dubbo.order.service.SnDubboService;
import com.kingthy.entity.Sn;
import com.kingthy.mapper.SnMapper;
import com.kingthy.response.WebApiResponse;
import com.kingthy.service.TbSnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author xumin
 * @Description:
 * @DATE Created by 14:39 on 2017/8/8.
 * @Modified by:
 */
@Service(version = "1.0.0", timeout = 10000)
public class SnDubboServiceImpl implements SnDubboService
{

    @Autowired
    private SnMapper snMapper;

    @Autowired
    private TbSnService tbSnService;

    @Override
    public int insert(Sn sn) {
        return snMapper.insert(sn);
    }

    @Override
    public int updateByUuid(Sn sn) {
        return 0;
    }

    @Override
    public List<Sn> selectAll() {
        return snMapper.selectAll();
    }

    @Override
    public Sn selectByUuid(String uuid) {
        return null;
    }

    @Override
    public int selectCount(Sn sn) {
        return snMapper.selectCount(sn);
    }

    @Override
    public List<Sn> select(Sn var1) {
        return snMapper.select(var1);
    }

    @Override
    public Sn selectOne(Sn var1) {
        return snMapper.selectOne(var1);
    }

    @Override
    public PageInfo<Sn> queryPage(Integer pageNum, Integer pageSize, Sn sn) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(snMapper.select(sn));
    }

    @Override
    public int updateLastValue(Sn sn) {
        return snMapper.updateLastValue(sn);
    }

    @Override
    public WebApiResponse<String> generateSn(String type) {

        if (!StringUtils.isEmpty(type))
        {
            String sn = tbSnService.generateSn(type);

            if ("error".equals(sn))
            {
                return new WebApiResponse<String>().error("未定义的类型");
            }
            return new WebApiResponse<String>().success(sn);
        }
        else
        {
            return new WebApiResponse<String>().error("type is null");
        }

    }
}
