import modelos.Partido;
import modelos.Candidato;

public class Main {

    public static void main(String[] args) {

        Partido partido = new Partido("Tabajara", "1122323");
        Candidato candidato_001 = new Candidato("Marco", 1111, partido, "");

        System.out.println(candidato_001.getNome());

    }
}
