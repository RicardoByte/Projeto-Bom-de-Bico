package pacotes.models.usuario;

public class Anunciante extends Usuario {
    
    private String cnpj;
    private String nomeFantasia;
    private String descricao;
    private Double avaliacaoMedia;
    
    public Anunciante() {
        super();
        this.avaliacaoMedia = 0.0;
    }
    
    public Anunciante(String nome, String email, String senha, String telefone, 
                      String cnpj, String nomeFantasia) {
        super(nome, email, senha, telefone);
        this.cnpj = cnpj;
        this.nomeFantasia = nomeFantasia;
        this.avaliacaoMedia = 0.0;
    }
    
    // Getters e Setters
    public String getCnpj() { return cnpj; }
    public void setCnpj(String cnpj) { this.cnpj = cnpj; }
    
    public String getNomeFantasia() { return nomeFantasia; }
    public void setNomeFantasia(String nomeFantasia) { this.nomeFantasia = nomeFantasia; }
    
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    
    public Double getAvaliacaoMedia() { return avaliacaoMedia; }
    public void setAvaliacaoMedia(Double avaliacaoMedia) { this.avaliacaoMedia = avaliacaoMedia; }
    
    @Override
    public String toString() {
        return "Anunciante{id=%d, nome='%s', nomeFantasia='%s', cnpj='%s'}"
            .formatted(id, nome, nomeFantasia, cnpj);
    }
}