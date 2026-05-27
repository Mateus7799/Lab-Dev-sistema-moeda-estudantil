package com.moedaestudantil.dto;

import com.moedaestudantil.model.Resgate;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ResgateResponse {

    private Long id;
    private Long alunoId;
    private String alunoNome;
    private Long vantagemId;
    private String vantagemNome;
    private String empresaNome;
    private Integer custo;
    private String status;
    private LocalDateTime criadoEm;

    public static ResgateResponse from(Resgate r) {
        ResgateResponse dto = new ResgateResponse();
        dto.setId(r.getId());
        dto.setAlunoId(r.getAluno().getId());
        dto.setAlunoNome(r.getAluno().getNome());
        dto.setVantagemId(r.getVantagem().getId());
        dto.setVantagemNome(r.getVantagem().getNome());
        dto.setEmpresaNome(r.getVantagem().getEmpresa().getNome());
        dto.setCusto(r.getCusto());
        dto.setStatus(r.getStatus().name());
        dto.setCriadoEm(r.getCriadoEm());
        return dto;
    }
}