package ru.bellintegrator.practice.office.model;

import ru.bellintegrator.practice.organization.model.Organization;

import javax.persistence.*;

/**
 * Офис
 */
@Entity
@Table(name = "Office")
public class Office {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
     * Адрес
     */
    @Column(name = "address", length = 1000, nullable = false)
    private String address;

    /**
     * Номер телефона
     */
    @Column(name = "phone", length = 11, nullable = true)
    private String phone;

    /**
     * Запись действительна
     */
    @Column(name = "is_active")
    private boolean isActive;

    /**
     * Ссылка на Организацию
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "org_id")
    private Organization organization;

    /**
     * Конструктор для hibernate
     */
    public Office() {
    }

    public Office(Organization organization, String name, String phone,
                  String address, boolean isActive) {
        this.organization = organization;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.isActive = isActive;
    }

    public Long getId() {
        return id;
    }

    public Organization getOrganization() {
        return this.organization;
    }

    public Long getOrgId() {
        return this.organization.getId();
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

    public void setOrganization(Organization organization) {
        this.organization = organization;
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
