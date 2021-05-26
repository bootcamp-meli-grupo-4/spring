package br.com.mercadolivre.exercicionumeroromanos.controller;

import br.com.mercadolivre.exercicionumeroromanos.service.ConversorNumero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/conversor")
public class ConversorController {
    @Autowired
    private ConversorNumero service;

    @GetMapping("/{numero}")
    public String converterNumero(@PathVariable Integer numero){
        return service.converter(numero);
    }
}
