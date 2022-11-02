import constantes.NomesEstados;
import modelos.*;
import service.GeradorDeCandidatosEPartidosService;

import java.util.HashSet;

public class Main {

    public static void main(String[] args) {

        Urna urnaEleitoral = new Urna(GeradorDeCandidatosEPartidosService.getInstance());

        Presidente presidente = new Presidente("Teste", 'W', NomesEstados.ACRE, new Partido("", 56677));

        System.out.println(presidente.getNumero());
        System.out.println(presidente.getCargo());

        DeputadoFederal deputadoFederal = new DeputadoFederal("Teste", 'W', NomesEstados.ACRE, new Partido("", 777));
        System.out.println(deputadoFederal.getNumero());
        System.out.println(deputadoFederal.getCargo());

    }
}
