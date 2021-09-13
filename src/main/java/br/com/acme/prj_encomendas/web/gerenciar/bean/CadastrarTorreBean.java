/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acme.prj_encomendas.web.gerenciar.bean;

import br.com.acme.prj_encomendas.dao.ApartamentoDao;
import br.com.acme.prj_encomendas.dao.TorreDao;
import br.com.acme.prj_encomendas.domain.Apartamento;
import br.com.acme.prj_encomendas.util.Constantes;
import br.com.acme.prj_encomendas.util.Navegacao;
import br.com.acme.prj_encomendas.util.Numeros;
import br.com.acme.prj_encomendas.util.Utils;
import br.com.acme.prj_encomendas.web.gerenciar.helper.CadastrarTorreHelper;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author frasilva
 */
@Named(value = "cadastrarTorreBean")
@SessionScoped
public class CadastrarTorreBean implements Serializable {

    private CadastrarTorreHelper helper;

    @Inject
    private TorreDao torreDao;

    @Inject
    private ApartamentoDao apartamentoDao;

    public CadastrarTorreBean() {
        setHelper(new CadastrarTorreHelper());
    }

    public String iniciarPagina() {
        getHelper().inicializar();
        return Navegacao.CADASTRAR_TORRE;
    }

    public void gravar() throws ClassNotFoundException {
        List<Apartamento> aptoPorTorre = new ArrayList<>();

        try {
            Integer torreId = torreDao.buscarIdTorrePorDescricao(getHelper().getDescricaoTorre());
            if (torreId > Numeros.ZERO) {
                aptoPorTorre = apartamentoDao.buscarApartamentosByIdTorre(torreId);
                if (aptoPorTorre.isEmpty()) {
                    cadastrarApartamentos(torreId);
                } else {
                    Utils.addWarnMesssage("Já existe um registro com essas informções!");
                }
            } else {
                torreDao.cadastrar(getHelper().getDescricaoTorre());
                cadastrarApartamentos(torreId);
            }
        } catch (SQLException e) {
            Utils.addErrorMesssage(e.getMessage(), false);
        }
    }

    private void cadastrarApartamentos(Integer idTorre) throws SQLException, ClassNotFoundException {
        List<Apartamento> apartamentos = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        for (Integer i = 1; i <= getHelper().getAndares(); i++) {
            for (Integer j = 1; j <= getHelper().getApartamentos(); j++) {
                String number = i.toString() + j.toString();
                builder.append(getHelper().getDescricaoTorre().toUpperCase());
                builder.append(Constantes.HIFFEN);
                builder.append(number);

                Apartamento apartamento = new Apartamento();
                apartamento.setNumero(Integer.parseInt(number));
                apartamento.setDescricao(builder.toString());
                apartamentos.add(apartamento);
                builder = new StringBuilder();
            }
        }

        for (Apartamento apartamento : apartamentos) {
            apartamento.setTorreId(idTorre);
            apartamentoDao.cadastrar(apartamento);
        }
        Utils.addInfoMessage("Operação realziada com sucesso!");
        getHelper().inicializar();
    }

    public CadastrarTorreHelper getHelper() {
        return helper;
    }

    private void setHelper(CadastrarTorreHelper helper) {
        this.helper = helper;
    }

}
