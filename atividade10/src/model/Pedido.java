package model;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private int id;
    private Cliente cliente;
    private List<ItemPedido> itens = new ArrayList<>();
    private StatusPedido status = StatusPedido.CREATED;

    public Pedido(int id, Cliente cliente) {
        this.id = id;
        this.cliente = cliente;
    }

    public void adicionarItem(ItemPedido item) {
        itens.add(item);
    }
    
    public int getId() { 
    	return id; 
    }
    
    public double getTotal() {
        double total = 0;
        for (ItemPedido i : itens) total += i.getSubtotal();
        return total;
    }

    public List<ItemPedido> getItens() { return itens; }
    public StatusPedido getStatus() { return status; }
    public void setStatus(StatusPedido status) { this.status = status; }
    public Cliente getCliente() { return cliente; }
}