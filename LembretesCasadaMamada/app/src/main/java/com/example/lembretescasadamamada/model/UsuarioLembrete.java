package com.example.lembretescasadamamada.model;

import java.util.Date;

public class UsuarioLembrete {
    private int id;
    private String nomeCompleto;
    private String tituloLembrete;
    private Date dataLembrete;
    private String lembrete;

    public UsuarioLembrete() {
    }
    public UsuarioLembrete(int id, String nomeCompleto, String lembrete) {
        this.setId(id);
        this.setNomeCompleto(nomeCompleto);
        this.setLembrete(lembrete);
    }

    public UsuarioLembrete(int id, String nomeCompleto, String tituloLembrete, Date dataLembrete, String lembrete) {
        this.setId(id);
        this.setNomeCompleto(nomeCompleto);
        this.setLembrete(lembrete);
        this.setDataLembrete(dataLembrete);
        this.setTituloLembrete(tituloLembrete);
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

    public String getLembrete() {
        return lembrete;
    }

    public void setLembrete(String lembrete) {
        this.lembrete = lembrete;
    }

    public String getTituloLembrete() {
        return tituloLembrete;
    }

    public void setTituloLembrete(String tituloLembrete) {
        this.tituloLembrete = tituloLembrete;
    }

    public Date getDataLembrete() {
        return dataLembrete;
    }

    public void setDataLembrete(Date dataLembrete) {
        this.dataLembrete = dataLembrete;
    }
}
