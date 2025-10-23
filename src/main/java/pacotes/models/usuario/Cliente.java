package pacotes.models.usuario;

public class Cliente extends Usuario {
    
    private String cpf;
    private String endereco;
    
    public Cliente() {
        super();
    }
    
    public Cliente(String nome, String email, String senha, String telefone, String cpf) {
        super(nome, email, senha, telefone);
        this.cpf = cpf;
    }
    
    // Getters e Setters
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    
    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }
    
    @Override
    public String toString() {
        return "Cliente{id=%d, nome='%s', email='%s', cpf='%s'}"
            .formatted(id, nome, email, cpf);
    }
}