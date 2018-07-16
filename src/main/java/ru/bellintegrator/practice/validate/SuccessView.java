package ru.bellintegrator.practice.validate;

import io.swagger.annotations.ApiModelProperty;

public class SuccessView {

    @ApiModelProperty

    public String result;

    @Override
    public String toString() {
        return "{result" + result + "}";
    }
}

