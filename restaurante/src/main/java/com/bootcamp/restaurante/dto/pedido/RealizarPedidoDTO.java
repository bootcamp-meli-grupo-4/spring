package com.bootcamp.restaurante.dto.pedido;

import java.util.List;

public class RealizarPedidoDTO {

    private List<PratoPedidoDTO> pedidos;

    public List<PratoPedidoDTO> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<PratoPedidoDTO> pedidos) {
        this.pedidos = pedidos;
    }
}
