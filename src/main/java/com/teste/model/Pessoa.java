package com.teste.model;

import java.time.LocalDate;
import java.util.Objects;

public class Pessoa {
    private final String nome;
    private final LocalDate dataNascimento;

    public Pessoa(String nome, LocalDate dataNascimento) {
        this.nome = Objects.requireNonNull(nome, "nome");
        this.dataNascimento = Objects.requireNonNull(dataNascimento, "dataNascimento");
    }

    public String getNome() { return nome; }
    public LocalDate getDataNascimento() { return dataNascimento; }
}
