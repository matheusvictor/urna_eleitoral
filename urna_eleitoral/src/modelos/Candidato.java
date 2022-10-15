package modelos;

public class Candidato extends Pessoa {

    private static int contador = 0;
    private int idCandidato;
    private int numero;
    private String cargo;
    private Partido partido;

    public Candidato(String nome, char sexo, int idade, int numero, Partido partido, String cargo) {
        super(nome, sexo, idade);

        contador++;
        this.cargo = cargo;
        this.numero = numero;
        this.partido = partido;
        this.idCandidato = contador;
    }

    public int getIdContador() {
        return idCandidato;
    }

    public int getNumero() {
        return numero;
    }

    public String getCargo() {
        return cargo;
    }

    public Partido getPartido() {
        return partido;
    }

}
