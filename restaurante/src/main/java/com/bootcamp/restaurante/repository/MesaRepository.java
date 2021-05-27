package com.bootcamp.restaurante.repository;

import com.bootcamp.restaurante.dto.mesa.CriarMesaDTO;
import com.bootcamp.restaurante.entity.Mesa;
import com.bootcamp.restaurante.entity.Restaurante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.locks.ReentrantLock;

@Repository
public class MesaRepository {

    public Map<Integer, Mesa> mesas = new HashMap<>();

    private final ReentrantLock lock = new ReentrantLock();

    private PedidoRepository pedidoRepository;

    public Optional<Mesa> findById(Integer id) {
        return Optional.ofNullable(mesas.getOrDefault(id, null));
    }

    @Autowired
    public void setPedidoRepository(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public Mesa save(CriarMesaDTO payload) {
        lock.lock();
        try {
            Mesa mesa = new Mesa();
            mesa.setId(payload.getId());
            if(!mesas.containsKey(payload.getId())) {
                mesas.put(payload.getId(), mesa);
                return mesa;
            }
        } finally {
            lock.unlock();
        }

        throw new RuntimeException("Mesa já existe");
    }

    public List<Mesa> getAll() {
        return new ArrayList<>(mesas.values());
    }

    public Mesa delete(Integer id) {
        lock.lock();
        Mesa mesa = null;
        try {
            if (mesas.containsKey(id)) {
                mesa = mesas.get(id);
                mesa.getPedidos().forEach(pedido -> pedidoRepository.delete(pedido.getId()));
                mesas.remove(id);
                Restaurante.adicionarSaldo(mesa.getDebito());
            }
        } finally {
            lock.unlock();
        }

        return mesa;
    }

}
