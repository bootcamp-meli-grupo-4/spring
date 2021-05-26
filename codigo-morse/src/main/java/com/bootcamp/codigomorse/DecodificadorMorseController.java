package com.bootcamp.codigomorse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/decodificador")
public class DecodificadorMorseController {

    @Autowired
    private DecodificadorService decodificadorService;

    @GetMapping("/morse/{texto}")
    public String decodificadorMorse(@PathVariable String texto) {
        return decodificadorService.decodificadorMorse(texto);
    }
}
