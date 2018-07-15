package ru.bellintegrator.practice.organization.view;

import io.swagger.annotations.ApiModelProperty;

public class OrganizationToSave {
    @ApiModelProperty
    public Long id;

    @ApiModelProperty(required  =  true)
    public String name;

    @ApiModelProperty(required  =  true)
    public String fullName;

    @ApiModelProperty(required  =  true)
    public String inn;

    @ApiModelProperty(required  =  true)
    public String kpp;

    @ApiModelProperty(required  =  true)
    public String address;

    public String phone;

    public boolean isActive;

    @Override
    public String toString() {
        return "{id:" + id + ";name:" + name + ";fullName" + fullName +
                ";inn:" + inn + ";kpp:" + kpp +";phone:" + phone +
                ";address:" + address + ";isActive:" + isActive + "}";
    }
}
