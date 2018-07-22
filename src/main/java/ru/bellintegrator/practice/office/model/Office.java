package ru.bellintegrator.practice.office.model;

import ru.bellintegrator.practice.organization.model.Organization;
import ru.bellintegrator.practice.user.model.User;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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
    //@Column(name = "org_id", nullable = false)
    //private Long orgId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "org_id")
    private Organization organization;

    @OneToMany(
            mappedBy="office",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<User> users;

    public Set<User> getUsers() {
        if (users == null) {
            users = new HashSet<>();
        }
        return users;
    }
    public void addUser(User user) {
        getUsers().add(user);
        user.setOfficeId(this);
    }

    public void removeUser(User user) {
        getUsers().remove(user);
        user.setOfficeId(null);
    }

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

    public Office(Long id, Organization organization, String name, String phone,
                  String address, boolean isActive) {
        this.id = id;
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
