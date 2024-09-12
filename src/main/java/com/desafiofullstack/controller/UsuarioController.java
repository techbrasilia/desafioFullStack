package com.desafiofullstack.controller;

import com.desafiofullstack.model.Usuario;
import com.desafiofullstack.service.UsuarioService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Usuario> criarUsuario(@RequestBody Usuario usuario) {
        Usuario novoUsuario = usuarioService.criarUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Usuario> obterUsuario(@PathVariable Long id, Authentication authentication) {
        Usuario usuario = usuarioService.obterUsuario(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        // Garantir que o usuário comum só pode acessar seu próprio perfil
        if (!authentication.getName().equals(usuario.getEmail()) && !authentication.getAuthorities().contains("ROLE_ADMIN")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        return ResponseEntity.ok(usuario);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuarioAtualizado, Authentication authentication) {
        Usuario usuario = usuarioService.obterUsuario(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        // Garantir que o usuário comum só pode atualizar seu próprio perfil (exceto o perfil em si)
        if (!authentication.getName().equals(usuario.getEmail()) && !authentication.getAuthorities().contains("ROLE_ADMIN")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        usuario.setNome(usuarioAtualizado.getNome());
        usuario.setEmail(usuarioAtualizado.getEmail());

        Usuario usuarioAtualizadoResponse = usuarioService.atualizarUsuario(id, usuario);
        return ResponseEntity.ok(usuarioAtualizadoResponse);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id) {
        usuarioService.deletarUsuario(id);
        return ResponseEntity.noContent().build();
    }

}
