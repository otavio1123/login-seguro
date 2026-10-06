package com.otavio.loginseguro.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.otavio.loginseguro.model.Usuario;
import com.otavio.loginseguro.repository.UsuarioRepository;

@Service
public class DetalhesUsuarioService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public DetalhesUsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Usuário não encontrado"));

        String[] perfis = usuario.getPerfis()
                .stream()
                .map(perfil -> "ROLE_" + perfil.name())
                .toArray(String[]::new);

        return User.withUsername(usuario.getEmail())
                .password(usuario.getSenha())
                .authorities(perfis)
                .disabled(!usuario.isAtivo())
                .build();
    }
}