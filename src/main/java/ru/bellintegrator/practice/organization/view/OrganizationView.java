package ru.bellintegrator.practice.organization.view;

import io.swagger.annotations.ApiModelProperty;

public class OrganizationView {

    @ApiModelProperty(hidden = true)
    public Long id;

    public String name;

    public String fullName;

    public String inn;

    public String kpp;

    public String phone;

    public String address;

    public boolean isActive;

    public String isSuccess;


    @Override
    public String toString() {
        return "{id:" + id + ";name:" + name + ";fullName" + fullName + ";inn:" + inn + ";kpp:" + kpp +";phone:" +
                phone + ";phone:" + address + ";address:" + isActive + "}";
    }
}
