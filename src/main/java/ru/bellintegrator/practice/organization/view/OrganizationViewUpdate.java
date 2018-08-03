package ru.bellintegrator.practice.organization.view;

public class OrganizationViewUpdate {

    private Long id;

    private String name;

    private String inn;

    private boolean isActive;

    private String fullName;

    private String kpp;

    private String address;

    private String phone;

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
