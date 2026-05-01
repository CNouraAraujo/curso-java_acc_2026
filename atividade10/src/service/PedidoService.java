package service;

import java.util.*;

import exception.*;
import model.*;

public class PedidoService {

    public void reservarEstoque(Pedido pedido) {
        for (ItemPedido item : pedido.getItens()) {
            Produto p = item.getProduto();

            if (p.getEstoque() < item.getQuantidade()) {
                throw new EstoqueInsuficienteException(p.getNome());
            }

            p.reduzirEstoque(item.getQuantidade());
        }

        pedido.setStatus(StatusPedido.RESERVED);
    }

    public void pagarPedido(Pedido pedido) {
    	if (pedido.getStatus() != StatusPedido.RESERVED) {
    	    throw new PedidoInvalidoException("Pedido precisa estar reservado antes do pagamento.");
    	}

        for (ItemPedido item : pedido.getItens()) {
            item.getProduto().registrarVenda(item.getQuantidade());
        }

        pedido.setStatus(StatusPedido.PAID);
    }

    public void cancelarPedido(Pedido pedido) {
        for (ItemPedido item : pedido.getItens()) {
            item.getProduto().aumentarEstoque(item.getQuantidade());
        }

        pedido.setStatus(StatusPedido.FAILED);
    }

    // ✔ FATURAMENTO POR CATEGORIA
    public Map<String, Double> faturamentoPorCategoria(List<Pedido> pedidos) {
        Map<String, Double> mapa = new HashMap<>();

        for (Pedido p : pedidos) {
            if (p.getStatus() != StatusPedido.PAID) continue;

            for (ItemPedido item : p.getItens()) {
                String cat = item.getProduto().getCategoria();
                double valor = item.getSubtotal();

                mapa.put(cat, mapa.getOrDefault(cat, 0.0) + valor);
            }
        }

        return mapa;
    }

    // ✔ TOP 3 PRODUTOS
    public List<Produto> top3Produtos(Collection<Produto> produtos) {
        List<Produto> lista = new ArrayList<>(produtos);

        lista.sort((a, b) -> b.getVendidos() - a.getVendidos());

        return lista.subList(0, Math.min(3, lista.size()));
    }

    // ✔ CLIENTES COM MAIS PEDIDOS
    public Map<String, Integer> clientesMaisPedidos(List<Pedido> pedidos) {
        Map<String, Integer> mapa = new HashMap<>();

        for (Pedido p : pedidos) {
            String nome = p.getCliente().getNome();
            mapa.put(nome, mapa.getOrDefault(nome, 0) + 1);
        }

        return mapa;
    }
}