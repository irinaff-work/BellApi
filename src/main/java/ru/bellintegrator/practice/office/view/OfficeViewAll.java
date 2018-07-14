package ru.bellintegrator.practice.office.view;

import io.swagger.annotations.ApiModelProperty;

public class OfficeViewAll {

    @ApiModelProperty(hidden = true)
    public Long id;

    public Long orgId;

    public String name;

    public String phone;

    public String address;

    public boolean isActive;

    @Override
    public String toString() {
        return "{id:" + id + "orgId:" + orgId +";name:" + name + ";address:" + address + ";phone:" +
                phone + ";isActive:" + isActive + "}";
    }
}
