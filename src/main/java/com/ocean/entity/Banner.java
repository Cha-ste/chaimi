/*
*
* Banner.java
* Copyright(C) 2017-2020 ocean
* @date 2020-02-27
*/
package com.ocean.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("banner")
public class Banner {
    private Integer id;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("图片")
    private String img;

    @ApiModelProperty("描述")
    private String remark;

    private String reserve1;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getReserve1() {
        return reserve1;
    }

    public void setReserve1(String reserve1) {
        this.reserve1 = reserve1 == null ? null : reserve1.trim();
    }
}