package pacotes.repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import pacotes.models.logistica.*;

public class LogisticaDb {

    
    public static boolean adicionarEntrega(Long entregaId, Endereco origem, Endereco destino, Transporte transporte, String status) {
        try {
            String sql = """
                INSERT INTO entrega (id, origem, destino, transporte, status)
                VALUES (%d, '%s', '%s', '%s', '%s')
            """.formatted(entregaId, origem.getCidade(), destino.getCidade(), transporte.getTipo(), status);

            return ConexaoDb.executarSql(sql);

        } catch (Exception e) {
            System.out.println("Erro ao adicionar entrega: " + e.getMessage());
        }

        return false;
    }

    
    public static boolean removerEntrega(Long entregaId) {
        try {
            String sql = "DELETE FROM entrega WHERE id = " + entregaId;
            return ConexaoDb.executarSql(sql);

        } catch (Exception e) {
            System.out.println("Erro ao remover entrega: " + e.getMessage());
        }

        return false;
    }

    
    public static boolean atualizarStatusEntrega(Long entregaId, String novoStatus) {
        try {
            String sql = "UPDATE entrega SET status = '" + novoStatus + "' WHERE id = " + entregaId;
            return ConexaoDb.executarSql(sql);

        } catch (Exception e) {
            System.out.println("Erro ao atualizar status da entrega: " + e.getMessage());
        }

        return false;
    }

    
    public static List<EntregaResumo> listarEntregas() {
        try {
            String sql = "SELECT id, status FROM entrega";
            Connection con = ConexaoDb.conectar();
            ResultSet rs = ConexaoDb.executarQuery(sql, con);

            List<EntregaResumo> entregas = new ArrayList<>();
            while (rs.next()) {
                EntregaResumo entrega = new EntregaResumo(
                        rs.getLong("id"),
                        rs.getString("status")
                );
                entregas.add(entrega);
            }

            con.close();
            return entregas;

        } catch (Exception e) {
            System.out.println("Erro ao listar entregas: " + e.getMessage());
        }

        return null;
    }

    
    public static EntregaDetalhada buscarEntregaPorId(Long entregaId) {
        try {
            String sql = "SELECT * FROM entrega WHERE id = " + entregaId;
            Connection con = ConexaoDb.conectar();
            ResultSet rs = ConexaoDb.executarQuery(sql, con);

            EntregaDetalhada entrega = null;
            if (rs.next()) {
                entrega = new EntregaDetalhada(
                        rs.getLong("id"),
                        rs.getString("origem"),
                        rs.getString("destino"),
                        rs.getString("transporte"),
                        rs.getString("status")
                );
            }

            con.close();
            return entrega;

        } catch (Exception e) {
            System.out.println("Erro ao buscar entrega por ID: " + e.getMessage());
        }

        return null;
    }

    
    public static class EntregaResumo {
        private Long id;
        private String status;

        public EntregaResumo(Long id, String status) {
            this.id = id;
            this.status = status;
        }

        public Long getId() {
            return id;
        }

        public String getStatus() {
            return status;
        }
    }

   
    public static class EntregaDetalhada {
        private Long id;
        private String origem;
        private String destino;
        private String transporte;
        private String status;

        public EntregaDetalhada(Long id, String origem, String destino, String transporte, String status) {
            this.id = id;
            this.origem = origem;
            this.destino = destino;
            this.transporte = transporte;
            this.status = status;
        }

        public Long getId() { return id; }
        public String getOrigem() { return origem; }
        public String getDestino() { return destino; }
        public String getTransporte() { return transporte; }
        public String getStatus() { return status; }
    }
}
