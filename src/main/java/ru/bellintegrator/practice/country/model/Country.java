package ru.bellintegrator.practice.country.model;

import ru.bellintegrator.practice.user.model.User;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Справочник стран
 */
@Entity
@Table(name = "Country")
public class Country {

    @Id
    @GeneratedValue
    @Column(name = "Id")
    private Long id;

    /**
     * Служебное поле hibernate
     */
    @Version
    private Integer version;

    @OneToMany(
            mappedBy="country",
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
        user.setCountry(this);
    }

    public void removeUser(User user) {
        getUsers().remove(user);
        user.setCountry(null);
    }

    /**
     * Код страны
     */
    @Column(name = "citizenship_code", length = 100, nullable = false)
    private String citizenshipCode;
    /**
     * Название страны
     */
    @Column(name = "citizenship_name", length = 250, nullable = true)
    private String citizenshipName;


    /**
     * Конструктор для hibernate
     */
    public Country() {

    }

    public Country(Long id, String citizenshipCode, String citizenshipName) {
        this.id = id;
        this.citizenshipCode = citizenshipCode;
        this.citizenshipName = citizenshipName;
    }

    public Long getId() {
        return id;
    }

    public String getCitizenshipCode() {
        return citizenshipCode;
    }

    public String getCitizenshipName() {
        return citizenshipName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
