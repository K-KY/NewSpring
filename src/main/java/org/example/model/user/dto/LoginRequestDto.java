package org.example.model.user.dto;


public class LoginRequestDto {

    private String userEmail;
    private String userSecret;

    // 기본 생성자
    public LoginRequestDto() {
    }

    // 매개변수가 있는 생성자
    public LoginRequestDto(String userEmail, String userSecret) {
        this.userEmail = userEmail;
        this.userSecret = userSecret;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserSecret() {
        return userSecret;
    }

    public void setUserSecret(String userSecret) {
        this.userSecret = userSecret;
    }

    @Override
    public String toString() {
        return "LoginRequestDto{" +
                "userEmail='" + userEmail + '\'' +
                ", userSecret='" + userSecret + '\'' +
                '}';
    }
}
