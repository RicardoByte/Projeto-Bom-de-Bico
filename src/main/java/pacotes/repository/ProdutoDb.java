package pacotes.repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import pacotes.models.produtos.Produto;  

public class ProdutoDb {

    public static boolean adicionarProduto(int anuncianteId, String nome, String descricao, 
                                           String categoria, double preco, int quantidadeEstoque, String imagemUrl) {
        String sql = "INSERT INTO produtos (anunciante_id, nome, descricao, categoria, preco, quantidade_estoque, imagem_url, ativo) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, 1)";
        
        try (Connection conn = ConexaoDb.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, anuncianteId);
            pstmt.setString(2, nome);
            pstmt.setString(3, descricao);
            pstmt.setString(4, categoria);
            pstmt.setDouble(5, preco);
            pstmt.setInt(6, quantidadeEstoque);
            pstmt.setString(7, imagemUrl);
            
            int linhasAfetadas = pstmt.executeUpdate();
            return linhasAfetadas > 0;
            
        } catch (SQLException e) {
            System.out.println("Erro ao adicionar produto: " + e.getMessage());
            return false;
        }
    }

    public static boolean atualizarProduto(int id, String nome, String descricao, 
                                          String categoria, double preco, int quantidadeEstoque, String imagemUrl) {
        String sql = "UPDATE produtos SET nome = ?, descricao = ?, categoria = ?, " +
                     "preco = ?, quantidade_estoque = ?, imagem_url = ? WHERE id = ?";
        
        try (Connection conn = ConexaoDb.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, nome);
            pstmt.setString(2, descricao);
            pstmt.setString(3, categoria);
            pstmt.setDouble(4, preco);
            pstmt.setInt(5, quantidadeEstoque);
            pstmt.setString(6, imagemUrl);
            pstmt.setInt(7, id);
            
            int linhasAfetadas = pstmt.executeUpdate();
            return linhasAfetadas > 0;
            
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar produto: " + e.getMessage());
            return false;
        }
    }

    public static boolean deletarProduto(int id) {
        String sql = "DELETE FROM produtos WHERE id = ?";
        
        try (Connection conn = ConexaoDb.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            int linhasAfetadas = pstmt.executeUpdate();
            return linhasAfetadas > 0;
            
        } catch (SQLException e) {
            System.out.println("Erro ao deletar produto: " + e.getMessage());
            return false;
        }
    }

    public static List<Produto> listarProdutos() {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM produtos WHERE ativo = 1";
        
        try (Connection conn = ConexaoDb.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Produto produto = new Produto(
                    rs.getInt("id"),
                    rs.getInt("anunciante_id"),
                    rs.getString("nome"),
                    rs.getString("descricao"),
                    rs.getString("categoria"),
                    rs.getDouble("preco"),
                    rs.getInt("quantidade_estoque"),
                    rs.getString("imagem_url"),
                    rs.getInt("ativo")
                );
                produtos.add(produto);
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao listar produtos: " + e.getMessage());
        }
        
        return produtos;
    }

    public static Produto buscarProdutoPorId(int id) {
        String sql = "SELECT * FROM produtos WHERE id = ?";
        
        try (Connection conn = ConexaoDb.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return new Produto(
                    rs.getInt("id"),
                    rs.getInt("anunciante_id"),
                    rs.getString("nome"),
                    rs.getString("descricao"),
                    rs.getString("categoria"),
                    rs.getDouble("preco"),
                    rs.getInt("quantidade_estoque"),
                    rs.getString("imagem_url"),
                    rs.getInt("ativo")
                );
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao buscar produto: " + e.getMessage());
        }
        
        return null;
    }

    public static List<Produto> listarProdutosPorAnunciante(int anuncianteId) {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM produtos WHERE anunciante_id = ? AND ativo = 1";
        
        try (Connection conn = ConexaoDb.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, anuncianteId);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Produto produto = new Produto(
                    rs.getInt("id"),
                    rs.getInt("anunciante_id"),
                    rs.getString("nome"),
                    rs.getString("descricao"),
                    rs.getString("categoria"),
                    rs.getDouble("preco"),
                    rs.getInt("quantidade_estoque"),
                    rs.getString("imagem_url"),
                    rs.getInt("ativo")
                );
                produtos.add(produto);
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao listar produtos por anunciante: " + e.getMessage());
        }
        
        return produtos;
    }

    public static boolean desativarProduto(int id) {
        String sql = "UPDATE produtos SET ativo = 0 WHERE id = ?";
        
        try (Connection conn = ConexaoDb.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            int linhasAfetadas = pstmt.executeUpdate();
            return linhasAfetadas > 0;
            
        } catch (SQLException e) {
            System.out.println("Erro ao desativar produto: " + e.getMessage());
            return false;
        }
    }
}