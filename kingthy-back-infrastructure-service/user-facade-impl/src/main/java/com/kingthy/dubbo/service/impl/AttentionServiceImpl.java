package com.kingthy.dubbo.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingthy.dto.AttentionMemberDTO;
import com.kingthy.dto.FansDTO;
import com.kingthy.dto.MomentDto;
import com.kingthy.dubbo.user.service.AttentionDubboService;
import com.kingthy.entity.Attention;
import com.kingthy.entity.Member;
import com.kingthy.mapper.AttentionMapper;
import com.kingthy.mapper.MemberMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;

import java.util.*;

/**
 *
 * @author  likejie
 * @date 2017/8/7.
 */
@Service(version = "1.0.0", timeout = 10000)
public class AttentionServiceImpl implements AttentionDubboService {

    private static final Logger LOG = LoggerFactory.getLogger(AttentionServiceImpl.class);
    @Autowired
    private AttentionMapper mapper;

    @Autowired
    private MemberMapper memberMapper;

    @Override
    public int insert(Attention entity) {
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
    public PageInfo<AttentionMemberDTO> pagingSelectAttentionUsers(String memberUuid, int pageSize, int pageNum){

        try {
            //分页，但不查询总数量
            PageHelper.startPage(pageNum, pageSize,false);
            List<AttentionMemberDTO> list= getAttentionMemberList(memberUuid);
            return new PageInfo<>(list);
        } catch (Exception ex) {
            LOG.error(ex.toString());
            throw ex;
        }
    }

    @Override
    public List<AttentionMemberDTO> getAttentionMemberList(String memberUuid) {
        try {
            List<AttentionMemberDTO> list=mapper.getAttentionMemberList(memberUuid);
            if(list.size()>0){
                List<String> uuids=new ArrayList<>();
                for(AttentionMemberDTO dto :list){
                    uuids.add(dto.getMemberUuid());
                }
                List<Member> memberList =memberMapper.selectMemberListByUuids(uuids);
                for(AttentionMemberDTO dto :list){
                    for(Member member :memberList){
                        if(dto.getMemberUuid().equals(member.getUuid())){
                            dto.setHeadImage(member.getHeadImage());
                            dto.setNickName(member.getNickName());
                            dto.setPhone(member.getPhone());
                            dto.setUserName(dto.getPhone());
                        }
                    }
                }
            }
            return list;
        } catch (Exception ex) {
            LOG.error(ex.toString());
            throw ex;
        }
    }

    @Override
    public List<FansDTO> getFansLilst(String memberUuid) {
        try {

            List<String> memberUuids=mapper.selectFansUuidList(memberUuid);
            if(memberUuids.size()>0) {
                return mapper.getFansLilst(memberUuids);
            }else{
                return new ArrayList<>();
            }
        } catch (Exception ex) {
            LOG.error(ex.toString());
            throw ex;
        }
    }

    @Override
    public PageInfo<FansDTO> pagingSelectFans(String memberUuid, int pageSize, int pageNum) {
        try {
            //分页，但不查询总数量
            PageHelper.startPage(pageNum, pageSize,false);
            List<FansDTO> list= getFansLilst(memberUuid);
            return new PageInfo<>(list);
        } catch (Exception ex) {
            LOG.error(ex.toString());
            throw ex;
        }
    }

    @Override
    public int batchDeleteAttention(String memberUuid, List<String> attentionUuids) {
        try {
            return mapper.batchDeleteAttention(memberUuid, attentionUuids);
        } catch (Exception ex) {
            LOG.error(ex.toString());
            throw ex;
        }
    }

    @Override
    public int getAttentionCount(String memberUuid, String attentionUuid) {
        try {
            return mapper.getAttentionCount(memberUuid, attentionUuid);
        } catch (Exception ex) {
            LOG.error(ex.toString());
            throw ex;
        }
    }

    @Override
    public List<String> isattention(String queryMemberUuid, List<MomentDto> moments) {
        try {
            Map<String, Object> map = new HashMap<>(2);
            map.put("queryMemberUuid", queryMemberUuid);
            map.put("momentDtoList", moments);
            List<String> list = mapper.isattention(map);
            if (list != null) {
                return list;
            }
            return new ArrayList<>();
        } catch (Exception ex) {
            LOG.error(ex.toString());
            throw ex;
        }
    }
}
