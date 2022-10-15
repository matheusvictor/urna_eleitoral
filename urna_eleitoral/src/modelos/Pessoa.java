package modelos;

public class Pessoa {

    private String nome;
    private char sexo;
    private int idade;
    private boolean jaVotou;

    public Pessoa(String nome, char sexo, int idade) {
        this.nome = nome;
        this.sexo = sexo;
        this.idade = idade;
        this.jaVotou = false;
    }

    public String getNome() {
        return this.nome;
    }

    public char getSexo() {
        return this.sexo;
    }

    public int getIdade() {
        return this.idade;
    }

    public boolean getJaVotou() {
        return this.jaVotou;
    }

}
