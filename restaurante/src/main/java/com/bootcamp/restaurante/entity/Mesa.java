package com.bootcamp.restaurante.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Mesa {
    private Integer id;

    private List<Pedido> pedidos;

    public Mesa() {
        pedidos = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public Double getDebito() {
        return pedidos.stream()
                .map(pedido -> pedido.getPrato().getPreco() * pedido.getQuantidade())
                .reduce(Double::sum).orElse(0.);
    }
}
