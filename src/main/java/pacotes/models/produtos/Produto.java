package pacotes.models.produtos ;

public class Produto {
    private int id;
    private int anuncianteId;
    private String nome;
    private String descricao;
    private String categoria;
    private double preco;
    private int quantidadeEstoque;
    private String imagemUrl;
    private int ativo;

    public Produto(int id, int anuncianteId, String nome, String descricao, String categoria,
                   double preco, int quantidadeEstoque, String imagemUrl, int ativo) {
        this.id = id;
        this.anuncianteId = anuncianteId;
        this.nome = nome;
        this.descricao = descricao;
        this.categoria = categoria;
        this.preco = preco;
        this.quantidadeEstoque = quantidadeEstoque;
        this.imagemUrl = imagemUrl;
        this.ativo = ativo;
    }

    // Getters
    public int getId() {
        return id;
    }

    public int getAnuncianteId() {
        return anuncianteId;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getCategoria() {
        return categoria;
    }

    public double getPreco() {
        return preco;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public String getImagemUrl() {
        return imagemUrl;
    }

    public int getAtivo() {
        return ativo;
    }

    // Setters
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public void setImagemUrl(String imagemUrl) {
        this.imagemUrl = imagemUrl;
    }

    public void setAtivo(int ativo) {
        this.ativo = ativo;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", anuncianteId=" + anuncianteId +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", categoria='" + categoria + '\'' +
                ", preco=" + preco +
                ", quantidadeEstoque=" + quantidadeEstoque +
                ", imagemUrl='" + imagemUrl + '\'' +
                ", ativo=" + ativo +
                '}';
    }

    public double calcularPrecoTotal(int i) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'calcularPrecoTotal'");
    }
}