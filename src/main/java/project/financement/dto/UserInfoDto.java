package project.financement.dto;

import jakarta.persistence.Column;

import java.util.UUID;

public class UserInfoDto {
    private UUID userInfoId;
    private String username;
    private String email;
    private String password;
    private String phoneNumber;

    public UUID getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(UUID userInfoId) {
        this.userInfoId = userInfoId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
