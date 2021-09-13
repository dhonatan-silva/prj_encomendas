/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acme.prj_encomendas.web.morador.bean;

import br.com.acme.prj_encomendas.dao.ApartamentoDao;
import br.com.acme.prj_encomendas.dao.ImageDao;
import br.com.acme.prj_encomendas.dao.MoradorDao;
import br.com.acme.prj_encomendas.dao.TorreDao;
import br.com.acme.prj_encomendas.dao.VeiculoDao;
import br.com.acme.prj_encomendas.domain.Apartamento;
import br.com.acme.prj_encomendas.domain.Morador;
import br.com.acme.prj_encomendas.domain.Torre;
import br.com.acme.prj_encomendas.domain.Veiculo;
import br.com.acme.prj_encomendas.util.Constantes;
import br.com.acme.prj_encomendas.util.Navegacao;
import br.com.acme.prj_encomendas.util.Numeros;
import br.com.acme.prj_encomendas.util.TypeMessage;
import br.com.acme.prj_encomendas.util.Utils;
import br.com.acme.prj_encomendas.web.morador.helper.AlterarMoradorHelper;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

/**
 *
 * @author frasilva
 */
@Named(value = "alterarMoradorBean")
@SessionScoped
public class AlterarMoradorBean implements Serializable {

    private AlterarMoradorHelper helper;

    @Inject
    private TorreDao torreDao;

    @Inject
    private ApartamentoDao apartamentoDao;

    @Inject
    private ImageDao imageDao;

    @Inject
    private MoradorDao moradorDao;

    @Inject
    private VeiculoDao veiculoDao;

    /**
     * Creates a new instance of AlterarMoradorBean
     */
    public AlterarMoradorBean() {
        setHelper(new AlterarMoradorHelper());
    }

    public String iniciarPagina(Morador morador) {
        getHelper().inicializar();
        getHelper().setMorador(morador);
        popularTorreApartamento();
        popularComboBloco();
        popularImagem();
        popularComboTipoVeiculo();
        popularVeiculo();
        return Navegacao.ALTERAR_MORADOR;
    }

    public void verificarDisponibilidade() {
        List<Apartamento> apartamentos = getHelper().getApartamentos().stream().filter(x -> x.getNumero().equals(getHelper().getAptoSelecionado())).collect(Collectors.toList());
        Apartamento apartamento = apartamentos.get(0);
        if (apartamento.getMoradorId() > Numeros.ZERO) {
            Utils.addWarnMesssage("Apartamento já vinculado a um morador, operação não permitida!");
            getHelper().setDisabledBtnSalvar(Boolean.TRUE);
        } else {
            Utils.clearMesssage();
            getHelper().setDisabledBtnSalvar(Boolean.FALSE);
        }
    }

    public void popularComboTipoVeiculo() {
        getHelper().getListaTipo().add(new SelectItem(0, "Selecione"));
        getHelper().getListaTipo().add(new SelectItem(1, "Moto"));
        getHelper().getListaTipo().add(new SelectItem(2, "Carro"));
    }

    public void popularTorreApartamento() {
        try {
            Apartamento apartamento = apartamentoDao.buscarApartamentoPorIdMorador(getHelper().getMorador().getId());
            if (apartamento != null) {
                getHelper().setApartamento(new Apartamento(apartamento));
                Torre torre = torreDao.buscarTorrePorId(apartamento.getTorreId());
                getHelper().setTorre(torre);
            }
        } catch (ClassNotFoundException | SQLException e) {
            Utils.addErrorMesssage(e.getMessage(), false);
        }
    }

    public void popularComboBloco() {
        try {
            getHelper().getTorres().addAll(torreDao.buscarTodos());
        } catch (ClassNotFoundException | SQLException e) {
            Utils.addErrorMesssage(e.getMessage(), false);
        }
    }

    public void popularComboApartamento() {
        getHelper().getApartamentos().clear();
        if (getHelper().getTorreSelecionada() == Numeros.ZERO) {
            getHelper().setDisabledComboApto(true);
        } else {
            try {
                List<Apartamento> apartamentos = apartamentoDao.buscarApartamentosByIdTorre(getHelper().getTorreSelecionada());
                getHelper().getApartamentos().addAll(apartamentos);
                getHelper().setDisabledComboApto(false);
            } catch (ClassNotFoundException | SQLException e) {
                Utils.addErrorMesssage(e.getMessage(), true);
            }
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
                getHelper().setVeiculo(veiculo);
                getHelper().setTipoSeleciondado(veiculo.getTipo());
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Utils.addErrorMesssage(ex.getMessage(), false);
        }
    }

    public void desligarMorador() {
        try {
            apartamentoDao.desligarMorador(getHelper().getApartamento());
            getHelper().setApartamento(null);
            Utils.closeModal(true, "Operação realizada com sucesso!", TypeMessage.INFO);
        } catch (ClassNotFoundException | SQLException e) {
            Utils.closeModal(true, e.getMessage(), TypeMessage.ERROR);
        }
    }

    public void removerVeiculo() {
        if (getHelper().getTipoSeleciondado() == Numeros.ZERO) {
            getHelper().setVeiculo(null);
        } else {
            getHelper().setVeiculo(new Veiculo());
            getHelper().getVeiculo().setTipo(getHelper().getTipoSeleciondado());
        }
    }

    public String voltar() {
        return Navegacao.LISTAR_MORADORES;
    }

    public void salvar() {
        try {
            moradorDao.atualizar(getHelper().getMorador());

            if (!getHelper().getFoto2().isEmpty()) {
                imageDao.gravarImagem(getHelper().getFoto2(), getHelper().getMorador().getId());
                getHelper().setFoto2(Constantes.VAZIO);
            }

            if (getHelper().getTipoSeleciondado() != Numeros.ZERO) {
                getHelper().getVeiculo().setTipo(getHelper().getTipoSeleciondado());
                getHelper().getVeiculo().setIdMorador(getHelper().getMorador().getId());
                veiculoDao.cadastrar(getHelper().getVeiculo());
            }

            iniciarPagina(getHelper().getMorador());
            Utils.addInfoMessage("Atualização realizada com sucesso!");
        } catch (ClassNotFoundException | SQLException e) {
            Utils.addErrorMesssage(e.getMessage(), false);
        }
    }

    public AlterarMoradorHelper getHelper() {
        return helper;
    }

    private void setHelper(AlterarMoradorHelper helper) {
        this.helper = helper;
    }

}
