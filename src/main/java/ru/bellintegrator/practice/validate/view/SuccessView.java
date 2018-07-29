package ru.bellintegrator.practice.validate.view;

import io.swagger.annotations.ApiModelProperty;

public class SuccessView {

    @ApiModelProperty
    public String result;

    public SuccessView() {
        this.result = "success";
    }

    @Override
    public String toString() {
        return "{result" + result + "}";
    }
}

