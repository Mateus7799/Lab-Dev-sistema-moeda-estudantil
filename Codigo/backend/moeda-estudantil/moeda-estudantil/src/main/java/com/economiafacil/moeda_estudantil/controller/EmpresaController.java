package com.economiafacil.moeda_estudantil.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import com.economiafacil.moeda_estudantil.model.EmpresaParceira;
import com.economiafacil.moeda_estudantil.service.EmpresaService;

@RestController
@RequestMapping("/empresas")
@CrossOrigin("*")
public class EmpresaController {

    @Autowired
    private EmpresaService service;

    @PostMapping
    public EmpresaParceira criar(@RequestBody EmpresaParceira e) {
        return service.salvar(e);
    }

    @GetMapping
    public List<EmpresaParceira> listar() {
        return service.listar();
    }
}