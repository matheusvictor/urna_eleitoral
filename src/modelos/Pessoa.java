package modelos;

import java.time.LocalDate;
import java.time.Period;

public class Pessoa {

    private String nome;
    private char sexo;
    private int idade;
    private String estado;

    private LocalDate dataNascimento;

    public Pessoa(String nome, char sexo, LocalDate dataNascimento, String estado) {
        this.nome = nome;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
        this.idade = gerarIdade(dataNascimento);
        this.estado = estado;
    }

    private int gerarIdade(LocalDate dataNascimento) {

        LocalDate dataAtual = LocalDate.now();

        Period periodo = Period.between(dataNascimento, dataAtual);
        int idade = periodo.getYears();

        return idade;
    }

    public String getNome() {
        return this.nome;
    }

    public char getSexo() {
        return this.sexo;
    }

    public int getIdade() {
        return this.idade;
    }

    public String getEstado() {
        return estado;
    }

}
