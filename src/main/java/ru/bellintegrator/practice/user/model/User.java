package ru.bellintegrator.practice.user.model;

import ru.bellintegrator.practice.country.model.Country;
import ru.bellintegrator.practice.document.model.Document;
import ru.bellintegrator.practice.office.model.Office;

import javax.persistence.*;

import java.util.Date;
import java.util.Set;

/**
 * Пользователь
 */
@Entity
@Table(name = "User")
public class User {

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
     * Ссылка на Офис
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "office_id")
    private Office office;

    /**
     * Ссылка на страну
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    private Country country;

    /**
     * Ссылка на документ
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doc_id")
    private Document document;

    /**
     * Имя
     */
    @Column(name = "first_name", length = 100, nullable = false)
    private String firstName;
    /**
     * Фамилия
     */
    @Column(name = "last_name", length = 100, nullable = true)
    private String lastName;
    /**
     * Отчество
     */
    @Column(name = "middle_name", length = 100, nullable = true)
    private String middleName;
    /**
     * Номер телефона
     */
    @Column(name = "phone", length = 9, nullable = true)
    private String phone;
    /**
     * Должность
     */
    @Column(name = "position", length = 100, nullable = false)
    private String position;

    /**
     * Запись действительна
     */
    @Column(name = "is_active")
    private boolean isIdentified;


    /**
     * Конструктор для hibernate
     */
    public User() {

    }
    public User(Long id, Office office, Country country, Document document, String firstName, String lastName, String middleName,
                String phone, String position, boolean isIdentified) {
        this.id = id;
        this.office = office;
        this.country = country;
        this.document = document;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.phone = phone;
        this.position = position;
        this.isIdentified = isIdentified;
    }

    public Long getId() {
        return id;
    }

    public Office getOffice() {
        return  this.office;
    }

    public Long getOfficeId() {
        return this.office.getId();
    }

    public Country getCountry() {
        return this.country;
    }

    public Document getDocument() {
        return this.document;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getPhone() {
        return phone;
    }

    public String getPosition() {
        return position;
    }

    public String getDocNumber() {
        return this.document.getDocNumber();
    };
    public String getDocName() {
        return this.document.getDocType().getDocName();
    };

    public String getDocDate() {
        return this.document.getDocDate();
    };

    public String getCitizenshipCode() {
        return this.country.getCitizenshipCode();
    };

    public String getCitizenshipName() {
        return this.country.getCitizenshipName();
    };

    public boolean isIdentified() {
        return isIdentified;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public void setOfficeId(Office office) {
//        this.office = office;
//    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public void setDocument(Document document) {
        this.document = document;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setIdentified(boolean identified) {
        isIdentified = identified;
    }
}
