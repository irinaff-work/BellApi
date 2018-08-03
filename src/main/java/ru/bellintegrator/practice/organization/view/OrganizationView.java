package ru.bellintegrator.practice.organization.view;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

public class OrganizationView {

    @ApiModelProperty(hidden = true)
    private Long id;

    private String name;

    private String inn;

    private boolean isActive;

    @JsonProperty
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @JsonIgnore
    public String getInn() {
        return inn;
    }

    public boolean isActive() {
        return isActive;
    }

    @JsonIgnore
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty
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
