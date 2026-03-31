package com.kh.vira_dev.ecommerce.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponse<T> {
    private boolean success;
    private String message;
    private int status;
    private T data;
    private LocalDateTime timestamp;

    public static <T> ApiResponse<T> success(String message, HttpStatus status, T data) {
        return ApiResponse.<T>builder()
                .success(true)
                .message(message)
                .status(status.value())
                .data(data)
                .timestamp(LocalDateTime.now())
                .build();
    }

    public static <T> ApiResponse<T> success(String message,T data) {
        return ApiResponse.<T>builder()
                .success(true)
                .message(message)
                .status(HttpStatus.OK.value())
                .data(data)
                .timestamp(LocalDateTime.now())
                .build();
    }
}
