package com.tapumandal.ims.entity.dto;

import org.springframework.stereotype.Component;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Component
public class CompanyDto {

    @NotNull
    private int id;

    @NotNull(message = "Company name can't be empty")
    @Size(min=2, max = 32, message = "Write a proper name", groups = FinalVersion.class)
    protected String name;

    @Email(message = "Company email is not valid", groups = FinalVersion.class)
    protected String email;

    @NotNull(message = "phone can't be empty")
    @Size(min=7, max = 20, message = "Phone number is not usable", groups = FinalVersion.class)
    protected String phone;

    @NotNull(message = "address can't be empty")
    @Size(min=12, max = 32, message = "Address information is very less", groups = FinalVersion.class)
    protected String address;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
