package com.ravionics.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="logintest")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="userid")
    private int id;

    @Column(name = "email")
    private String email;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "password")
    private String password;

    @Column(name = "active")
    private int active;

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name="logintest_roles", joinColumns=@JoinColumn(name="userid"), inverseJoinColumns=@JoinColumn(name="roleid"))
    private Set<Role> roles;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}