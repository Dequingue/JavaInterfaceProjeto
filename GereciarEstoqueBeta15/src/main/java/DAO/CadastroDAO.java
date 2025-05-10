/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.CadastroDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
}
