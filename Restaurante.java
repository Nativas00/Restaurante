package com.mycompany.restaurante;

import java.util.Scanner;
import java.util.ArrayList;

public class Restaurante {
    private static ArrayList<Cliente> clientes = new ArrayList<>();
    private static ArrayList<ItemMenu> menu = new ArrayList<>();
    private static ArrayList<Pedido> pedidos = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        iniciarMenu();

        while (true) {
            System.out.println("\n--- Sabor Gourmet ---");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Visualizar Menu");
            System.out.println("3. Fazer Pedido");
            System.out.println("4. Acompanhar Pedido");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarCliente(scanner);
                    break;
                case 2:
                    visualizarMenu();
                    break;
                case 3:
                    fazerPedido(scanner);
                    break;
                case 4:
                    acompanharPedido(scanner);
                    break;
                case 5:
                    System.out.println("Encerrando o sistema...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private static void iniciarMenu() {
        menu.add(new ItemMenu("1)Hambúrguer", "Lanche", 20.0));
        menu.add(new ItemMenu("2)Pizza", "Pizza", 40.0));
        menu.add(new ItemMenu("3)Salada", "Salada", 15.0));
        menu.add(new ItemMenu("4)Refrigerante", "Bebida", 5.0));
        menu.add(new ItemMenu("5)Suco", "Bebida", 7.0));
    }

    private static void cadastrarCliente(Scanner scanner) {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();

        Cliente cliente = new Cliente(nome, endereco, telefone);
        clientes.add(cliente);
        System.out.println("Cliente cadastrado com sucesso!");
    }

    private static void visualizarMenu() {
        System.out.println("\n--- Menu ---");
        for (ItemMenu item : menu) {
            System.out.println(item.getDetalhesItem());
        }
    }

    private static void fazerPedido(Scanner scanner) {
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado. Cadastre um cliente primeiro.");
            return;
        }

        System.out.println("Clientes cadastrados:");
        for (int i = 0; i < clientes.size(); i++) {
            System.out.println((i + 1) + ". " + clientes.get(i).getNome());
        }

        System.out.print("Selecione o número do cliente: ");
        int clienteIndex = scanner.nextInt() - 1;
        if (clienteIndex < 0 || clienteIndex >= clientes.size()) {
            System.out.println("Cliente inválido.");
            return;
        }

        Cliente cliente = clientes.get(clienteIndex);
        Pedido pedido = new Pedido(cliente);

        while (true) {
            visualizarMenu();
            System.out.print("Selecione o número do item para adicionar ao pedido (0 para finalizar): ");
            int itemIndex = scanner.nextInt() - 1;
            if (itemIndex == -1) {
                break;
            }
            if (itemIndex < 0 || itemIndex >= menu.size()) {
                System.out.println("Item inválido.");
                continue;
            }
            pedido.adicionarItem(menu.get(itemIndex));
        }

        pedidos.add(pedido);
        pedido.visualizarPedido();
    }

    private static void acompanharPedido(Scanner scanner) {
        if (pedidos.isEmpty()) {
            System.out.println("Nenhum pedido realizado.");
            return;
        }

        for (int i = 0; i < pedidos.size(); i++) {
            System.out.println((i + 1) + ". Pedido de " + pedidos.get(i).getCliente().getNome() + " - Status: " + pedidos.get(i).getStatus());
        }

        System.out.print("Selecione o número do pedido para atualizar o status (0 para sair): ");
        int pedidoIndex = scanner.nextInt() - 1;
        if (pedidoIndex == -1) {
            return;
        }
        if (pedidoIndex < 0 || pedidoIndex >= pedidos.size()) {
            System.out.println("Pedido inválido.");
            return;
        }

        Pedido pedido = pedidos.get(pedidoIndex);
        System.out.println("Status atual: " + pedido.getStatus());
        System.out.println("1. Em preparação");
        System.out.println("2. Pronto para entrega");
        System.out.println("3. Entregue");
        System.out.print("Escolha o novo status: ");
        int status = scanner.nextInt();

        switch (status) {
            case 1:
                pedido.atualizarStatus("Em preparação");
                break;
            case 2:
                pedido.atualizarStatus("Pronto para entrega");
                break;
            case 3:
                pedido.atualizarStatus("Entregue");
                break;
            default:
                System.out.println("Status inválido.");
        }

        pedido.visualizarPedido();
    }
}
