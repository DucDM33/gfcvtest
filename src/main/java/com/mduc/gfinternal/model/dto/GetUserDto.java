package com.mduc.gfinternal.model.dto;

import com.mduc.gfinternal.model.Role;

import java.time.LocalDate;

public class GetUserDto {
    private Integer id;
    private String fullName;
    private String email;
    private String username;
    private boolean isDelete;
    private String phoneNumber;
    private Role role;
    public GetUserDto(Integer id, String fullName, String email, String username, boolean isDelete, String phoneNumber, Role role) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.username = username;
        this.isDelete = isDelete;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }

    public GetUserDto() {

    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
