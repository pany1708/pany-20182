package com.kingthy.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingthy.dubbo.user.service.MemberFavoriteDubboService;
import com.kingthy.entity.MemberFavorite;
import com.kingthy.mapper.MemberFavoriteMapper;
import com.kingthy.page.PageT;
import com.kingthy.request.MemberFavoritePageReq;
import com.kingthy.request.MemberFavoriteReq;
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
public class MemberFavoriteServiceImpl implements MemberFavoriteDubboService {

    private static final Logger LOG = LoggerFactory.getLogger(MemberFavoriteServiceImpl.class);
    @Autowired
    private MemberFavoriteMapper mapper;

    @Override
    public int insert(MemberFavorite entity) {
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
    public int updateByUuid(MemberFavorite entity) {
        try {
            Date now = new Date();
            entity.setModifyDate(now);
            Example example = new Example(MemberFavorite.class);
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
    public List<MemberFavorite> selectAll() {
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
    public MemberFavorite selectByUuid(String uuid) {
        try {
            Example example = new Example(MemberFavorite.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("uuid", uuid);
            List<MemberFavorite> list = mapper.selectByExample(example);
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
    public int selectCount(MemberFavorite memberFavorite)
    {
        try {
            return mapper.selectCount(memberFavorite);
        } catch (Exception ex) {
            LOG.error(ex.toString());
            throw ex;
        }
    }

    @Override
    public List<MemberFavorite> select(MemberFavorite memberFavorite)
    {

        try {
            return mapper.select(memberFavorite);
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
    public MemberFavorite selectOne(MemberFavorite var1) {
        try {
            return mapper.selectOne(var1);
        } catch (Exception ex) {
            LOG.error(ex.toString());
            throw ex;
        }
    }

    @Override
    public PageInfo<MemberFavorite> queryPage(Integer pageNum, Integer pageSize, MemberFavorite entity) {
        try {
            PageHelper.startPage(pageNum, pageSize);
            PageHelper.orderBy(" create_date desc ");
            List<MemberFavorite> list = mapper.select(entity);
            return new PageInfo<>(list);
        } catch (Exception ex) {
            LOG.error(ex.toString());
            throw ex;
        }
    }

    @Override
    public PageT<MemberFavorite> pageGetFavoriteListByTimesapn(MemberFavoritePageReq req) {

        try {
            List<MemberFavorite> list = mapper.pageGetFavoriteListByTimesapn(req);
            // 分页信息
            PageT<MemberFavorite> pageInfo = new PageT<>();
            pageInfo.setPageData(list);
            pageInfo.setPageSize(req.getPageSize());
            // 获取最后一条数据的时间戳
            if (list != null && !list.isEmpty()) {
                MemberFavorite dto = list.get(list.size() - 1);
                if (dto.getCreateDate() != null) {
                    pageInfo.setTimespan(dto.getCreateDate().getTime());
                }
            }
            return pageInfo;
        } catch (Exception ex) {
            LOG.error(ex.toString());
            throw ex;
        }
    }
    @Override
    public int updateAllFavorite(MemberFavoriteReq memberFavoriteReq) {
        try {
            return mapper.updateAllFavorite(memberFavoriteReq);
        } catch (Exception ex) {
            LOG.error(ex.toString());
            throw ex;
        }
    }
    @Override
    public int updateSomeFavorite(MemberFavoriteReq memberFavoriteReq) {
        try {
            return mapper.updateSomeFavorite(memberFavoriteReq);
        } catch (Exception ex) {
            LOG.error(ex.toString());
            throw ex;
        }
    }

    @Override
    public Long selectFavoriteCountByGoodsUuidAndMembersUuid(String goodsUuid, String membersUuid) {
        try {
            return mapper.selectFavoriteCountByGoodsUuidAndMembersUuid(goodsUuid, membersUuid);
        } catch (Exception ex) {
            LOG.error(ex.toString());
            throw ex;
        }
    }
}
