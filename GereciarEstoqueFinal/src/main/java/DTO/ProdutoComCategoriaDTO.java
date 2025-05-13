/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author LUIZ
 */
public class ProdutoComCategoriaDTO {
    private int id_produto;
    private String nome_produto;
    private int quantidade_produto;
    private double preco_produto;
    private String descricao_produto;
    private int id_categoria;
    private String nome_categoria;
    private int id_usuario;
    private String nome_usuario;
    
    public int getId_produto() {
        return id_produto;
    }

    public void setId_produto(int id_produto) {
        this.id_produto = id_produto;
    }

    public String getNome_produto() {
        return nome_produto;
    }

    public void setNome_produto(String nome_produto) {
        this.nome_produto = nome_produto;
    }

    public int getQuantidade_produto() {
        return quantidade_produto;
    }

    public void setQuantidade_produto(int quantidade_produto) {
        this.quantidade_produto = quantidade_produto;
    }

    public double getPreco_produto() {
        return preco_produto;
    }

    public void setPreco_produto(double preco_produto) {
        this.preco_produto = preco_produto;
    }

    public String getDescricao_produto() {
        return descricao_produto;
    }

    public void setDescricao_produto(String descricao_produto) {
        this.descricao_produto = descricao_produto;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public String getNome_categoria() {
        return nome_categoria;
    }

    public void setNome_categoria(String nome_categoria) {
        this.nome_categoria = nome_categoria;
    }
    
    public int getId_usuario() {
    return id_usuario;
}

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }
    
    public String getNome_usuario() { 
        return nome_usuario; 
    }
    public void setNome_usuario(String nome_usuario) { 
        this.nome_usuario = nome_usuario; 
    }
}
