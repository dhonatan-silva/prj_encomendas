/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acme.prj_encomendas.web.morador.bean;

import br.com.acme.prj_encomendas.dao.ApartamentoDao;
import br.com.acme.prj_encomendas.dao.ImageDao;
import br.com.acme.prj_encomendas.dao.TorreDao;
import br.com.acme.prj_encomendas.dao.VeiculoDao;
import br.com.acme.prj_encomendas.domain.Apartamento;
import br.com.acme.prj_encomendas.domain.Morador;
import br.com.acme.prj_encomendas.domain.Veiculo;
import br.com.acme.prj_encomendas.util.Navegacao;
import br.com.acme.prj_encomendas.util.Utils;
import br.com.acme.prj_encomendas.web.morador.helper.DetalharMoradorHelper;
import java.io.Serializable;
import java.sql.SQLException;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.inject.Inject;

/**
 *
 * @author frasilva
 */
@Named(value = "detalharMoradorBean")
@SessionScoped
public class DetalharMoradorBean implements Serializable {
    
    private DetalharMoradorHelper helper;
    
    @Inject
    private ImageDao imageDao;
    
    @Inject
    private ApartamentoDao apartamentoDao;
    
    @Inject
    private TorreDao torreDao;
    
    @Inject
    private VeiculoDao veiculoDao;

    /**
     * Creates a new instance of DetalharMoradorBean
     */
    public DetalharMoradorBean() {
        setHelper(new DetalharMoradorHelper());
    }
    
    public String iniciarPagina(Morador morador) {
        getHelper().inicializar();
        getHelper().setMorador(morador);
        popularApartamento();
        popularImagem();
        popularVeiculo();
        return Navegacao.DETALHAR_MORADOR;
    }
    
    private void popularApartamento() {
        try {
            Apartamento apartamento = apartamentoDao.buscarApartamentoPorIdMorador(getHelper().getMorador().getId());
            if (apartamento != null) {
                getHelper().setApartamento(new Apartamento(apartamento));
                getHelper().setTorre(torreDao.buscarTorrePorId(apartamento.getTorreId()));
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Utils.addErrorMesssage(ex.getMessage(), false);
        }
    }
    
    private void popularImagem() {
        try {
            byte[] foto = imageDao.buscarFotoByIdMorador(getHelper().getMorador().getId());
            if (foto != null) {
                getHelper().setFoto(new String(foto));
            }
            
        } catch (ClassNotFoundException | SQLException ex) {
            Utils.addErrorMesssage(ex.getMessage(), false);
        }
    }
    
    private void popularVeiculo() {
        try {
            Veiculo veiculo = veiculoDao.buscarVeiculoPorIdMorador(getHelper().getMorador().getId());
            if (veiculo != null) {
                getHelper().setVeiculo(new Veiculo(veiculo));
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Utils.addErrorMesssage(ex.getMessage(), false);
        }
    }
    
    public String voltar() {
        return Navegacao.LISTAR_MORADORES;
    }
    
    public DetalharMoradorHelper getHelper() {
        return helper;
    }
    
    private void setHelper(DetalharMoradorHelper helper) {
        this.helper = helper;
    }
    
    public ImageDao getImageDao() {
        return imageDao;
    }
    
    public void setImageDao(ImageDao imageDao) {
        this.imageDao = imageDao;
    }
    
}
