/*
*
* Goods.java
* Copyright(C) 2017-2020 ocean
* @date 2020-03-03
*/
package com.ocean.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Goods {
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
    private String carouselImage;
    private String detailImage;
    private String tags;
    private String specificationId;
    private Integer visibleGridId;
    private String remark;
    private Integer ifTest;

}