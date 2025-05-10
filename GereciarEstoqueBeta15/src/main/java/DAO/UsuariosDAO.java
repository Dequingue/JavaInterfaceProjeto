/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.UsuariosDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author LUIZ
 */
public class UsuariosDAO {
    Connection con;
    
  
    public ResultSet autenticarUsers(UsuariosDTO objusuariodto){
        con = new Conexao().getCon();
        
        if (con == null) {
        JOptionPane.showMessageDialog(null, "Erro: conexão com o banco de dados não foi estabelecida.");
        return null;
        }
        try{
            String sql = "select * from usuarios where email=? and senha=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, objusuariodto.getNome_usuario());
            ps.setString(2, objusuariodto.getSenha_usuario());
            
            ResultSet rs = ps.executeQuery();
            return rs;
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Class UsuariosDAO"+e);
            return null;
        }     
    }
    
    public UsuariosDTO autenticarUsuario(String email, String senha) {
    UsuariosDTO usuario = null;

    String sql = "SELECT * FROM usuarios WHERE email = ? AND senha = ?";
    con = new Conexao().getCon();

    try {
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, email);
        pst.setString(2, senha);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            usuario = new UsuariosDTO();
            usuario.setId_Usuarios(rs.getInt("usuario_id"));
            usuario.setNome_usuario(rs.getString("email"));
            
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Erro ao autenticar: " + e.getMessage());
    }

    return usuario;
}
    
}
