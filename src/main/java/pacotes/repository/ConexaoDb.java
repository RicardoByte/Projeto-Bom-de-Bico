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

         String sqlUsuario = """
            CREATE TABLE IF NOT EXISTS usuario (
                id               INTEGER PRIMARY KEY AUTOINCREMENT,
                nome   TEXT,
                email  TEXT,
                senha   TEXT,
                telefone   TEXT,
                endereco     TEXT,
                ativo    INTEGER DEFAULT 1,
                pfp_url TEXT,
                data_cadastro TEXT
            )
        """;


        String sqlCliente = """
            CREATE TABLE IF NOT EXISTS cliente (
                cpf     TEXT PRIMARY KEY NOT NULL,
                usuario_id        INTEGER NOT NULL,
                FOREIGN KEY (usuario_id) REFERENCES usuario(id) ON DELETE CASCADE
            )
        """;

        String sqlAnunciantes = """
            CREATE TABLE IF NOT EXISTS anunciantes (
                usuario_id        INTEGER PRIMARY KEY NOT NULL,
                cnpj     TEXT UNIQUE,
                cpf      TEXT UNIQUE,
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
                FOREIGN KEY (cliente_id) REFERENCES cliente(id) ON DELETE CASCADE,
                FOREIGN KEY (produto_id) REFERENCES produtos(id) ON DELETE CASCADE
            )
        """;

         String sqlCarrinhoProdutos = """
            CREATE TABLE IF NOT EXISTS carrinho_produtos (
                id         INTEGER PRIMARY KEY AUTOINCREMENT,
                carrinho_id     INTEGER NOT NULL,
                produto_id INTEGER NOT NULL,
                quantidade INTEGER NOT NULL,

                FOREIGN KEY (carrinho_id) REFERENCES carrinho(id) ON DELETE CASCADE,
                FOREIGN KEY (produto_id) REFERENCES produtos(id) ON DELETE CASCADE
            )
        """;

        String sqlPedidos = """
            CREATE TABLE IF NOT EXISTS pedidos (
                id             INTEGER PRIMARY KEY AUTOINCREMENT,
                carrinho_id     INTEGER NOT NULL,
                status         TEXT    NOT NULL,
                data_pedido    TEXT    NOT NULL,
                FOREIGN KEY (carrinho_id) REFERENCES carrinho(id) ON DELETE CASCADE
            )
        """;

        String sqlLogistica = """
            CREATE TABLE IF NOT EXISTS logistica (
                id               INTEGER PRIMARY KEY AUTOINCREMENT,
                pedido_id        INTEGER NOT NULL,
                transportadora   TEXT,
                codigo_rastreio  TEXT NOT NULL,
                status_entrega   TEXT NOT NULL,
                data_envio       TEXT NOT NULL,
                data_entrega     TEXT,
                FOREIGN KEY (pedido_id) REFERENCES pedidos(id) ON DELETE CASCADE
            )
        """;

        stmt.executeUpdate(sqlUsuario);
        stmt.executeUpdate(sqlAnunciantes);
        stmt.executeUpdate(sqlCliente);
        stmt.executeUpdate(sqlProdutos);
        stmt.executeUpdate(sqlCarrinho);
        stmt.executeUpdate(sqlCarrinhoProdutos);
        stmt.executeUpdate(sqlPedidos);
        stmt.executeUpdate(sqlLogistica);

        stmt.close();
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