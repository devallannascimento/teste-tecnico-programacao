package com.teste.service;

import com.teste.model.Funcionario;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FuncionarioService {

    public List<Funcionario> inserirDadosIniciais() {
        return new ArrayList<>(List.of(
                new Funcionario("Maria",   LocalDate.of(2000,10,18), new BigDecimal("2009.44"), "Operador"),
                new Funcionario("João",    LocalDate.of(1990, 5,12), new BigDecimal("2284.38"), "Operador"),
                new Funcionario("Caio",    LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"),
                new Funcionario("Miguel",  LocalDate.of(1988,10,14), new BigDecimal("19119.88"), "Diretor"),
                new Funcionario("Alice",   LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"),
                new Funcionario("Heitor",  LocalDate.of(1999,11,19), new BigDecimal("1582.72"), "Operador"),
                new Funcionario("Arthur",  LocalDate.of(1993, 3,31), new BigDecimal("4071.84"), "Contador"),
                new Funcionario("Laura",   LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"),
                new Funcionario("Heloísa", LocalDate.of(2003, 5,24), new BigDecimal("1606.85"), "Eletricista"),
                new Funcionario("Helena",  LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente")
        ));
    }

    public void removerPorNome(List<Funcionario> funcionarios, String nome) {
        funcionarios.removeIf(f -> f.getNome().equalsIgnoreCase(nome));
    }

    public void aplicarReajuste(List<Funcionario> funcionarios, BigDecimal percentual) {
        funcionarios.forEach(f -> f.reajustarSalario(percentual));
    }

    public Map<String, List<Funcionario>> agruparPorFuncao(List<Funcionario> funcionarios) {
        return funcionarios.stream().collect(Collectors.groupingBy(
                Funcionario::getFuncao, LinkedHashMap::new, Collectors.toList()));
    }

    public List<Funcionario> aniversariantesNosMeses(List<Funcionario> funcionarios, Set<Integer> meses) {
        return funcionarios.stream()
                .filter(f -> meses.contains(f.getDataNascimento().getMonthValue()))
                .collect(Collectors.toList());
    }

    public Optional<Funcionario> maisVelho(List<Funcionario> funcionarios) {
        return funcionarios.stream()
                .min(Comparator.comparing(Funcionario::getDataNascimento));
    }

    public List<Funcionario> ordenarPorNome(List<Funcionario> funcionarios) {
        return funcionarios.stream()
                .sorted(Comparator.comparing(Funcionario::getNome, String.CASE_INSENSITIVE_ORDER))
                .collect(Collectors.toList());
    }

    public BigDecimal totalSalarios(List<Funcionario> funcionarios) {
        return funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public record NomeIdade(String nome, int idade) {}
    public NomeIdade calcularIdade(Funcionario f, LocalDate hoje) {
        int anos = Period.between(f.getDataNascimento(), hoje).getYears();
        return new NomeIdade(f.getNome(), anos);
    }

    public Map<Funcionario, BigDecimal> salariosEmMultiploDoMinimo(List<Funcionario> funcionarios, BigDecimal salarioMinimo) {
        return funcionarios.stream().collect(Collectors.toMap(
                Function.identity(),
                f -> f.getSalario().divide(salarioMinimo, 2, java.math.RoundingMode.HALF_UP),
                (a,b) -> a,
                LinkedHashMap::new
        ));
    }
}
