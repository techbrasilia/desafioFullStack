package com.desafiofullstack.controller;

import com.desafiofullstack.model.Perfil;
import com.desafiofullstack.model.Usuario;
import com.desafiofullstack.request.LoginRequest;
import com.desafiofullstack.response.UsuarioResponse;
import com.desafiofullstack.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest, Authentication authentication, HttpSession session) {
        if (authentication == null) {
            return ResponseEntity.status(401).body("Login falhou");
        }

        Usuario user = (Usuario) authentication.getPrincipal();
        Perfil perfil = user.getPerfil();

        UsuarioResponse usuarioResponse = new UsuarioResponse(user);

        session.setAttribute("userProfile", perfil.getNome());

        return ResponseEntity.ok().body(usuarioResponse);
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
    }
}