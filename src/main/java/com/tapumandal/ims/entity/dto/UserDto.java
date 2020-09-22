package com.tapumandal.ims.entity.dto;

import com.sun.istack.Nullable;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;

@Component
public class UserDto{
    @NotNull
    private int id;

    @NotNull(message = "name can't be empty")
    @Size(min=4, max = 32, message = "Write a proper name")
    protected String name;

    @NotNull(message = "UserName can't be empty")
    protected String username;

    @NotNull(message = "phone can't be empty")
    @Size(min=7, max = 20, message = "Phone number is not usable")
    protected String phone;

    @NotNull(message = "password can't be empty")
    @Size(min=6, max = 32, message = "Password is not valid")
    protected String password;

    @NotNull(message = "address can't be empty")
    @Size(min=12, max = 32, message = "Address information is very less")
    protected String address;

    @NotNull(message = "Work title can't be empty")
    @Size(min=3, max = 32, message = "Work Title is not valid")
    protected String work_title;


    protected String role;

    protected boolean is_active = true;

    protected boolean is_deleted = false;

    @Nullable
    private CompanyDto company;

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

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWork_title() {
        return work_title;
    }

    public void setWork_title(String work_title) {
        this.work_title = work_title;
    }

    public CompanyDto getCompany() {
        return company;
    }

    public void setCompany(CompanyDto company) {
        this.company = company;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isActive() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

    public boolean isDeleted() {
        return is_deleted;
    }

    public void setIs_deleted(boolean is_deleted) {
        this.is_deleted = is_deleted;
    }

    public boolean isIs_active() {
        return is_active;
    }

    public boolean isIs_deleted() {
        return is_deleted;
    }
}
