package com.teste;

import com.teste.model.Funcionario;
import com.teste.service.FuncionarioService;
import com.teste.util.Formatters;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        var service = new FuncionarioService();

        // 3.1 Inserir funcionários
        var funcionarios = service.inserirDadosIniciais();

        // 3.2 Remover "João"
        service.removerPorNome(funcionarios, "João");

        // 3.3 Imprimir todos os funcionários (Tabela)
        System.out.println("-------------------------- Funcionários --------------------------- ");
        printTabela(funcionarios);

        // 3.4 Reajuste de 10%
        service.aplicarReajuste(funcionarios, new BigDecimal("0.10"));

        // 3.5 Agrupar por função
        var porFuncao = service.agruparPorFuncao(funcionarios);

        // 3.6 Imprimir agrupados por função (Tabela por função)
        System.out.println("\n---------------------- Agrupados por função ----------------------- ");
        porFuncao.forEach((funcao, lista) -> {
            System.out.println("[" + funcao + "]");
            printTabela(lista);
            System.out.println();
        });

        // 3.8 Aniversariantes em outubro e dezembro (Tabela)
        System.out.println("\n-------------------- Aniversariantes (10 e 12) -------------------- ");
        var meses = new HashSet<>(Arrays.asList(10, 12));
        var aniversariantes = service.aniversariantesNosMeses(funcionarios, meses);
        printTabela(aniversariantes);

        // 3.9 Mais velho (nome e idade)
        System.out.println("\n--------------------- Funcionário mais velho ---------------------- ");
        service.maisVelho(funcionarios).ifPresent(f -> {
            var ni = service.calcularIdade(f, LocalDate.now());
            System.out.printf("%-15s | %d anos%n", ni.nome(), ni.idade());
        });

        // 3.10 Lista por ordem alfabética (Tabela)
        System.out.println("\n------------------------- Ordem alfabética ------------------------ ");
        printTabela(service.ordenarPorNome(funcionarios));

        // 3.11 Total dos salários
        System.out.println("\n----------------------- Total dos salários ------------------------ ");
        var total = service.totalSalarios(funcionarios);
        System.out.println(Formatters.moeda(total));

        // 3.12 Salários mínimos (Tabela)
        System.out.println("\n-------------------- Salários mínimos por funcionário ------------------ ");
        var salarioMinimo = new BigDecimal("1212.00");
        printTabelaSalariosMinimos(service.salariosEmMultiploDoMinimo(funcionarios, salarioMinimo));
    }

    // Tabela genérica de funcionários
    private static void printTabela(List<Funcionario> funcionarios) {
        System.out.printf("%-15s | %-12s | %-12s | %-15s%n", "Nome", "Nascimento", "Salário", "Função");
        System.out.println("-------------------------------------------------------------------");
        funcionarios.forEach(f -> System.out.printf("%-15s | %-12s | %-12s | %-15s%n",
                f.getNome(),
                Formatters.data(f.getDataNascimento()),
                Formatters.moeda(f.getSalario()),
                f.getFuncao()));
    }

    // Tabela específica para salários mínimos
    private static void printTabelaSalariosMinimos(Map<Funcionario, BigDecimal> mapa) {
        System.out.printf("%-15s | %-12s | %-22s | %-15s%n",
                "Nome", "Salário", "Qtd. Salários Mínimos", "Função");
        System.out.println("------------------------------------------------------------------------");

        mapa.forEach((f, mult) -> System.out.printf("%-15s | %-12s | %-22s | %-15s%n",
                f.getNome(),
                Formatters.moeda(f.getSalario()),
                Formatters.numero(mult),
                f.getFuncao()));
    }
}
