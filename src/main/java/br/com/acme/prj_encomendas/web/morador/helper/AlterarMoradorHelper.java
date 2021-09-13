/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acme.prj_encomendas.web.morador.helper;

import br.com.acme.prj_encomendas.domain.Apartamento;
import br.com.acme.prj_encomendas.domain.Morador;
import br.com.acme.prj_encomendas.domain.Torre;
import br.com.acme.prj_encomendas.domain.Veiculo;
import br.com.acme.prj_encomendas.util.Constantes;
import br.com.acme.prj_encomendas.util.Numeros;
import java.util.ArrayList;
import java.util.List;
import javax.faces.model.SelectItem;

/**
 *
 * @author frasilva
 */
public class AlterarMoradorHelper {

    private Apartamento apartamento;
    private Torre torre;
    private String foto;
    private String foto2;
    private Morador morador;
    private Veiculo veiculo;

    private Integer aptoSelecionado;
    private List<Apartamento> apartamentos;
    private Boolean disabledComboApto;

    private Integer torreSelecionada;
    private List<Torre> torres;

    private List<SelectItem> listaTipo;
    private Integer tipoSeleciondado;
    
    private Boolean disabledBtnSalvar;

    public void inicializar() {
        setTorre(null);
        setApartamento(null);
        setFoto(null);
        setMorador(new Morador());

        setAptoSelecionado(Numeros.ZERO);
        setApartamentos(new ArrayList<>());
        setDisabledComboApto(true);

        setTorreSelecionada(Numeros.ZERO);
        setTorres(new ArrayList<>());
        setVeiculo(new Veiculo());
        setFoto2(Constantes.VAZIO);

        setListaTipo(new ArrayList<>());
        setTipoSeleciondado(Numeros.ZERO);
        
        setDisabledBtnSalvar(Boolean.FALSE);
    }

    public Torre getTorre() {
        return torre;
    }

    public void setTorre(Torre torre) {
        this.torre = torre;
    }

    public Apartamento getApartamento() {
        return apartamento;
    }

    public void setApartamento(Apartamento apartamento) {
        this.apartamento = apartamento;
    }

    public String getFoto2() {
        return foto2;
    }

    public void setFoto2(String foto2) {
        this.foto2 = foto2;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Morador getMorador() {
        return morador;
    }

    public void setMorador(Morador morador) {
        this.morador = morador;
    }

    public Integer getAptoSelecionado() {
        return aptoSelecionado;
    }

    public void setAptoSelecionado(Integer aptoSelecionado) {
        this.aptoSelecionado = aptoSelecionado;
    }

    public List<Apartamento> getApartamentos() {
        return apartamentos;
    }

    public void setApartamentos(List<Apartamento> apartamentos) {
        this.apartamentos = apartamentos;
    }

    public Integer getTorreSelecionada() {
        return torreSelecionada;
    }

    public void setTorreSelecionada(Integer torreSelecionada) {
        this.torreSelecionada = torreSelecionada;
    }

    public List<Torre> getTorres() {
        return torres;
    }

    public void setTorres(List<Torre> torres) {
        this.torres = torres;
    }

    public Boolean getDisabledComboApto() {
        return disabledComboApto;
    }

    public void setDisabledComboApto(Boolean disabledComboApto) {
        this.disabledComboApto = disabledComboApto;
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

    public Boolean getDisabledBtnSalvar() {
        return disabledBtnSalvar;
    }

    public void setDisabledBtnSalvar(Boolean disabledBtnSalvar) {
        this.disabledBtnSalvar = disabledBtnSalvar;
    }
    
    

}
