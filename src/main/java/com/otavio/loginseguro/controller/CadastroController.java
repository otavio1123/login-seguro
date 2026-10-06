package com.otavio.loginseguro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.otavio.loginseguro.dto.CadastroUsuario;
import com.otavio.loginseguro.service.UsuarioService;

import jakarta.validation.Valid;

@Controller
public class CadastroController {

    private final UsuarioService usuarioService;

    public CadastroController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/cadastro")
    public String exibirCadastro(Model model) {
        model.addAttribute("cadastroUsuario", new CadastroUsuario());
        return "cadastro";
    }

    @PostMapping("/cadastro")
    public String cadastrar(
            @Valid @ModelAttribute("cadastroUsuario") CadastroUsuario cadastroUsuario,
            BindingResult resultado,
            Model model) {

        if (resultado.hasErrors()) {
            return "cadastro";
        }

        try {
            usuarioService.cadastrar(cadastroUsuario);
            return "redirect:/login?cadastro=sucesso";
        } catch (IllegalArgumentException e) {
            model.addAttribute("erro", e.getMessage());
            return "cadastro";
        }
    }
}