package ru.bellintegrator.practice.organization.view;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;

@JsonPropertyOrder({"id", "name", "inn", "isActive"})
public class OrganizationView {

    @ApiModelProperty(hidden = true)
    private String id;

    private String name;

    private String inn;

    private String isActive;

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @JsonIgnore
    public String getInn() {
        return inn;
    }

    public String isActive() {
        return isActive;
    }

    @JsonIgnore
    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("inn")
    public void setInn(String inn) {
        this.inn = inn;
    }

    public void setActive(String active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "{id:" + id + ";name:" + name + ";inn:" + inn + ";isActive:" + isActive + "}";
    }
}
