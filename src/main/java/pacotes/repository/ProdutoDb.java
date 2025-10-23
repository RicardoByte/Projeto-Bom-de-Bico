package pacotes.repository;

import pacotes.models.produtos.Produto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDb {


    public static boolean addProduto(Produto produto) {
        try {
            String sql = "INSERT INTO produto (anunciante_id, nome, descricao, categoria, preco, quantidade_estoque, imagem_url, ativo) " +
                         "VALUES (" + produto.getAnuncianteId() + ", '" + produto.getNome() + "', '" + produto.getDescricao() + "', '" +
                         produto.getCategoria() + "', " + produto.getPreco() + ", " + produto.getQuantidadeEstoque() + ", '" +
                         produto.getImagemUrl() + "', " + produto.isAtivo() + ")";
            
            return ConexaoDb.executarSql(sql);

        } catch (Exception e) {
            System.out.println("Erro ao adicionar produto: " + e.getMessage());
        }
        return false;
    }

   
    public static boolean atualizarProduto(Produto produto) {
        try {
            String sql = "UPDATE produto SET " +
                         "nome = '" + produto.getNome() + "', " +
                         "descricao = '" + produto.getDescricao() + "', " +
                         "categoria = '" + produto.getCategoria() + "', " +
                         "preco = " + produto.getPreco() + ", " +
                         "quantidade_estoque = " + produto.getQuantidadeEstoque() + ", " +
                         "imagem_url = '" + produto.getImagemUrl() + "', " +
                         "ativo = " + produto.isAtivo() +
                         " WHERE id = " + produto.getId();
            
            return ConexaoDb.executarSql(sql);

        } catch (Exception e) {
            System.out.println("Erro ao atualizar produto: " + e.getMessage());
        }
        return false;
    }

   
    public static boolean removerProduto(Long id) {
        try {
            String sql = "DELETE FROM produto WHERE id = " + id;
            return ConexaoDb.executarSql(sql);

        } catch (Exception e) {
            System.out.println("Erro ao remover produto: " + e.getMessage());
        }
        return false;
    }

   
    public static Produto buscarProdutoPorId(Long id) {
        try {
            String sql = "SELECT * FROM produto WHERE id = " + id;
            Connection con = ConexaoDb.conectar();
            ResultSet rs = ConexaoDb.executarQuery(sql, con);

            if (rs != null && rs.next()) {
                Produto p = new Produto();
                p.setId(rs.getLong("id"));
                p.setAnuncianteId(rs.getLong("anunciante_id"));
                p.setNome(rs.getString("nome"));
                p.setDescricao(rs.getString("descricao"));
                p.setCategoria(rs.getString("categoria"));
                p.setPreco(rs.getDouble("preco"));
                p.setQuantidadeEstoque(rs.getInt("quantidade_estoque"));
                p.setAtivo(rs.getBoolean("ativo"));
                return p;
            }
            con.close();
            return null;

        } catch (Exception e) {
            System.out.println("Erro ao buscar produto: " + e.getMessage());
        }
        return null;
    }


    public static List<Produto> listarProdutos() {
        List<Produto> lista = new ArrayList<>();
        try {
            String sql = "SELECT * FROM produto";
             Connection con = ConexaoDb.conectar();
            ResultSet rs = ConexaoDb.executarQuery(sql, con);

            while (rs != null && rs.next()) {
                Produto p = new Produto();
                p.setId(rs.getLong("id"));
                p.setAnuncianteId(rs.getLong("anunciante_id"));
                p.setNome(rs.getString("nome"));
                p.setDescricao(rs.getString("descricao"));
                p.setCategoria(rs.getString("categoria"));
                p.setPreco(rs.getDouble("preco"));
                p.setQuantidadeEstoque(rs.getInt("quantidade_estoque"));
                p.setAtivo(rs.getBoolean("ativo"));
                lista.add(p);
            }

            con.close();
            return lista;

        } catch (Exception e) {
            System.out.println("Erro ao listar produtos: " + e.getMessage());
        }
        return lista;
    }
}
