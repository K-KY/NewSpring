package org.example.model.user.dto;

import org.example.dto.Dto;

public class RegisterDto {
    private final String userName;
    private final String userGender;
    private final String userEmail;
    private final String userRegion;
    private final String userSecret;

    public RegisterDto(String userName, String userGender, String userEmail, String userRegion, String userSecret) {
        this.userName = userName;
        this.userGender = userGender;
        this.userEmail = userEmail;
        this.userRegion = userRegion;
        this.userSecret = userSecret;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserGender() {
        return userGender;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserRegion() {
        return userRegion;
    }

    public String getUserSecret() {
        return userSecret;
    }

    @Override
    public String toString() {
        return "RegisterDto{" +
                "userName='" + userName + '\'' +
                ", userGender='" + userGender + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userRegion='" + userRegion + '\'' +
                ", userSecret='" + userSecret + '\'' +
                '}';
    }
}
