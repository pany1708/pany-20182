package com.kingthy.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingthy.dto.MemberReceiverDTO;
import com.kingthy.dubbo.user.service.ReceiverDubboService;
import com.kingthy.entity.Receiver;
import com.kingthy.mapper.ReceiverMapper;
import com.kingthy.transaction.ReceoverTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import java.util.Date;
import java.util.List;

/**
 * @author  likejie
 * @date 2017/8/7.
 */
@Service(version = "1.0.0", timeout = 10000)
public class ReceiverServiceImpl  implements ReceiverDubboService {

    private static final Logger LOG = LoggerFactory.getLogger(ReceiverServiceImpl.class);
    @Autowired
    private ReceiverMapper mapper;

    @Autowired
    private ReceoverTransaction receoverTransaction;

    @Override
    public int insert(Receiver entity) {
        try {
            Date nowDate = new Date();
            entity.setCreateDate(nowDate);
            entity.setModifyDate(nowDate);
            entity.setDelFlag(false);
            if (entity.getIsDefault() == null) {
                entity.setIsDefault(false);
            }
            return receoverTransaction.addReceiver(entity);
        } catch (Exception ex) {
            LOG.error(ex.toString());
            throw ex;
        }
    }

    @Override
    public int updateByUuid(Receiver entity) {
        try {
            //当前版本号
            int currentVersion = entity.getVersion();
            //设置新的版本号
            entity.setVersion(currentVersion + 1);
            Date now = new Date();
            entity.setModifyDate(now);
            Example example = new Example(Receiver.class);
            Example.Criteria criteria = example.createCriteria();

            criteria.andEqualTo("uuid", entity.getUuid());
            criteria.andEqualTo("version", currentVersion);

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
    public List<Receiver> selectAll() {
        try {
            return mapper.selectAll();
        } catch (Exception ex) {
            LOG.error(ex.toString());
            throw ex;
        }
    }

    @Override
    public Receiver selectByUuid(String uuid) {
        try {
            Receiver cond=new Receiver();
            cond.setUuid(uuid);
            return mapper.selectOne(cond);
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
    public int selectCount(Receiver entity) {
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
    public List<Receiver> select(Receiver var1) {
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
    public Receiver selectOne(Receiver var1) {
        try {
            return mapper.selectOne(var1);
        } catch (Exception ex) {
            LOG.error(ex.toString());
            throw ex;
        }
    }

    @Override
    public PageInfo<Receiver> queryPage(Integer pageNum, Integer pageSize, Receiver receiver)
    {
        try {
            PageHelper.startPage(pageNum, pageSize);
            List<Receiver> list = mapper.select(receiver);
            return new PageInfo<>(list);
        } catch (Exception ex) {
            LOG.error(ex.toString());
            throw ex;
        }
    }

    /**
     * 获取会员的收获地址
     *
     * @param memberUuid
     */
    @Override
    public List<MemberReceiverDTO> getMemberReceiverList(String memberUuid) {
        try {
            return mapper.getMemberReceiverList(memberUuid);
        } catch (Exception ex) {
            LOG.error(ex.toString());
            throw ex;
        }
    }

    @Override
    public Receiver getDefaultReceiver(String memberUuid) {
        try {
            Example example = new Example(Receiver.class);
            Criteria criteria = example.createCriteria();
            criteria.andEqualTo("memberUuid", memberUuid);
            criteria.andEqualTo("isDefault", true);
            List<Receiver> list = mapper.selectByExample(example);
            return !list.isEmpty() ? list.get(0) : null;
        } catch (Exception ex) {
            LOG.error(ex.toString());
            throw ex;
        }

    }

    @Override
    public int setDefaultReceiver(String memberUuid, String uuid) {
        try {
            return receoverTransaction.setDefaultReceiver(memberUuid, uuid);
        } catch (Exception ex) {
            LOG.error(ex.toString());
            throw ex;
        }
    }

    /**
     * 删除
     *
     * @param uuid 会员收获地址业务主键
     */
    @Override
    public int deleteReceiver(String uuid, String memberUuid) {
        try {
            Example example = new Example(Receiver.class);
            Criteria criteria = example.createCriteria();
            criteria.andEqualTo("uuid", uuid);
            criteria.andEqualTo("memberUuid", memberUuid);
            return mapper.deleteByExample(example);
        } catch (Exception ex) {
            LOG.error(ex.toString());
            throw ex;
        }
    }

    /**
     * 取消默认收获地址
     *
     * @param
     */
    @Override
    public int cancelDefaultReceiver(String memberUuid, String uuid) {
        try {
            return mapper.cancelDefaultReceiver(memberUuid, uuid);
        } catch (Exception ex) {
            LOG.error(ex.toString());
            throw ex;
        }
    }
}
