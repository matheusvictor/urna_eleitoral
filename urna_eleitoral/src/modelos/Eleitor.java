package modelos;

public class Eleitor extends Pessoa {

    private static int contador = 1;

    private int idEleitor;


    public Eleitor(String nome, char sexo, int idade) {
        super(nome, sexo, idade);

        this.idEleitor = contador;

        contador++;
    }

    public static int getContador() {
        return contador;
    }

    public int getIdEleitor() {
        return idEleitor;
    }

}
