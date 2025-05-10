/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;


import DAO.Conexao;
import DTO.ProdutoComCategoriaDTO;
import DTO.SessaoUsuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author LUIZ
 */
public class ProdutosDAO {
Connection con;
PreparedStatement ps;
ResultSet rs;

 ArrayList<ProdutoComCategoriaDTO> lista = new ArrayList<>();

public void cadastrarProdutoComCategoria(ProdutoComCategoriaDTO dto) {
    String sqlVerificaCategoria = "SELECT id_categoria FROM categorias WHERE nome_categoria = ?";
    String sqlCategoria = "INSERT INTO categorias (nome_categoria) VALUES (?)";
    String sqlProduto = "INSERT INTO produtos (nome_produto, quantidade_produto, preco_produto, descricao_produto, id_categoria, usuario_id) VALUES (?, ?, ?, ?, ?, ?)";

    int idUsuario = SessaoUsuario.getIdUsuario();
    System.out.println("ID do usuário logado: " + idUsuario);
    
    con = new Conexao().getCon();

    try {
        int id_categoria = 0;

        // Verifica se a categoria já existe
        PreparedStatement pstVerifica = con.prepareStatement(sqlVerificaCategoria);
        pstVerifica.setString(1, dto.getNome_categoria());
        rs = pstVerifica.executeQuery();

        if (rs.next()) {
            // Categoria já existe, pega o ID
            id_categoria = rs.getInt("id_categoria");
        } else {
            // Categoria não existe, insere uma nova
            PreparedStatement pstCategoria = con.prepareStatement(sqlCategoria, Statement.RETURN_GENERATED_KEYS);
            pstCategoria.setString(1, dto.getNome_categoria());
            pstCategoria.executeUpdate();

            rs = pstCategoria.getGeneratedKeys();
            if (rs.next()) {
                id_categoria = rs.getInt(1);
            }
        }

        // Insere o produto com o ID da categoria (existente ou nova)
        PreparedStatement pstProduto = con.prepareStatement(sqlProduto);
        pstProduto.setString(1, dto.getNome_produto());
        pstProduto.setInt(2, dto.getQuantidade_produto());
        pstProduto.setDouble(3, dto.getPreco_produto());
        pstProduto.setString(4, dto.getDescricao_produto());
        pstProduto.setInt(5, id_categoria); // ← isso é o id da categoria
        pstProduto.setInt(6, idUsuario);    // ← isso é o id do usuário

        pstProduto.executeUpdate();

    } catch (SQLException erro) {
        JOptionPane.showMessageDialog(null, "ProdutosDAO cadastrarProdutoComCategoria: " + erro);
    }

}

    public ArrayList<ProdutoComCategoriaDTO> listarProdutosComCategoria(int idUsuario) {
        lista.clear();
   
       String sql = "SELECT p.id_produto, p.nome_produto, p.quantidade_produto, p.preco_produto, " +
             "p.descricao_produto, c.id_categoria, c.nome_categoria, u.usuario_id, u.email AS email " +
             "FROM produtos p " +
             "INNER JOIN categorias c ON p.id_categoria = c.id_categoria " +
             "INNER JOIN usuarios u ON p.usuario_id = u.usuario_id " +
             "WHERE p.usuario_id = ?";
       
        con = new Conexao().getCon();
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, idUsuario);
            rs = ps.executeQuery();

