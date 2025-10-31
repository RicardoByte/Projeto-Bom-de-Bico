package pacotes.repository;

import java.sql.*;

public class ConexaoDb {
    private static final String URL = "jdbc:sqlite:D:\\Projeto BBC\\BancoDBB.db";

    
    public static Connection conectar() throws SQLException {
        Connection con = DriverManager.getConnection(URL);
        // Habilita foreign keys no SQLite (necessário!)
        con.createStatement().execute("PRAGMA foreign_keys = ON");
        criarTabelas(con);
        return con;
    }

    public static void criarTabelas(Connection con) throws SQLException {
        Statement stmt = con.createStatement();

        // 1. Tabela de usuários (base para cliente e anunciante)
        String sqlUsuario = """
            CREATE TABLE IF NOT EXISTS usuario (
                id               INTEGER PRIMARY KEY AUTOINCREMENT,
                nome             TEXT NOT NULL,
                email            TEXT UNIQUE NOT NULL,
                senha            TEXT NOT NULL,
                telefone         TEXT,
                endereco         TEXT,
                ativo            INTEGER DEFAULT 1,
                pfp_url          TEXT,
                data_cadastro    TEXT DEFAULT CURRENT_TIMESTAMP
            )
        """;

        // 2. Tabela de clientes (vinculada a usuário)
        String sqlCliente = """
            CREATE TABLE IF NOT EXISTS cliente (
                id         INTEGER PRIMARY KEY AUTOINCREMENT,
                cpf        TEXT UNIQUE NOT NULL,
                usuario_id INTEGER NOT NULL,
                FOREIGN KEY (usuario_id) REFERENCES usuario(id) ON DELETE CASCADE
            )
        """;

        // 3. Tabela de anunciantes (vinculada a usuário)
        String sqlAnunciantes = """
            CREATE TABLE IF NOT EXISTS anunciantes (
                id         INTEGER PRIMARY KEY AUTOINCREMENT,
                usuario_id INTEGER UNIQUE NOT NULL,
                cnpj       TEXT UNIQUE,
                cpf        TEXT UNIQUE,
                ativo      INTEGER DEFAULT 1,
                FOREIGN KEY (usuario_id) REFERENCES usuario(id) ON DELETE CASCADE
            )
        """;

        // 4. Tabela de produtos (vinculada a anunciante)
        String sqlProdutos = """
            CREATE TABLE IF NOT EXISTS produtos (
                id                 INTEGER PRIMARY KEY AUTOINCREMENT,
                anunciante_id      INTEGER NOT NULL,
                nome               TEXT NOT NULL,
                descricao          TEXT,
                categoria          TEXT,
                preco              REAL NOT NULL CHECK(preco >= 0),
                quantidade_estoque INTEGER DEFAULT 0 CHECK(quantidade_estoque >= 0),
                imagem_url         TEXT,
                ativo              INTEGER DEFAULT 1,
                data_cadastro      TEXT DEFAULT CURRENT_TIMESTAMP,
                FOREIGN KEY (anunciante_id) REFERENCES anunciantes(id) ON DELETE CASCADE
            )
        """;

        // 5. Tabela de carrinho (um carrinho por cliente)
        String sqlCarrinho = """
            CREATE TABLE IF NOT EXISTS carrinho (
                id             INTEGER PRIMARY KEY AUTOINCREMENT,
                cliente_id     INTEGER NOT NULL UNIQUE,
                data_criacao   TEXT DEFAULT CURRENT_TIMESTAMP,
                data_atualizacao TEXT DEFAULT CURRENT_TIMESTAMP,
                FOREIGN KEY (cliente_id) REFERENCES cliente(id) ON DELETE CASCADE
            )
        """;

        // 6. Tabela de produtos no carrinho (itens do carrinho)
        String sqlCarrinhoProdutos = """
            CREATE TABLE IF NOT EXISTS carrinho_produtos (
                id          INTEGER PRIMARY KEY AUTOINCREMENT,
                carrinho_id INTEGER NOT NULL,
                produto_id  INTEGER NOT NULL,
                quantidade  INTEGER NOT NULL CHECK(quantidade > 0),
                FOREIGN KEY (carrinho_id) REFERENCES carrinho(id) ON DELETE CASCADE,
                FOREIGN KEY (produto_id) REFERENCES produtos(id) ON DELETE CASCADE,
                UNIQUE(carrinho_id, produto_id)
            )
        """;

        // 7. Tabela de pedidos (carrinho finalizado vira pedido)
        String sqlPedidos = """
            CREATE TABLE IF NOT EXISTS pedidos (
                id             INTEGER PRIMARY KEY AUTOINCREMENT,
                cliente_id     INTEGER NOT NULL,
                status         TEXT NOT NULL DEFAULT 'PENDENTE',
                valor_total    REAL NOT NULL CHECK(valor_total >= 0),
                data_pedido    TEXT DEFAULT CURRENT_TIMESTAMP,
                FOREIGN KEY (cliente_id) REFERENCES cliente(id) ON DELETE CASCADE
            )
        """;

        // 8. Tabela de itens do pedido
        String sqlPedidoItens = """
            CREATE TABLE IF NOT EXISTS pedido_itens (
                id          INTEGER PRIMARY KEY AUTOINCREMENT,
                pedido_id   INTEGER NOT NULL,
                produto_id  INTEGER NOT NULL,
                quantidade  INTEGER NOT NULL CHECK(quantidade > 0),
                preco_unitario REAL NOT NULL CHECK(preco_unitario >= 0),
                FOREIGN KEY (pedido_id) REFERENCES pedidos(id) ON DELETE CASCADE,
                FOREIGN KEY (produto_id) REFERENCES produtos(id) ON DELETE RESTRICT
            )
        """;

        // 9. Tabela de logística
        String sqlLogistica = """
            CREATE TABLE IF NOT EXISTS logistica (
                id               INTEGER PRIMARY KEY AUTOINCREMENT,
                pedido_id        INTEGER NOT NULL UNIQUE,
                transportadora   TEXT,
                codigo_rastreio  TEXT UNIQUE,
                status_entrega   TEXT NOT NULL DEFAULT 'PREPARANDO',
                data_envio       TEXT,
                data_entrega     TEXT,
                FOREIGN KEY (pedido_id) REFERENCES pedidos(id) ON DELETE CASCADE
            )
        """;

        // Executar criação das tabelas na ordem correta (respeitando dependências)
        stmt.executeUpdate(sqlUsuario);
        stmt.executeUpdate(sqlCliente);
        stmt.executeUpdate(sqlAnunciantes);
        stmt.executeUpdate(sqlProdutos);
        stmt.executeUpdate(sqlCarrinho);
        stmt.executeUpdate(sqlCarrinhoProdutos);
        stmt.executeUpdate(sqlPedidos);
        stmt.executeUpdate(sqlPedidoItens);
        stmt.executeUpdate(sqlLogistica);

        stmt.close();
    }

    public static boolean executarSql(String sql) {
        Connection conn = null;
        Statement stmt = null;
        
        try {
            conn = conectar();
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            return true;
            
        } catch (SQLException e) {
            System.out.println("Erro ao executar SQL: " + e.getMessage());
            e.printStackTrace();
            return false;
            
        } finally {
            // Sempre fechar recursos
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
    }

    public static ResultSet executarQuery(String sql, Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        return stmt.executeQuery(sql);
        // ATENÇÃO: Quem chamar este método precisa fechar o Statement e ResultSet!
    }
    
    // Método alternativo mais seguro que fecha automaticamente
    public static void executarQueryComCallback(String sql, ResultSetCallback callback) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = conectar();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            callback.processar(rs);
            
        } catch (SQLException e) {
            System.out.println("Erro ao executar query: " + e.getMessage());
            e.printStackTrace();
            
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
    }
    
    // Interface funcional para processar ResultSet
    @FunctionalInterface
    public interface ResultSetCallback {
        void processar(ResultSet rs) throws SQLException;
    }
}