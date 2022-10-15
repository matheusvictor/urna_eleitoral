package modelos;

import java.util.HashSet;

public class Partido {

    private static int contador = 0;
    private int idPartido;

    private String nomePartido;
    private int numeroPartido;
    
    private HashSet<Candidato> filiados; // A ordem não importa; acesso O(1).

    public Partido(String nome, int numeroPartido) {
        contador++;

        this.idPartido = contador;
        this.nomePartido = nome;
        this.numeroPartido = numeroPartido;
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

}