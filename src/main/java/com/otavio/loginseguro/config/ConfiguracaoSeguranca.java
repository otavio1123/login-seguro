package com.otavio.loginseguro.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ConfiguracaoSeguranca {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .authorizeHttpRequests(auth -> auth

                // páginas públicas
                .requestMatchers(
                    "/login",
                    "/cadastro",
                    "/css/**",
                    "/js/**",
                    "/images/**"
                ).permitAll()

                // áreas por perfil
                .requestMatchers("/admin/**")
                    .hasRole("ADMIN")

                .requestMatchers("/moderador/**")
                    .hasAnyRole("MODERADOR", "ADMIN")

                .requestMatchers("/usuario/**")
                    .hasAnyRole("USUARIO", "MODERADOR", "ADMIN")

                // qualquer outra página exige login
                .anyRequest().authenticated()
            )

            .formLogin(form -> form
                .loginPage("/login")
                .usernameParameter("email")
                .passwordParameter("senha")
                .defaultSuccessUrl("/inicio", true)
                .failureUrl("/login?erro")
                .permitAll()
            )

            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .permitAll()
            )

            .exceptionHandling(exception -> exception
                .accessDeniedPage("/acesso-negado")
            );

        return http.build();
    }
}