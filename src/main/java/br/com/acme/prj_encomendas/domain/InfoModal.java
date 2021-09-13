/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acme.prj_encomendas.domain;

/**
 *
 * @author frasilva
 */
public class InfoModal {

    private String title;
    private String message;
    private Boolean visible;

    public InfoModal() {
        setTitle("");
        setMessage("");
        setVisible(false);
    }

    public void showModal(String title, String message) {
        setTitle(title);
        setMessage(message);
        setVisible(true);
    }

    public void hiddenModal() {
        setTitle("");
        setMessage("");
        setVisible(false);
    }

    public String getTitle() {
        return title;
    }

    private void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    private void setMessage(String message) {
        this.message = message;
    }

    public Boolean getVisible() {
        return visible;
    }

    private void setVisible(Boolean visible) {
        this.visible = visible;
    }

}
