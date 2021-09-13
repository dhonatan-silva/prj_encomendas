/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acme.prj_encomendas.web.morador.bean;

import br.com.acme.prj_encomendas.dao.MoradorDao;
import br.com.acme.prj_encomendas.domain.Morador;
import br.com.acme.prj_encomendas.util.Navegacao;
import br.com.acme.prj_encomendas.util.Numeros;
import br.com.acme.prj_encomendas.util.TypeMessage;
import br.com.acme.prj_encomendas.util.Utils;
import br.com.acme.prj_encomendas.web.morador.helper.ListarMoradoresHelper;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;

/**
 *
 * @author frasilva
 */
@Named(value = "listarMoradoresBean")
@SessionScoped
public class ListarMoradoresBean implements Serializable {

    private ListarMoradoresHelper helper;

    @Inject
    private CadastrarMoradorBean cadastrarMoradorBean;

    @Inject
    private AlterarMoradorBean alterarMoradorBean;

    @Inject
    private DetalharMoradorBean detalharMoradorBean;

    @Inject
    private MoradorDao dao;

    /**
     * Creates a new instance of ListarMoradorBean
     */
    public ListarMoradoresBean() {
        setHelper(new ListarMoradoresHelper());
    }

    public String iniciarPagina() {
        getHelper().inicializar();
        popularTabela();
        return Navegacao.LISTAR_MORADORES;
    }

    private void popularTabela() {
        try {
            List<Morador> moradores = dao.buscarTodos();
            getHelper().setListaMoradores(moradores);
        } catch (ClassNotFoundException | SQLException e) {
            Utils.addErrorMesssage(e.getMessage(), false);
        }
    }

    public String voltar() {
        return Navegacao.HOME;
    }

    public String novo() {
        return cadastrarMoradorBean.iniciarPagina();
    }

    public String detalhar() {
        Morador morador = getMorador();
        if (morador != null) {
            return detalharMoradorBean.iniciarPagina(morador);
        }
        return null;
    }

    public String alterar() {
        Morador morador = getMorador();
        if (morador != null) {
            return alterarMoradorBean.iniciarPagina(morador);
        }
        return null;
    }

    public void excluir() {
        try {
            dao.excluir(getHelper().getCodigoMorador());
            Utils.closeModal(true, "Operação realizada com sucesso!", TypeMessage.INFO);
        } catch (ClassNotFoundException | SQLException e) {
            Utils.closeModal(true, e.getMessage(), TypeMessage.ERROR);
        }
    }

    private Morador getMorador() {
        List<Morador> moradores = getHelper().getListaMoradores().stream()
                .filter(x -> x.getId().equals(getHelper().getCodigoMorador()))
                .collect(Collectors.toList());
        if (moradores.size() > Numeros.ZERO) {
            return moradores.get(Numeros.ZERO);
        }
        return null;
    }

    public ListarMoradoresHelper getHelper() {
        return helper;
    }

    private void setHelper(ListarMoradoresHelper helper) {
        this.helper = helper;
    }

}
