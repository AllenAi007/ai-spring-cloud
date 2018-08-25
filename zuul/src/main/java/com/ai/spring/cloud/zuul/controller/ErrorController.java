package com.ai.spring.cloud.zuul.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorController {

    @GetMapping("/sso/error")
    public ErrorMessage errorMessage(@RequestParam String errorType, @RequestParam String errorCode, @RequestParam String errorMessage) {
        return new ErrorMessage(errorType, errorCode, errorMessage);
    }

    public class ErrorMessage {

        String errorType;
        String errorCode;
        String errorMessage;

        public ErrorMessage() {
        }

        public ErrorMessage(String errorType, String errorCode) {
            this.errorType = errorType;
            this.errorCode = errorCode;
        }

        public ErrorMessage(String errorType, String errorCode, String errorMessage) {
            this.errorType = errorType;
            this.errorCode = errorCode;
            this.errorMessage = errorMessage;
        }
    }
}
