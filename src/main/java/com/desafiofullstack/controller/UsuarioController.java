package com.desafiofullstack.controller;

import com.desafiofullstack.model.Usuario;
import com.desafiofullstack.service.UsuarioService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Usuario> criarUsuario(@RequestBody Usuario usuario, Authentication authentication) {
        SimpleGrantedAuthority sg = new SimpleGrantedAuthority("ROLE_ADMIN");
        if (!authentication.getAuthorities().contains(sg)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        Usuario novoUsuario = usuarioService.criarUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
    }

    /*@GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> obterUsuario(@PathVariable Long id, Authentication authentication) {
        Usuario usuario = usuarioService.obterUsuario(id).orElse(null);

        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new EntityNotFoundException("Usuário não encontrado").getMessage());
        }

        SimpleGrantedAuthority sg = new SimpleGrantedAuthority("ROLE_ADMIN");

        if (!authentication.getName().equals(usuario.getEmail()) && !authentication.getAuthorities().contains(sg)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        return ResponseEntity.ok(usuario);
    }*/

    @GetMapping("/{email}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> obterUsuarioPorEmail(@PathVariable String email, Authentication authentication) {
        Usuario usuario = usuarioService.obterUsuarioPorEmail(email).orElse(null);

        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new EntityNotFoundException("Usuário não encontrado").getMessage());
        }

        SimpleGrantedAuthority sg = new SimpleGrantedAuthority("ROLE_ADMIN");

        if (!authentication.getName().equals(usuario.getEmail()) && !authentication.getAuthorities().contains(sg)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        return ResponseEntity.ok(usuario);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> atualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuarioAtualizado, Authentication authentication) {
        Usuario usuario = usuarioService.obterUsuario(id).orElse(null);

        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new EntityNotFoundException("Usuário não encontrado").getMessage());
        }

        SimpleGrantedAuthority sg = new SimpleGrantedAuthority("ROLE_ADMIN");

        if (!authentication.getName().equals(usuario.getEmail()) && !authentication.getAuthorities().contains(sg)) {
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
