 package pacotes.models.usuario;
  import java.time.LocalDateTime;

public abstract class Usuario {
    
    protected Long id;
    protected String nome;
    protected String email;
    protected String senha;
    protected String telefone;
    protected boolean ativo;
    protected LocalDateTime dataCadastro;
    
    public Usuario() {
        this.ativo = true;
        this.dataCadastro = LocalDateTime.now();
    }
    
    public Usuario(String nome, String email, String senha, String telefone) {
        this();
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
    }
    
    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
    
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    
    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }
    
    public LocalDateTime getDataCadastro() { return dataCadastro; }
    public void setDataCadastro(LocalDateTime dataCadastro) { this.dataCadastro = dataCadastro; }
}