package ru.bellintegrator.practice.user.view;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class UserView {

    @ApiModelProperty(hidden = true)

    public Long id;

    public Long officeId;

    public String firstName;

    public String lastName;

    public String middleName;

    public String docNumber;

    public String position;

    public String citizenshipCode;

    public boolean isIdentified;
}
