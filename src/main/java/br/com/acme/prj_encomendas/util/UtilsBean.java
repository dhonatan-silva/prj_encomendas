/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acme.prj_encomendas.util;

import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author frasilva
 */
@Named(value = "utilsBean")
@ApplicationScoped
public class UtilsBean {

    /**
     * Creates a new instance of UtilsBean
     */
    public UtilsBean() {
    }

    public void closeMessage() {
        Utils.clearMesssage();
    }

}
