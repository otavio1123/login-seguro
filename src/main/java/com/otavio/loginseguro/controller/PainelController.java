package com.otavio.loginseguro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PainelController {

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }
}