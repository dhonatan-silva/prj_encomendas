/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acme.prj_encomendas.auth;

import br.com.acme.prj_encomendas.util.Navegacao;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author frasilva
 */
@Named(value = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {

    Map<String, Object> sessionMap;

    public LoginBean() {
        sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
    }

    public void sair() {
        try {
            sessionMap.clear();
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            FacesContext.getCurrentInstance().getExternalContext().redirect(request.getContextPath() + Navegacao.LOGIN);
        } catch (IOException ex) {
            Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
