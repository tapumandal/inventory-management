package com.tapumandal.ims.entity;

import com.tapumandal.ims.entity.dto.CompanyDto;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "vehicle")
public class Vehicle {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected int id;

    @Column(name = "name")
    protected String name;

    @Column(name = "model")
    protected String model;

    @Column(name = "registration")
    protected String registration;

    @Column(name = "is_active")
    protected boolean isActive;

    @Column(name = "is_deleted")
    protected boolean isDeleted;

    @Column(name = "created_at")
    @CreationTimestamp
    protected Date createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    protected Date updatedAt;

}
