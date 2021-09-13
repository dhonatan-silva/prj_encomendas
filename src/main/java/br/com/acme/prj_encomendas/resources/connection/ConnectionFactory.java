/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acme.prj_encomendas.resources.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author frasilva
 */
public class ConnectionFactory {

    private static final String HOST = "localhost";
    private static final String PORT = "1527";
    private static final String DATA_BASE = "DB_MORADORES";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private static final String DRIVER = "org.apache.derby.jdbc.ClientDriver";
    private static final String URL = "jdbc:derby://" + HOST + ":" + PORT + "/" + DATA_BASE;

    public Connection openConnection() throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER);
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        return connection;
    }

}
