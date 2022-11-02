package modelos;

import constantes.NomesEstados;
import constantes.CargosCandidatos;
import constantes.QuantidadeDigitosPorCargo;

import java.util.Random;

public class DeputadoEstadual extends Candidato {

    public DeputadoEstadual(String nome, char sexo, NomesEstados estado, Partido partido) {
        super(nome, sexo, estado, partido);
        this.setCargo(CargosCandidatos.DEPUTADO_ESTADUAL);
    }

    @Override
    public int gerarNumeroCandidato() {
        Random random = new Random();

        int numeroBase = (int) (this.getPartido().getNumeroPartido() * Math.pow(10, QuantidadeDigitosPorCargo.DEPUTADO_ESTADUAL - QuantidadeDigitosPorCargo.DIGITOS_PARTIDO) + 0);

        int intervaloMinimo = 100;
        int intervaloMaximo = 999;

        return numeroBase + random.nextInt(intervaloMaximo - intervaloMinimo) + intervaloMinimo;

    }
}
