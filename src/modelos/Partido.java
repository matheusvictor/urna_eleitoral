package modelos;

public class Partido {

    private static int contador;
    private int idPartido;

    private String nomePartido;
    private int numeroPartido;

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