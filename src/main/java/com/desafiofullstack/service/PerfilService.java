package com.desafiofullstack.service;

import com.desafiofullstack.model.Perfil;
import com.desafiofullstack.repository.PerfilRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PerfilService {

    @Autowired
    private PerfilRepository perfilRepository;

    public Perfil criarPerfil(Perfil perfil) {
        return perfilRepository.save(perfil);
    }

    public Optional<Perfil> obterPerfil(Long id) {
        return perfilRepository.findById(id);
    }

    public List<Perfil> listarPerfis() {
        return perfilRepository.findAll();
    }

    public Perfil atualizarPerfil(Long id, Perfil perfilAtualizado) {
        Perfil perfil = perfilRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Perfil não encontrado"));

        perfil.setNome(perfilAtualizado.getNome());

        return perfilRepository.save(perfil);
    }

    public void deletarPerfil(Long id) {
        perfilRepository.deleteById(id);
    }
}
