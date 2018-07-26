package ru.bellintegrator.practice.user.view;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class UserViewFull extends UserView {

    @ApiModelProperty

    public String phone;

    public String docName;

    public String docCode;

    public String docDate;

}
