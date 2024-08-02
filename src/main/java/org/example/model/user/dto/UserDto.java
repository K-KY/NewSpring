package org.example.model.user.dto;

public class UserDto {
    private String userEmail;
    private String userName;
    private String userGender;
    private String userRegion;
    private String userSecret;

    public UserDto(String userEmail, String userName, String userGender, String userRegion, String userSecret) {
        this.userEmail = userEmail;
        this.userName = userName;
        this.userGender = userGender;
        this.userRegion = userRegion;
        this.userSecret = userSecret;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserGender() {
        return userGender;
    }

    public String getUserRegion() {
        return userRegion;
    }

    public String getUserSecret() {
        return userSecret;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "userEmail='" + userEmail + '\'' +
                ", userName='" + userName + '\'' +
                ", userGender='" + userGender + '\'' +
                ", userRegion='" + userRegion + '\'' +
                ", userSecret='" + userSecret + '\'' +
                '}';
    }
}
