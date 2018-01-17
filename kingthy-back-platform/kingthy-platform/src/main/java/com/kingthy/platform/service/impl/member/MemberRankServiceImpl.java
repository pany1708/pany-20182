/**
 * 系统项目名称
 * com.kingthy.platform.service.impl.member
 * MemberRankServiceImpl.java
 * <p>
 * 2017年4月17日-下午8:14:11
 * 2017金昔科技有限公司-版权所有
 */
package com.kingthy.platform.service.impl.member;

import com.kingthy.platform.dto.member.MemberRankReq;
import com.kingthy.platform.entity.member.MemberRank;
import com.kingthy.platform.mapper.member.MemberRankMapper;
import com.kingthy.platform.service.member.MemberRankService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import java.util.Date;
import java.util.List;

/**
 *
 * MemberRankServiceImpl
 *
 * yuanml 2017年4月17日 下午8:14:11
 *
 * @version 1.0.0
 *
 */
@Service("memberRankService")
public class MemberRankServiceImpl implements MemberRankService
{
    @Autowired
    private MemberRankMapper memberRankMapper;
    
    private static final Integer DEFAULTVERSION = 1;
    
    @Autowired
    private HttpServletRequest httpServletRequest;
    
    @Override
    public List<MemberRank> findMemberRank()
    {
        Example example = new Example(MemberRank.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("delFlag", false);
        return memberRankMapper.selectByExample(example);
    }
    
    @Override
    public int addMemberRank(MemberRankReq memberRankReq)
    {
        MemberRank memberRank = new MemberRank();
        BeanUtils.copyProperties(memberRankReq, memberRank);
        memberRank.setCreateDate(new Date());
        memberRank.setCreator(getUuid());
        memberRank.setDelFlag(false);
        memberRank.setVersion(DEFAULTVERSION);
        return memberRankMapper.insert(memberRank);
    }
    
    /**
     * @desc 获取请求用户
     *
     * @author yuanml
     *
     * @return
     */
    private String getUuid()
    {
        String uuid = httpServletRequest.getHeader("uuid");
        if (null == uuid)
        {
            uuid = "";
        }
        return uuid;
    }
    
    @Override
    public int editMemberRank(MemberRankReq memberRankReq)
    {
        Example example = new Example(MemberRank.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid", memberRankReq.getUuid());
        MemberRank memberRank = memberRankMapper.selectByExample(example).get(0);
        memberRank.setAmountMax(memberRankReq.getAmountMax());
        memberRank.setAmountMin(memberRankReq.getAmountMin());
        memberRank.setName(memberRankReq.getName());
        memberRank.setModifier(getUuid());
        memberRank.setModifyDate(new Date());
        return memberRankMapper.updateByExample(memberRank, example);
    }
    
    @Override
    public int deleteMemberRank(String memberRankUuid)
    {
        Example example = new Example(MemberRank.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid", memberRankUuid);
        MemberRank memberRank = memberRankMapper.selectByExample(example).get(0);
        memberRank.setDelFlag(true);
        memberRank.setModifier(getUuid());
        memberRank.setModifyDate(new Date());
        return memberRankMapper.updateByExample(memberRank, example);
    }
    
    @Override
    public MemberRank findOneMemberRank(String memberRankUuid)
    {
        MemberRank memberRank = new MemberRank();
        memberRank.setUuid(memberRankUuid);
        return memberRankMapper.selectOne(memberRank);
    }
    
}
