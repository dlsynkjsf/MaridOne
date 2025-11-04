package org.example.maridone.embeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Size;

@Embeddable
public class Address {

    @Column(name = "permanent_address")
    private String permanentAddress;

    @Column(name = "temporary_address")
    private String temporaryAddress;

    @Size(min = 0,max = 50)
    @Column(name = "city")
    private String city;

    @Size(min = 0,max = 50)
    @Column(name = "state")
    private String state;

    @Size(min = 0,max = 50)
    @Column(name = "country")
    private String country;

    @Column(name = "zip_code")
    private String zipCode;

    public String getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(String permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public String getTemporaryAddress() {
        return temporaryAddress;
    }

    public void setTemporaryAddress(String temporaryAddress) {
        this.temporaryAddress = temporaryAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
