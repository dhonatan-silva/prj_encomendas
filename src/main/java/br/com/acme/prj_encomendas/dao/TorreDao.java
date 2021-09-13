/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acme.prj_encomendas.dao;

import br.com.acme.prj_encomendas.domain.Torre;
import br.com.acme.prj_encomendas.resources.connection.ConnectionFactory;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author frasilva
 */
@SessionScoped
public class TorreDao implements Serializable {

    private ConnectionFactory factory = null;
    private Connection conn = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;

    public TorreDao() {
        factory = new ConnectionFactory();
    }

    public void cadastrar(String descricaoTorre) throws SQLException, ClassNotFoundException {
        try {
            String sql = "INSERT INTO torre (descricao) VALUES (?)";
            conn = factory.openConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, descricaoTorre);
            stmt.execute();
        } finally {
            closeConnection();
        }
    }

    public List<Torre> buscarTodos() throws SQLException, ClassNotFoundException {
        List<Torre> torres = new ArrayList<>();
        String sql = "SELECT id, descricao FROM torre";

        try {
            conn = factory.openConnection();
            stmt = conn.prepareStatement(sql);
            stmt.execute();
            rs = stmt.getResultSet();

            while (rs.next()) {
                Torre torre = new Torre();
                torre.setId(rs.getInt("id"));
                torre.setDescricao(rs.getString("descricao"));
                torres.add(torre);
            }
            stmt.execute();
        } finally {
            closeConnection();
        }
        return torres;
    }

    public Integer buscarUltimoRegistro() throws SQLException {
        Integer id = 0;
        String sql = "SELECT MAX(id) FROM torre";
        try {
            conn = factory.openConnection();
            stmt = conn.prepareStatement(sql);
            stmt.execute();
            rs = stmt.getResultSet();

            while (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (ClassNotFoundException | SQLException e) {
            closeConnection();
        }
        return id;
    }

    public Integer buscarIdTorrePorDescricao(String descricaoTorre) throws SQLException {
        Integer id = 0;
        String sql = "SELECT id FROM torre WHERE descricao = ?";

        try {
            conn = factory.openConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, descricaoTorre);
            stmt.execute();
            rs = stmt.getResultSet();

            while (rs.next()) {
                id = rs.getInt("id");
            }
        } catch (ClassNotFoundException | SQLException e) {
            closeConnection();
        }
        return id;
    }
    
    public Torre buscarTorrePorId(Integer idTorre) throws SQLException {
        Torre torre = null;
        String sql = "SELECT id, descricao FROM torre WHERE id = ?";

        try {
            conn = factory.openConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idTorre);
            stmt.execute();
            rs = stmt.getResultSet();

            while (rs.next()) {
                torre = new Torre();
                torre.setId(rs.getInt("id"));
                torre.setDescricao(rs.getString("descricao"));
            }
        } catch (ClassNotFoundException | SQLException e) {
            closeConnection();
        }
        return torre;
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
