package com.bootcamp.restaurante.dto.mesa;

import com.bootcamp.restaurante.dto.pedido.PedidoDTO;

import java.util.List;

public class MesaDTO {
    private Integer id;

    private List<PedidoDTO> pedidos;

    private Double debito;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<PedidoDTO> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<PedidoDTO> pedidos) {
        this.pedidos = pedidos;
    }

    public Double getDebito() {
        return debito;
    }

    public void setDebito(Double debito) {
        this.debito = debito;
    }
}
