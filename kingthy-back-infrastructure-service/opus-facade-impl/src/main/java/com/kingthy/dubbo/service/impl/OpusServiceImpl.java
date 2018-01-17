package com.kingthy.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingthy.conf.MyBatisConfig;
import com.kingthy.dubbo.opus.service.OpusDubboService;
import com.kingthy.entity.Opus;
import com.kingthy.exception.BizException;
import com.kingthy.mapper.OpusMapper;
import com.kingthy.request.OpusSearchReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * opusServiceImpl(描述其作用)
 * <p>
 * @author 赵生辉 2017年08月02日 15:35
 *
 * @version 1.0.0
 */
@Service(version="1.0.0", timeout = 10000)
public class OpusServiceImpl implements OpusDubboService
{
    private static final Logger LOG = LoggerFactory.getLogger(OpusServiceImpl.class);

    @Autowired
    private OpusMapper opusMapper;

    private static final int VERSION = 1;

    @Override
    public int insert(Opus opus)
    {
        Date date = new Date();
        opus.setCreateDate(date);
        opus.setModifyDate(date);
        opus.setVersion(VERSION);
        opus.setDelFlag(false);
        int result;
        try
        {
            result = opusMapper.insertSelective(opus);
        }
        catch (Exception e)
        {
            throw new BizException("添加作品失败");
        }
        return result;
    }

    @Override
    public String insertReturn(Opus opus)
    {
        Date date = new Date();
        opus.setCreateDate(date);
        opus.setModifyDate(date);
        opus.setVersion(VERSION);
        opus.setDelFlag(false);
        try
        {
            opusMapper.insertSelective(opus);
        }
        catch (Exception e)
        {
            throw new BizException("添加作品失败");
        }
        return opus.getUuid();
    }

    @Override
    public int updateByUuid(Opus opus)
    {
        Date date = new Date();
        opus.setModifyDate(date);
        Example example = new Example(Opus.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid",opus.getUuid());
        criteria.andEqualTo("memberUuid",opus.getMemberUuid());
        Opus opusOrig = new Opus();
        try
        {
            opusOrig  = opusMapper.selectByExample(example).get(0);
        }
        catch (Exception e)
        {
            LOG.error(e.toString());
        }
        opus.setVersion(opusOrig.getVersion() + 1);
        criteria.andEqualTo("version",opusOrig.getVersion());
        /*if(result != 0){//更新消息后更新缓存信息
            String cacheKey= redisManager.generateCacheKey(OpusDto.class, opus.getUuid());
            long expire = redisManager.getExpire(cacheKey);
            if (expire <= 0)
            {
                OpusDto opusDto = opusMapper.selectOpusInfo(opus.getUuid());
                redisManager.set(cacheKey, JSON.toJSONString(opusDto),redisManager.getRandomExpire(), TimeUnit.DAYS);
            }
        }*/
        return opusMapper.updateByExampleSelective(opus,example);
    }

    @Override
    public List<Opus> selectAll()
    {
        return opusMapper.selectAll();
    }

    @Override
    public Opus selectByUuid(String uuid)
    {
        Opus opus = new Opus();
        opus.setUuid(uuid);
        opus.setDelFlag(false);

        return selectOne(opus);
    }

    @Override
    public int selectCount(Opus opus)
    {
        return opusMapper.selectCount(opus);
    }

    @Override
    public List<Opus> select(Opus opus)
    {
        return opusMapper.select(opus);
    }

    @Override
    public Opus selectOne(Opus opus)
    {
        return opusMapper.selectOne(opus);
    }

    @Override
    public PageInfo<Opus> queryPage(Integer pageNum, Integer pageSize, Opus opus)
    {
        PageHelper.startPage(pageNum, pageSize);
        OpusSearchReq opusSearchReq = JSONObject.parseObject(JSONObject.toJSONString(opus),OpusSearchReq.class);
        List<Opus> list = opusMapper.findByPage(opusSearchReq);
        return new PageInfo<>(list);
    }

    @Override
    public int deleteByUuids(List<String> list, String memberUuid)
    {
        Example example = new Example(Opus.class);
        example.createCriteria().andIn("uuid",list).andEqualTo("memberUuid",memberUuid);
        Date date = new Date();
        Opus opus = new Opus();
        opus.setDelFlag(false);
        opus.setModifyDate(date);
        return opusMapper.updateByExample(opus ,example);
    }

    @Override
    public long findNumByDesignerUuid(String designerUuid) {
        Opus opus = new Opus();
        opus.setDelFlag(false);
        opus.setOpusStatus(Opus.OpusStatus.opusPublish.getValue());
        opus.setMemberUuid(designerUuid);

        return opusMapper.selectCount(opus);
    }

    @Override
    public PageInfo<Opus> findByPage(OpusSearchReq opusReq) {
        PageHelper.startPage(opusReq.getPageNum(),opusReq.getPageSize());
        List<Opus> opusList = opusMapper.findByPage(opusReq);
        return new PageInfo<>(opusList);
    }
}