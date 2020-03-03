/*
*
* SpecificationItem.java
* Copyright(C) 2017-2020 ocean
* @date 2020-03-03
*/
package com.ocean.entity;

public class SpecificationItem {
    private Integer id;

    private Integer specificationId;

    private String name;

    private String spend;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSpecificationId() {
        return specificationId;
    }

    public void setSpecificationId(Integer specificationId) {
        this.specificationId = specificationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getSpend() {
        return spend;
    }

    public void setSpend(String spend) {
        this.spend = spend == null ? null : spend.trim();
    }
}