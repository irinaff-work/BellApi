package ru.bellintegrator.practice.organization.view;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;

@JsonPropertyOrder({"name", "fullName", "inn", "kpp", "address", "phone", "isActive"})
public class OrganizationViewAdd {

    private String name;

    private String fullName;

    private String inn;

    private String kpp;

    private String address;

    private String phone;

    private String isActive;

    public String getName() {
        return name;
    }

    public String getInn() {
        return inn;
    }

    public String isActive() {
        return isActive;
    }

    public String getFullName() {
        return fullName;
    }

    public String getKpp() {
        return kpp;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public void setActive(String active) {
        isActive = active;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setKpp(String kpp) {
        this.kpp = kpp;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
