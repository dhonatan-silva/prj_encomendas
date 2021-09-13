/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acme.prj_encomendas.dao;

import br.com.acme.prj_encomendas.resources.connection.ConnectionFactory;
import br.com.acme.prj_encomendas.domain.Morador;
import br.com.acme.prj_encomendas.util.Numeros;
import br.com.acme.prj_encomendas.util.Utils;
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
public class MoradorDao implements Serializable {

    private ConnectionFactory factory = null;
    private Connection conn = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;

    public MoradorDao() {
        factory = new ConnectionFactory();
    }

    public void cadastrar(Morador morador) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO morador (nome, data_nascimento, email, celular, telefone, rg, cpf) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            conn = factory.openConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, morador.getNome());
            stmt.setDate(2, Utils.convertDateToSqlDate(morador.getDataNascimento()));
            stmt.setString(3, morador.getEmail());
            stmt.setLong(4, morador.getCelular());
            stmt.setLong(5, Utils.tratatValorNulo(morador.getTelefone()));
            stmt.setLong(6, Utils.tratatValorNulo(morador.getRg()));
            stmt.setLong(7, Utils.tratatValorNulo(morador.getCpf()));
            stmt.execute();
        } finally {
            closeConnection();
        }
    }

    public List<Morador> buscarTodos() throws SQLException, ClassNotFoundException {
        List<Morador> listaMoradores = new ArrayList<>();
        String sql = "SELECT id, nome, data_nascimento, email, celular, telefone, rg, cpf FROM morador";

        try {
            conn = factory.openConnection();
            stmt = conn.prepareStatement(sql);
            stmt.execute();
            rs = stmt.getResultSet();

            while (rs.next()) {
                Morador morador = new Morador();
                morador.setId(rs.getInt("id"));
                morador.setNome(rs.getString("nome"));
                morador.setDataNascimento(rs.getDate("data_nascimento"));
                morador.setEmail(rs.getString("email"));
                morador.setCelular(rs.getLong("celular"));
                morador.setTelefone(rs.getLong("telefone"));
                morador.setRg(rs.getLong("rg"));
                morador.setCpf(rs.getLong("cpf"));
                listaMoradores.add(morador);
            }
        } finally {
            closeConnection();
        }
        return listaMoradores;
    }

    public List<Morador> buscarPorId(Integer id) throws SQLException, ClassNotFoundException {
        List<Morador> listaMoradores = new ArrayList<>();
        String sql = "SELECT id, nome, data_nascimento, email, celular, telefone, rg, cpf FROM morador WHERE id = ?";

        try {
            conn = factory.openConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
            rs = stmt.getResultSet();

            while (rs.next()) {
                Morador morador = new Morador();
                morador.setId(rs.getInt("id"));
                morador.setNome(rs.getString("nome"));
                morador.setDataNascimento(rs.getDate("data_nascimento"));
                morador.setEmail(rs.getString("email"));
                morador.setCelular(rs.getLong("celular"));
                morador.setTelefone(rs.getLong("telefone"));
                morador.setRg(rs.getLong("rg"));
                morador.setCpf(rs.getLong("cpf"));
                listaMoradores.add(morador);
            }
        } finally {
            closeConnection();
        }
        return listaMoradores;
    }

    public void atualizar(Morador morador) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE MORADOR SET nome = ?, data_nascimento = ? , email = ?, celular = ?, telefone = ?, rg = ?, cpf = ? WHERE ID = ?";

        try {
            conn = factory.openConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, morador.getNome());
            stmt.setDate(2, Utils.convertDateToSqlDate(morador.getDataNascimento()));
            stmt.setString(3, morador.getEmail());
            stmt.setLong(4, morador.getCelular());
            stmt.setLong(5, morador.getTelefone());
            stmt.setLong(6, morador.getRg());
            stmt.setLong(7, morador.getCpf());
            stmt.setInt(8, morador.getId());
            stmt.execute();
        } finally {
            closeConnection();
        }
    }

    public void excluir(Integer id) throws ClassNotFoundException, SQLException {
        String sql = "DELETE FROM morador WHERE id = ?";

        try {
            conn = factory.openConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
        } finally {
            closeConnection();
        }
    }

    public Integer buscarUltimoId() throws ClassNotFoundException, SQLException {
        Integer id = Numeros.ZERO;
        String sql = "SELECT MAX(id) FROM morador";
        try {
            conn = factory.openConnection();
            stmt = conn.prepareStatement(sql);
            stmt.execute();

            rs = stmt.getResultSet();

            while (rs.next()) {
                id = rs.getInt(1);
            }

        } finally {
            closeConnection();
        }
        return id;
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
