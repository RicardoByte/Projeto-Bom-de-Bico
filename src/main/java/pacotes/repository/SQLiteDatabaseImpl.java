package pacotes.repository;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLiteDatabaseImpl implements Database {

    private String dbPath;
    private String dbUrl;

    public SQLiteDatabaseImpl() {
        String userHome = System.getProperty("user.home");
        File dbDir = new File(userHome, ".meuapp");
        
        if (!dbDir.exists()) {
            dbDir.mkdirs();
        }
        
        this.dbPath = dbDir.getAbsolutePath() + File.separator + "dados.db";
        this.dbUrl = "jdbc:sqlite:" + this.dbPath;
    }

    @Override
    public boolean bancoExiste() {
        File dbFile = new File(this.dbPath);
        return dbFile.exists() && dbFile.length() > 0; 
    }

    @Override
    public void criarBanco() throws SQLException {
        try (Connection conn = DriverManager.getConnection(this.dbUrl);
             Statement stmt = conn.createStatement()) {

            String sqlTabelaUsuarios = "CREATE TABLE IF NOT EXISTS usuarios (" +
                                       " id INTEGER PRIMARY KEY AUTOINCREMENT," +
                                       " nome TEXT NOT NULL," +
                                       " email TEXT UNIQUE" +
                                       ");";
            
            String sqlTabelaProdutos = "CREATE TABLE IF NOT EXISTS produtos (" +
                                        " id INTEGER PRIMARY KEY AUTOINCREMENT," +
                                        " nome TEXT NOT NULL," +
                                        " preco REAL" +
                                        ");";

            stmt.execute(sqlTabelaUsuarios);
            stmt.execute(sqlTabelaProdutos);

            System.out.println("Banco e tabelas verificados/criados com sucesso em: " + this.dbPath);

        } catch (SQLException e) {
            System.err.println("Erro ao criar o banco de dados: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public void salvar() throws SQLException {
        System.out.println("Método salvar() chamado.");
    }

    @Override
    public void atualizar() throws SQLException {
        System.out.println("Método atualizar() chamado.");
    }

    @Override
    public void deletar() throws SQLException {
        System.out.println("Método deletar() chamado.");
    }

    @Override
    public void carregar(int id) throws SQLException {
        System.out.println("Método carregar() chamado com id: " + id);
    }
}