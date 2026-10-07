package com.otavio.loginseguro.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AcessoController {

    @GetMapping("/usuario")
    public String areaUsuario(Authentication authentication, Model model) {
        model.addAttribute("email", authentication.getName());
        return "usuario";
    }

    @GetMapping("/moderador")
    public String areaModerador(Authentication authentication, Model model) {
        model.addAttribute("email", authentication.getName());
        return "moderador";
    }

    @GetMapping("/admin")
    public String areaAdmin(Authentication authentication, Model model) {
        model.addAttribute("email", authentication.getName());
        return "admin";
    }

    @GetMapping("/acesso-negado")
    public String acessoNegado() {
        return "acesso-negado";
    }
}