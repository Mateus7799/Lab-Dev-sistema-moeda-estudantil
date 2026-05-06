package com.economiafacil.moeda_estudantil.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import com.economiafacil.moeda_estudantil.model.EmpresaParceira;
import com.economiafacil.moeda_estudantil.repository.EmpresaRepository;

@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepository repo;

    public EmpresaParceira salvar(EmpresaParceira e) {
        return repo.save(e);
    }

    public List<EmpresaParceira> listar() {
        return repo.findAll();
    }
}