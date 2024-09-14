package com.desafiofullstack.response;

import com.desafiofullstack.model.Usuario;

public class UsuarioResponse {

    private Long id;
    private String nome;
    private String email;
    private PerfilResponse perfil;

    public UsuarioResponse (Usuario usuario) {
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.perfil = new PerfilResponse(usuario.getPerfil());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public PerfilResponse getPerfil() {
        return perfil;
    }

    public void setPerfil(PerfilResponse perfil) {
        this.perfil = perfil;
    }
}
