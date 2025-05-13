/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
/**
 *
 * @author Pichau
 */
public class Conexao {
    private static final String URL = "jdbc:mysql://localhost:3306/Gerenciar";
    private static final String USUARIO = "root";
    private static final String SENHA = "";
    public  Connection getCon() {
        try {
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar com o banco: " + e.getMessage());
            return null;
        }
    }
}
