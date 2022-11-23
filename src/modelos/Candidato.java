package modelos;

import constantes.NomesEstados;
import interfaces.GeradorNumeroCandidato;

public abstract class Candidato implements GeradorNumeroCandidato, Comparable<Candidato> { //add comparable para ordenação de listas

    private static int contador = 0;

    protected int idCandidato;
    protected String nome;
    protected char sexo;
    protected NomesEstados estado;
    protected int numero;
    protected String cargo;
    protected Partido partido;
    protected int numeroVotos;

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
        this.numeroVotos += 1;
    }

    public int getNumeroVotos() {
        return numeroVotos;
    }

    public String getDetalhesCandidato() {
        return this.nome + " :: " + this.partido.getSiglaPartido() + " :: " + this.cargo;
    }

    @Override
    public String toString() {
        return "Candidato: " + nome + "\n" +
                "Estado: " + estado + "\n" +
                "Número: " + numero + "\n" +
                "Cargo: " + cargo + "\n" +
                "Partido: " + partido.getNomePartido() + "\n" +
                "Número de votos: " + numeroVotos;
    }
    
    @Override
    public int compareTo(Candidato c) {
    	return c.getNumeroVotos() - this.getNumeroVotos();  //metodo comparable
    }

}
