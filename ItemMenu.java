package com.mycompany.restaurante;

public class ItemMenu {
    private String nome;
    private String categoria;
    private double preco;

    public ItemMenu(String nome, String categoria, double preco) {
        this.nome = nome;
        this.categoria = categoria;
        this.preco = preco;
    }

    public String getDetalhesItem() {
        return "Nome: " + nome + " | Categoria: " + categoria + " | Preço: R$" + preco;
    }

    public double getPreco() {
        return preco;
    }
}