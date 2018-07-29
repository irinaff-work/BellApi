package ru.bellintegrator.practice.validate.view;

import io.swagger.annotations.ApiModelProperty;

public class ErrorView {

    @ApiModelProperty

    public String error;

    public ErrorView(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "Ошибка сервера";
    }
}

