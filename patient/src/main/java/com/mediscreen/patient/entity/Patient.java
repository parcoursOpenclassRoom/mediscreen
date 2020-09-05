package com.mediscreen.patient.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Patient {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String firstName;
    private String sex;
    private Date birthday;
    private String address;
    private String phone;
    private Date created;
    private Date modified;

    public Patient() {
    }

    public Patient(String name, String firstName, String sex, Date birthday, String address, String phone) {
        this.name = name;
        this.firstName = firstName;
        this.sex = sex;
        this.birthday = birthday;
        this.address = address;
        this.phone = phone;
    }

    public Patient(int id, String name, String firstName, String sex, Date birthday, String address, String phone) {
        this.id = id;
        this.name = name;
        this.firstName = firstName;
        this.sex = sex;
        this.birthday = birthday;
        this.address = address;
        this.phone = phone;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", firstName='" + firstName + '\'' +
                ", sex='" + sex + '\'' +
                ", birthday=" + birthday +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", created=" + created +
                ", modified=" + modified +
                '}';
    }
}
