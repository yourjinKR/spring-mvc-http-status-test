package com.example.controller;

public record CommonResponse<T>(
        Integer status,
        T data,
        Object error
) {
}
