package com.bootcamp.restaurante.entity;

public class Pedido {
    private Integer Id;

    private Mesa mesa;

    private Prato prato;

    private Integer quantidade;

    public Pedido() { }

    public Pedido(Integer id, Mesa mesa, Prato prato, Integer quantidade) {
        Id = id;
        this.mesa = mesa;
        this.prato = prato;
        this.quantidade = quantidade;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Prato getPrato() {
        return prato;
    }

    public void setPrato(Prato prato) {
        this.prato = prato;
    }
}
