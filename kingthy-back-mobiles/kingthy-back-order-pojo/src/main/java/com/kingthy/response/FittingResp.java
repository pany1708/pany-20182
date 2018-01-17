package com.kingthy.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kingthy.dto.GoodsImageDto;
import com.kingthy.dto.PartInfoDto;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @author xumin
 * @Description:
 * @DATE Created by 17:06 on 2017/12/20.
 * @Modified by:
 */
@Data
@ToString
public class FittingResp implements Serializable
{

    private static final long serialVersionUID = 1L;

    private String goodsName;
    private String standardPrice;
    private String goodsImage;
    @JsonIgnore
    private String partInfo;
    @JsonIgnore
    private String materielInfo;
    @JsonIgnore
    private String accessoriesInfo;
    private String cover;
    private String goodsUuid;
    private String fittingUuid;

    public List<GoodsImageDto> goodsImageDtoList;
    @JsonIgnore
    private List<PartInfo> partInfoList;

    private List<MaterielInfo> materielInfoList;

    private List<AccessoriesInfo> accessoriesInfoList;

    @Data
    @ToString
    public class PartInfo{

        private String sn;
        private String partsubCategoryName;
        private String partsubCategoryUuid;
        private String material;
        private String parts;
        private String imagePath;
        private String partsubStatus;

    }

    @Data
    @ToString
    public class MaterielInfo{
        private String name;
        private String uuid;
        private List<ImagesDto> images;

    }

    @Data
    @ToString
    public class ImagesDto{
        private String image;
    }

    @Data
    @ToString
    public class AccessoriesInfo{
        private String image;
        private String name;
    }

}
