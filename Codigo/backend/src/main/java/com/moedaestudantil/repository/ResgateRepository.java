package com.moedaestudantil.repository;

import com.moedaestudantil.model.Resgate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResgateRepository extends JpaRepository<Resgate, Long> {
    // Resgates feitos por um aluno
    List<Resgate> findByAlunoIdOrderByCriadoEmDesc(Long alunoId);

    // Resgates de vantagens de uma empresa (para o dashboard da empresa)
    List<Resgate> findByVantagemEmpresaIdOrderByCriadoEmDesc(Long empresaId);
}