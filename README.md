# Projeto — Teste Técnico de Programação

## 📖 Descrição
Solução desenvolvida para o **Teste Técnico de Programação**, que consistia em implementar classes de `Pessoa` e `Funcionário`, operações de manipulação de lista, agrupamento, ordenação, cálculos salariais e impressão formatada em tabelas no terminal.

O projeto aplica conceitos de **Programação Orientada a Objetos**, **Streams API** e o princípio **SRP (Single Responsibility Principle)** do SOLID, garantindo que cada classe tenha apenas uma responsabilidade bem definida.

---

## ⚙️ Tecnologias

- **Java 17**
- **Maven**
- **Java Streams / Collections**
- **NumberFormat e DateTimeFormatter** para formatação
- **Paradigma OO** (herança, encapsulamento)

---

## 🧩 Aplicação do SOLID (S — Single Responsibility)

- **`Pessoa`** → representa unicamente uma pessoa (nome e data de nascimento).
- **`Funcionario`** → estende `Pessoa` e adiciona apenas a responsabilidade de conter salário e função.
- **`Formatters`** → isolado para formatações (datas, moeda, números), sem regra de negócio.
- **`FuncionarioService`** → centraliza a lógica de negócio sobre funcionários (inserir, remover, reajustar, agrupar, ordenar etc.).
- **`Main`** → ponto de entrada, responsável apenas por orquestrar chamadas e exibir resultados.

Essa separação evita acoplamento e facilita manutenção, testes e futuras extensões do sistema.

---

## 🔧 Instalação

Clone o repositório:
```bash
git clone https://github.com/seu-usuario/projeto-teste-tecnico.git
