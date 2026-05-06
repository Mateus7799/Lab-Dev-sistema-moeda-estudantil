package com.economiafacil.moeda_estudantil.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import com.economiafacil.moeda_estudantil.model.Aluno;
import com.economiafacil.moeda_estudantil.service.AlunoService;

@RestController
@RequestMapping("/alunos")
@CrossOrigin("*")
public class AlunoController {

    @Autowired
    private AlunoService service;

    @PostMapping
    public Aluno criar(@RequestBody Aluno aluno) {
        return service.salvar(aluno);
    }

    @GetMapping
    public List<Aluno> listar() {
        return service.listar();
    }
    
}