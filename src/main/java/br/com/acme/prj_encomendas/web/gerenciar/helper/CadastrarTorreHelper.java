/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acme.prj_encomendas.web.gerenciar.helper;

import br.com.acme.prj_encomendas.util.Constantes;

/**
 *
 * @author frasilva
 */
public class CadastrarTorreHelper {

    private String descricaoTorre;
    private Integer andares;
    private Integer apartamentos;

    public void inicializar() {
        setDescricaoTorre(Constantes.VAZIO);
        setAndares(null);
        setApartamentos(null);
    }

    public String getDescricaoTorre() {
        return descricaoTorre;
    }

    public void setDescricaoTorre(String descricaoTorre) {
        this.descricaoTorre = descricaoTorre;
    }

    public Integer getAndares() {
        return andares;
    }

    public void setAndares(Integer andares) {
        this.andares = andares;
    }

    public Integer getApartamentos() {
        return apartamentos;
    }

    public void setApartamentos(Integer apartamentos) {
        this.apartamentos = apartamentos;
    }

}
