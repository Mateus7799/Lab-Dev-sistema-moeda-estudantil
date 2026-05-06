package com.economiafacil.moeda_estudantil.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.economiafacil.moeda_estudantil.model.EmpresaParceira;

public interface EmpresaRepository extends JpaRepository<EmpresaParceira, Long> {}