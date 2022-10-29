import modelos.Candidato;
import modelos.Urna;
import service.GeradorDeCandidatosEPartidosService;

import java.util.HashSet;

public class Main {

    public static void main(String[] args) {

        GeradorDeCandidatosEPartidosService geradorDeCandidatosEPartidosService = GeradorDeCandidatosEPartidosService.getInstance();
        Urna urnaEleitoral = new Urna(geradorDeCandidatosEPartidosService);

        HashSet<Candidato> c = geradorDeCandidatosEPartidosService.getCandidatos();

        geradorDeCandidatosEPartidosService.imprimirInformacoesDosCandidatos();

    }
}
