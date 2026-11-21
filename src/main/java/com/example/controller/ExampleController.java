package com.example.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ExampleController {

    @GetMapping("/example")
    public CommonResponse<?> example() {
        return new CommonResponse<>(201, "test", null);
    }
}
