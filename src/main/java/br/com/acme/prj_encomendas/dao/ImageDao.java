/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acme.prj_encomendas.dao;

import br.com.acme.prj_encomendas.resources.connection.ConnectionFactory;
import br.com.acme.prj_encomendas.util.Utils;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author frasilva
 */
@SessionScoped
public class ImageDao implements Serializable {

    private ConnectionFactory factory = null;
    private Connection conn = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;

    public ImageDao() {
        factory = new ConnectionFactory();
    }

    public void gravarImagem(String image, Integer moradorId) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO foto_morador (foto, morador_id) VALUES (?, ?)";

        try {
            conn = factory.openConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setBlob(1, Utils.base64ToInputStream(image));
            stmt.setInt(2, moradorId);
            stmt.execute();
        } finally {
            closeConnection();
        }
    }

    public byte[] buscarFotoByIdMorador(Integer moradorId) throws ClassNotFoundException, SQLException {
        byte[] bytes = null;
        String sql = "SELECT foto FROM foto_morador WHERE morador_id = ?";
        try {
            conn = factory.openConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, moradorId);
            stmt.execute();
            rs = stmt.getResultSet();

            while (rs.next()) {
                Blob blob = rs.getBlob("foto");
                if (blob != null) {
                    bytes = blob.getBytes(1, (int) blob.length());
                }
            }

        } finally {
            closeConnection();
        }
        return bytes;
    }

    private void closeConnection() throws SQLException {
        if (rs != null) {
            rs.close();
        }
        if (stmt != null) {
            stmt.close();
        }
        if (conn != null) {
            conn.close();
        }
    }

}
