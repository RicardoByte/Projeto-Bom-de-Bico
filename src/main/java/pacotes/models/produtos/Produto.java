package pacotes.models.produtos;

import java.time.LocalDateTime;

/**
 * Classe modelo que representa um produto anunciado na plataforma.
 * Versão aprimorada a nível de demonstração, mantendo compatibilidade com Double.
 */
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
    private final LocalDateTime dataCadastro;

    // ======== CONSTRUTORES ========

    public Produto() {
        this.ativo = true;
        this.quantidadeEstoque = 0;
        this.dataCadastro = LocalDateTime.now();
    }

    public Produto(Long anuncianteId, String nome, String descricao,
                   String categoria, Double preco, Integer quantidadeEstoque) {
        this();
        setAnuncianteId(anuncianteId);
        setNome(nome);
        setDescricao(descricao);
        setCategoria(categoria);
        setPreco(preco);
        setQuantidadeEstoque(quantidadeEstoque);
    }

    // ======== MÉTODOS DE NEGÓCIO ========

    public boolean temEstoque(int quantidade) {
        return quantidadeEstoque >= quantidade;
    }

    public void adicionarEstoque(int quantidade) {
        if (quantidade <= 0)
            throw new IllegalArgumentException("A quantidade deve ser positiva.");
        this.quantidadeEstoque += quantidade;
    }

    public void removerEstoque(int quantidade) {
        if (quantidade <= 0)
            throw new IllegalArgumentException("A quantidade deve ser positiva.");
        if (quantidade > this.quantidadeEstoque)
            throw new IllegalArgumentException("Estoque insuficiente.");
        this.quantidadeEstoque -= quantidade;
    }

    public Double calcularPrecoTotal(int quantidade) {
        if (quantidade <= 0)
            throw new IllegalArgumentException("Quantidade deve ser positiva.");
        return preco * quantidade;
    }

    public void ativar() {
        this.ativo = true;
    }

    public void desativar() {
        this.ativo = false;
    }

    // ======== GETTERS E SETTERS ========

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getAnuncianteId() { return anuncianteId; }
    public void setAnuncianteId(Long anuncianteId) {
        if (anuncianteId == null || anuncianteId <= 0)
            throw new IllegalArgumentException("ID do anunciante inválido.");
        this.anuncianteId = anuncianteId;
    }

    public String getNome() { return nome; }
    public void setNome(String nome) {
        if (nome == null || nome.isBlank())
            throw new IllegalArgumentException("Nome do produto não pode ser vazio.");
        this.nome = nome.trim();
    }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) {
        this.descricao = (descricao == null) ? "" : descricao.trim();
    }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) {
        this.categoria = (categoria == null) ? "Sem categoria" : categoria.trim();
    }

    public Double getPreco() { return preco; }
    public void setPreco(Double preco) {
        if (preco == null || preco < 0)
            throw new IllegalArgumentException("Preço deve ser maior ou igual a zero.");
        this.preco = preco;
    }

    public Integer getQuantidadeEstoque() { return quantidadeEstoque; }
    public void setQuantidadeEstoque(Integer quantidadeEstoque) {
        if (quantidadeEstoque == null || quantidadeEstoque < 0)
            throw new IllegalArgumentException("Estoque não pode ser negativo.");
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public String getImagemUrl() { return imagemUrl; }
    public void setImagemUrl(String imagemUrl) { this.imagemUrl = imagemUrl; }

    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }

    public LocalDateTime getDataCadastro() { return dataCadastro; }

    public String getInfo() {
    return "Anunciante: " + anuncianteId + ", Descrição: " + descricao + 
           ", Imagem: " + imagemUrl + ", Ativo: " + ativo + ", Data: " + dataCadastro;
    }
    
    @Override
    public String toString() {
        return "Produto{id=%d, nome='%s', preco=R$%.2f, estoque=%d, categoria='%s'}"
            .formatted(id, nome, preco, quantidadeEstoque, categoria);
    }
}