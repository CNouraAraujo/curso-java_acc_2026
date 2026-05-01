package controller;

import java.util.*;

import model.*;
import service.PedidoService;

public class PedidoController {

    private PedidoService service = new PedidoService();

    public void reservar(Pedido p) {
        try {
            service.reservarEstoque(p);
            System.out.println("Estoque reservado.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void pagar(Pedido p) {
        try {
            service.pagarPedido(p);
            System.out.println("Pedido pago.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void relatorios(List<Pedido> pedidos, Map<String, Produto> produtos) {

        System.out.println("\n=== TOP 3 PRODUTOS ===");
        for (Produto p : service.top3Produtos(produtos.values())) {
            System.out.println(p.getNome() + " - vendidos: " + p.getVendidos());
        }

        System.out.println("\n=== FATURAMENTO POR CATEGORIA ===");
        Map<String, Double> fat = service.faturamentoPorCategoria(pedidos);
        for (String cat : fat.keySet()) {
            System.out.println(cat + ": " + fat.get(cat));
        }

        System.out.println("\n=== CLIENTES COM MAIS PEDIDOS ===");
        Map<String, Integer> cli = service.clientesMaisPedidos(pedidos);
        for (String nome : cli.keySet()) {
            System.out.println(nome + ": " + cli.get(nome));
        }
    }
}