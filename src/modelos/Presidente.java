package modelos;

import constantes.CargosCandidatos;
import interfaces.GeradorNumeroCandidato;

public class Presidente extends Candidato implements GeradorNumeroCandidato {

    public Presidente(String nome, char sexo, String estado, Partido partido) {
        super(nome, sexo, estado, partido);
        this.setCargo(CargosCandidatos.PRESIDENTE);
    }

    @Override
    public int gerarNumeroCandidato() {
        return this.getPartido().getNumeroPartido();
    }

}
