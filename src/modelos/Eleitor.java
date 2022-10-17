package modelos;

public class Eleitor extends Pessoa {

    private static int contador = 1;
    private int idEleitor;
    private boolean jaVotou;


    public Eleitor(String nome, char sexo, int idade, String estado) {
        super(nome, sexo, idade, estado);
        this.idEleitor = contador;
        this.jaVotou = false;
        contador++;
    }

    public static int getContador() {
        return contador;
    }

    public int getIdEleitor() {
        return idEleitor;
    }

    public boolean getJaVotou() {
        return this.jaVotou;
    }

}
