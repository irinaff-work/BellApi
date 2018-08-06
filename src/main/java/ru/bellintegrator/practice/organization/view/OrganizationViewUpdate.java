package ru.bellintegrator.practice.organization.view;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"id", "name", "fullName", "inn", "kpp", "address", "phone", "isActive"})
public class OrganizationViewUpdate {

    private String id;

    private String name;

    private String inn;

    private String fullName;

    private String kpp;

    private String address;

    private String phone;

    private String isActive;

    public String getId() {
        return id;
    }

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

    public void setId(String id) {
        this.id = id;
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
