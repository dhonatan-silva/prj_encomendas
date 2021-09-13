/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acme.prj_encomendas.domain;

import br.com.acme.prj_encomendas.util.Constantes;
import br.com.acme.prj_encomendas.util.Utils;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author frasilva
 */
public class Morador implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String nome;
    private Date dataNascimento;
    private String email;
    private Long celular;
    private Long telefone;
    private Long rg;
    private Long cpf;

    public Morador() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getCelular() {
        return celular;
    }

    public void setCelular(Long celular) {
        this.celular = celular;
    }

    public Long getTelefone() {
        return telefone;
    }

    public void setTelefone(Long telefone) {
        this.telefone = telefone;
    }

    public Long getRg() {
        return rg;
    }

    public void setRg(Long rg) {
        this.rg = rg;
    }

    public Long getCpf() {
        return cpf;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

    public String getCelularFormatado() {
        if (this.celular == null || this.celular.equals(0L)) {
            return Constantes.VAZIO;
        }
        String cel = Utils.leftPad("0", this.celular.toString(), 11, true);
        String ddd = cel.substring(0, 2);
        String num1 = cel.substring(2, 7);
        String num2 = cel.substring(7, 11);
        return "(" + ddd + ") " + num1 + "-" + num2;
    }

}
