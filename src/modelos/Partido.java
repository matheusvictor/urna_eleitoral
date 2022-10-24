package modelos;

import service.ValidadorDigitos;

import java.util.HashSet;

public class Partido  {

    private static int contador = 0;

    private int idPartido;
    private String nomePartido;
    private int numeroPartido;
    private HashSet<Candidato> filiados; // A ordem não importa; acesso O(1).
    
    public void inserirCandidato(Candidato cand) {
    	filiados.add(cand);
    }
    public Partido(String nome, int numeroPartido) {
        contador++;
        this.idPartido = contador;
        this.nomePartido = nome;
        this.numeroPartido = ValidadorDigitos.validarNumeroPartido(numeroPartido);
        this.filiados = new HashSet<>();
    }

    public int getIdPartido() {
        return this.idPartido;
    }

    public String getNomePartido() {
        return this.nomePartido;
    }

    public int getNumeroPartido() {
        return this.numeroPartido;
    }
    
    public HashSet<Candidato> getFiliados() {
    	return filiados;
    }

    public HashSet<Candidato> getFiliados() {
        return this.filiados;
    }

}
