package com.example.springboot_book.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorHandlerController implements ErrorController {

    @GetMapping("/error")
    public String customError() {
        return "/error-404";
    }


}
