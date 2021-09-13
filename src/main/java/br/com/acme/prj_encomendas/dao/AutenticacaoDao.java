/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acme.prj_encomendas.dao;

import br.com.acme.prj_encomendas.auth.Auth;
import br.com.acme.prj_encomendas.resources.connection.ConnectionFactory;
import java.io.Serializable;
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
public class AutenticacaoDao implements Serializable {

    private ConnectionFactory factory = null;
    private Connection conn = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;

    public AutenticacaoDao() {
        factory = new ConnectionFactory();
    }

    public Boolean usuarioExiste(String usuario) throws ClassNotFoundException, SQLException {
        Boolean existe = Boolean.FALSE;
        String sql = "SELECT 1 FROM identificacao WHERE usuario = ?";
        try {
            conn = factory.openConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, usuario.toUpperCase());
            stmt.execute();
            rs = stmt.getResultSet();
            if (rs.next()) {
                existe = rs.getInt(1) == 1;
            }
        } finally {
            closeConnection();
        }
        return existe;
    }
    
    public Auth autenticar(Auth auth) throws ClassNotFoundException, SQLException {
        String sql = "SELECT usuario FROM identificacao WHERE usuario = ? AND senha = ?";
        try {
            conn = factory.openConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, auth.getUsuario().toUpperCase());
            stmt.setString(2, auth.getSenha());
            stmt.execute();
            rs = stmt.getResultSet();
            if (!rs.next()) {
               auth = null;
            }
        } finally {
            closeConnection();
        }
        return auth;
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
