package ru.bellintegrator.practice.office.view;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"id", "name", "address", "phone", "isActive"})
public class OfficeViewUpdate {

    private String id;

    private String name;

    private String address;

    private String phone;

    private String isActive;

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
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

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setActive(String active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "{id:" + id + ";name:" + name + ";address:" + address +
                ";isActive:" + isActive + "}";
    }
}
