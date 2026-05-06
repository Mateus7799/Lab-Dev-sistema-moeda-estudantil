package com.economiafacil.moeda_estudantil.model;

import jakarta.persistence.*;

@Entity
public class EmpresaParceira {

    @Id
    @GeneratedValue
    private Long id;

    private String nome;
    private String email;
    private String cnpj;

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Long getId() {
        return id;
    }

}