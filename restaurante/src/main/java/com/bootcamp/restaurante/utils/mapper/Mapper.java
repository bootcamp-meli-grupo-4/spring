package com.bootcamp.restaurante.utils.mapper;

import com.bootcamp.restaurante.dto.mesa.MesaDTO;
import com.bootcamp.restaurante.dto.pedido.PedidoDTO;
import com.bootcamp.restaurante.dto.prato.PratoDTO;
import com.bootcamp.restaurante.entity.Mesa;
import com.bootcamp.restaurante.entity.Pedido;
import com.bootcamp.restaurante.entity.Prato;

import java.util.stream.Collectors;

public class Mapper {

    public static PedidoDTO convertFrom(Pedido pedido) {
        PedidoDTO pedidoDTO = new PedidoDTO();
        pedidoDTO.setId(pedido.getId());
        pedidoDTO.setPrato(convertFrom(pedido.getPrato()));
        pedidoDTO.setQuantidade(pedido.getQuantidade());
        return pedidoDTO;
    }

    public static MesaDTO convertFrom(Mesa mesa) {
        MesaDTO mesaDTO = new MesaDTO();
        mesaDTO.setDebito(mesa.getDebito());
        mesaDTO.setId(mesa.getId());
        mesaDTO.setPedidos(mesa.getPedidos().stream().map(Mapper::convertFrom).collect(Collectors.toList()));
        return mesaDTO;
    }

    public static PratoDTO convertFrom(Prato prato) {
        PratoDTO pratoDTO = new PratoDTO();
        pratoDTO.setId(prato.getId());
        pratoDTO.setDescricao(prato.getDescricao());
        pratoDTO.setPreco(prato.getPreco());
        return pratoDTO;
    }

}
