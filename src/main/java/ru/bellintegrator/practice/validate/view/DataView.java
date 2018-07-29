package ru.bellintegrator.practice.validate.view;

import io.swagger.annotations.ApiModelProperty;

public class DataView {

    @ApiModelProperty
    public Object data;

    public DataView (Object data)
    {
        this.data = data;
    }
}

