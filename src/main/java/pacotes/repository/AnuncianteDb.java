package pacotes.repository;

import pacotes.models.usuario.Anunciante;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnuncianteDb {

    public static boolean adicionarAnunciante(int usuarioId, String cnpj, String cpf) {
        String sql = "INSERT INTO anunciantes (usuario_id, cnpj, cpf, ativo) VALUES (?, ?, ?, 1)";
        
        try (Connection conn = ConexaoDb.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, usuarioId);
            pstmt.setString(2, cnpj);
            pstmt.setString(3, cpf);
            
            int linhasAfetadas = pstmt.executeUpdate();
            return linhasAfetadas > 0;
            
        } catch (SQLException e) {
            System.out.println("Erro ao adicionar anunciante: " + e.getMessage());
            return false;
        }
    }

    public static boolean atualizarAnunciante(int usuarioId, String cnpj, String cpf) {
        String sql = "UPDATE anunciantes SET cnpj = ?, cpf = ? WHERE usuario_id = ?";
        
        try (Connection conn = ConexaoDb.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, cnpj);
            pstmt.setString(2, cpf);
            pstmt.setInt(3, usuarioId);
            
            int linhasAfetadas = pstmt.executeUpdate();
            return linhasAfetadas > 0;
            
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar anunciante: " + e.getMessage());
            return false;
        }
    }

    public static boolean deletarAnunciante(int usuarioId) {
        String sql = "DELETE FROM anunciantes WHERE usuario_id = ?";
        
        try (Connection conn = ConexaoDb.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, usuarioId);
            int linhasAfetadas = pstmt.executeUpdate();
            return linhasAfetadas > 0;
            
        } catch (SQLException e) {
            System.out.println("Erro ao deletar anunciante: " + e.getMessage());
            return false;
        }
    }

    public static List<Anunciante> listarAnunciantes() {
        List<Anunciante> anunciantes = new ArrayList<>();
        String sql = """
            SELECT a.usuario_id, u.nome, u.email, u.senha, u.telefone, u.endereco, 
                   u.ativo, a.cnpj, a.cpf
            FROM anunciantes a
            INNER JOIN usuario u ON a.usuario_id = u.id
            WHERE a.ativo = 1
        """;
        
        try (Connection conn = ConexaoDb.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Anunciante anunciante = new Anunciante(
                    rs.getInt("usuario_id"),
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("senha"),
                    rs.getString("telefone"),
                    rs.getString("endereco"),
                    rs.getInt("ativo"),
                    rs.getString("cnpj"),
                    rs.getString("cpf")
                );
                anunciantes.add(anunciante);
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao listar anunciantes: " + e.getMessage());
        }
        
        return anunciantes;
    }

    public static Anunciante buscarAnunciantePorId(int usuarioId) {
        String sql = """
            SELECT a.usuario_id, u.nome, u.email, u.senha, u.telefone, u.endereco, 
                   u.ativo, a.cnpj, a.cpf
            FROM anunciantes a
            INNER JOIN usuario u ON a.usuario_id = u.id
            WHERE a.usuario_id = ?
        """;
        
        try (Connection conn = ConexaoDb.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, usuarioId);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return new Anunciante(
                    rs.getInt("usuario_id"),
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("senha"),
                    rs.getString("telefone"),
                    rs.getString("endereco"),
                    rs.getInt("ativo"),
                    rs.getString("cnpj"),
                    rs.getString("cpf")
                );
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao buscar anunciante: " + e.getMessage());
        }
        
        return null;
    }

    public static Anunciante buscarAnunciantePorCnpj(String cnpj) {
        String sql = """
            SELECT a.usuario_id, u.nome, u.email, u.senha, u.telefone, u.endereco, 
                   u.ativo, a.cnpj, a.cpf
            FROM anunciantes a
            INNER JOIN usuario u ON a.usuario_id = u.id
            WHERE a.cnpj = ?
        """;
        
        try (Connection conn = ConexaoDb.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, cnpj);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return new Anunciante(
                    rs.getInt("usuario_id"),
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("senha"),
                    rs.getString("telefone"),
                    rs.getString("endereco"),
                    rs.getInt("ativo"),
                    rs.getString("cnpj"),
                    rs.getString("cpf")
                );
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao buscar anunciante por CNPJ: " + e.getMessage());
        }
        
        return null;
    }

    public static Anunciante buscarAnunciantePorCpf(String cpf) {
        String sql = """
            SELECT a.usuario_id, u.nome, u.email, u.senha, u.telefone, u.endereco, 
                   u.ativo, a.cnpj, a.cpf
            FROM anunciantes a
            INNER JOIN usuario u ON a.usuario_id = u.id
            WHERE a.cpf = ?
        """;
        
        try (Connection conn = ConexaoDb.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, cpf);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return new Anunciante(
                    rs.getInt("usuario_id"),
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("senha"),
                    rs.getString("telefone"),
                    rs.getString("endereco"),
                    rs.getInt("ativo"),
                    rs.getString("cnpj"),
                    rs.getString("cpf")
                );
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao buscar anunciante por CPF: " + e.getMessage());
        }
        
        return null;
    }

    public static boolean desativarAnunciante(int usuarioId) {
        String sql = "UPDATE anunciantes SET ativo = 0 WHERE usuario_id = ?";
        
        try (Connection conn = ConexaoDb.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, usuarioId);
            int linhasAfetadas = pstmt.executeUpdate();
            return linhasAfetadas > 0;
            
        } catch (SQLException e) {
            System.out.println("Erro ao desativar anunciante: " + e.getMessage());
            return false;
        }
    }
}