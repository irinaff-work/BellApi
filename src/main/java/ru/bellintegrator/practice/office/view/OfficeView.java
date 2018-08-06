package ru.bellintegrator.practice.office.view;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;

@JsonPropertyOrder({"id", "name", "address", "phone", "isActive"})
public class OfficeView {

    @ApiModelProperty(hidden = true)
    private Long id;

    private Long orgId;

    private String name;

    private String phone;

    private boolean isActive;

    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    @JsonIgnore
    public Long getOrgId() {
        return orgId;
    }

    public String getName() {
        return name;
    }

    @JsonIgnore
    public String getPhone() {
        return phone;
    }

    public boolean isActive() {
        return isActive;
    }

    @JsonIgnore
    public void setId(Long id) {
        this.id = id;
    }

    @JsonProperty("orgId")
    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("phone")
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "{id:" + id + "orgId:" + orgId + ";name:" + name + ";isActive:" + isActive + "}";
    }
}
