/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public boolean cadastrarProduto (ProdutosDTO produto){
        
        String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";
        
        conn = new conectaDAO().connectDB();
        if (conn == null) {
            return false;
        }
        
        try {
            prep = conn.prepareStatement(sql);
            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3, produto.getStatus());
            prep.executeUpdate();
            return true;
        } catch (SQLException erro) {
            System.out.println("Erro ao cadastrar produto: " + erro.getMessage());
            return false;
        } finally {
            fecharRecursos();
        }
        
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        
        String sql = "SELECT id, nome, valor, status FROM produtos";
        
        listagem.clear();
        conn = new conectaDAO().connectDB();
        if (conn == null) {
            return listagem;
        }
        
        try {
            prep = conn.prepareStatement(sql);
            resultset = prep.executeQuery();
            
            while (resultset.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(resultset.getInt("id"));
                produto.setNome(resultset.getString("nome"));
                produto.setValor(resultset.getInt("valor"));
                produto.setStatus(resultset.getString("status"));
                listagem.add(produto);
            }
        } catch (SQLException erro) {
            System.out.println("Erro ao listar produtos: " + erro.getMessage());
        } finally {
            fecharRecursos();
        }
        
        return listagem;
    }
    
    public boolean venderProduto(int id){
        
        String sql = "UPDATE produtos SET status = ? WHERE id = ?";
        
        conn = new conectaDAO().connectDB();
        if (conn == null) {
            return false;
        }
        
        try {
            prep = conn.prepareStatement(sql);
            prep.setString(1, "Vendido");
            prep.setInt(2, id);
            int linhasAlteradas = prep.executeUpdate();
            return linhasAlteradas > 0;
        } catch (SQLException erro) {
            System.out.println("Erro ao vender produto: " + erro.getMessage());
            return false;
        } finally {
            fecharRecursos();
        }
        
    }
    
    public ArrayList<ProdutosDTO> listarProdutosVendidos(){
        
        String sql = "SELECT id, nome, valor, status FROM produtos WHERE status = ?";
        ArrayList<ProdutosDTO> vendidos = new ArrayList<>();
        
        conn = new conectaDAO().connectDB();
        if (conn == null) {
            return vendidos;
        }
        
        try {
            prep = conn.prepareStatement(sql);
            prep.setString(1, "Vendido");
            resultset = prep.executeQuery();
            
            while (resultset.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(resultset.getInt("id"));
                produto.setNome(resultset.getString("nome"));
                produto.setValor(resultset.getInt("valor"));
                produto.setStatus(resultset.getString("status"));
                vendidos.add(produto);
            }
        } catch (SQLException erro) {
            System.out.println("Erro ao listar produtos vendidos: " + erro.getMessage());
        } finally {
            fecharRecursos();
        }
        
        return vendidos;
    }
    
    private void fecharRecursos() {
        try {
            if (resultset != null) {
                resultset.close();
            }
            if (prep != null) {
                prep.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException erro) {
            System.out.println("Erro ao fechar a conexão: " + erro.getMessage());
        }
    }
    
    
    
        
}

