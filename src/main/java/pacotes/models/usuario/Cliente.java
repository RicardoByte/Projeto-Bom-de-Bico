package pacotes.models.usuario;

public class Cliente extends Usuario {
    private String cpf;

    // Construtor completo
    public Cliente(int id, String nome, String email, String senha,
                   String telefone, String endereco, int ativo,
                   String cpf) {
        super(id, nome, email, senha, telefone, endereco, "cliente", ativo);
        this.cpf = cpf;
    }

    // Construtor para novo cliente (sem ID)
    public Cliente(String nome, String email, String senha,
                   String telefone, String endereco,
                   String cpf) {
        super(0, nome, email, senha, telefone, endereco, 1, "cliente", null);
        this.cpf = cpf;
    }

    // Getters e Setters
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + getId() +
                ", nome='" + getNome() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", telefone='" + getTelefone() + '\'' +
                ", endereco='" + getEndereco() + '\'' +
                ", cpf='" + cpf + '\'' +
                ", ativo=" + getAtivo() +
                '}';
    }
}