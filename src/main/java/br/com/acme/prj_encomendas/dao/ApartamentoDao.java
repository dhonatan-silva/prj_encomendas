/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acme.prj_encomendas.dao;

import br.com.acme.prj_encomendas.domain.Apartamento;
import br.com.acme.prj_encomendas.resources.connection.ConnectionFactory;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author frasilva
 */
@SessionScoped
public class ApartamentoDao implements Serializable {

    private ConnectionFactory factory = null;
    private Connection conn = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;

    public ApartamentoDao() {
        factory = new ConnectionFactory();
    }

    public void cadastrar(Apartamento apartamento) throws SQLException, ClassNotFoundException {
        try {
            String sql = "INSERT INTO apartamento (descricao, numero, torre_id, morador_id) VALUES (?, ?, ?, ?)";
            conn = factory.openConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, apartamento.getDescricao());
            stmt.setInt(2, apartamento.getNumero());
            stmt.setInt(3, apartamento.getTorreId());
            if (apartamento.getMoradorId() == null) {
                stmt.setNull(4, Types.INTEGER);
            } else {
                stmt.setInt(4, apartamento.getMoradorId());
            }
            stmt.execute();
        } finally {
            closeConnection();
        }
    }

    public List<Apartamento> buscarTodos() throws SQLException, ClassNotFoundException {
        List<Apartamento> apartamentos = new ArrayList<>();
        String sql = "SELECT id, descricao, numero, torre_id, morador_id FROM apartamento";

        try {
            conn = factory.openConnection();
            stmt = conn.prepareStatement(sql);
            stmt.execute();
            rs = stmt.getResultSet();

            while (rs.next()) {
                Apartamento apartamento = new Apartamento();
                apartamento.setId(rs.getInt("id"));
                apartamento.setDescricao(rs.getString("descricao"));
                apartamento.setNumero(rs.getInt("numero"));
                apartamento.setMoradorId(rs.getInt(rs.getInt("morador_id")));
                apartamentos.add(apartamento);
            }
            stmt.execute();
        } finally {
            closeConnection();
        }
        return apartamentos;
    }

    public List<Apartamento> buscarApartamentosByIdTorre(Integer torreId) throws SQLException, ClassNotFoundException {
        List<Apartamento> apartamentos = new ArrayList<>();
        String sql = "SELECT id, descricao, numero, torre_id, morador_id FROM apartamento WHERE torre_id = ?";

        try {
            conn = factory.openConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, torreId);
            stmt.execute();

            rs = stmt.getResultSet();

            while (rs.next()) {
                Apartamento apartamento = new Apartamento();
                apartamento.setId(rs.getInt("id"));
                apartamento.setDescricao(rs.getString("descricao"));
                apartamento.setNumero(rs.getInt("numero"));
                apartamento.setTorreId(rs.getInt("torre_id"));
                apartamento.setMoradorId(rs.getInt("morador_id"));
                apartamentos.add(apartamento);
            }
        } finally {
            closeConnection();
        }
        return apartamentos;
    }

    public Apartamento buscarApartamentoPorIdMorador(Integer moradorId) throws SQLException, ClassNotFoundException {
        Apartamento apartamento = null;
        String sql = "SELECT id, descricao, numero, torre_id, morador_id FROM apartamento WHERE morador_id = ?";

        try {
            conn = factory.openConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, moradorId);
            stmt.execute();

            rs = stmt.getResultSet();

            while (rs.next()) {
                apartamento = new Apartamento();
                apartamento.setId(rs.getInt("id"));
                apartamento.setDescricao(rs.getString("descricao"));
                apartamento.setNumero(rs.getInt("numero"));
                apartamento.setTorreId(rs.getInt("torre_id"));
                apartamento.setMoradorId(rs.getInt("morador_id"));
            }
        } finally {
            closeConnection();
        }
        return apartamento;
    }

    public void atualizarMorador(Apartamento apartamento) throws ClassNotFoundException, SQLException {
        String sql = "UPDATE apartamento SET morador_id = ? WHERE id = ?";

        try {
            conn = factory.openConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, apartamento.getMoradorId());
            stmt.setInt(2, apartamento.getId());
            stmt.execute();
        } finally {
            closeConnection();
        }
    }

    public void desligarMorador(Apartamento apartamento) throws ClassNotFoundException, SQLException {
        String sql = "UPDATE apartamento SET morador_id = null WHERE descricao = ?";

        try {
            conn = factory.openConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, apartamento.getDescricao());
            stmt.execute();
        } finally {
            closeConnection();
        }
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
