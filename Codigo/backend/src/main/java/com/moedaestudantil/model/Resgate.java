package com.moedaestudantil.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "resgate")
@Data
@NoArgsConstructor
public class Resgate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "aluno_id", nullable = false)
    private Aluno aluno;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "vantagem_id", nullable = false)
    private Vantagem vantagem;

    @Column(nullable = false)
    private Integer custo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusResgate status = StatusResgate.PENDENTE;

    @Column(name = "criado_em", nullable = false)
    private LocalDateTime criadoEm = LocalDateTime.now();

    public enum StatusResgate {
        PENDENTE, CONFIRMADO, CANCELADO
    }
}