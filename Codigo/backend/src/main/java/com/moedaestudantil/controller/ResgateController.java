package com.moedaestudantil.controller;

import com.moedaestudantil.dto.ResgateResponse;
import com.moedaestudantil.model.Aluno;
import com.moedaestudantil.model.Resgate;
import com.moedaestudantil.model.Vantagem;
import com.moedaestudantil.repository.AlunoRepository;
import com.moedaestudantil.repository.ResgateRepository;
import com.moedaestudantil.repository.VantagemRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/resgates")
public class ResgateController {

    private final ResgateRepository resgateRepository;
    private final AlunoRepository alunoRepository;
    private final VantagemRepository vantagemRepository;

    public ResgateController(ResgateRepository resgateRepository,
                             AlunoRepository alunoRepository,
                             VantagemRepository vantagemRepository) {
        this.resgateRepository = resgateRepository;
        this.alunoRepository = alunoRepository;
        this.vantagemRepository = vantagemRepository;
    }

    /**
     * POST /api/resgates
     * Aluno resgata uma vantagem gastando moedas.
     * Body: { "alunoId": 1, "vantagemId": 2 }
     */
    @PostMapping
    public ResponseEntity<?> resgatar(@RequestBody Map<String, Long> body) {
        Long alunoId = body.get("alunoId");
        Long vantagemId = body.get("vantagemId");

        if (alunoId == null || vantagemId == null) {
            return ResponseEntity.badRequest()
                    .body(Map.of("erro", "alunoId e vantagemId são obrigatórios."));
        }

        Aluno aluno = alunoRepository.findById(alunoId).orElse(null);
        if (aluno == null) {
            return ResponseEntity.badRequest().body(Map.of("erro", "Aluno não encontrado."));
        }

        Vantagem vantagem = vantagemRepository.findById(vantagemId).orElse(null);
        if (vantagem == null) {
            return ResponseEntity.badRequest().body(Map.of("erro", "Vantagem não encontrada."));
        }

        if (aluno.getSaldoMoedas() < vantagem.getCusto()) {
            return ResponseEntity.badRequest()
                    .body(Map.of("erro", "Saldo insuficiente. Você tem " + aluno.getSaldoMoedas()
                            + " moedas e esta vantagem custa " + vantagem.getCusto() + "."));
        }

        // Debita do aluno
        aluno.setSaldoMoedas(aluno.getSaldoMoedas() - vantagem.getCusto());
        alunoRepository.save(aluno);

        // Registra resgate
        Resgate resgate = new Resgate();
        resgate.setAluno(aluno);
        resgate.setVantagem(vantagem);
        resgate.setCusto(vantagem.getCusto());
        resgate.setStatus(Resgate.StatusResgate.PENDENTE);
        Resgate saved = resgateRepository.save(resgate);

        return ResponseEntity.ok(ResgateResponse.from(saved));
    }

    /**
     * GET /api/resgates/aluno/{id}
     * Histórico de resgates de um aluno.
     */
    @GetMapping("/aluno/{id}")
    public ResponseEntity<?> resgatesDoAluno(@PathVariable Long id) {
        List<ResgateResponse> lista = resgateRepository
                .findByAlunoIdOrderByCriadoEmDesc(id)
                .stream()
                .map(ResgateResponse::from)
                .toList();
        return ResponseEntity.ok(lista);
    }

    /**
     * GET /api/resgates/empresa/{id}
     * Resgates de vantagens de uma empresa (painel da empresa).
     */
    @GetMapping("/empresa/{id}")
    public ResponseEntity<?> resgatesDaEmpresa(@PathVariable Long id) {
        List<ResgateResponse> lista = resgateRepository
                .findByVantagemEmpresaIdOrderByCriadoEmDesc(id)
                .stream()
                .map(ResgateResponse::from)
                .toList();
        return ResponseEntity.ok(lista);
    }

    /**
     * PUT /api/resgates/{id}/confirmar
     * Empresa confirma a entrega da vantagem.
     */
    @PutMapping("/{id}/confirmar")
    public ResponseEntity<?> confirmar(@PathVariable Long id) {
        return resgateRepository.findById(id)
                .map(r -> {
                    r.setStatus(Resgate.StatusResgate.CONFIRMADO);
                    return ResponseEntity.ok(ResgateResponse.from(resgateRepository.save(r)));
                })
                .orElse(ResponseEntity.notFound().build());
    }
}