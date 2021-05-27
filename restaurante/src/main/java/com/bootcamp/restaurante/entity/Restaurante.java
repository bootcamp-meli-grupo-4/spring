package com.bootcamp.restaurante.entity;

import java.util.concurrent.atomic.AtomicReference;

public class Restaurante {
    private static AtomicReference<Double> saldo = new AtomicReference<>(0.);

    public static void adicionarSaldo(Double saldo) {
        Restaurante.saldo.accumulateAndGet(saldo, Double::sum);
    }

    public Double getSaldo() {
        return saldo.get();
    }
}
