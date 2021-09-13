/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acme.prj_encomendas.dao;

import br.com.acme.prj_encomendas.domain.Veiculo;
import br.com.acme.prj_encomendas.resources.connection.ConnectionFactory;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author frasilva
 */
public class VeiculoDao implements Serializable {

    private ConnectionFactory factory = null;
    private Connection conn = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;

    public VeiculoDao() {
        factory = new ConnectionFactory();
    }

    public void cadastrar(Veiculo veiculo) throws SQLException, ClassNotFoundException {
        try {
            String sql = "INSERT INTO veiculo (tipo, marca, modelo, cor, placa, morador_id) VALUES (?, ?, ?, ?, ?, ?)";
            conn = factory.openConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, veiculo.getTipo());
            stmt.setString(2, veiculo.getMarca());
            stmt.setString(3, veiculo.getModelo());
            stmt.setString(4, veiculo.getCor());
            stmt.setString(5, veiculo.getPlaca());
            stmt.setInt(6, veiculo.getIdMorador());
            stmt.execute();
        } finally {
            closeConnection();
        }
    }

    public Veiculo buscarVeiculoPorIdMorador(Integer idMorador) throws SQLException, ClassNotFoundException {
        Veiculo veiculo = null;
        String sql = "SELECT id, tipo, marca, modelo, cor, placa, morador_id FROM veiculo WHERE morador_id = ?";

        try {
            conn = factory.openConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idMorador);
            stmt.execute();
            rs = stmt.getResultSet();

            while (rs.next()) {
                veiculo = new Veiculo();
                veiculo.setId(rs.getInt("id"));
                veiculo.setTipo(rs.getInt("tipo"));
                veiculo.setMarca(rs.getString("marca"));
                veiculo.setModelo(rs.getString("modelo"));
                veiculo.setCor(rs.getString("cor"));
                veiculo.setPlaca(rs.getString("placa"));
                veiculo.setIdMorador(rs.getInt("morador_id"));
            }
        } finally {
            closeConnection();
        }
        return veiculo;
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
