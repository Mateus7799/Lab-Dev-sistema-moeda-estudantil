package com.economiafacil.moeda_estudantil.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import com.economiafacil.moeda_estudantil.model.Aluno;
import com.economiafacil.moeda_estudantil.repository.AlunoRepository;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository repo;

    public Aluno salvar(Aluno aluno) {
        return repo.save(aluno);
    }

    public List<Aluno> listar() {
        return repo.findAll();
    }
}