package modelos;

import constantes.CargosCandidatos;
import constantes.NomesEstados;
import constantes.QuantidadeDigitosPorCargo;

import java.util.Random;

public class Governador extends Candidato {

    public Governador(String nome, char sexo, NomesEstados estado, Partido partido) {
        super(nome, sexo, estado, partido);
        this.setCargo(CargosCandidatos.GOVERNADOR);
    }

    @Override
    public int gerarNumeroCandidato() {
        Random random = new Random();

        int numeroBase = (int) (this.getPartido().getNumeroPartido() * Math.pow(10, QuantidadeDigitosPorCargo.GOVERNADOR - QuantidadeDigitosPorCargo.DIGITOS_PARTIDO) + 0);

        int intervaloMinimo = 1;
        int intervaloMaximo = 9;

        return numeroBase + random.nextInt(intervaloMaximo - intervaloMinimo) + intervaloMinimo;

    }
}
