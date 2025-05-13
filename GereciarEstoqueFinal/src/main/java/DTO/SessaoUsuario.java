/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author LUIZ
 */
public class SessaoUsuario {
   
    private static int idUsuario;

    // Método para setar o id do usuário
    public static void setIdUsuario(int id) {
        idUsuario = id;
    }

    // Método para pegar o id do usuário
    public static int getIdUsuario() {
        return idUsuario;
    }

}
