/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acme.prj_encomendas.web.morador.helper;

import br.com.acme.prj_encomendas.domain.Morador;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author frasilva
 */
public class ListarMoradoresHelper {

    private List<Morador> listaMoradores;
    private Integer codigoMorador;

    public void inicializar() {
        setListaMoradores(new ArrayList<>());
        setCodigoMorador(null);
    }

    public List<Morador> getListaMoradores() {
        return listaMoradores;
    }

    public void setListaMoradores(List<Morador> listaMoradores) {
        this.listaMoradores = listaMoradores;
    }

    public Integer getCodigoMorador() {
        return codigoMorador;
    }

    public void setCodigoMorador(Integer codigoMorador) {
        this.codigoMorador = codigoMorador;
    }

}
