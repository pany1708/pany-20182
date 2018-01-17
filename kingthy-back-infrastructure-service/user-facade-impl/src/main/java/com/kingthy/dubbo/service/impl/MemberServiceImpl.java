package com.kingthy.dubbo.service.impl;

import java.util.*;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingthy.dto.*;
import com.kingthy.dubbo.user.service.MemberDubboService;
import com.kingthy.entity.Member;
import com.kingthy.mapper.MemberMapper;
import com.kingthy.request.MemberBaseInfoReq;
import com.kingthy.request.MemberPageReq;
import com.kingthy.request.MemberUuidArrayReq;
import tk.mybatis.mapper.entity.Example;

/**
 * @author likejie
 * @date 2017/8/7.
 */
@Service(version = "1.0.0", timeout = 10000)
public class MemberServiceImpl implements MemberDubboService
{
    private static final Logger LOG = LoggerFactory.getLogger(MemberServiceImpl.class);
    
    @Autowired
    private MemberMapper mapper;
    
    @Override
    public int changeIsEnableBatch(MemberUuidArrayReq uuidArrayReq)
    {
        try
        {
            Map<String, Object> paramMap = new HashMap<>(3);
            paramMap.put("isEnabled", uuidArrayReq.getIsEnabled());
            paramMap.put("uuidArray", uuidArrayReq.getArray());
            paramMap.put("modifyDate", new Date());
            return mapper.changeIsEnableBatch(paramMap);
        }
        catch (Exception ex)
        {
            LOG.error(ex.toString());
            throw ex;
        }
    }
    
    @Override
    public int checkNameIsExist(String uuid, String nickName)
    {
        try
        {
            return mapper.checkNameIsExist(uuid, nickName);
        }
        catch (Exception ex)
        {
            LOG.error(ex.toString());
            throw ex;
        }
    }
    
    @Override
    public int checkPhoneIsExist(String uuid, String phone)
    {
        try
        {
            return mapper.checkPhoneIsExist(uuid, phone);
        }
        catch (Exception ex)
        {
            LOG.error(ex.toString());
            throw ex;
        }
    }
    
    @Override
    public Member findByUuid(String uuid)
    {
        try
        {
            Member cond=new Member();
            cond.setUuid(uuid);
            return mapper.selectOne(cond);
        }
        catch (Exception ex)
        {
            LOG.error(ex.toString());
            throw ex;
        }
    }
    
    @Override
    public PageInfo<Member> findMemberByPage(MemberPageReq memberPageReq)
    {
        try
        {
            PageHelper.startPage(memberPageReq.getPageNum(), memberPageReq.getPageSize());
            List<Member> memberList = mapper.findMemberByPage(memberPageReq);
            return new PageInfo<>(memberList);
        }
        catch (Exception ex)
        {
            LOG.error(ex.toString());
            throw ex;
        }
    }
    
    @Override
    public ArrayList<CommentMemberDto> findMemberInfoByList(List<BuyersShowDTO> list)
    {
        try
        {
            if(list.size()==0){
                return new ArrayList<>();
            }
            return (ArrayList<CommentMemberDto>)mapper.findMemberInfoByList(list);
        }
        catch (Exception ex)
        {
            LOG.error(ex.toString());
            throw ex;
        }
    }
    
    @Override
    public int insert(Member member)
    {
        try
        {
            Date now = new Date();
            member.setCreateDate(now);
            member.setModifyDate(now);
            return mapper.insertSelective(member);
        }
        catch (Exception ex)
        {
            LOG.error(ex.toString());
            throw ex;
        }
    }
    
    @Override
    public int modifyPassword(String uuid, String password, String salt)
    {
        try
        {
            return mapper.modifyPassword(uuid, password, salt);
        }
        catch (Exception ex)
        {
            LOG.error(ex.toString());
            throw ex;
        }
    }
    
    @Override
    public int modifyPaymentPassword(String uuid, String paymentPassword, String paymenSalt)
    {
        try
        {
            return mapper.modifyPaymentPassword(uuid, paymentPassword, paymenSalt);
        }
        catch (Exception ex)
        {
            LOG.error(ex.toString());
            throw ex;
        }
    }
    
    @Override
    public int modifyPhone(String uuid, String phone)
    {
        try
        {
            return mapper.modifyPhone(uuid, phone);
        }
        catch (Exception ex)
        {
            LOG.error(ex.toString());
            throw ex;
        }
    }
    
