package com.bootcamp.restaurante.controller;

import com.bootcamp.restaurante.dto.prato.CriarPratoDTO;
import com.bootcamp.restaurante.dto.prato.PratoDTO;
import com.bootcamp.restaurante.entity.Prato;
import com.bootcamp.restaurante.repository.PratoRepository;
import com.bootcamp.restaurante.utils.mapper.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/prato")
public class PratoController {

    private final PratoRepository pratoRepository;

    public PratoController(PratoRepository pratoRepository) {
        this.pratoRepository = pratoRepository;
    }

    @PostMapping
    public ResponseEntity<PratoDTO> criarPrato(@RequestBody CriarPratoDTO payload) {
        Prato prato = pratoRepository.save(payload);
        return new ResponseEntity<>(Mapper.convertFrom(prato), HttpStatus.CREATED);
    }

}
