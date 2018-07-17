package ru.bellintegrator.practice.office.model;

import javax.persistence.*;

/**
 * Офис
 */
@Entity
@Table(name = "Office")
public class Office {

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
     * Ссылка на Организацию
     */
    @Column(name = "org_id", nullable = false)
    private Long orgId;
    /**
     * Наименование
     */
    @Column(name = "name", length = 100, nullable = false)
    private String name;
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

    /**
     * Конструктор для hibernate
     */
    public Office() {

    }

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
