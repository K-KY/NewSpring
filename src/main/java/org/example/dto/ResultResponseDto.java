package org.example.dto;

import org.example.dto.register.RegisterDto;


public class ResultResponseDto implements Dto {
    private Dto dto;
    private boolean isSuccess;
    private String message;

    public ResultResponseDto(Dto dto, boolean isSuccess, String message) {
        this.dto = dto;
        this.isSuccess = isSuccess;
        this.message = message;
    }

    public Dto getDto() {
        return dto;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "ResultResponseDto{" +
                "dto=" + dto +
                ", isSuccess=" + isSuccess +
                ", message='" + message + '\'' +
                '}';
    }
}
