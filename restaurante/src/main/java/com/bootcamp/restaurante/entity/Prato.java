package com.bootcamp.restaurante.entity;

public class Prato {
    private Integer Id;

    private Double preco;

    private String descricao;

    public Prato() { }

    public Prato(Integer id, Double preco, String descricao) {
        Id = id;
        this.preco = preco;
        this.descricao = descricao;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
}