            while (rs.next()) {
                ProdutoComCategoriaDTO dto = new ProdutoComCategoriaDTO();
                dto.setId_produto(rs.getInt("id_produto"));
                dto.setNome_produto(rs.getString("nome_produto"));
                dto.setQuantidade_produto(rs.getInt("quantidade_produto"));
                dto.setPreco_produto(rs.getDouble("preco_produto"));
                dto.setDescricao_produto(rs.getString("descricao_produto"));
                dto.setId_categoria(rs.getInt("id_categoria"));
                dto.setNome_categoria(rs.getString("nome_categoria"));
                dto.setId_usuario(idUsuario);
                dto.setId_usuario(rs.getInt("usuario_id"));
                dto.setNome_usuario(rs.getString("email"));

                lista.add(dto);
         }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar produtos com categoria: " + e.getMessage());
        }

    return lista;
}
public void atualizarProdutos(ProdutoComCategoriaDTO objProdutoComCategoriadto){
    String sqlproduto = "UPDATE produtos SET nome_produto=?, quantidade_produto=?, preco_produto=?, descricao_produto=?, id_categoria=? WHERE id_produto=?";
    String sqlBuscaCategoria = "SELECT id_categoria FROM categorias WHERE nome_categoria = ?";
    String sqlAtualizaCategoria = "UPDATE categorias SET nome_categoria=? WHERE id_categoria=?";

    PreparedStatement psProduto = null;
    PreparedStatement psBuscaCategoria = null;
    PreparedStatement psAtualizaCategoria = null;
    ResultSet rsCategoria = null;

    try {
        con = new Conexao().getCon();

        int id_categoria = objProdutoComCategoriadto.getId_categoria();

        // Verifica se o nome da categoria já existe (e pode ser outra categoria)
        psBuscaCategoria = con.prepareStatement(sqlBuscaCategoria);
        psBuscaCategoria.setString(1, objProdutoComCategoriadto.getNome_categoria());
        rsCategoria = psBuscaCategoria.executeQuery();

        if (rsCategoria.next()) {
            // Categoria já existe, usa o id existente
            id_categoria = rsCategoria.getInt("id_categoria");
        } else {
            // Categoria não existe com esse nome, então atualiza a existente
            psAtualizaCategoria = con.prepareStatement(sqlAtualizaCategoria);
            psAtualizaCategoria.setString(1, objProdutoComCategoriadto.getNome_categoria());
            psAtualizaCategoria.setInt(2, objProdutoComCategoriadto.getId_categoria());
            psAtualizaCategoria.executeUpdate();
        }

        // Atualiza o produto com os dados e com o ID da categoria correta
        psProduto = con.prepareStatement(sqlproduto);
        psProduto.setString(1, objProdutoComCategoriadto.getNome_produto());
        psProduto.setInt(2, objProdutoComCategoriadto.getQuantidade_produto());
        psProduto.setDouble(3, objProdutoComCategoriadto.getPreco_produto());
        psProduto.setString(4, objProdutoComCategoriadto.getDescricao_produto());
        psProduto.setInt(5, id_categoria); // usa o id atualizado
        psProduto.setInt(6, objProdutoComCategoriadto.getId_produto());
        psProduto.executeUpdate();

        JOptionPane.showMessageDialog(null, "Produto atualizado com sucesso!");

    } catch(SQLException e){
        JOptionPane.showMessageDialog(null, "Erro ao atualizar produto: " + e.getMessage());
    } finally {
        try {
            if (psProduto != null) psProduto.close();
            if (psBuscaCategoria != null) psBuscaCategoria.close();
            if (psAtualizaCategoria != null) psAtualizaCategoria.close();
            if (rsCategoria != null) rsCategoria.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao fechar recursos: " + e.getMessage());
        }
    }
}
    
             

      
public void ExcluindoProdutos(ProdutoComCategoriaDTO objProdutoComCategoriadto){
        String sqlproduto = "delete from produtos where id_produto=?";
        String sqlcategoria = "delete from categorias where id_categoria=?";
        
            con = new Conexao().getCon();
           try{
               PreparedStatement PsProduto = con.prepareStatement(sqlproduto);
               PsProduto.setInt(1, objProdutoComCategoriadto.getId_produto());
                       
               PsProduto.executeUpdate();
               PsProduto.close();
               
               PreparedStatement PsCategoria = con.prepareStatement(sqlcategoria);
               PsCategoria.setInt(1, objProdutoComCategoriadto.getId_categoria());
               
               PsCategoria.executeUpdate();
               PsCategoria.close();
               
                          
            
           }catch(SQLException e){
               JOptionPane.showMessageDialog(null,"ProdutoDAO Excluir"+e);
           }
        
    }

public ArrayList<ProdutoComCategoriaDTO> pesquisarProduto(String nomeProduto, int idUsuario) {
    ArrayList<ProdutoComCategoriaDTO> lista = new ArrayList<>();
    String sql = "SELECT p.id_produto, p.nome_produto, p.quantidade_produto, p.preco_produto, " +
                 "p.descricao_produto, c.id_categoria, c.nome_categoria, u.usuario_id, u.email AS email " +
                 "FROM produtos p " +
                 "INNER JOIN categorias c ON p.id_categoria = c.id_categoria " +
                 "INNER JOIN usuarios u ON p.usuario_id = u.usuario_id " +
                 "WHERE p.nome_produto LIKE ? AND p.usuario_id = ?";

    try {
        con = new Conexao().getCon();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, "%" + nomeProduto + "%"); // pesquisa parcial
        ps.setInt(2, idUsuario);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            ProdutoComCategoriaDTO dto = new ProdutoComCategoriaDTO();
            dto.setId_produto(rs.getInt("id_produto"));
            dto.setNome_produto(rs.getString("nome_produto"));
            dto.setQuantidade_produto(rs.getInt("quantidade_produto"));
            dto.setPreco_produto(rs.getDouble("preco_produto"));
            dto.setDescricao_produto(rs.getString("descricao_produto"));
            dto.setId_categoria(rs.getInt("id_categoria"));
            dto.setNome_categoria(rs.getString("nome_categoria"));
            dto.setId_usuario(rs.getInt("usuario_id"));
            dto.setNome_usuario(rs.getString("email"));

            lista.add(dto);
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Erro ao pesquisar produto: " + e.getMessage());
    }

    return lista;
}
}
