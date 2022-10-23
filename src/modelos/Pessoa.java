package modelos;

public class Pessoa {

    private String nome;
    private char sexo;
    private int idade;
    private String estado;

    public Pessoa(String nome, char sexo, int idade, String estado) {
        this.nome = nome;
        this.sexo = sexo;
        this.idade = idade;
        this.estado = estado;
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

    public String getEstado() {
        return estado;
    }

}
