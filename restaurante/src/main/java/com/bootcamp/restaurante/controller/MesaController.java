package com.bootcamp.restaurante.controller;

import com.bootcamp.restaurante.dto.mesa.CriarMesaDTO;
import com.bootcamp.restaurante.dto.mesa.MesaDTO;
import com.bootcamp.restaurante.dto.pedido.RealizarPedidoDTO;
import com.bootcamp.restaurante.entity.Mesa;
import com.bootcamp.restaurante.repository.MesaRepository;
import com.bootcamp.restaurante.repository.PedidoRepository;
import com.bootcamp.restaurante.utils.mapper.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/mesa")
public class MesaController {

    private final MesaRepository mesaRepository;

    private final PedidoRepository pedidoRepository;

    public MesaController(MesaRepository mesaRepository, PedidoRepository pedidoRepository) {
        this.mesaRepository = mesaRepository;
        this.pedidoRepository = pedidoRepository;
    }

    @GetMapping
    public ResponseEntity<List<MesaDTO>> obterMesas() {
        List<Mesa> mesas = mesaRepository.getAll();
        List<MesaDTO> mesasResultado = mesas.stream().map(Mapper::convertFrom).collect(Collectors.toList());
        return new ResponseEntity<>(mesasResultado, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MesaDTO> obterMesaPorId(@PathVariable Integer id) {
        Optional<Mesa> mesaOp = mesaRepository.findById(id);

        if(mesaOp.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        Mesa mesa = mesaOp.get();

        return new ResponseEntity<>(Mapper.convertFrom(mesa), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MesaDTO> criarMesa(@RequestBody CriarMesaDTO payload) {
        Mesa mesa = mesaRepository.save(payload);
        if(mesa != null) {
            MesaDTO mesaDTO = Mapper.convertFrom(mesa);
            return new ResponseEntity<>(mesaDTO, HttpStatus.CREATED);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/{id}/pedido")
    public ResponseEntity<MesaDTO> realizarPedido(@PathVariable Integer id, @RequestBody RealizarPedidoDTO payload) {
        Optional<Mesa> mesaOp = mesaRepository.findById(id);
        if(mesaOp.isPresent()) {
            Mesa mesa = mesaOp.get();
            pedidoRepository.save(payload, mesa.getId());
            mesa.setPedidos(pedidoRepository.findByMesaId(mesa.getId()));
            MesaDTO mesaDTO = Mapper.convertFrom(mesa);
            return new ResponseEntity<>(mesaDTO, HttpStatus.CREATED);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}/pagar")
    public ResponseEntity realizarPagamento(@PathVariable Integer id) {
        Optional<Mesa> mesaOp = mesaRepository.findById(id);
        if(mesaOp.isPresent()) {
            Mesa mesa = mesaOp.get();
            mesaRepository.delete(mesa.getId());
        }

        return new ResponseEntity(HttpStatus.OK);
    }

}
