package com.teste.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Funcionario extends Pessoa {
    private BigDecimal salario;
    private final String funcao;

    public Funcionario(String nome, java.time.LocalDate dataNascimento, BigDecimal salario, String funcao) {
        super(nome, dataNascimento);
        this.salario = Objects.requireNonNull(salario, "salario");
        this.funcao = Objects.requireNonNull(funcao, "funcao");
    }

    public BigDecimal getSalario() { return salario; }
    public String getFuncao() { return funcao; }

    public void reajustarSalario(BigDecimal percentual) {
        this.salario = this.salario.add(this.salario.multiply(percentual));
    }
}
