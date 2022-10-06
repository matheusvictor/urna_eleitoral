package modelos;

public class Pessoa {

    private String nome;
    private char sexo;
    private int idade;
    private String nacionalidade;

    public Pessoa(String nome, char sexo, int idade, String nacionalidade) {
        this.nome = nome;
        this.sexo = sexo;
        this.idade = idade;
        this.nacionalidade = nacionalidade;
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

    public String getNacionalidade() {
        return this.nacionalidade;
    }


}
