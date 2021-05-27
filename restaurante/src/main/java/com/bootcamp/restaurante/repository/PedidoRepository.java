package com.bootcamp.restaurante.repository;

import com.bootcamp.restaurante.dto.pedido.RealizarPedidoDTO;
import com.bootcamp.restaurante.entity.Mesa;
import com.bootcamp.restaurante.entity.Pedido;
import com.bootcamp.restaurante.entity.Prato;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

@Repository
public class PedidoRepository {

    private final AtomicInteger contadorPedidos = new AtomicInteger(0);

    public Map<Integer, Pedido> pedidos = new HashMap<>();

    private final ReentrantLock lock = new ReentrantLock();

    private final PratoRepository pratoRepository;

    private final MesaRepository mesaRepository;

    public PedidoRepository(PratoRepository pratoRepository, MesaRepository mesaRepository) {
        this.pratoRepository = pratoRepository;
        this.mesaRepository = mesaRepository;
    }

    public Optional<Pedido> findById(Integer id) {
        return Optional.of(pedidos.getOrDefault(id, null));
    }

    public List<Pedido> save(RealizarPedidoDTO payload, Integer mesaId) {
        lock.lock();

        List<Pedido> pedidosSalvos = new ArrayList<>();
        try {
            Optional<Mesa> mesaOp = mesaRepository.findById(mesaId);

            if(mesaOp.isEmpty()) throw new RuntimeException("Mesa não existe");

            payload.getPedidos().forEach(pedido -> {

                Integer id = contadorPedidos.incrementAndGet();
                Pedido novoPedido = new Pedido();
                Optional<Prato> pratoOp = pratoRepository.findById(pedido.getPrato());

                if(pratoOp.isPresent()) {
                    novoPedido.setId(id);
                    novoPedido.setPrato(pratoOp.get());
                    novoPedido.setMesa(mesaOp.get());
                    novoPedido.setQuantidade(pedido.getQuantidade());

                    pedidos.put(id, novoPedido);

                    pedidosSalvos.add(novoPedido);
                } else {
                    System.out.println("Prato nao existe :  " +  pedido.getPrato());
                }

            });

            return pedidosSalvos;
        } finally {
            lock.unlock();
        }
    }

    public List<Pedido> getAll() {
        return new ArrayList<>(pedidos.values());
    }

    public Pedido delete(Integer id) {
        lock.lock();
        Pedido pedido = null;
        try {
            if (pedidos.containsKey(id)) {
                pedido = pedidos.get(id);
                pedidos.remove(id);
            }
        } finally {
            lock.unlock();
        }

        return pedido;
    }

    public List<Pedido> findByMesaId(Integer mesaId) {
        return pedidos.values().stream().filter(pedido -> pedido.getMesa().getId().equals(mesaId)).collect(Collectors.toList());
    }
}
