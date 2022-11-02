package modelos;

import constantes.NomesEstados;
import interfaces.GeradorNumeroCandidato;

public abstract class Candidato implements GeradorNumeroCandidato {

    private static int contador = 0;

    private int idCandidato;
    private String nome;
    private char sexo;
    private NomesEstados estado;
    private int numero;
    private String cargo;
    private Partido partido;
    private int numeroVotos;

    public Candidato(String nome, char sexo, NomesEstados estado, Partido partido) {
        contador++;
        this.idCandidato = contador;

        this.numeroVotos = 0;

        this.nome = nome;
        this.sexo = sexo;
        this.estado = estado;
        this.partido = partido;
        this.numero = gerarNumeroCandidato();
    }

    public int idCandidato() {
        return idCandidato;
    }

    public String getNome() {
        return nome;
    }

    public char getSexo() {
        return sexo;
    }

    public String getEstado() {
        return estado.getNome();
    }

    public int getNumero() {
        return numero;
    }

    protected void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getCargo() {
        return cargo;
    }

    public Partido getPartido() {
        return partido;
    }

    protected void receberVoto() {
        this.numeroVotos++;
    }

    public int getNumeroVotos() {
        return numeroVotos;
    }

}
