package ru.bellintegrator.practice.organization.model;

public class Organization {

    private Long id;

    private String name;

    private String fullName;

    private String inn;

    private String kpp;

    private String phone;

    private String address;

    private boolean isActive;

    public Organization(Long id, String name, String fullName,
                         String inn, String kpp, String phone,
                        String address, boolean isActive) {
        this.id = id;
        this.name = name;
        this.fullName = fullName;
        this.inn = inn;
        this.kpp = kpp;
        this.phone = phone;
        this.address = address;
        this.isActive = isActive;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFullName() {
        return fullName;
    }

    public String getInn() {
        return inn;
    }

    public String getPhone() {
        return phone;
    }

    public boolean isActive() {
        return isActive;
    }

    public String getKpp() {
        return kpp;
    }

    public String getAddress() {
        return address;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setKpp(String kpp) {
        this.kpp = kpp;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "{id:" + id + ";name:" + name + ";fullName" + fullName + ";inn:" + inn + ";kpp:" + kpp +";phone:" +
                phone + ";address:" + address + ";isActive:" + isActive + "}";
    }
}
