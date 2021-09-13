/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acme.prj_encomendas.web.depedente.bean;

import br.com.acme.prj_encomendas.util.Navegacao;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author frasilva
 */
@Named(value = "cadastarDependenteBean")
@RequestScoped
public class CadastrarDependenteBean implements Serializable{

    /**
     * Creates a new instance of CadastarDependenteBean
     * @return 
     */
    
    public String iniciarPagina() {
        return Navegacao.CADASTAR_DEPENDENTE;
    }
    
    public CadastrarDependenteBean() {
    }
    
}
