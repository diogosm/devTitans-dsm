package com.example.lembretescasadamamada.model;

public class Usuario {
    private int id;
    private String nomeCompleto;
    private String senha;

    public Usuario(int id, String nomeCompleto, String senha) {
        this.setId(id);
        this.setNomeCompleto(nomeCompleto);
        this.setSenha(senha);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
