package com.aly.brightskies.task2.springdata.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
public class Member {
    @Id
    private Long id;
    @Column(name ="name")
    private String name;
    @Column(name ="email")
    private String email;
    @Column(name ="date")
    private Date date;

    public Member(Long id, Date date, String email, String name) {
        this.id = id;
        this.date = date;
        this.email = email;
        this.name = name;
    }

    public Member() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
