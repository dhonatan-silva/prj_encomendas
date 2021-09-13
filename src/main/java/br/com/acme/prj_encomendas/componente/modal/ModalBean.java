/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acme.prj_encomendas.componente.modal;

import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author frasilva
 */
@Named(value = "modalBean")
@ApplicationScoped
public class ModalBean {

    private static String title;
    private static String message;
    private static String labelButton;
    private static Boolean visible;

    /**
     * Creates a new instance of ModalBean
     */
    public ModalBean() {
        setTitle("");
        setMessage("");
        setLabelButton("");
        setVisible(false);
    }

    public void showModal(String title, String message, String labelButton) {
        setTitle(title);
        setMessage(message);
        setLabelButton(labelButton);
        setVisible(true);
    }

    public void hiddenModal() {
        setTitle("");
        setMessage("");
        setLabelButton("");
        setVisible(false);
    }

    public String getTitle() {
        return title;
    }

    private void setTitle(String title) {
        ModalBean.title = title;
    }

    public String getMessage() {
        return message;
    }

    private void setMessage(String message) {
        ModalBean.message = message;
    }

    public String getLabelButton() {
        return labelButton;
    }

    private void setLabelButton(String labelButton) {
        ModalBean.labelButton = labelButton;
    }

    public Boolean getVisible() {
        return visible;
    }

    private void setVisible(Boolean visible) {
        ModalBean.visible = visible;
    }

}
