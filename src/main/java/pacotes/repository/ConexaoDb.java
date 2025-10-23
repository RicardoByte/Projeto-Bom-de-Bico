package pacotes.repository;

import java.sql.*;

public class ConexaoDb {
    private static final String URL = "jdbc:sqlite:D:\\Projeto BBC\\BancoDBB.db";

    
    public static Connection conectar() throws SQLException {
        Connection con = DriverManager.getConnection(URL); // Abre a conexão com o banco a partir da URL fornecida
        criarTabelas(con); // Cria as tabelas do banco caso não existam
        return con; // Retorna a conexão ativa
    }

    public static void criarTabelas(Connection con) throws SQLException {
        // Statement é usado para enviar comandos SQL ao banco
        Statement stmt = con.createStatement();

        String sqlAnunciantes = """
            CREATE TABLE IF NOT EXISTS anunciantes (
                id       INTEGER PRIMARY KEY AUTOINCREMENT,
                nome     TEXT    NOT NULL,
                email    TEXT    NOT NULL UNIQUE,
                cnpj     TEXT    NOT NULL UNIQUE,
                telefone TEXT,
                endereco TEXT,
                ativo    INTEGER DEFAULT 1
            )
        """;

        String sqlProdutos = """
            CREATE TABLE IF NOT EXISTS produtos (
                id                 INTEGER PRIMARY KEY AUTOINCREMENT,
                anunciante_id      INTEGER NOT NULL,
                nome               TEXT    NOT NULL,
                descricao          TEXT,
                categoria          TEXT,
                preco              REAL    NOT NULL,
                quantidade_estoque INTEGER DEFAULT 0,
                imagem_url         TEXT,
                ativo              INTEGER DEFAULT 1,
                FOREIGN KEY (anunciante_id) REFERENCES anunciantes(id) ON DELETE CASCADE
            )
        """;

        String sqlCarrinho = """
            CREATE TABLE IF NOT EXISTS carrinho (
                id         INTEGER PRIMARY KEY AUTOINCREMENT,
                cliente_id INTEGER NOT NULL,
                produto_id INTEGER NOT NULL,
                quantidade INTEGER NOT NULL,
                FOREIGN KEY (produto_id) REFERENCES produtos(id) ON DELETE CASCADE
            )
        """;

        String sqlPedidos = """
            CREATE TABLE IF NOT EXISTS pedidos (
                id             INTEGER PRIMARY KEY AUTOINCREMENT,
                cliente_id     INTEGER NOT NULL,
                anunciante_id  INTEGER NOT NULL,
                produto_id     INTEGER NOT NULL,
                quantidade     INTEGER NOT NULL,
                valor_total    REAL    NOT NULL,
                status         TEXT    NOT NULL,
                data_pedido    TEXT    NOT NULL,
                FOREIGN KEY (anunciante_id) REFERENCES anunciantes(id) ON DELETE CASCADE,
                FOREIGN KEY (produto_id) REFERENCES produtos(id) ON DELETE CASCADE
            )
        """;

        String sqlLogistica = """
            CREATE TABLE IF NOT EXISTS logistica (
                id               INTEGER PRIMARY KEY AUTOINCREMENT,
                pedido_id        INTEGER NOT NULL,
                transportadora   TEXT,
                codigo_rastreio  TEXT,
                status_entrega   TEXT,
                data_envio       TEXT,
                data_entrega     TEXT,
                FOREIGN KEY (pedido_id) REFERENCES pedidos(id) ON DELETE CASCADE
            )
        """;

        stmt.executeUpdate(sqlAnunciantes);
        stmt.executeUpdate(sqlProdutos);
        stmt.executeUpdate(sqlCarrinho);
        stmt.executeUpdate(sqlPedidos);
        stmt.executeUpdate(sqlLogistica);
    }

    public static boolean executarSql(String sql) {
        try {
            // Abre a conexão
            Connection conn = conectar();

            // Cria um Statement simples
            Statement stmt = conn.createStatement();

            // Executa o comando
            stmt.executeUpdate(sql);

            conn.close();

            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao executar SQL: " + e.getMessage());
        }

        return false;
    }

 
    public static ResultSet executarQuery(String sql, Connection conn) throws SQLException {
        // Cria o Statement
        Statement stmt = conn.createStatement();

        return stmt.executeQuery(sql);
    }
}