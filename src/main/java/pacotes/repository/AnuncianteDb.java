package pacotes.repository;

import pacotes.models.usuario.Anunciante;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnuncianteDb {
    
    public static boolean cadastrar(String nome, String email, String cnpj, String telefone) {
        try {
            String sql = "INSERT INTO anunciante (nome, email, cnpj, telefone) " +
                    "VALUES ('" + nome + "', '" + email + "', '" + cnpj + "', '" + telefone + "')";
            
            return ConexaoDb.executarSql(sql);
            
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar anunciante: " + e.getMessage());
        }
        return false;
    }
    
    public static boolean atualizar(Long id, String nome, String email, String telefone) {
        try {
            String sql = "UPDATE anunciante SET nome = '" + nome + "', " +
                    "email = '" + email + "', telefone = '" + telefone + "' " +
                    "WHERE id = " + id;
            
            return ConexaoDb.executarSql(sql);
            
        } catch (Exception e) {
            System.out.println("Erro ao atualizar anunciante: " + e.getMessage());
        }
        return false;
    }
    
    public static boolean deletar(Long id) {
        try {
            String sql = "DELETE FROM anunciante WHERE id = " + id;
            return ConexaoDb.executarSql(sql);
            
        } catch (Exception e) {
            System.out.println("Erro ao deletar anunciante: " + e.getMessage());
        }
        return false;
    }
    
    public static Anunciante buscarPorId(Long id) {
        try {
            String sql = "SELECT * FROM anunciante WHERE id = " + id;
            
            Connection con = ConexaoDb.conectar();
            ResultSet rs = ConexaoDb.executarQuery(sql, con);
            
            Anunciante anunciante = null;
            if (rs.next()) {
                anunciante = new Anunciante(
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("senha"),
                    rs.getString("telefone"),
                    rs.getString("cnpj"),
                    rs.getString("nome_fantasia")
                );
                anunciante.setId(rs.getLong("id"));
                anunciante.setDescricao(rs.getString("descricao"));
                anunciante.setAvaliacaoMedia(rs.getDouble("avaliacao_media"));
            }
            
            con.close();
            return anunciante;
            
        } catch (Exception e) {
            System.out.println("Erro ao buscar anunciante: " + e.getMessage());
        }
        return null;
    }
    
    public static List<Anunciante> listarTodos() {
        try {
            String sql = "SELECT * FROM anunciante ORDER BY nome";
            
            Connection con = ConexaoDb.conectar();
            ResultSet rs = ConexaoDb.executarQuery(sql, con);
            
            List<Anunciante> anunciantes = new ArrayList<>();
            while (rs.next()) {
                Anunciante anunciante = new Anunciante(
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("senha"),
                    rs.getString("telefone"),
                    rs.getString("cnpj"),
                    rs.getString("nome_fantasia")
                );
                anunciante.setId(rs.getLong("id"));
                anunciante.setDescricao(rs.getString("descricao"));
                anunciante.setAvaliacaoMedia(rs.getDouble("avaliacao_media"));
                anunciantes.add(anunciante);
            }
            
            con.close();
            return anunciantes;
            
        } catch (Exception e) {
            System.out.println("Erro ao listar anunciantes: " + e.getMessage());
        }
        return null;
    }
    
    public static Anunciante buscarPorEmail(String email) {
        try {
            String sql = "SELECT * FROM anunciante WHERE email = '" + email + "'";
            
            Connection con = ConexaoDb.conectar();
            ResultSet rs = ConexaoDb.executarQuery(sql, con);
            
            Anunciante anunciante = null;
            if (rs.next()) {
                anunciante = new Anunciante(
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("senha"),
                    rs.getString("telefone"),
                    rs.getString("cnpj"),
                    rs.getString("nome_fantasia")
                );
                anunciante.setId(rs.getLong("id"));
                anunciante.setDescricao(rs.getString("descricao"));
                anunciante.setAvaliacaoMedia(rs.getDouble("avaliacao_media"));
            }
            
            con.close();
            return anunciante;
            
        } catch (Exception e) {
            System.out.println("Erro ao buscar anunciante por email: " + e.getMessage());
        }
        return null;
    }
    
    public static Integer contarAnunciantes() {
        try {
            String sql = "SELECT COUNT(*) as total FROM anunciante";
            
            Connection con = ConexaoDb.conectar();
            ResultSet rs = ConexaoDb.executarQuery(sql, con);
            
            Integer total = 0;
            if (rs.next()) {
                total = rs.getInt("total");
            }
            
            con.close();
            return total;
            
        } catch (Exception e) {
            System.out.println("Erro ao contar anunciantes: " + e.getMessage());
        }
        return 0;
    }
}