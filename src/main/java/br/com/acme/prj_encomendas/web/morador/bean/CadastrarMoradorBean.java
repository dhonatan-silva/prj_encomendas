/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acme.prj_encomendas.web.morador.bean;

import br.com.acme.prj_encomendas.web.morador.helper.CadastrarMoradorHelper;
import br.com.acme.prj_encomendas.dao.ImageDao;
import br.com.acme.prj_encomendas.dao.MoradorDao;
import br.com.acme.prj_encomendas.dao.VeiculoDao;
import br.com.acme.prj_encomendas.util.Navegacao;
import br.com.acme.prj_encomendas.util.Numeros;
import br.com.acme.prj_encomendas.util.Utils;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.SQLException;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

/**
 *
 * @author frasilva
 */
@Named(value = "cadastrarMoradorBean")
@SessionScoped
public class CadastrarMoradorBean implements Serializable {

    private CadastrarMoradorHelper helper;

    @Inject
    private MoradorDao moradorDao;

    @Inject
    private ImageDao imageDao;

    @Inject
    private VeiculoDao veiculoDao;

    public CadastrarMoradorBean() {
        setHelper(new CadastrarMoradorHelper());
    }

    public String iniciarPagina() {
        getHelper().inicializar();
        popularComboTipoVeiculo();
        return Navegacao.CADASTRAR_MORADOR;
    }

    public void popularComboTipoVeiculo() {
        getHelper().getListaTipo().add(new SelectItem(0, "Selecione"));
        getHelper().getListaTipo().add(new SelectItem(1, "Moto"));
        getHelper().getListaTipo().add(new SelectItem(2, "Carro"));
    }

    public void salvar() {
        try {
            moradorDao.cadastrar(getHelper().getMorador());

            Integer moradorId = moradorDao.buscarUltimoId();
            if (moradorId > Numeros.ZERO) {
                gravarImagem(moradorId);
                gravarVeiculo(moradorId);
            }
            iniciarPagina();
            Utils.addInfoMessage("Morador cadastrado com sucesso!");
        } catch (ClassNotFoundException | SQLException e) {
            Utils.addErrorMesssage(e.getMessage(), false);
        }
    }

    private void gravarImagem(Integer moradorId) throws SQLException, ClassNotFoundException {
        if (getHelper().getFoto() != null && !getHelper().getFoto().isEmpty()) {
            imageDao.gravarImagem(getHelper().getFoto(), moradorId);
        }
    }

    private void gravarVeiculo(Integer moradorId) throws SQLException, ClassNotFoundException{
        if (getHelper().getTipoSeleciondado() != Numeros.ZERO) {
            getHelper().getVeiculo().setTipo(getHelper().getTipoSeleciondado());
            getHelper().getVeiculo().setIdMorador(moradorId);
            veiculoDao.cadastrar(getHelper().getVeiculo());
        }
    }

    public String voltar() {
        return Navegacao.LISTAR_MORADORES;
    }

    public CadastrarMoradorHelper getHelper() {
        return helper;
    }

    private void setHelper(CadastrarMoradorHelper helper) {
        this.helper = helper;
    }

}
