package model;

public class Produto {
    private String sku;
    private String nome;
    private String categoria;
    private double preco;
    private int estoque;
    private int vendidos = 0;
    
    public Produto(String sku, String nome, String categoria, double preco, int estoque) {
        this.sku = sku;
        this.nome = nome;
        this.categoria = categoria;
        this.preco = preco;
        this.estoque = estoque;
    }

    public String getSku() { return sku; }
    
    public String getNome() { return nome; }
    public String getCategoria() { return categoria; }
    public double getPreco() { return preco; }
    public int getVendidos() { return vendidos; }
    public int getEstoque() { return estoque; }

    public void reduzirEstoque(int qtd) {
        estoque -= qtd;
    }

    public void aumentarEstoque(int qtd) {
        estoque += qtd;
    }
    
    public void registrarVenda(int qtd) {
        vendidos += qtd;
    }
    
}