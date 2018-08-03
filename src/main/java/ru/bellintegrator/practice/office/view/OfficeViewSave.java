package ru.bellintegrator.practice.office.view;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;

@JsonPropertyOrder({"name", "address", "phone", "isActive"})
public class OfficeViewSave {

    @ApiModelProperty(hidden = true)
    private Long id;

    private String name;

    private String address;

    private String phone;

    private boolean isActive;

    @JsonProperty
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
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

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "{id:" + id + ";name:" + name + ";address:" + address + ";phone:" +
                phone + ";isActive:" + isActive + "}";
    }
}
