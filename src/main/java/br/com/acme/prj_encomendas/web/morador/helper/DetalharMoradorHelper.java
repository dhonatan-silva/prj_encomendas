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

/**
 *
 * @author frasilva
 */
public class DetalharMoradorHelper {
    
    private Morador morador;
    private String foto;
    private Torre torre;
    private Apartamento apartamento;
    private Veiculo veiculo;
    
    public void inicializar() {
        setMorador(new Morador());
        setFoto(null);
        setTorre(new Torre());
        setApartamento(null);
        setVeiculo(null);
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
    
    public Veiculo getVeiculo() {
        return veiculo;
    }
    
    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }
    
}
