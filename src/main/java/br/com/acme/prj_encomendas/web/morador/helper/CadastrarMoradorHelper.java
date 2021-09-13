/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acme.prj_encomendas.web.morador.helper;

import br.com.acme.prj_encomendas.domain.Morador;
import br.com.acme.prj_encomendas.domain.Veiculo;
import br.com.acme.prj_encomendas.util.Constantes;
import br.com.acme.prj_encomendas.util.Numeros;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.model.SelectItem;

/**
 *
 * @author frasilva
 */
public class CadastrarMoradorHelper implements Serializable {

    private Morador morador;
    private String foto;
    private Veiculo veiculo;
    private List<SelectItem> listaTipo;
    private Integer tipoSeleciondado;

    public void inicializar() {
        setMorador(new Morador());
        setFoto(null);
        setVeiculo(new Veiculo());
        setListaTipo(new ArrayList<>());
        setTipoSeleciondado(Numeros.ZERO);
    }

    public Morador getMorador() {
        return morador;
    }

    public void setMorador(Morador morador) {
        this.morador = morador;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public List<SelectItem> getListaTipo() {
        return listaTipo;
    }

    public void setListaTipo(List<SelectItem> listaTipo) {
        this.listaTipo = listaTipo;
    }

    public Integer getTipoSeleciondado() {
        return tipoSeleciondado;
    }

    public void setTipoSeleciondado(Integer tipoSeleciondado) {
        this.tipoSeleciondado = tipoSeleciondado;
    }

}
