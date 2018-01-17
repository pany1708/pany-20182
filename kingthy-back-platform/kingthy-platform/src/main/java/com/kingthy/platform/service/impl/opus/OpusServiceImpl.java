/**
 * 系统项目名称
 * com.kingthy.platform.service.impl.opus
 * OpusServiceImpl.java
 * <p>
 * 2017年4月5日-下午4:04:24
 * 2017金昔科技有限公司-版权所有
 */
package com.kingthy.platform.service.impl.opus;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingthy.exception.OpusException;
import com.kingthy.platform.dto.opus.*;
import com.kingthy.platform.entity.basedata.PartsCategory;
import com.kingthy.platform.entity.material.Accessories;
import com.kingthy.platform.entity.material.Material;
import com.kingthy.platform.entity.opus.Opus;
import com.kingthy.platform.mapper.basedata.PartsCategoryMapper;
import com.kingthy.platform.mapper.material.AccessoriesMapper;
import com.kingthy.platform.mapper.material.MaterialMapper;
import com.kingthy.platform.mapper.member.MemberMapper;
import com.kingthy.platform.mapper.opus.OpusMapper;
import com.kingthy.platform.mapper.opus.PartSubMapper;
import com.kingthy.platform.response.WebApiResponse;
import com.kingthy.platform.service.opus.OpusService;
import com.kingthy.platform.util.SnFeignClient;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * OpusServiceImpl
 *
 * yuanml 2017年4月5日 下午4:04:24
 *
 * @version 1.0.0
 *
 */
@Service("opusService")
public class OpusServiceImpl implements OpusService
{
    @Autowired
    private transient OpusMapper opusMapper;

    @Autowired
    private transient MemberMapper memberMapper;

    @Autowired
    private transient AccessoriesMapper accessoriesMapper;

    @Autowired
    private transient MaterialMapper materialMapper;

    @Autowired
    private transient PartsCategoryMapper partsCategoryMapper;
    
    @Autowired
    private PartSubMapper partSubMapper;
    
    @Autowired
    private transient SnFeignClient snFeignClient;
    
    @Autowired
    private HttpServletRequest httpServletRequest;
    
    private Map<String, Object> parameterMap;
    
    /**
     * 默认版本号
     */
    private static final int DEFAULTVERSION = 1;
    
