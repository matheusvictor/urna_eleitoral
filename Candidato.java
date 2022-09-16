public class Candidato {

    public static int contador;
    private int idContador;
    private String nome;
    private int numero;
    private String partido;
    private String cargo;

    public Candidato(String nome, int numero, String partido, String cargo) {
        this.idContador = contador;
        this.nome = nome;
        this.numero = numero;
        this.partido = partido;
        this.cargo = cargo;
    }

    public int obterIdContador() {
        return idContador;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String obterNome() {
        return nome;
    }

    public int obterNumero() {
        return numero;
    }

    public String obterCargo() {
        return cargo;
    }

    public String obterPartido() {
        return partido;
    }
}
