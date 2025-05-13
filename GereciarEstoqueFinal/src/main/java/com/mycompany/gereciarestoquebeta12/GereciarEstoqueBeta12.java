/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.gereciarestoquebeta12;

import DAO.Conexao;
import View.TelaLogin;
import java.sql.Connection;
import javax.swing.JOptionPane;

/**
 *
 * @author aluno.den
 */
public class GereciarEstoqueBeta12 {

    public static void main(String[] args) {
            
        
         Conexao conexao = new Conexao();
        Connection con = conexao.getCon();

        if (con != null) {
            JOptionPane.showMessageDialog(null, "Conexão bem-sucedida!");
            TelaLogin lg = new TelaLogin();
            lg.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Erro ao conectar com o banco.");
        }
        
}
    }