    @Override
    public PageInfo<Opus> findOpus(OpusSearchReq opusReq)
    {
        Example example = new Example(Opus.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("delFlag",false);
        PageHelper.startPage(opusReq.getPageNum(), opusReq.getPageSize());
        if(null != opusReq.getOpusName()){
            criteria.andEqualTo("opusName",opusReq.getOpusName());
        }
        if(null != opusReq.getMemberNick()){
            criteria.andEqualTo("memberNick",opusReq.getMemberNick());
        }
        if(null != opusReq.getOpusStatus()){
            criteria.andEqualTo("opusStatus",opusReq.getOpusStatus());
        }
        if(null != opusReq.getBeginDate() && null != opusReq.getEndDate()){
            criteria.andBetween("createDate",opusReq.getBeginDate(),opusReq.getEndDate());
        }

        List<Opus> opusDtos = opusMapper.selectByExample(example);
        PageInfo<Opus> pageInfo = new PageInfo<>(opusDtos);
        return pageInfo;
    }
    
    @Override
    public int create(OpusInsertReq opusInsertReq)
    {
        Opus opus = new Opus();
        BeanUtils.copyProperties(opusInsertReq, opus);
        WebApiResponse<String> webApiResponse = snFeignClient.generateSn("opus");
        if (SnFeignClient.NOSERVICE == webApiResponse.getCode())
        {
            throw OpusException.SERVICE_CALL_RESULT;
        }
        String sn = webApiResponse.getData();
        
        opus.setSn(sn);
        opus.setCreateDate(new Date());
        opus.setVersion(DEFAULTVERSION);
        opus.setDelFlag(false);
        if (null == opus.getIsShow())
            opus.setIsShow(true);
        opus.setCreator(getUuid());
        int result = opusMapper.insert(opus);
        String opusUuid = opus.getUuid();
        OpusRelationsReq opusRelationsReq = new OpusRelationsReq();
        opusRelationsReq.setOpusUuid(opusUuid);
        if(null != opusInsertReq.getOpusTagUuids() && opusInsertReq.getOpusTagUuids().length > 0)
        {
            opusRelationsReq.setRelationUuids(opusInsertReq.getOpusTagUuids());
            addOpusRelations(opusRelationsReq,"opus_tag");
        }
        if(null != opusInsertReq.getOpusPartUuids() && opusInsertReq.getOpusPartUuids().length > 0)
        {
            opusRelationsReq.setRelationUuids(opusInsertReq.getOpusPartUuids());
            addOpusRelations(opusRelationsReq,"opus_part");
        }
        if(null != opusInsertReq.getOpusMaterialUuids() && opusInsertReq.getOpusMaterialUuids().length > 0)
        {
            opusRelationsReq.setRelationUuids(opusInsertReq.getOpusMaterialUuids());
            addOpusRelations(opusRelationsReq,"opus_material");
        }
        if(null != opusInsertReq.getOpusAccessoriesUuids() && opusInsertReq.getOpusAccessoriesUuids().length > 0)
        {
            opusRelationsReq.setRelationUuids(opusInsertReq.getOpusAccessoriesUuids());
            addOpusRelations(opusRelationsReq,"opus_accessories");
        }
        return result;
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
    public int isShowOpus(String opusUuid, Boolean isShow)
    {
        String[] opusUuids = opusUuid.split(",");
        parameterMap = new HashMap<>();
        parameterMap.put("opusUuids", opusUuids);
        parameterMap.put("isShow", isShow);
        parameterMap.put("modifier", getUuid());
        return opusMapper.updateOpusIsShow(parameterMap);
    }
    
    @Override
    public OpusDto findOpusOne(String opusUuid)
    {
        Opus opus = new Opus();
        opus.setUuid(opusUuid);
        opus.setDelFlag(false);
        opus = opusMapper.selectOne(opus);
        if(opus == null)
        {
            return null;
        }
        OpusDto opusDto = new OpusDto();
        BeanUtils.copyProperties(opus, opusDto);

        List<String> accessoriesStringList = JSON.parseArray(opus.getOpusAccessoriesInfo(),String.class);
        Example accessoriesExample = new Example(Accessories.class);
        Criteria accessoriesCriteria = accessoriesExample.createCriteria();
        accessoriesCriteria.andIn("uuid",accessoriesStringList);
        List<Accessories> accessoriesList = accessoriesMapper.selectByExample(accessoriesExample);
        opusDto.setOpusAccessoriesList(accessoriesList);

        List<String> materialStringList = JSON.parseArray(opus.getOpusMaterialInfo(),String.class);
        Example materialExample = new Example(Material.class);
        Criteria materialCriteria = materialExample.createCriteria();
        materialCriteria.andIn("uuid" ,materialStringList );
        List<Material> materialList = materialMapper.selectByExample(materialExample);
        opusDto.setOpusMaterialList(materialList);

        List<String> partsCategoryStringList = JSON.parseArray(opus.getOpusPartsInfo(),String.class);
        Example partsCategoryExample = new Example(PartsCategory.class);
        Criteria partsCategoryCriteria = partsCategoryExample.createCriteria();
        partsCategoryCriteria.andIn("uuid" ,partsCategoryStringList );
        List<PartsCategory> partsCategoryList = partsCategoryMapper.selectByExample(partsCategoryExample);
        opusDto.setOpusPartList(partsCategoryList);

        return opusDto;
    }
    
    @Override
    public List<OpusRelationsDto> findOpusTag(String opusUuid)
    {
        return opusMapper.findOpusTag(opusUuid);
    }
    
    @Override
    public List<OpusPartSubDto> findOpusPart(String opusUuid)
    {
        List<String> opusUuids = opusMapper.findOpusPart(opusUuid);
        List<OpusPartSubDto> opusPartSubDtos = new ArrayList<>();
        if (opusUuids.size() > 0)
        {
            opusPartSubDtos = partSubMapper.selectByUuids(opusUuids);
            parameterMap = new HashMap<>();
            for (OpusPartSubDto opusPartSubDto : opusPartSubDtos)
            {
                for (int i = 0; i < 2; i++)
                {
                    parameterMap.put("partSubUuid", opusPartSubDto.getUuid());
                    parameterMap.put("table", 0 == i ? "opus_partsub_material" : "opus_partsub_accessories");
                    Map<?, ?> partSubMaterialAccessories = partSubMapper.findPartSubMaterial(parameterMap);
                    if (0 == i)
                    {
                        opusPartSubDto.setOpusPartSubMaterials(partSubMaterialAccessories);
                    }
                    else
                    {
                        opusPartSubDto.setOpusPartSubAccessoriess(partSubMaterialAccessories);
                    }
                }
            }
        }
        return opusPartSubDtos;
    }
    
    @Override
    public List<OpusRelationsDto> findOpusRelations(String opusUuid, String tableName)
    {
        parameterMap = new ConcurrentHashMap<>();
        parameterMap.put("opusUuid", opusUuid);
        parameterMap.put("tableName", tableName);
        return opusMapper.findOpusRelations(parameterMap);
    }
    
    @Override
    public int addOpusRelations(OpusRelationsReq opusRelationsReq, String relationTableName)
    {
        parameterMap = new ConcurrentHashMap<>();
        parameterMap.put("opusRelationsReq", opusRelationsReq);
        parameterMap.put("tableName", relationTableName);
        return opusMapper.addOpusRelations(parameterMap);
    }
    
    @Override
    public int deleteOpusRelations(OpusRelationsReq opusRelationsReq, String relationTableName)
    {
        parameterMap = new ConcurrentHashMap<>();
        parameterMap.put("opusRelationsReq", opusRelationsReq);
        parameterMap.put("tableName", relationTableName);
        return opusMapper.deleteOpusRelations(parameterMap);
    }
    
    @Override
    public int updateOpus(OpusInsertReq opusInsertReq)
    {
        String opusUuid = opusInsertReq.getUuid();
        Example example = new Example(Opus.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid", opusUuid);
        Opus opus = new Opus();
        opus.setUuid(opusUuid);
        opus = opusMapper.selectOne(opus);
        String[] includesProperties = {"uuid", "memberUuid"};
        BeanUtils.copyProperties(opusInsertReq, opus, includesProperties);
        opus.setModifier(getUuid());
        opus.setModifyDate(new Date());
        return opusMapper.updateByExample(opus, example);
    }
    
}
