package ru.bellintegrator.practice.user.view;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserView {

    private Long id;

    private Long officeId;

    private String firstName;

    private String lastName;

    private String middleName;

    private String position;

    private String docCode;

    private String citizenshipCode;


    @JsonProperty
    public Long getId() {
        return id;
    }

    @JsonIgnore
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

    @JsonIgnore
    public String getDocCode() {
        return docCode;
    }

    public String getPosition() {
        return position;
    }

    @JsonIgnore
    public String getCitizenshipCode() {
        return citizenshipCode;
    }

    @JsonIgnore
    public void setId(Long id) {
        this.id = id;
    }

    @JsonProperty
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

    @JsonProperty
    public void setDocCode(String docCode) {
        this.docCode = docCode;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @JsonProperty
    public void setCitizenshipCode(String citizenshipCode) {
        this.citizenshipCode = citizenshipCode;
    }
}
