package pacotes.repository;

import java.sql.SQLException;

public interface Database {

    // --- Métodos CRUD existentes ---

    void salvar() throws SQLException;
    void atualizar() throws SQLException;
    void deletar() throws SQLException;
    void carregar(int id) throws SQLException;

    
    /**
     * Verifica se o arquivo físico do banco de dados (ex: .db) já existe.
     * * @return true se o arquivo existir, false caso contrário.
     */
    boolean bancoExiste();

    /**
     * Cria o arquivo do banco de dados e/ou suas tabelas iniciais.
     * Este método deve ser "idempotente" (seguro de rodar várias vezes),
     * geralmente usando "CREATE TABLE IF NOT EXISTS".
     *
     * @throws SQLException Se houver um erro de SQL durante a criação.
     */
    void criarBanco() throws SQLException;
}