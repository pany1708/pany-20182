package com.kingthy.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingthy.dubbo.user.service.ShowVideosDubboService;
import com.kingthy.entity.Receiver;
import com.kingthy.entity.ShowVideos;
import com.kingthy.mapper.ShowVideosMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * @author  likejie
 * @date 2017/8/7.
 */
@Service(version = "1.0.0", timeout = 10000)
public class ShowVideosDubboServiceImpl implements ShowVideosDubboService {

    private static final Logger LOG = LoggerFactory.getLogger(ShowVideosDubboServiceImpl.class);

    @Autowired
    private ShowVideosMapper showVideosMapper;

    @Override
    public int insert(ShowVideos showVideos) {
        try {
            Date nowDate = new Date();
            showVideos.setCreateDate(nowDate);
            showVideos.setModifyDate(nowDate);
            showVideos.setDelFlag(false);
            return showVideosMapper.insert(showVideos);
        } catch (Exception ex) {
            LOG.error(ex.toString());
            throw ex;
        }
    }

    @Override
    public int updateByUuid(ShowVideos showVideos) {

        try {
            //当前版本号
            int currentVersion = showVideos.getVersion();
            //设置新的版本号
            showVideos.setVersion(currentVersion + 1);
            Date now = new Date();
            showVideos.setModifyDate(now);
            Example example = new Example(Receiver.class);
            Example.Criteria criteria = example.createCriteria();

            criteria.andEqualTo("uuid", showVideos.getUuid());
            criteria.andEqualTo("version", currentVersion);
            return showVideosMapper.updateByExample(showVideos, criteria);
        } catch (Exception ex) {
            LOG.error(ex.toString());
            throw ex;
        }
    }

    /**
     * 未被调用，若调用需编写SQL查询
     * 
     * @return
     */
    @Override
    public List<ShowVideos> selectAll() {
        try {
            ShowVideos cond = new ShowVideos();
            cond.setDelFlag(false);
            return showVideosMapper.select(cond);
        } catch (Exception ex) {
            LOG.error(ex.toString());
            throw ex;
        }
    }

    /**
     * 未被调用，若调用需编写SQL查询
     * 
     * @param uuid
     * @return
     */
    @Override
    public ShowVideos selectByUuid(String uuid) {
        try {
            ShowVideos showVideos = new ShowVideos();
            showVideos.setUuid(uuid);
            return showVideosMapper.selectOne(showVideos);
        } catch (Exception ex) {
            LOG.error(ex.toString());
            throw ex;
        }
    }

    /**
     * 未被调用，若调用需编写SQL查询
     * 
     * @param showVideos
     * @return
     */
    @Override
    public int selectCount(ShowVideos showVideos) {
        try {
            showVideos.setDelFlag(false);
            return showVideosMapper.selectCount(showVideos);
        } catch (Exception ex) {
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
    public List<ShowVideos> select(ShowVideos var1) {
        try {
            var1.setDelFlag(false);
            return showVideosMapper.select(var1);
        } catch (Exception ex) {
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
    public ShowVideos selectOne(ShowVideos var1) {
        try {
            var1.setDelFlag(false);
            return showVideosMapper.selectOne(var1);
        } catch (Exception ex) {
            LOG.error(ex.toString());
            throw ex;
        }
    }

    @Override
    public PageInfo<ShowVideos> queryPage(Integer pageNum, Integer pageSize, ShowVideos showVideos) {
        try {
            PageHelper.startPage(pageNum, pageSize);
            showVideos.setDelFlag(false);
            List<ShowVideos> list = showVideosMapper.query(showVideos);
            return new PageInfo<>(list);
        } catch (Exception ex) {
            LOG.error(ex.toString());
            throw ex;
        }
    }
}
