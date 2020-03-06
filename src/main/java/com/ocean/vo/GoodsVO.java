/*
*
* Goods.java
* Copyright(C) 2017-2020 ocean
* @date 2020-03-03
*/
package com.ocean.vo;

import com.ocean.entity.Goods;
import com.ocean.utils.StringUtils;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.List;

@Data
public class GoodsVO {
    private Integer id;
    private String headline;
    private String subhead;
    private BigDecimal price;
    private Integer discount;
    private Integer categoryId;
    private Integer itemId;
    private Integer ifPutaway;
    private String primaryImage;
    private String themeImage;
    private List<String> carouselImageList;
    private String detailImage;
    private List<String> tagList;
    private List<String> specificationList;
    private Integer visibleGridId;
    private String remark;
    private Integer ifTest;

    /**
     * vo 转 entity
     * @return goodsVO
     */
    public Goods copyRepositories() {
        Goods goods = new Goods();
        BeanUtils.copyProperties(this, goods);
        String carouselImage = StringUtils.append(getCarouselImageList());
        String tags = StringUtils.append(getTagList());
        String specification = StringUtils.append(getSpecificationList());
        goods.setCarouselImage(carouselImage);
        goods.setTags(tags);
        goods.setSpecificationId(specification);

        return goods;
    }

    /**
     * entity 转 vo
     * @param goods 商品
     */
    public void setProperties(Goods goods) {
        BeanUtils.copyProperties(goods, this);
        setCarouselImageList(StringUtils.stringToList(goods.getCarouselImage()));
        setTagList(StringUtils.stringToList(goods.getTags()));
        setSpecificationList(StringUtils.stringToList(goods.getSpecificationId()));
    }
}