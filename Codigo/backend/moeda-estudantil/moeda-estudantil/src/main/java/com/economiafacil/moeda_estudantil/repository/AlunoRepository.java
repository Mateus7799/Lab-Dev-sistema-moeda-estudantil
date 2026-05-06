package com.economiafacil.moeda_estudantil.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.economiafacil.moeda_estudantil.model.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {}