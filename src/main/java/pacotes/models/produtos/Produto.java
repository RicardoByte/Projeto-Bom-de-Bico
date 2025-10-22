package pacotes.models.produtos;


import java.time.LocalDateTime;

public class Produto {
    
    private Long id;
    private Long anuncianteId;
    private String nome;
    private String descricao;
    private String categoria;
    private Double preco;
    private Integer quantidadeEstoque;
    private String imagemUrl;
    private boolean ativo;
    private LocalDateTime dataCadastro;
    
    public Produto() {
        this.ativo = true;
        this.quantidadeEstoque = 0;
        this.dataCadastro = LocalDateTime.now();
    }
    
    public Produto(Long anuncianteId, String nome, String descricao, 
                   String categoria, Double preco, Integer quantidadeEstoque) {
        this();
        this.anuncianteId = anuncianteId;
        this.nome = nome;
        this.descricao = descricao;
        this.categoria = categoria;
        this.preco = preco;
        this.quantidadeEstoque = quantidadeEstoque;
    }
    
    // Métodos de negócio
    public boolean temEstoque() {
        return quantidadeEstoque > 0;
    }
    
    public boolean temEstoque(int quantidade) {
        return quantidadeEstoque >= quantidade;
    }
    
    public void adicionarEstoque(int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser positiva");
        }
        this.quantidadeEstoque += quantidade;
    }
    
    public void removerEstoque(int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser positiva");
        }
        if (quantidade > this.quantidadeEstoque) {
            throw new IllegalArgumentException("Estoque insuficiente");
        }
        this.quantidadeEstoque -= quantidade;
    }
    
    public Double calcularPrecoTotal(int quantidade) {
        return preco * quantidade;
    }
    
    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Long getAnuncianteId() { return anuncianteId; }
    public void setAnuncianteId(Long anuncianteId) { this.anuncianteId = anuncianteId; }
    
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    
    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    
    public Double getPreco() { return preco; }
    public void setPreco(Double preco) { this.preco = preco; }
    
    public Integer getQuantidadeEstoque() { return quantidadeEstoque; }
    public void setQuantidadeEstoque(Integer quantidadeEstoque) { 
        this.quantidadeEstoque = quantidadeEstoque; 
    }
    
    public String getImagemUrl() { return imagemUrl; }
    public void setImagemUrl(String imagemUrl) { this.imagemUrl = imagemUrl; }
    
    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }
    
    public LocalDateTime getDataCadastro() { return dataCadastro; }
    public void setDataCadastro(LocalDateTime dataCadastro) { 
        this.dataCadastro = dataCadastro; 
    }
    
    @Override
    public String toString() {
        return "Produto{id=%d, nome='%s', preco=R$%.2f, estoque=%d, categoria='%s'}"
            .formatted(id, nome, preco, quantidadeEstoque, categoria);
    }
}