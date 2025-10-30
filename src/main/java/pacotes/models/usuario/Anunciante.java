package pacotes.models.usuario;


public class Anunciante extends Usuario {
    private String cnpj;
    private String cpf;

    // Construtor completo
    public Anunciante(int id, String nome, String email, String senha,
                      String telefone, String endereco, int ativo,
                      String cnpj, String cpf) {
        super(id, nome, email, senha, telefone, endereco, "anunciante", ativo);
        this.cnpj = cnpj;
        this.cpf = cpf;
    }

    // Construtor para novo anunciante (sem ID)
    public Anunciante(String nome, String email, String senha,
                      String telefone, String endereco,
                      String cnpj, String cpf) {
        super(0, nome, email, senha, telefone, endereco, 1, "anunciante", null);
        this.cnpj = cnpj;
        this.cpf = cpf;
    }

    // Getters e Setters
    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    // Métodos auxiliares
    public boolean isPessoaJuridica() {
        return cnpj != null && !cnpj.isEmpty();
    }

    public boolean isPessoaFisica() {
        return cpf != null && !cpf.isEmpty();
    }

    @Override
    public String toString() {
        return "Anunciante{" +
                "id=" + getId() +
                ", nome='" + getNome() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", telefone='" + getTelefone() + '\'' +
                ", endereco='" + getEndereco() + '\'' +
                ", cnpj='" + cnpj + '\'' +
                ", cpf='" + cpf + '\'' +
                ", ativo=" + getAtivo() +
                '}';
    }
}