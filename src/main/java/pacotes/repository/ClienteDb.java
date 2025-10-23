package pacotes.repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDb {
    
    
    public static boolean addCarrinho(Long clienteId, Long produtoId, Integer quantidade) {
        try {
            String sql = "INSERT INTO carrinho (cliente_id, produto_id, quantidade) " +
                    "VALUES (" + clienteId + ", " + produtoId + ", " + quantidade + ")";
            
            return ConexaoDb.executarSql(sql);
            
        } catch (Exception e) {
            System.out.println("Erro ao adicionar ao carrinho: " + e.getMessage());
        }
        
        return false;
    }
    
    
    public static boolean removeCarrinho(Long clienteId, Long produtoId) {
        try {
            String sql = "DELETE FROM carrinho WHERE cliente_id = " + clienteId + 
                    " AND produto_id = " + produtoId;
            
            return ConexaoDb.executarSql(sql);
            
        } catch (Exception e) {
            System.out.println("Erro ao remover do carrinho: " + e.getMessage());
        }
        
        return false;
    }
    
    
    public static boolean alterCarrinho(Long clienteId, Long produtoId, Integer novaQuantidade) {
        try {
            String sql = "UPDATE carrinho SET quantidade = " + novaQuantidade + 
                    " WHERE cliente_id = " + clienteId + " AND produto_id = " + produtoId;
            
            return ConexaoDb.executarSql(sql);
            
        } catch (Exception e) {
            System.out.println("Erro ao alterar carrinho: " + e.getMessage());
        }
        
        return false;
    }
    
    
    public static List<Long> listarCarrinho(Long clienteId) {
        try {
            String sql = "SELECT produto_id FROM carrinho WHERE cliente_id = " + clienteId;
            
            Connection con = ConexaoDb.conectar();
            ResultSet rs = ConexaoDb.executarQuery(sql, con);
            
            List<Long> produtos = new ArrayList<>();
            while (rs.next()) {
                produtos.add(rs.getLong("produto_id"));
            }
            
            con.close();
            return produtos;
            
        } catch (Exception e) {
            System.out.println("Erro ao listar carrinho: " + e.getMessage());
        }
        
        return null;
    }
    
   
    public static List<ItemCarrinho> listarCarrinhoDetalhado(Long clienteId) {
        try {
            String sql = """
            SELECT c.produto_id, c.quantidade
            FROM carrinho c
            WHERE c.cliente_id = """ + clienteId;
            
            Connection con = ConexaoDb.conectar();
            ResultSet rs = ConexaoDb.executarQuery(sql, con);
            
            List<ItemCarrinho> itens = new ArrayList<>();
            while (rs.next()) {
                ItemCarrinho item = new ItemCarrinho(
                        rs.getLong("produto_id"),
                        rs.getInt("quantidade")
                );
                itens.add(item);
            }
            
            con.close();
            return itens;
            
        } catch (Exception e) {
            System.out.println("Erro ao listar carrinho detalhado: " + e.getMessage());
        }
        
        return null;
    }
    

    public static boolean limparCarrinho(Long clienteId) {
        try {
            String sql = "DELETE FROM carrinho WHERE cliente_id = " + clienteId;
            return ConexaoDb.executarSql(sql);
            
        } catch (Exception e) {
            System.out.println("Erro ao limpar carrinho: " + e.getMessage());
        }
        
        return false;
    }
    
   
    public static boolean produtoNoCarrinho(Long clienteId, Long produtoId) {
        try {
            String sql = "SELECT COUNT(*) as total FROM carrinho " +
                    "WHERE cliente_id = " + clienteId + " AND produto_id = " + produtoId;
            
            Connection con = ConexaoDb.conectar();
            ResultSet rs = ConexaoDb.executarQuery(sql, con);
            
            boolean existe = false;
            if (rs.next()) {
                existe = rs.getInt("total") > 0;
            }
            
            con.close();
            return existe;
            
        } catch (Exception e) {
            System.out.println("Erro ao verificar produto no carrinho: " + e.getMessage());
        }
        
        return false;
    }
    

    public static Integer obterQuantidade(Long clienteId, Long produtoId) {
        try {
            String sql = "SELECT quantidade FROM carrinho " +
                    "WHERE cliente_id = " + clienteId + " AND produto_id = " + produtoId;
            
            Connection con = ConexaoDb.conectar();
            ResultSet rs = ConexaoDb.executarQuery(sql, con);
            
            Integer quantidade = 0;
            if (rs.next()) {
                quantidade = rs.getInt("quantidade");
            }
            
            con.close();
            return quantidade;
            
        } catch (Exception e) {
            System.out.println("Erro ao obter quantidade: " + e.getMessage());
        }
        
        return 0;
    }
    
    
    public static Integer contarItens(Long clienteId) {
        try {
            String sql = "SELECT COUNT(*) as total FROM carrinho WHERE cliente_id = " + clienteId;
            
            Connection con = ConexaoDb.conectar();
            ResultSet rs = ConexaoDb.executarQuery(sql, con);
            
            Integer total = 0;
            if (rs.next()) {
                total = rs.getInt("total");
            }
            
            con.close();
            return total;
            
        } catch (Exception e) {
            System.out.println("Erro ao contar itens do carrinho: " + e.getMessage());
        }
        
        return 0;
    }
    
    /**
     * Classe auxiliar para representar item do carrinho com seus detalhes
     */
    public static class ItemCarrinho {
        private Long produtoId;
        private Integer quantidade;
        
        public ItemCarrinho(Long produtoId, Integer quantidade) {
            this.produtoId = produtoId;
            this.quantidade = quantidade;
        }
        
        public Long getProdutoId() {
            return produtoId;
        }
        
        public void setProdutoId(Long produtoId) {
            this.produtoId = produtoId;
        }
        
        public Integer getQuantidade() {
            return quantidade;
        }
        
        public void setQuantidade(Integer quantidade) {
            this.quantidade = quantidade;
        }
    }
}