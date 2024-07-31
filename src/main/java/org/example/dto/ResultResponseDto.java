package org.example.dto;

import org.example.dto.register.RegisterDto;


public class ResultResponseDto<D> implements Dto {
    private D data;
    private boolean isSuccess;
    private String message;

    public ResultResponseDto(D data, boolean isSuccess, String message) {
        this.data = data;
        this.isSuccess = isSuccess;
        this.message = message;
    }

    public D getDto() {
        return data;
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
                "data=" + data +
                ", isSuccess=" + isSuccess +
                ", message='" + message + '\'' +
                '}';
    }
}
