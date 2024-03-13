package com.mobilers.gift_hommie_mobile.model.auth;

public class Account {
    private String email;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private int roleId;

    public Account() {
    }

    public Account(String username, String password) {
        this.email = username + "@gmail.com";
        this.username = username;
        this.password = password;
        this.firstName = "";
        this.lastName = "";
        this.roleId = 2;
    }
    public Account(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.firstName = "";
        this.lastName = "";
        this.roleId = 2;
    }

    public Account(String email, String username, String firstName, String lastName, int roleId) {
        this.email = email;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.roleId = roleId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}