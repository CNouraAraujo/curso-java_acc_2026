package view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import controller.PedidoController;
import model.Cliente;
import model.ItemPedido;
import model.Pedido;
import model.Produto;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        PedidoController controller = new PedidoController();

        Map<String, Produto> produtos = new HashMap<>();
        List<Pedido> pedidos = new ArrayList<>();

        Cliente cliente = new Cliente(1, "Joao");

        int idPedido = 1;

        while (true) {
            try {
            	System.out.println("Olá " + cliente.getNome() + "!");
                System.out.println("\n1-Add Produto 2-Listar 3-Criar Pedido 4-Reservar 5-Pagar 6-Relatorios 0-Sair");
                int op = lerInteiro(sc, "Opcao: ");

                if (op == 0) {
                    break;
                }

                switch (op) {
                    case 1:
                        System.out.print("SKU: ");
                        String sku = sc.next();
                        System.out.print("Nome: ");
                        String nome = sc.next();
                        System.out.print("Categoria: ");
                        String cat = sc.next();
                        double preco = lerDouble(sc, "Preco: ");
                        int est = lerInteiro(sc, "Estoque: ");

                        if (preco <= 0) {
                            throw new IllegalArgumentException("O preco deve ser maior que zero.");
                        }

                        if (est < 0) {
                            throw new IllegalArgumentException("O estoque nao pode ser negativo.");
                        }

                        produtos.put(sku, new Produto(sku, nome, cat, preco, est));
                        System.out.println("Produto cadastrado com sucesso.");
                        break;

                    case 2:
                        if (produtos.isEmpty()) {
                            System.out.println("Nenhum produto cadastrado.");
                            break;
                        }

                        for (Produto p : produtos.values()) {
                            System.out.println(p.getNome() + " | Estoque: " + p.getEstoque());
                        }
                        break;

                    case 3:
                        if (produtos.isEmpty()) {
                            System.out.println("Cadastre pelo menos um produto antes de criar pedidos.");
                            break;
                        }

                        Pedido ped = new Pedido(idPedido, cliente);
                        int qtd = lerInteiro(sc, "Quantos itens? ");

                        if (qtd <= 0) {
                            throw new IllegalArgumentException("O pedido precisa ter pelo menos um item.");
                        }

                        for (int i = 0; i < qtd; i++) {
                            System.out.print("SKU: ");
                            String skuItem = sc.next();
                            Produto p = produtos.get(skuItem);

                            if (p == null) {
                                throw new IllegalArgumentException("Produto nao encontrado: " + skuItem);
                            }

                            int q = lerInteiro(sc, "Qtd: ");

                            if (q <= 0) {
                                throw new IllegalArgumentException("A quantidade deve ser maior que zero.");
                            }

                            ped.adicionarItem(new ItemPedido(p, q));
                        }

                        pedidos.add(ped);
                        idPedido++;
                        System.out.println("Pedido criado ID: " + ped.getId());
                        break;

                    case 4:
                        int idR = lerInteiro(sc, "ID Pedido: ");
                        controller.reservar(buscarPedidoPorId(pedidos, idR));
                        break;

                    case 5:
                        int idP = lerInteiro(sc, "ID Pedido: ");
                        controller.pagar(buscarPedidoPorId(pedidos, idP));
                        break;

                    case 6:
                        controller.relatorios(pedidos, produtos);
                        break;

                    default:
                        System.out.println("Opcao invalida.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada invalida. Digite um valor numerico quando solicitado.");
                sc.nextLine();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        sc.close();
    }

    private static int lerInteiro(Scanner sc, String mensagem) {
        System.out.print(mensagem);
        return sc.nextInt();
    }

    private static double lerDouble(Scanner sc, String mensagem) {
        System.out.print(mensagem);
        return sc.nextDouble();
    }

    private static Pedido buscarPedidoPorId(List<Pedido> pedidos, int id) {
        for (Pedido pedido : pedidos) {
            if (pedido.getId() == id) {
                return pedido;
            }
        }

        throw new IllegalArgumentException("Pedido nao encontrado.");
    }
}
