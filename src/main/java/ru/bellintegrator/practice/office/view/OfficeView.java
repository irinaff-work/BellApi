package ru.bellintegrator.practice.office.view;

import io.swagger.annotations.ApiModelProperty;

public class OfficeView {

    @ApiModelProperty(hidden = true)
    public Long id;

    public Long orgId;

    public String name;

    public boolean isActive;

    @Override
    public String toString() {
        return "{id:" + id + "orgId:" + orgId + ";name:" + name + ";isActive:" + isActive + "}";
    }
}