    /**
     * 未被调用，若调用需编写SQL查询
     *
     * @param pageNum
     * @param pageSize
     * @param member
     * @return
     */
    @Override
    public PageInfo<Member> queryPage(Integer pageNum, Integer pageSize, Member member)
    {
        try
        {
            PageHelper.startPage(pageNum, pageSize);
            
            List<Member> list = mapper.select(member);
            
            return new PageInfo<>(list);
        }
        catch (Exception ex)
        {
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
    public List<Member> select(Member var1)
    {
        try
        {
            return mapper.select(var1);
        }
        catch (Exception ex)
        {
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
    public List<Member> selectAll()
    {
        try
        {
            return mapper.selectAll();
        }
        catch (Exception ex)
        {
            LOG.error(ex.toString());
            throw ex;
        }
    }
    
    @Override
    public Member selectByUuid(String uuid)
    {
        try
        {
            Member cond=new Member();
            cond.setUuid(uuid);
            return mapper.selectOne(cond);
        }
        catch (Exception ex)
        {
            LOG.error(ex.toString());
            throw ex;
        }
    }
    
    @Override
    public int selectCount(Member member)
    {
        try
        {
            return mapper.selectCount(member);
        }
        catch (Exception ex)
        {
            LOG.error(ex.toString());
            throw ex;
        }
    }
    
    @Override
    public GoodsDTO.DesignerInfo selectDesignerInfoByDesignerUuid(String memberUuid, String designerUuid)
    {
        try
        {
            return mapper.selectDesignerInfoByDesignerUuid(memberUuid, designerUuid);
        }
        catch (Exception ex)
        {
            LOG.error(ex.toString());
            throw ex;
        }
    }
    
    @Override
    public List<Member> selectMemberListByName(String name)
    {
        try
        {
            try {
                Example example = new Example(Member.class);
                Example.Criteria criteria = example.createCriteria();
                criteria.andCondition(" user_name like  concat('%'," + name + ",'%') ");
                return mapper.selectByExample(example);
            } catch (Exception ex) {
                LOG.error(ex.toString());
                throw ex;
            }
        }
        catch (Exception ex)
        {
            LOG.error(ex.toString());
            throw ex;
        }
    }
    
    @Override
    public List<Member> selectMemberListByUuids(List<String> uuids)
    {
        try
        {
            if(uuids.size()==0){
                return new ArrayList<>();
            }
            return mapper.selectMemberListByUuids(uuids);
        }
        catch (Exception ex)
        {
            LOG.error(ex.toString());
            throw ex;
        }
    }
    
    @Override
    public List<String> selectMemberUuidsByName(String name)
    {
        try
        {
            if(StringUtils.isBlank(name)){
                return new ArrayList<>();
            }
            return mapper.selectMemberUuidsByName(name);
        }
        catch (Exception ex)
        {
            LOG.error(ex.toString());
            throw ex;
        }
    }
    
    @Override
    public Member selectOne(Member member)
    {
        try
        {
            return mapper.selectOne(member);
        }
        catch (Exception ex)
        {
            LOG.error(ex.toString());
            throw ex;
        }
    }
    
    @Override
    public int updateByUuid(Member member)
    {
        try
        {
            Date now = new Date();
            member.setModifyDate(now);
            Example example = new Example(Member.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("uuid", member.getUuid());
            return mapper.updateByExampleSelective(member, example);
        }
        catch (Exception ex)
        {
            LOG.error(ex.toString());
            throw ex;
        }
    }
    
    @Override
    public int updateLocked(String phone)
    {
        try
        {
            return mapper.updateLocked(phone);
        }
        catch (Exception ex)
        {
            LOG.error(ex.toString());
            throw ex;
        }
    }
    
    @Override
    public int updateName(String uuid, String name)
    {
        try
        {
            return mapper.updateName(uuid, name);
        }
        catch (Exception ex)
        {
            LOG.error(ex.toString());
            throw ex;
        }
    }
    
    @Override
    public int updateSignature(String uuid, String signature)
    {
        try
        {
            return mapper.updateSignature(uuid, signature);
        }
        catch (Exception ex)
        {
            LOG.error(ex.toString());
            throw ex;
        }
    }
    
    @Override
    public MemberHomeDTO getHomeInfo(String memberUuid)
    {
        try
        {
            return mapper.getHomeInfo(memberUuid);
        }
        catch (Exception ex)
        {
            LOG.error(ex.toString());
            throw ex;
        }
    }
    
    @Override
    public MemberDTO getMemberByUuid(String uuid)
    {
        try
        {
            return mapper.getMemberByUuid(uuid);
        }
        catch (Exception ex)
        {
            LOG.error(ex.toString());
            throw ex;
        }
    }
    
    @Override
    public List<MemberBaseInfoDTO> getMembersBaseInfo(MemberBaseInfoReq memberBaseInfoReq)
    {
        try
        {
            return mapper.getMembersBaseInfo(memberBaseInfoReq.getUuids());
        }
        catch (Exception ex)
        {
            LOG.error(ex.toString());
            throw ex;
        }
    }
    @Override
    public int updateMemberLoginInfo(UpdateLoginInfoDTO dto){
        try
        {
            return mapper.updateMemberLoginInfo(dto);
        }
        catch (Exception ex)
        {
            LOG.error(ex.toString());
            throw ex;
        }
    }
}

