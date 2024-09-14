package com.desafiofullstack.request;

import com.desafiofullstack.model.Usuario;

public class UsuarioRequest {

    private String nome;
    private String email;
    private PerfilRequest perfil;

    public UsuarioRequest() {}

    public UsuarioRequest(Usuario usuario) {
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.perfil = new PerfilRequest(usuario.getPerfil());
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public PerfilRequest getPerfil() {
        return perfil;
    }

    public void setPerfil(PerfilRequest perfil) {
        this.perfil = perfil;
    }
}
