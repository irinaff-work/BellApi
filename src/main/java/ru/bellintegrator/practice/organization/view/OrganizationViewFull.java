package ru.bellintegrator.practice.organization.view;

import io.swagger.annotations.ApiModelProperty;

public class OrganizationViewFull  extends OrganizationView{

    @ApiModelProperty(required  =  true)
    public String fullName;

    @ApiModelProperty(required  =  true)
    public String kpp;

    @ApiModelProperty(required  =  true)
    public String address;

    public String phone;

}
