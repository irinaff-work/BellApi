package ru.bellintegrator.practice.office.view;

import io.swagger.annotations.ApiModelProperty;

public class OfficeViewFull extends OfficeView{

    @ApiModelProperty
    public String phone;

    public String address;

    @Override
    public String toString() {
        return "{id:" + id + "orgId:" + orgId +";name:" + name + ";address:" + address + ";phone:" +
                phone + ";isActive:" + isActive + "}";
    }
}
