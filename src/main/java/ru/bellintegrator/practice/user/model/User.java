package ru.bellintegrator.practice.user.model;

import ru.bellintegrator.practice.document.model.Document;

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
    @Column(name = "office_id", nullable = false)
    private Long officeId;
    /**
     * Ссылка на страну
     */
    @Column(name = "country_id", nullable = false)
    private Long countryId;
    /**
     * Ссылка на документ
     */
    @Column(name = "doc_id", nullable = false)
    private Long docId;

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

    private String docCode;

    private String docName;

    private String docNumber;

    private String docDate;

    private String citizenshipCode;
    /**
     * Запись действительна
     */
    @Column(name = "is_active")
    private boolean isIdentified;

    private Set<Document> documents;

    /**
     * Конструктор для hibernate
     */
    public User() {

    }
    public User(Long id, Long officeId, String firstName, String lastName, String middleName,
                String phone, String position, String docCode, String docName,
                String docNumber, String docDate, String citizenshipCode, boolean isIdentified) {
        this.id = id;
        this.officeId = officeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.phone = phone;
        this.position = position;
        this.docCode = docCode;
        this.docName = docName;
        this.docNumber = docNumber;
        this.docDate = docDate;
        this.citizenshipCode = citizenshipCode;
        this.isIdentified = isIdentified;
    }

    public Long getId() {
        return id;
    }

    public Long getOfficeId() {
        return officeId;
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

    public String getDocCode() {
        return docCode;
    }

    public String getDocName() {
        return docName;
    }

    public String getDocNumber() {
        return docNumber;
    }

    public String getDocDate() {
        return docDate;
    }

    public String getCitizenshipCode() {
        return citizenshipCode;
    }

    public boolean isIdentified() {
        return isIdentified;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOfficeId(Long officeId) {
        this.officeId = officeId;
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

    public void setDocCode(String docCode) {
        this.docCode = docCode;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public void setDocNumber(String docNumber) {
        this.docNumber = docNumber;
    }

    public void setDocDate(String docDate) {
        this.docDate = docDate;
    }

    public void setCitizenshipCode(String citizenshipCode) {
        this.citizenshipCode = citizenshipCode;
    }

    public void setIdentified(boolean identified) {
        isIdentified = identified;
    }
}
