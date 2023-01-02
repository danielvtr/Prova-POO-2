package classes;

public class Produto implements Comparable <Produto>{

    private String nome; 
    private int codigoProduto;
    private int qtdEstoque;
    private int preco;

    public Produto(){}

    public Produto(int codigoProduto, String nome, int preco, int qtdEstoque){
        this.codigoProduto = codigoProduto;
        this.nome = nome;
        this.preco = preco;
        this.qtdEstoque = qtdEstoque;
    }

    
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public int getCodigoproduto() {
        return codigoProduto;
    }
    public void setCodigoproduto(int codigoProduto) {
        this.codigoProduto = codigoProduto;
    }
    public int getQtdEstoque() {
        return qtdEstoque;
    }
    public void setQtdestoque(int qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }
    public int getPreco() {
        return preco;
    }
    public void setPreco(int preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return String.format(" Código do produto: %d\n Nome do produto: %s\n Valor do produto: %d\n" + 
        "Quantidade no estoque: %d\n_____________________________________"
        ,getCodigoproduto(), getNome(), getPreco(), getQtdEstoque());
    }
    public int removerQuantidadeEstoque(int quantidade) {
        return qtdEstoque = qtdEstoque - quantidade;
    }


    public int compareTo (Produto o){

        return nome.compareTo(o.getNome());

    }

}
