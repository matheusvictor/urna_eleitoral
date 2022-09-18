package br.ufba.urna.modelos;

public class Partido {

    private static int contador;
    private int id;

    private String nome;
    private String numeroPartido = "";

    public Partido(String nome, String numeroPartido) {
        contador++;
        this.id = contador;
        this.nome = nome;

        if (numeroPartido.length() > 2) {
            for (int i = 0; i < 2; i++) {
                this.numeroPartido += numeroPartido.toCharArray()[i];
            }
        }
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getNumeroPartido() {
        return numeroPartido;
    }
}