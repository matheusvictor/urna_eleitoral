package modelos;

import constantes.NomesEstados;
import constantes.CargosCandidatos;
import constantes.QuantidadeDigitosPorCargo;

import java.util.Random;

public class DeputadoFederal extends Candidato {

    public DeputadoFederal(String nome, char sexo, NomesEstados estado, Partido partido) {
        super(nome, sexo, estado, partido);
        this.setCargo(CargosCandidatos.DEPUTADO_FEDERAL);
    }

    @Override
    public int gerarNumeroCandidato() {
        Random random = new Random();

        int numeroBase = (int) (this.getPartido().getNumeroPartido() * Math.pow(10, QuantidadeDigitosPorCargo.DEPUTADO_FEDERAL - QuantidadeDigitosPorCargo.DIGITOS_PARTIDO) + 0);

        int intervaloMinimo = 10;
        int intervaloMaximo = 99;

        return numeroBase + random.nextInt(intervaloMaximo - intervaloMinimo) + intervaloMinimo;
    }
}
