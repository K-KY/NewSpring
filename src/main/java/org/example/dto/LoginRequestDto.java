package org.example.dto;


public class LoginRequestDto {
    private final String userEmail;
    private final String userSecret;

    public LoginRequestDto(String userEmail, String userSecret) {
        this.userEmail = userEmail;
        this.userSecret = userSecret;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserSecret() {
        return userSecret;
    }

    @Override
    public String toString() {
        return "LoginRequestDto{" +
                "userEmail='" + userEmail + '\'' +
                ", userSecret='" + userSecret + '\'' +
                '}';
    }
}
