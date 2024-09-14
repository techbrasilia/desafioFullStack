package com.desafiofullstack.request;

import com.desafiofullstack.model.Perfil;

public class PerfilRequest {

    private String nome;

    public PerfilRequest() {
    }

    public PerfilRequest(Perfil perfil) {
        this.nome = perfil.getNome();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
