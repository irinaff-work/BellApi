package ru.bellintegrator.practice.organization.model;

import javax.persistence.*;

/**
 * Организация
 */
@Entity
@Table(name = "Organization")
public class Organization {
    @Id
    @GeneratedValue
    @Column(name = "Id")
    private Long id;

    /**
     * Служебное поле hibernate
     */
    @Version
    private Integer version;

    /**
     * Наименование
     */
    @Column(name = "name", length = 100, nullable = false)
    private String name;

    /**
     * Полное наименование
     */
    @Column(name = "full_name", length = 250, nullable = true)
    private String fullName;

    /**
     * ИНН
     */
    @Column(name = "inn", length = 12, nullable = false)
    private String inn;

    /**
     * КПП
     */
    @Column(name = "kpp", length = 12, nullable = false)
    private String kpp;

    /**
     * Номер телефона
     */
    @Column(name = "phone", length = 9, nullable = true)
    private String phone;

    /**
     * Адрес
     */
    @Column(name = "address", length = 1000, nullable = false)
    private String address;

    /**
     * Запись действительна
     */
    @Column(name = "is_active")
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
