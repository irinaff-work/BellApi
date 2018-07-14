package ru.bellintegrator.practice.organization.view;

import io.swagger.annotations.ApiModelProperty;

public class OrganizationView {

    @ApiModelProperty(hidden = true)
    public Long id;

    public String name;

    public String inn;

    public boolean isActive;

    @Override
    public String toString() {
        return "{id:" + id + ";name:" + name + ";inn:" + inn + ";isActive:" + isActive + "}";
    }
}
