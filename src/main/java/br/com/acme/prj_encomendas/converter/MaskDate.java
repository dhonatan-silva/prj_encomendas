/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acme.prj_encomendas.converter;

import br.com.acme.prj_encomendas.util.Constantes;
import br.com.acme.prj_encomendas.util.Utils;
import java.text.ParseException;
import java.util.Date;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author frasilva
 */
@FacesConverter(value = "maskDate")
public class MaskDate implements Converter<Object> {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        try {
            if (value == null || value.isEmpty()) {
                return null;
            }
            return Utils.StringToDate(value);
        } catch (Exception ex) {
            Utils.addErrorMesssage(ex.getMessage(), false);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object value) {
        if (value == null) {
            return Constantes.VAZIO;
        }
        return Utils.DateToString((Date) value);
    }

}
