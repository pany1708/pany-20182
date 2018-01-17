package com.kingthy.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.kingthy.dubbo.user.service.BodyModelDubboService;
import com.kingthy.entity.BodyModel;
import com.kingthy.exception.BizException;
import com.kingthy.mapper.BodyModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * 体型数据操作
 * @author  likejie
 * @date 2017/8/7.
 */
@Service(version = "1.0.0", timeout = 10000)
public class BodyModelServiceImpl implements BodyModelDubboService {

    @Autowired
    private BodyModelMapper mapper;

    /**
     * 未被调用，若调用需编写SQL查询
     * 
     * @param bodyModel
     * @return
     */
    @Override
    public List<BodyModel> getBodyModelList(BodyModel bodyModel) {
        return mapper.select(bodyModel);
    }

    @Override
    public int createBodyModel(BodyModel bodyModel)
    {
        Date date = new Date();
        bodyModel.setCreateDate(date);
        bodyModel.setModifyDate(date);
        bodyModel.setDelFlag(false);
        int result = mapper.insertSelective(bodyModel);
        if(result == 0){
            throw new BizException("创建模型失败");
        }
        return result;
    }

    @Override
    public int deleteBodyModel(String uuid)
    {
        Example example = new Example(BodyModel.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid",uuid);
        BodyModel bodyModel = new BodyModel();
        bodyModel.setDelFlag(true);
        int result = mapper.updateByExampleSelective(bodyModel,example);
        if(result == 0){
            throw new BizException("修改模型失败");
        }
        return result;
    }
    @Override
    public int updateBodyModel(BodyModel bodyModel)
    {
        Example example = new Example(BodyModel.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid",bodyModel.getUuid());
        criteria.andEqualTo("delFlag",false);
        int result = mapper.updateByExampleSelective(bodyModel,example);
        if(result == 0){
            throw new BizException("修改模型失败");
        }
        return result;
    }
}
