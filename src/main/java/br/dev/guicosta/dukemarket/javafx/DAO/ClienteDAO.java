/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.dev.guicosta.dukemarket.javafx.DAO;

import br.dev.guicosta.dukemarket.javafx.model.Cliente;
import connection.MySQLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author sesi2dia
 */
public class ClienteDAO {
    
    public void create(Cliente c) {

        Connection conn = MySQLConnection.getConnection();
        PreparedStatement stmt = null;

        try {
            String sql = "INSERT INTO Clientes (cpf, nome) VALUES (?, ?)";

            stmt = conn.prepareStatement(sql);
            stmt.setString(1, c.getCpf());
            stmt.setString(2, c.getNome());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MySQLConnection.closeConnection(conn, stmt);
        }
    }

    public ObservableList<Cliente> getResults() {

        Connection conn = MySQLConnection.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        ObservableList<Cliente> lista = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM Clientes";

            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Cliente c = new Cliente(
                    rs.getInt("id"),
                    rs.getString("cpf"),
                    rs.getString("nome")
                );

                lista.add(c);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MySQLConnection.closeConnection(conn, stmt, rs);
        }

        return lista;
    }

    public Cliente getResultsByID(int id) {

        Connection conn = MySQLConnection.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        Cliente c = null;

        try {
            String sql = "SELECT * FROM Clientes WHERE id = ?";

            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            rs = stmt.executeQuery();

            if (rs.next()) {
                c = new Cliente(
                    rs.getInt("id"),
                    rs.getString("cpf"),
                    rs.getString("nome")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MySQLConnection.closeConnection(conn, stmt, rs);
        }

        return c;
    }

    public void update(Cliente c) {

        Connection conn = MySQLConnection.getConnection();
        PreparedStatement stmt = null;

        try {
            String sql = "UPDATE Clientes SET cpf = ?, nome = ? WHERE id = ?";

            stmt = conn.prepareStatement(sql);
            stmt.setString(1, c.getCpf());
            stmt.setString(2, c.getNome());
            stmt.setInt(3, c.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MySQLConnection.closeConnection(conn, stmt);
        }
    }

    public void delete(int id) {

        Connection conn = MySQLConnection.getConnection();
        PreparedStatement stmt = null;

        try {
            String sql = "DELETE FROM Clientes WHERE id = ?";

            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MySQLConnection.closeConnection(conn, stmt);
        }
    }
}