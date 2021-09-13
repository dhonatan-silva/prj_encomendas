/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acme.prj_encomendas.domain;

/**
 *
 * @author frasilva
 */
public class Apartamento {

    private Integer id;
    private Integer numero;
    private String descricao;
    private Integer torreId;
    private Integer moradorId;

    public Apartamento() {
    }

    public Apartamento(Integer id, Integer numero, String descricao) {
        this.id = id;
        this.numero = numero;
        this.descricao = descricao;
    }

    public Apartamento(Apartamento apartamento) {
        this.id = apartamento.getId();
        this.numero = apartamento.getNumero();
        this.descricao = apartamento.getDescricao();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getTorreId() {
        return torreId;
    }

    public void setTorreId(Integer torreId) {
        this.torreId = torreId;
    }

    public Integer getMoradorId() {
        return moradorId;
    }

    public void setMoradorId(Integer moradorId) {
        this.moradorId = moradorId;
    }

}
