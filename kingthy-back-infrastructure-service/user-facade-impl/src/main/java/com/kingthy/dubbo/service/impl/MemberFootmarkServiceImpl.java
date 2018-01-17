package com.kingthy.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingthy.dubbo.user.service.MemberFootmarkDubboService;
import com.kingthy.entity.MemberFootmark;
import com.kingthy.mapper.MemberFootmarkMapper;
import com.kingthy.page.PageT;
import com.kingthy.request.MemberFootmarkPageReq;
import org.apache.ibatis.annotations.Param;
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
public class MemberFootmarkServiceImpl implements MemberFootmarkDubboService {

    private static final Logger LOG = LoggerFactory.getLogger(MemberFootmarkServiceImpl.class);
    @Autowired
    private MemberFootmarkMapper mapper;

    @Override
    public int insert(MemberFootmark entity) {
        try {
            Date now = new Date();
            entity.setCreateDate(now);
            entity.setModifyDate(now);
            return mapper.insertSelective(entity);
        } catch (Exception ex) {
            LOG.error(ex.toString());
            throw ex;
        }
    }

    @Override
    public int updateByUuid(MemberFootmark entity) {
        try {
            Date now = new Date();
            entity.setModifyDate(now);
            Example example = new Example(MemberFootmark.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("uuid", entity.getUuid());
            return mapper.updateByExampleSelective(entity, example);
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
    public List<MemberFootmark> selectAll() {
        try {
            return mapper.selectAll();
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
    public MemberFootmark selectByUuid(String uuid) {
        try {
            Example example = new Example(MemberFootmark.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("uuid", uuid);
            List<MemberFootmark> list = mapper.selectByExample(example);
            if (!list.isEmpty()) {
                return list.get(0);
            } else {
                return null;
            }
        } catch (Exception ex) {
            LOG.error(ex.toString());
            throw ex;
        }
    }

    @Override
    public int selectCount(MemberFootmark memberFootmark)
    {
        try {
            return mapper.selectCount(memberFootmark);
        } catch (Exception ex) {
            LOG.error(ex.toString());
            throw ex;
        }
    }

    @Override
    public List<MemberFootmark> select(MemberFootmark memberFootmark)
    {
        try {
            return mapper.select(memberFootmark);
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
    public MemberFootmark selectOne(MemberFootmark var1) {
        try {
            return mapper.selectOne(var1);
        } catch (Exception ex) {
            LOG.error(ex.toString());
            throw ex;
        }
    }

    @Override
    public PageInfo<MemberFootmark> queryPage(Integer pageNum, Integer pageSize, MemberFootmark memberFootmark)
    {
        try {
            PageHelper.startPage(pageNum, pageSize);
            List<MemberFootmark> list = mapper.select(memberFootmark);
            return new PageInfo<>(list);
        } catch (Exception ex) {
            LOG.error(ex.toString());
            throw ex;
        }
    }

    @Override
    public int batchDeleteFootmark(@Param("uuids") List<String> uuids) {
        try {
            return mapper.batchDeleteFootmark(uuids);
        } catch (Exception ex) {
            LOG.error(ex.toString());
            throw ex;
        }
    }

    @Override
    public List<MemberFootmark> getFootmarkByMemberUuid(String memberUuid) {
        try {
            return mapper.getFootmarkByMemberUuid(memberUuid);
        } catch (Exception ex) {
            LOG.error(ex.toString());
            throw ex;
        }
    }

    @Override
    public PageT<MemberFootmark> pageGetFootmarkListByTimesapn(MemberFootmarkPageReq req) {
        try {
            List<MemberFootmark> list = mapper.pageGetFootmarkListByTimesapn(req);
            PageT<MemberFootmark> pageT = new PageT<>();
            pageT.setPageData(list);
            pageT.setPageSize(req.getPageSize());
            // 获取最后一条数据的时间戳
            if (list != null && !list.isEmpty()) {
                MemberFootmark dto = list.get(list.size() - 1);
                if (dto.getCreateDate() != null) {
                    pageT.setTimespan(dto.getCreateDate().getTime());
                }
            }
            return pageT;
        } catch (Exception ex) {
            LOG.error(ex.toString());
            throw ex;
        }
    }
}
