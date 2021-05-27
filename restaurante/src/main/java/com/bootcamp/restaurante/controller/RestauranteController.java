package com.bootcamp.restaurante.controller;

import com.bootcamp.restaurante.entity.Restaurante;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restaurante")
public class RestauranteController {

    @GetMapping("/saldo")
    public ResponseEntity<Restaurante> obterSaldo() {
        return new ResponseEntity<>(new Restaurante(), HttpStatus.OK);
    }
}
