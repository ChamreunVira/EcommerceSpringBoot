package com.kh.vira_dev.ecommerce.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorResponse<T> {
    private boolean success;
    private String message;
    private int status;
    private T error;
    private LocalDateTime timestamp;

    public static <T> ErrorResponse<T> error(String message, HttpStatus status, T error) {
        return ErrorResponse.<T>builder()
                .success(false)
                .message(message)
                .status(status.value())
                .error(error)
                .timestamp(LocalDateTime.now())
                .build();
    }

    public static <T> ErrorResponse<T> error(String message,T error) {
        return ErrorResponse.<T>builder()
                .success(false)
                .message(message)
                .status(HttpStatus.BAD_REQUEST.value())
                .error(error)
                .timestamp(LocalDateTime.now())
                .build();
    }

    public static <T> ErrorResponse<T> error(String message) {
        return ErrorResponse.<T>builder()
                .success(false)
                .message(message)
                .status(HttpStatus.BAD_REQUEST.value())
                .error(null)
                .timestamp(LocalDateTime.now())
                .build();
    }
}
