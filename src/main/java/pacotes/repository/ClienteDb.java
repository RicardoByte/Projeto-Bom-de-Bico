package pacotes.repository;

import pacotes.models.usuario.Cliente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDb {

    public static boolean adicionarCliente(int usuarioId, String cpf) {
        String sql = "INSERT INTO cliente (cpf, usuario_id) VALUES (?, ?)";
        
        try (Connection conn = ConexaoDb.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, cpf);
            pstmt.setInt(2, usuarioId);
            
            int linhasAfetadas = pstmt.executeUpdate();
            return linhasAfetadas > 0;
            
        } catch (SQLException e) {
            System.out.println("Erro ao adicionar cliente: " + e.getMessage());
            return false;
        }
    }

    public static boolean atualizarCliente(String cpf, int usuarioId) {
        String sql = "UPDATE cliente SET usuario_id = ? WHERE cpf = ?";
        
        try (Connection conn = ConexaoDb.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, usuarioId);
            pstmt.setString(2, cpf);
            
            int linhasAfetadas = pstmt.executeUpdate();
            return linhasAfetadas > 0;
            
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar cliente: " + e.getMessage());
            return false;
        }
    }

    public static boolean deletarCliente(String cpf) {
        String sql = "DELETE FROM cliente WHERE cpf = ?";
        
        try (Connection conn = ConexaoDb.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, cpf);
            int linhasAfetadas = pstmt.executeUpdate();
            return linhasAfetadas > 0;
            
        } catch (SQLException e) {
            System.out.println("Erro ao deletar cliente: " + e.getMessage());
            return false;
        }
    }

    public static List<Cliente> listarClientes() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT c.cpf, c.usuario_id, u.nome, u.email, u.senha, u.telefone, " +
 "u.endereco, u.ativo " +
 "FROM cliente c " +
 "INNER JOIN usuario u ON c.usuario_id = u.id " +
 "WHERE u.ativo = 1";
        
        try (Connection conn = ConexaoDb.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Cliente cliente = new Cliente(
                    rs.getInt("usuario_id"),
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("senha"),
                    rs.getString("telefone"),
                    rs.getString("endereco"),
                    rs.getInt("ativo"),
                    rs.getString("cpf")
                );
                clientes.add(cliente);
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao listar clientes: " + e.getMessage());
        }
        
        return clientes;
    }

    public static Cliente buscarClientePorCpf(String cpf) {
        String sql = "SELECT c.cpf, c.usuario_id, u.nome, u.email, u.senha, u.telefone, " +
 "u.endereco, u.ativo " +
 "FROM cliente c " +
 "INNER JOIN usuario u ON c.usuario_id = u.id " +
 "WHERE c.cpf = ?";
        
        try (Connection conn = ConexaoDb.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, cpf);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return new Cliente(
                    rs.getInt("usuario_id"),
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("senha"),
                    rs.getString("telefone"),
                    rs.getString("endereco"),
                    rs.getInt("ativo"),
                    rs.getString("cpf")
                );
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao buscar cliente por CPF: " + e.getMessage());
        }
        
        return null;
    }

    public static Cliente buscarClientePorUsuarioId(int usuarioId) {
        String sql = "SELECT c.cpf, c.usuario_id, u.nome, u.email, u.senha, u.telefone, " +
 "u.endereco, u.ativo " +
 "FROM cliente c " +
 "INNER JOIN usuario u ON c.usuario_id = u.id " +
 "WHERE c.usuario_id = ?";
        
        try (Connection conn = ConexaoDb.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, usuarioId);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return new Cliente(
                    rs.getInt("usuario_id"),
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("senha"),
                    rs.getString("telefone"),
                    rs.getString("endereco"),
                    rs.getInt("ativo"),
                    rs.getString("cpf")
                );
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao buscar cliente por usuário ID: " + e.getMessage());
        }
        
        return null;
    }

    // ========== MÉTODOS DE CARRINHO ==========

    public static boolean addCarrinho(int clienteId, int produtoId, int quantidade) {
        String sql = "INSERT INTO carrinho (cliente_id, produto_id, quantidade) VALUES (?, ?, ?)";
        
        try (Connection conn = ConexaoDb.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, clienteId);
            pstmt.setInt(2, produtoId);
            pstmt.setInt(3, quantidade);
            
            int linhasAfetadas = pstmt.executeUpdate();
            return linhasAfetadas > 0;
            
        } catch (SQLException e) {
            System.out.println("Erro ao adicionar ao carrinho: " + e.getMessage());
            return false;
        }
    }

    public static boolean removeCarrinho(int clienteId, int produtoId) {
        String sql = "DELETE FROM carrinho WHERE cliente_id = ? AND produto_id = ?";
        
        try (Connection conn = ConexaoDb.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, clienteId);
            pstmt.setInt(2, produtoId);
            
            int linhasAfetadas = pstmt.executeUpdate();
            return linhasAfetadas > 0;
            
        } catch (SQLException e) {
            System.out.println("Erro ao remover do carrinho: " + e.getMessage());
            return false;
        }
    }

    public static boolean alterCarrinho(int clienteId, int produtoId, int novaQuantidade) {
        String sql = "UPDATE carrinho SET quantidade = ? WHERE cliente_id = ? AND produto_id = ?";
        
        try (Connection conn = ConexaoDb.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, novaQuantidade);
            pstmt.setInt(2, clienteId);
            pstmt.setInt(3, produtoId);
            
            int linhasAfetadas = pstmt.executeUpdate();
            return linhasAfetadas > 0;
            
        } catch (SQLException e) {
            System.out.println("Erro ao alterar carrinho: " + e.getMessage());
            return false;
        }
    }

    public static List<Integer> listarCarrinho(int clienteId) {
        List<Integer> produtos = new ArrayList<>();
        String sql = "SELECT produto_id FROM carrinho WHERE cliente_id = ?";
        
        try (Connection conn = ConexaoDb.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, clienteId);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                produtos.add(rs.getInt("produto_id"));
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao listar carrinho: " + e.getMessage());
        }
        
        return produtos;
    }

    public static List<ItemCarrinho> listarCarrinhoDetalhado(int clienteId) {
        List<ItemCarrinho> itens = new ArrayList<>();
        String sql = "SELECT produto_id, quantidade FROM carrinho WHERE cliente_id = ?";
        
        try (Connection conn = ConexaoDb.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, clienteId);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                ItemCarrinho item = new ItemCarrinho(
                    rs.getInt("produto_id"),
                    rs.getInt("quantidade")
                );
                itens.add(item);
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao listar carrinho detalhado: " + e.getMessage());
        }
        
        return itens;
    }

    public static boolean limparCarrinho(int clienteId) {
        String sql = "DELETE FROM carrinho WHERE cliente_id = ?";
        
        try (Connection conn = ConexaoDb.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, clienteId);
            int linhasAfetadas = pstmt.executeUpdate();
            return linhasAfetadas > 0;
            
        } catch (SQLException e) {
            System.out.println("Erro ao limpar carrinho: " + e.getMessage());
            return false;
        }
    }

    public static boolean produtoNoCarrinho(int clienteId, int produtoId) {
        String sql = "SELECT COUNT(*) as total FROM carrinho WHERE cliente_id = ? AND produto_id = ?";
        
        try (Connection conn = ConexaoDb.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, clienteId);
            pstmt.setInt(2, produtoId);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt("total") > 0;
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao verificar produto no carrinho: " + e.getMessage());
        }
        
        return false;
    }

    public static int obterQuantidade(int clienteId, int produtoId) {
        String sql = "SELECT quantidade FROM carrinho WHERE cliente_id = ? AND produto_id = ?";
        
        try (Connection conn = ConexaoDb.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, clienteId);
            pstmt.setInt(2, produtoId);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt("quantidade");
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao obter quantidade: " + e.getMessage());
        }
        
        return 0;
    }

    public static int contarItens(int clienteId) {
        String sql = "SELECT COUNT(*) as total FROM carrinho WHERE cliente_id = ?";
        
        try (Connection conn = ConexaoDb.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, clienteId);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt("total");
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao contar itens do carrinho: " + e.getMessage());
        }
        
        return 0;
    }

    /**
     * Classe auxiliar para representar item do carrinho com seus detalhes
     */
    public static class ItemCarrinho {
        private int produtoId;
        private int quantidade;
        
        public ItemCarrinho(int produtoId, int quantidade) {
            this.produtoId = produtoId;
            this.quantidade = quantidade;
        }
        
        public int getProdutoId() {
            return produtoId;
        }
        
        public void setProdutoId(int produtoId) {
            this.produtoId = produtoId;
        }
        
        public int getQuantidade() {
            return quantidade;
        }
        
        public void setQuantidade(int quantidade) {
            this.quantidade = quantidade;
        }
    }
}