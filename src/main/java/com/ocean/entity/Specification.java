/*
*
* Specification.java
* Copyright(C) 2017-2020 ocean
* @date 2020-03-03
*/
package com.ocean.entity;

public class Specification {
    private Integer id;

    private String name;

    private String remark;

    private String unit;

    private Byte ifStandard;

    private String reserve;

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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public Byte getIfStandard() {
        return ifStandard;
    }

    public void setIfStandard(Byte ifStandard) {
        this.ifStandard = ifStandard;
    }

    public String getReserve() {
        return reserve;
    }

    public void setReserve(String reserve) {
        this.reserve = reserve == null ? null : reserve.trim();
    }
}