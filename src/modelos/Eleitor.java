package modelos;

public class Eleitor extends Pessoa {

    private static int contador = 1;

    private int idEleitor;
    private boolean jaVotou;

    public Eleitor(String nome, char sexo, int idade) {
        super(nome, sexo, idade);

        this.jaVotou = false;
        this.idEleitor = contador;

        contador++;
    }

    public static int getContador() {
        return contador;
    }

    public int getIdEleitor() {
        return idEleitor;
    }

    public boolean isJaVotou() {
        return jaVotou;
    }

}
