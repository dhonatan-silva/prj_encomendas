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
@FacesConverter(value = "maskCpf")
public class MaskCpf implements Converter<Object> {

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
        String cpf = Utils.leftPad("0", value.toString(), 11, true);
        String num1 = cpf.substring(0, 3);
        String num2 = cpf.substring(3, 6);
        String num3 = cpf.substring(6, 9);
        String num4 = cpf.substring(9, 11);
        return num1 + "." + num2 + "." + num3 + "-" + num4;
    }

}
