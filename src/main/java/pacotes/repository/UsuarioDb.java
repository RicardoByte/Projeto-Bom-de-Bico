package pacotes.repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import pacotes.models.usuario.Usuario;

public class UsuarioDb {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static boolean adicionarUsuario(String nome, String email, String senha, 
                                          String telefone, String endereco, String pfpUrl) {
        String sql = "INSERT INTO usuario (nome, email, senha, telefone, endereco, ativo, pfp_url, data_cadastro) " +
                     "VALUES (?, ?, ?, ?, ?, 1, ?, ?)";
        
        try (Connection conn = ConexaoDb.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, nome);
            pstmt.setString(2, email);
            pstmt.setString(3, senha);
            pstmt.setString(4, telefone);
            pstmt.setString(5, endereco);
            pstmt.setString(6, pfpUrl);
            pstmt.setString(7, LocalDateTime.now().format(formatter));
            
            int linhasAfetadas = pstmt.executeUpdate();
            return linhasAfetadas > 0;
            
        } catch (SQLException e) {
            System.out.println("Erro ao adicionar usuário: " + e.getMessage());
            return false;
        }
    }

    public static boolean atualizarUsuario(int id, String nome, String email, 
                                          String telefone, String endereco, String pfpUrl) {
        String sql = "UPDATE usuario SET nome = ?, email = ?, telefone = ?, endereco = ?, pfp_url = ? WHERE id = ?";
        
        try (Connection conn = ConexaoDb.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, nome);
            pstmt.setString(2, email);
            pstmt.setString(3, telefone);
            pstmt.setString(4, endereco);
            pstmt.setString(5, pfpUrl);
            pstmt.setInt(6, id);
            
            int linhasAfetadas = pstmt.executeUpdate();
            return linhasAfetadas > 0;
            
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar usuário: " + e.getMessage());
            return false;
        }
    }

    public static boolean atualizarSenha(int id, String novaSenha) {
        String sql = "UPDATE usuario SET senha = ? WHERE id = ?";
        
        try (Connection conn = ConexaoDb.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, novaSenha);
            pstmt.setInt(2, id);
            
            int linhasAfetadas = pstmt.executeUpdate();
            return linhasAfetadas > 0;
            
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar senha: " + e.getMessage());
            return false;
        }
    }

    public static boolean deletarUsuario(int id) {
        String sql = "DELETE FROM usuario WHERE id = ?";
        
        try (Connection conn = ConexaoDb.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            int linhasAfetadas = pstmt.executeUpdate();
            return linhasAfetadas > 0;
            
        } catch (SQLException e) {
            System.out.println("Erro ao deletar usuário: " + e.getMessage());
            return false;
        }
    }

    public static List<Usuario> listarUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuario WHERE ativo = 1";
        
        try (Connection conn = ConexaoDb.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Usuario usuario = new Usuario(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("senha"),
                    rs.getString("telefone"),
                    rs.getString("endereco"),
                    rs.getInt("ativo"),
                    rs.getString("pfp_url"),
                    rs.getString("data_cadastro")
                );
                usuarios.add(usuario);
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao listar usuários: " + e.getMessage());
        }
        
        return usuarios;
    }

    public static Usuario buscarUsuarioPorId(int id) {
        String sql = "SELECT * FROM usuario WHERE id = ?";
        
        try (Connection conn = ConexaoDb.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return new Usuario(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("senha"),
                    rs.getString("telefone"),
                    rs.getString("endereco"),
                    rs.getInt("ativo"),
                    rs.getString("pfp_url"),
                    rs.getString("data_cadastro")
                );
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao buscar usuário: " + e.getMessage());
        }
        
        return null;
    }

    public static Usuario buscarUsuarioPorEmail(String email) {
        String sql = "SELECT * FROM usuario WHERE email = ?";
        
        try (Connection conn = ConexaoDb.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return new Usuario(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("senha"),
                    rs.getString("telefone"),
                    rs.getString("endereco"),
                    rs.getInt("ativo"),
                    rs.getString("pfp_url"),
                    rs.getString("data_cadastro")
                );
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao buscar usuário por email: " + e.getMessage());
        }
        
        return null;
    }

    public static boolean desativarUsuario(int id) {
        String sql = "UPDATE usuario SET ativo = 0 WHERE id = ?";
        
        try (Connection conn = ConexaoDb.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            int linhasAfetadas = pstmt.executeUpdate();
            return linhasAfetadas > 0;
            
        } catch (SQLException e) {
            System.out.println("Erro ao desativar usuário: " + e.getMessage());
            return false;
        }
    }
}