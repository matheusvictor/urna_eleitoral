package modelos;

import constantes.CargosCandidatos;
import constantes.QuantidadeDigitosPorCargo;

import java.util.Random;

public class Candidato extends Eleitor {

    private static int contador = 0;
    private int idCandidato;
    private int numero;
    private String cargo;
    private Partido partido;

    private int numeroVotos;

    public Candidato(String nome, char sexo, int idade, String estado, Partido partido, String cargo) {

        super(nome, sexo, idade, estado);

        contador++;
        this.cargo = cargo;
        this.numeroVotos = 0;
        this.partido = partido;
        this.idCandidato = contador;
        this.numero = gerarNumeroCandidato();
    }

    private int gerarNumeroCandidato() {

        Random random = new Random();

        switch (this.cargo) {
            case CargosCandidatos.DEPUTADO_FEDERAL: {

                int numeroBase = (int) (this.partido.getNumeroPartido() * Math.pow(10, QuantidadeDigitosPorCargo.DEPUTADO_FEDERAL - QuantidadeDigitosPorCargo.DIGITOS_PARTIDO) + 0);

                int intervaloMinimo = 10;
                int intervaloMaximo = 99;

                return numeroBase + random.nextInt(intervaloMaximo - intervaloMinimo) + intervaloMinimo;
            }
            case CargosCandidatos.DEPUTADO_ESTADUAL: {
                int numeroBase = (int) (this.partido.getNumeroPartido() * Math.pow(10, QuantidadeDigitosPorCargo.DEPUTADO_ESTADUAL - QuantidadeDigitosPorCargo.DIGITOS_PARTIDO) + 0);

                int intervaloMinimo = 100;
                int intervaloMaximo = 999;

                return numeroBase + random.nextInt(intervaloMaximo - intervaloMinimo) + intervaloMinimo;
            }
            case CargosCandidatos.SENADOR: {
                int numeroBase = (int) (this.partido.getNumeroPartido() * Math.pow(10, QuantidadeDigitosPorCargo.SENADOR - QuantidadeDigitosPorCargo.DIGITOS_PARTIDO) + 0);

                int intervaloMinimo = 1;
                int intervaloMaximo = 9;

                return numeroBase + random.nextInt(intervaloMaximo - intervaloMinimo) + intervaloMinimo;

            }
            default:
                return this.partido.getNumeroPartido();
        }
    }

    public int getIdContador() {
        return idCandidato;
    }

    public int getNumero() {
        return numero;
    }

    public String getCargo() {
        return cargo;
    }

    public Partido getPartido() {
        return partido;
    }

    public int getNumeroVotos() {
        return numeroVotos;
    }
    
}
