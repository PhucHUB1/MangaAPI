package com.example.mangaapi.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "UNCATEGORIZED ERROR"),
    INVALID_KEY(1001, "Invalid message key"),
    USER_EXISTS(1002, "User already exists"),
    USERNAME_INVALID(1003, "Username must be ay least 3 characters"),
    INVALID_PASSWORD(1004, "Password must be at least 6 characters"),


    ;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private final int code;
    private final String message;
}
