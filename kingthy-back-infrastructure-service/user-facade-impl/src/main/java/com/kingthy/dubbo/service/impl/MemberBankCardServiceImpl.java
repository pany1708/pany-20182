package com.kingthy.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingthy.dto.IncomeBalanceDTO;
import com.kingthy.dubbo.user.service.MemberBankCardDubboService;
import com.kingthy.entity.MemberBankCard;
import com.kingthy.mapper.MemberBankCardMapper;
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
public class MemberBankCardServiceImpl implements MemberBankCardDubboService {

    private static final Logger LOG = LoggerFactory.getLogger(MemberBankCardServiceImpl.class);
    @Autowired
    private MemberBankCardMapper mapper;

    @Override
    public int insert(MemberBankCard entity) {
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
    public int updateByUuid(MemberBankCard entity) {
        try {
            Date now = new Date();
            entity.setModifyDate(now);
            Example example = new Example(MemberBankCard.class);
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
    public List<MemberBankCard> selectAll() {
        try {
            return mapper.selectAll();
        } catch (Exception ex) {
            LOG.error(ex.toString());
            throw ex;
        }
    }

    @Override
    public MemberBankCard selectByUuid(String uuid) {
        try {
            return this.selectBankCardInfo(uuid);
        } catch (Exception ex) {
            LOG.error(ex.toString());
            throw ex;
        }
    }

    /**
     * 未被调用，若调用需编写SQL查询
     * 
     * @param entity
     * @return
     */
    @Override
    public int selectCount(MemberBankCard entity) {
        try {
            return mapper.selectCount(entity);
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
    public List<MemberBankCard> select(MemberBankCard var1) {
        try {
            return mapper.select(var1);
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
    public MemberBankCard selectOne(MemberBankCard var1) {
        try {
            return mapper.selectOne(var1);
        } catch (Exception ex) {
            LOG.error(ex.toString());
            throw ex;
        }
    }
    
    /**
     * 未被调用，若调用需编写SQL查询
     * 
     * @param pageNum
     * @param pageSize
     * @param entity
     * @return
     */
    @Override
    public PageInfo<MemberBankCard> queryPage(Integer pageNum, Integer pageSize, MemberBankCard entity) {
        try {
            PageHelper.startPage(pageNum, pageSize);
            List<MemberBankCard> list = mapper.select(entity);
            return new PageInfo<>(list);
        } catch (Exception ex) {
            LOG.error(ex.toString());
            throw ex;
        }
    }

    @Override
    public MemberBankCard selectBankCardInfo(String uuid) {
        try {
            return mapper.selectBankCardInfo(uuid);
        } catch (Exception ex) {
            LOG.error(ex.toString());
            throw ex;
        }
    }

    @Override
    public int countBankCardByCardNoAndMemberUuid(String cardNo, String membersUuid) {
        try {
            return mapper.countBankCardByCardNoAndMemberUuid(cardNo, membersUuid);
        } catch (Exception ex) {
            LOG.error(ex.toString());
            throw ex;
        }
    }

    @Override
    public IncomeBalanceDTO queryBankInfoByMembersUuid(String membersUuid) {
        try {
            return mapper.queryBankInfoByMembersUuid(membersUuid);
        } catch (Exception ex) {
            LOG.error(ex.toString());
            throw ex;
        }
    }
}
