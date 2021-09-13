/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acme.prj_encomendas.converter;

import br.com.acme.prj_encomendas.util.Utils;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author frasilva
 */
@FacesConverter(value = "maskTelefone")
public class MaskTelefone implements Converter<Object> {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if (value == null || value.isEmpty()) {
            return 0L;
        }
        return Utils.onlyNumbers(value);
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object value) {
        if (value == null || Long.parseLong(value.toString()) == 0L) {
            return "";
        }
        String celular = Utils.leftPad("0", value.toString(), 10, true);
        String ddd = celular.substring(0, 2);
        String num1 = celular.substring(2, 6);
        String num2 = celular.substring(6, 10);
        return "(" + ddd + ") " + num1 + "-" + num2;
    }

}
