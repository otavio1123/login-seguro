package com.otavio.loginseguro.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.otavio.loginseguro.model.Perfil;
import com.otavio.loginseguro.service.UsuarioService;

@Controller
public class AdministradorController {

    private final UsuarioService usuarioService;

    public AdministradorController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/admin/usuarios")
    public String listarUsuarios(Model model) {

        model.addAttribute("usuarios", usuarioService.listarTodos());
        model.addAttribute("perfis", Perfil.values());

        return "admin-usuarios";
    }

    @PostMapping("/admin/usuarios/{id}/perfil")
    public String alterarPerfil(
            @PathVariable String id,
            @RequestParam Perfil perfil,
            Authentication authentication,
            RedirectAttributes redirectAttributes) {

        try {

            usuarioService.alterarPerfil(
                    id,
                    perfil,
                    authentication.getName()
            );

        } catch (IllegalArgumentException e) {

            redirectAttributes.addFlashAttribute(
                    "erro",
                    e.getMessage()
            );
        }

        return "redirect:/admin/usuarios";
    }

    @PostMapping("/admin/usuarios/{id}/status")
    public String alterarStatus(
            @PathVariable String id,
            Authentication authentication,
            RedirectAttributes redirectAttributes) {

        try {

            usuarioService.alterarStatus(
                    id,
                    authentication.getName()
            );

        } catch (IllegalArgumentException e) {

            redirectAttributes.addFlashAttribute(
                    "erro",
                    e.getMessage()
            );
        }

        return "redirect:/admin/usuarios";
    }
}