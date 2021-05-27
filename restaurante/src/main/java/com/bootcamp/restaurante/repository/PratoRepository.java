package com.bootcamp.restaurante.repository;

import com.bootcamp.restaurante.dto.prato.CriarPratoDTO;
import com.bootcamp.restaurante.entity.Prato;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

@Repository
public class PratoRepository {

    private final AtomicInteger contador = new AtomicInteger(0);

    public Map<Integer, Prato> pratos = new HashMap<>();

    private final ReentrantLock lock = new ReentrantLock();

    public Optional<Prato> findById(Integer id) {
        return Optional.ofNullable(pratos.getOrDefault(id, null));
    }

    public Prato save(CriarPratoDTO payload) {
        Integer id = contador.incrementAndGet();

        Prato prato = new Prato();
        prato.setId(id);
        prato.setPreco(payload.getPreco());
        prato.setDescricao(payload.getDescricao());

        pratos.put(id, prato);

        return prato;
    }

    public List<Prato> getAll() {
        return new ArrayList<>(pratos.values());
    }

    public Prato delete(Integer id) {
        lock.lock();
        Prato prato = null;
        try {
            if (pratos.containsKey(id)) {
                prato = pratos.get(id);
                pratos.remove(id);
            }
        } finally {
            lock.unlock();
        }

        return prato;
    }
}
