package com.desafiofullstack.controller;

import com.desafiofullstack.model.Perfil;
import com.desafiofullstack.service.PerfilService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/perfis")
public class PerfilController {

    @Autowired
    private PerfilService perfilService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Perfil> criarPerfil(@RequestBody Perfil perfil) {
        Perfil novoPerfil = perfilService.criarPerfil(perfil);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoPerfil);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Perfil> obterPerfil(@PathVariable Long id) {
        Perfil perfil = perfilService.obterPerfil(id)
                .orElseThrow(() -> new EntityNotFoundException("Perfil não encontrado"));
        return ResponseEntity.ok(perfil);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Perfil> atualizarPerfil(@PathVariable Long id, @RequestBody Perfil perfilAtualizado) {
        Perfil perfil = perfilService.atualizarPerfil(id, perfilAtualizado);
        return ResponseEntity.ok(perfil);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deletarPerfil(@PathVariable Long id) {
        perfilService.deletarPerfil(id);
        return ResponseEntity.noContent().build();
    }
}
