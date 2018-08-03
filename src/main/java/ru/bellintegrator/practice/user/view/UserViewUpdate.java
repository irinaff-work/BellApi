package ru.bellintegrator.practice.user.view;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserViewUpdate {

    private Long id;

    private String firstName;

    private String lastName;

    private String middleName;

    private String position;

    private String phone;

    private String docName;

    private String docNumber;

    private String docDate;

    private String citizenshipName;

    private String citizenshipCode;

    private boolean isIdentified;

    public Long getId() {
        return id;
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

    public String getPosition() {
        return position;
    }

    public String getPhone() {
        return phone;
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

    @JsonProperty
    public String getCitizenshipName() {
        return citizenshipName;
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

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    @JsonIgnore
    public void setCitizenshipName(String citizenshipName) {
        this.citizenshipName = citizenshipName;
    }

    public void setCitizenshipCode(String citizenshipCode) {
        this.citizenshipCode = citizenshipCode;
    }

    public void setIdentified(boolean identified) {
        isIdentified = identified;
    }
}
