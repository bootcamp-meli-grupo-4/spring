package com.bootcamp.restaurante.dto.pedido;

import com.bootcamp.restaurante.dto.prato.PratoDTO;

public class PedidoDTO {
    private Integer id;

    private PratoDTO prato;

    private Integer quantidade;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PratoDTO getPrato() {
        return prato;
    }

    public void setPrato(PratoDTO prato) {
        this.prato = prato;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
