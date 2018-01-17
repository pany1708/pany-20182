package com.kingthy.platform.service.impl.member;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingthy.platform.dto.member.MemberPageReq;
import com.kingthy.platform.dto.member.MemberUuidArrayReq;
import com.kingthy.platform.entity.member.Member;
import com.kingthy.platform.mapper.member.MemberMapper;
import com.kingthy.platform.service.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 *
 * MemberServiceImpl(会员业务层实现类)
 * 
 * 陈钊 2017年4月14日 下午3:50:04
 * 
 * @version 1.0.0
 *
 */
@Service("memberService")
public class MemberServiceImpl implements MemberService
{
    @Autowired
    private MemberMapper memberMapper;
    


    @Override
    public PageInfo<Member> findMemberByPage(MemberPageReq memberPageReq)
    {
        PageHelper.startPage(memberPageReq.getPageNum(), memberPageReq.getPageSize());
        List<Member> memberList = memberMapper.findMemberByPage(memberPageReq);
        PageInfo<Member> pageInfo = new PageInfo<Member>(memberList);
        return pageInfo;
    }

    @Override
    public int changeIsLockedBatch(MemberUuidArrayReq uuidArrayReq)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isLocked", uuidArrayReq.getIsLocked());
        paramMap.put("uuidArray", uuidArrayReq.getArray());
        paramMap.put("modifyDate",new Date());
        int result = memberMapper.changeIsLockedBatch(paramMap);
        return result;
    }

    @Override
    public Member findByUuid(String uuid) {
        Example example = new Example(Member.class);
        Example.Criteria  criteria = example.createCriteria();
        criteria.andEqualTo("uuid",uuid);
        List<Member> list = memberMapper.selectByExample(example);
        if (list !=null && list.size()>0){
            return list.get(0);
        }
        return new Member();
    }
}
