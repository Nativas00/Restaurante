package com.mycompany.restaurante;

import java.util.ArrayList;

public class Pedido {
    private Cliente cliente;
    private ArrayList<ItemMenu> itens;
    private double total;
    private String status;

    public Pedido(Cliente cliente) {
        this.cliente = cliente;
        this.itens = new ArrayList<>();
        this.status = "Em preparação";
        this.total = 0;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void adicionarItem(ItemMenu item) {
        itens.add(item);
        calcularTotal();
    }

    public void calcularTotal() {
        total = 0;
        for (ItemMenu item : itens) {
            total += item.getPreco();
        }
    }

    public void atualizarStatus(String novoStatus) {
        this.status = novoStatus;
    }

    public void visualizarPedido() {
        System.out.println("Pedido de " + cliente.getNome() + ":");
        for (ItemMenu item : itens) {
            System.out.println(item.getDetalhesItem());
        }
        System.out.println("Total: R$" + total);
        System.out.println("Status: " + status);
    }

    public String getStatus() {
        return status;
    }
}
