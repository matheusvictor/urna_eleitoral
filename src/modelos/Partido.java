package modelos;

import constantes.NomesESiglasPartidos;
import service.ValidadorDigitos;

import java.util.HashSet;

public class Partido {

    private static int contador = 0;

    private int idPartido;
    private String nomePartido;
    private String siglaPartido;
    private int numeroPartido;
    private HashSet<Candidato> filiados;

    public Partido(NomesESiglasPartidos nomeESiglaPartido, int numeroPartido) {
        contador++;
        this.idPartido = contador;
        this.nomePartido = nomeESiglaPartido.getNomePartido();
        this.siglaPartido = nomeESiglaPartido.getSiglaPartido();
        this.numeroPartido = ValidadorDigitos.validarNumeroPartido(numeroPartido);
        this.filiados = new HashSet<>();
    }

    public int getIdPartido() {
        return this.idPartido;
    }

    public String getNomePartido() {
        return this.nomePartido;
    }

    public String getSiglaPartido() {
        return siglaPartido;
    }

    public int getNumeroPartido() {
        return this.numeroPartido;
    }

    public HashSet<Candidato> getFiliados() {
        return filiados;
    }

    public void inserirCandidato(Candidato cand) {
        filiados.add(cand);
    }

}
