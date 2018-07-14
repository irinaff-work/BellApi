package ru.bellintegrator.practice.office.model;

public class Office {

    private Long id;

    private Long orgId;

    private String name;

    private String phone;

    private String address;

    private boolean isActive;

    public Office(Long id, Long orgId, String name, String phone,
                        String address, boolean isActive) {
        this.id = id;
        this.orgId = orgId;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.isActive = isActive;
    }

    public Long getId() {
        return id;
    }

    public Long getOrgId() {
        return orgId;
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
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
}
