package br.ufba.urna.modelos;

public class Candidato {

    private static int contador;
    private int idCandidato;
    private String nome;
    private int numero;
    private Partido partido;
    private String cargo;

    public Candidato(String nome, int numero, Partido partido, String cargo) {
        contador++;
        this.idCandidato = contador;
        this.nome = nome;
        this.numero = numero;
        this.partido = partido;
        this.cargo = cargo;
    }

    public int obterIdContador() {
        return idCandidato;
    }

    public String getNome() {
        return nome;
    }

    public int getNumero() {
        return numero;
    }

    public String getCargo() {
        return cargo;
    }

    public Partido gerPartido() {
        return partido;
    }
}
