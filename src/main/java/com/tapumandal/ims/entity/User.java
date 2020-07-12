package com.tapumandal.ims.entity;

import java.sql.Date;

import javax.persistence.*;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tapumandal.ims.annotation.CustomCompanySerializer;
import com.tapumandal.ims.entity.dto.UserDto;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

@Entity
@Table(name="user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected int id;

    @Column(name = "name")
    protected String name;

    @Column(name = "email", unique = true)
    protected String email;

    @Column(name = "email_status")
    protected boolean isEmailVerified;

    @Column(name = "phone")
    protected String phone;

    @Column(name = "password")
    protected String password;

    @Column(name = "address")
    protected String address;

    @Column(name = "work_title")
    protected String workTitle;
    
    @Column(name = "role")
    protected String role;


    @Column(name = "is_active", columnDefinition = "boolean default 1")
    private boolean isActive = true;

    @Column(name = "is_deleted", columnDefinition = "boolean default 0")
    private boolean isDeleted = false;

    @Column(name = "created_at", updatable=false)
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Date updatedAt;

    private String userName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id")
    @Where(clause = "company_is_deleted = false AND company_is_active = true" )
    @JsonSerialize(using = CustomCompanySerializer.class)
    private Company company;


//    @OneToOne( mappedBy = "dsr" )
//    private DeliveryUnit dsr;
//    @OneToOne( mappedBy = "driver" )
//    private DeliveryUnit driver;
//    @OneToOne( mappedBy = "helpingHand" )
//    private DeliveryUnit helpingHand;

    public User(){};

    public User(UserDto userDto) {

        this.name = userDto.getName();
        this.email = userDto.getEmail();
        this.phone = userDto.getPhone();
        this.password = userDto.getPassword();
        this.address = userDto.getAddress();
        this.workTitle = userDto.getWork_title();
        this.role = userDto.getRole();
        this.isActive = userDto.isActive();
        this.isDeleted = userDto.isDeleted();

        this.userName = userDto.getEmail();

        if(userDto.getCompany() != null){
            this.company = new Company(userDto.getCompany());
        }
    }


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
        this.userName = email;
    }

    public boolean isEmailVerified() {
        return isEmailVerified;
    }

    public void setEmailVerified(boolean isEmailVerified) {
        this.isEmailVerified = isEmailVerified;
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getWorkTitle() {
        return workTitle;
    }

    public void setWorkTitle(String workTitle) {
        this.workTitle = workTitle;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getUsername(){
        return this.userName;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

//    public DeliveryUnit getDsr() {
//        return dsr;
//    }
//
//    public void setDsr(DeliveryUnit dsr) {
//        this.dsr = dsr;
//    }
//
//    public DeliveryUnit getDriver() {
//        return driver;
//    }
//
//    public void setDriver(DeliveryUnit driver) {
//        this.driver = driver;
//    }
//
//    public DeliveryUnit getHelpingHand() {
//        return helpingHand;
//    }
//
//    public void setHelpingHand(DeliveryUnit helpingHand) {
//        this.helpingHand = helpingHand;
//    }
}