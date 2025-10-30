package pacotes.repository;

import pacotes.models.pedido.Pedido;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoDb {

    public static boolean adicionarPedido(int carrinhoId, String status, String dataPedido) {
        String sql = "INSERT INTO pedidos (carrinho_id, status, data_pedido) VALUES (?, ?, ?)";
        
        try (Connection conn = ConexaoDb.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, carrinhoId);
            pstmt.setString(2, status);
            pstmt.setString(3, dataPedido);
            
            int linhasAfetadas = pstmt.executeUpdate();
            return linhasAfetadas > 0;
            
        } catch (SQLException e) {
            System.out.println("Erro ao adicionar pedido: " + e.getMessage());
            return false;
        }
    }

    public static int adicionarPedidoComRetornoId(int carrinhoId, String status, String dataPedido) {
        String sql = "INSERT INTO pedidos (carrinho_id, status, data_pedido) VALUES (?, ?, ?)";
        
        try (Connection conn = ConexaoDb.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setInt(1, carrinhoId);
            pstmt.setString(2, status);
            pstmt.setString(3, dataPedido);
            
            int linhasAfetadas = pstmt.executeUpdate();
            
            if (linhasAfetadas > 0) {
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao adicionar pedido: " + e.getMessage());
        }
        
        return -1;
    }

    public static boolean atualizarPedido(int id, String status) {
        String sql = "UPDATE pedidos SET status = ? WHERE id = ?";
        
        try (Connection conn = ConexaoDb.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, status);
            pstmt.setInt(2, id);
            
            int linhasAfetadas = pstmt.executeUpdate();
            return linhasAfetadas > 0;
            
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar pedido: " + e.getMessage());
            return false;
        }
    }

    public static boolean deletarPedido(int id) {
        String sql = "DELETE FROM pedidos WHERE id = ?";
        
        try (Connection conn = ConexaoDb.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            int linhasAfetadas = pstmt.executeUpdate();
            return linhasAfetadas > 0;
            
        } catch (SQLException e) {
            System.out.println("Erro ao deletar pedido: " + e.getMessage());
            return false;
        }
    }

    public static List<Pedido> listarPedidos() {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "SELECT * FROM pedidos ORDER BY data_pedido DESC";
        
        try (Connection conn = ConexaoDb.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Pedido pedido = new Pedido(
                    rs.getInt("id"),
                    rs.getInt("carrinho_id"),
                    rs.getString("status"),
                    rs.getString("data_pedido")
                );
                pedidos.add(pedido);
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao listar pedidos: " + e.getMessage());
        }
        
        return pedidos;
    }

    public static Pedido buscarPedidoPorId(int id) {
        String sql = "SELECT * FROM pedidos WHERE id = ?";
        
        try (Connection conn = ConexaoDb.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return new Pedido(
                    rs.getInt("id"),
                    rs.getInt("carrinho_id"),
                    rs.getString("status"),
                    rs.getString("data_pedido")
                );
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao buscar pedido: " + e.getMessage());
        }
        
        return null;
    }

    public static List<Pedido> listarPedidosPorCarrinho(int carrinhoId) {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "SELECT * FROM pedidos WHERE carrinho_id = ? ORDER BY data_pedido DESC";
        
        try (Connection conn = ConexaoDb.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, carrinhoId);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Pedido pedido = new Pedido(
                    rs.getInt("id"),
                    rs.getInt("carrinho_id"),
                    rs.getString("status"),
                    rs.getString("data_pedido")
                );
                pedidos.add(pedido);
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao listar pedidos por carrinho: " + e.getMessage());
        }
        
        return pedidos;
    }

    public static List<Pedido> listarPedidosPorStatus(String status) {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "SELECT * FROM pedidos WHERE status = ? ORDER BY data_pedido DESC";
        
        try (Connection conn = ConexaoDb.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, status);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Pedido pedido = new Pedido(
                    rs.getInt("id"),
                    rs.getInt("carrinho_id"),
                    rs.getString("status"),
                    rs.getString("data_pedido")
                );
                pedidos.add(pedido);
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao listar pedidos por status: " + e.getMessage());
        }
        
        return pedidos;
    }

    public static List<Pedido> listarPedidosPorCliente(int clienteId) {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = """
            SELECT p.* FROM pedidos p
            INNER JOIN carrinho c ON p.carrinho_id = c.id
            WHERE c.cliente_id = ?
            ORDER BY p.data_pedido DESC
        """;
        
        try (Connection conn = ConexaoDb.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, clienteId);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Pedido pedido = new Pedido(
                    rs.getInt("id"),
                    rs.getInt("carrinho_id"),
                    rs.getString("status"),
                    rs.getString("data_pedido")
                );
                pedidos.add(pedido);
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao listar pedidos por cliente: " + e.getMessage());
        }
        
        return pedidos;
    }

    public static int contarPedidosPorStatus(String status) {
        String sql = "SELECT COUNT(*) as total FROM pedidos WHERE status = ?";
        
        try (Connection conn = ConexaoDb.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, status);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt("total");
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao contar pedidos: " + e.getMessage());
        }
        
        return 0;
    }

    public static boolean cancelarPedido(int id) {
        return atualizarPedido(id, "Cancelado");
    }

    public static boolean finalizarPedido(int id) {
        return atualizarPedido(id, "Finalizado");
    }

    public static boolean confirmarPedido(int id) {
        return atualizarPedido(id, "Confirmado");
    }

    public static boolean pedidoExiste(int id) {
        String sql = "SELECT COUNT(*) as total FROM pedidos WHERE id = ?";
        
        try (Connection conn = ConexaoDb.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt("total") > 0;
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao verificar pedido: " + e.getMessage());
        }
        
        return false;
    }
}