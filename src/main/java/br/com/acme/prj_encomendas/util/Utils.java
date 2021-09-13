/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acme.prj_encomendas.util;

import br.com.acme.prj_encomendas.componente.modal.ModalBean;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.PartialResponseWriter;
import javax.faces.view.facelets.FaceletException;
import org.apache.derby.iapi.services.io.ArrayInputStream;

/**
 *
 * @author frasilva
 */
public class Utils {

    private static final String REGEX_NUMEROS = "[^0-9]";

    private static final String EMPTY = "";

    private static final Pattern VALID_EMAIL_ADDRESS_REGEX
            = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    private static final String PATTERN_DATE_BR = "dd/MM/yyyy";
    
    private static final String PATTERN_DATE = "yyyy-MM-dd";

    public static boolean validarEmail(String email) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        return matcher.find();
    }

    public static void addInfoMessage(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
    }

    public static void addWarnMesssage(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, message, null));
    }

    public static void addErrorMesssage(String message, boolean ajax) {
        if (ajax) {
            message = message.replace("'", "");
            callFunctionJs("showMessage", message, "error");
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
        }
    }

    public static void clearMesssage() {
        Iterator<FacesMessage> messages = FacesContext.getCurrentInstance().getMessages();
        while (messages.hasNext()) {
            messages.next();
            messages.remove();
        }
    }

    public static void callFunctionJs(String funcao, String... parametros) {
        StringBuilder params = new StringBuilder();

        if (parametros != null && parametros.length > 0) {
            for (String parametro : parametros) {
                if (params.length() > 0) {
                    params.append(",");
                }
                params.append("'").append(parametro).append("'");
            }
        }
        funcao = funcao + "(" + params.toString() + ");";

        FacesContext ctx = FacesContext.getCurrentInstance();
        ExternalContext extContext = ctx.getExternalContext();
        if (ctx.getPartialViewContext().isAjaxRequest()) {
            try {
                extContext.setResponseContentType("text/xml");
                extContext.addResponseHeader("Cache-Control", "no-cache");
                PartialResponseWriter writer = ctx.getPartialViewContext().getPartialResponseWriter();
                writer.startDocument();
                writer.startEval();
                writer.write(funcao);
                writer.endEval();
                writer.endDocument();
                writer.flush();
                ctx.responseComplete();
            } catch (IOException e) {
                throw new FaceletException(e);
            }
        }
    }

    public static ModalBean callInfoModal(String title, String message, String labelButton) {
        ModalBean modalBean = new ModalBean();
        modalBean.showModal(title, message, labelButton);
        return modalBean;
    }

    public static final void closeModal(Boolean show, String text, TypeMessage type) {
        text = text.replace("'", "");
        callFunctionJs("closeModal", show.toString(), text, type.getDescricao());
    }

    public static Date StringToDate(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_DATE);
        Date newDate = sdf.parse(date);
        return newDate;
    }

    public static String DateToString(Date date) {
        DateFormat dateFormat = new SimpleDateFormat(PATTERN_DATE);
        String dataFormatada = dateFormat.format(date);
        return dataFormatada;
    }

    public static String leftPad(String filler, String value, Integer size, boolean left) {
        while (value.length() < size) {
            if (left) {
                value = filler + value;
            } else {
                value = value + filler;
            }
        }
        return value;
    }

    public static Timestamp getSqlCurrentDate() {
        return new Timestamp(Calendar.getInstance().getTimeInMillis());
    }

    public static java.sql.Date convertDateToSqlDate(Date date) {
        java.sql.Date dataSql = new java.sql.Date(date.getTime());
        return dataSql;
    }

    public static Long onlyNumbers(String number) throws IllegalArgumentException {
        if (number == null) {
            throw new IllegalArgumentException("number is null");
        }
        String newNumbers = number.replaceAll(REGEX_NUMEROS, EMPTY);
        return Long.parseLong(newNumbers);
    }

    public static Long tratatValorNulo(Long value) {
        return (value == null) ? new Long(0) : value;
    }

    public static InputStream base64ToInputStream(String image) {
        return new ArrayInputStream(image.getBytes());
    }

    public static String arrayBytesToBase64(byte[] image) {
        return Base64.getEncoder().encodeToString(image);
    }
}
