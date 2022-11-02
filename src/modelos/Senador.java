package modelos;

import constantes.NomesEstados;
import constantes.CargosCandidatos;
import constantes.QuantidadeDigitosPorCargo;

import java.util.Random;

public class Senador extends Candidato {

    public Senador(String nome, char sexo, NomesEstados estado, Partido partido) {
        super(nome, sexo, estado, partido);
        this.setCargo(CargosCandidatos.SENADOR);
    }

    @Override
    public int gerarNumeroCandidato() {
        Random random = new Random();

        int numeroBase = (int) (this.getPartido().getNumeroPartido() * Math.pow(10, QuantidadeDigitosPorCargo.SENADOR - QuantidadeDigitosPorCargo.DIGITOS_PARTIDO) + 0);

        int intervaloMinimo = 1;
        int intervaloMaximo = 9;

        return numeroBase + random.nextInt(intervaloMaximo - intervaloMinimo) + intervaloMinimo;

    }
}
