package ru.bellintegrator.practice.organization.view;

import io.swagger.annotations.ApiModelProperty;

public class OrganizationView {

    @ApiModelProperty
    private Long id;

    @ApiModelProperty(required  =  true)
    private String name;

    private String inn;

    private boolean isActive;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getInn() {
        return inn;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "{id:" + id + ";name:" + name + ";inn:" + inn + ";isActive:" + isActive + "}";
    }
}
