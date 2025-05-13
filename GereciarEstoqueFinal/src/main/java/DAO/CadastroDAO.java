/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.CadastroDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import java.sql.SQLException;
/**
 *
 * @author LUIZ
 */
public class CadastroDAO {
    
    Connection con;
    PreparedStatement ps;
    
    public void cadastrando(CadastroDTO objcadastrodto){
        String sql = "insert into usuarios (email,senha) values(?,?)";
        
        con = new Conexao().getCon();
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, objcadastrodto.getNome_cadastrar());
            ps.setString(2, objcadastrodto.getSenha_cadastrar());
            
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Erro cadastroDAO" +e);
        }
    }
    
    
public boolean emailJaCadastrado(String email) {
    String sql = "SELECT COUNT(*) FROM usuarios WHERE email = ?";
    Connection con = new Conexao().getCon();

    try (PreparedStatement pst = con.prepareStatement(sql)) {
        pst.setString(1, email);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            return rs.getInt(1) > 0; // retorna true se encontrar algum registro
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Erro ao verificar e-mail: " + e.getMessage());
    }
    return false;
}
    
}
