package com.otavio.loginseguro.service;

import java.util.List;
import java.util.Set;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.otavio.loginseguro.dto.CadastroUsuario;
import com.otavio.loginseguro.model.Perfil;
import com.otavio.loginseguro.model.Usuario;
import com.otavio.loginseguro.repository.UsuarioRepository;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder) {

        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario cadastrar(CadastroUsuario cadastro) {

        if (usuarioRepository.existsByEmail(cadastro.getEmail())) {
            throw new IllegalArgumentException("E-mail já cadastrado");
        }

        if (!cadastro.getSenha().equals(cadastro.getConfirmarSenha())) {
            throw new IllegalArgumentException("As senhas não coincidem");
        }

        Usuario usuario = new Usuario(
                cadastro.getNome(),
                cadastro.getEmail(),
                passwordEncoder.encode(cadastro.getSenha())
        );

        return usuarioRepository.save(usuario);
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public void alterarPerfil(String id, Perfil perfil) {

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Usuário não encontrado"));

        usuario.setPerfis(Set.of(perfil));

        usuarioRepository.save(usuario);
    }
}