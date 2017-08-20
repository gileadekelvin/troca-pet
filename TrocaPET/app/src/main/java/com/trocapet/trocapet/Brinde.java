package com.trocapet.trocapet;

public class Brinde {

    private Integer ecopointsRequeridos;
    private Integer idFoto;
    private String nome;

    public Brinde(final String nome, final Integer ecopointsRequeridos, final Integer idFoto) {
        this.nome = nome;
        this.ecopointsRequeridos = ecopointsRequeridos;
        this.idFoto = idFoto;
    }

    public String getNome() {
        return nome;
    }

    public Integer getIdFoto() {
        return idFoto;
    }

    public Integer getEcopointsRequeridos() {
        return ecopointsRequeridos;
    }

    public void setEcopointsRequeridos(final Integer ecopointsRequeridos) {
        this.ecopointsRequeridos = ecopointsRequeridos;
    }

    public void setIdFoto(final Integer idFoto) {
        this.idFoto = idFoto;
    }

    public void setNome(final String nome) {
        this.nome = nome;
    }


}