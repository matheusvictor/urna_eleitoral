package service;

import constantes.CargosCandidatos;
import constantes.NumerosPartidos;
import modelos.Candidato;
import modelos.Partido;

import java.util.HashSet;

public class GeradorDeCandidatosEPartidosService {

    private HashSet<Partido> partidos;
    private HashSet<Candidato> candidatos;

    private static GeradorDeCandidatosEPartidosService gerador;

    public static GeradorDeCandidatosEPartidosService getInstance() {
        if (gerador == null) {
            gerador = new GeradorDeCandidatosEPartidosService();
        }
        return gerador;
    }

    private GeradorDeCandidatosEPartidosService() {
        gerarPartidos();
        gerarCandidatos();
    }

    private void gerarPartidos() {

        this.partidos = new HashSet<>();

        Partido partidoVelho = new Partido("Partido Velho", NumerosPartidos.VELHO);
        Partido pLUA = new Partido("Partido Louvar, Ungir e Agradecer", NumerosPartidos.PLUA);
        Partido partidoDosDesempregados = new Partido("Partido dos Desempregados", NumerosPartidos.PARTIDO_DOS_DESEMPREGADOS);
        Partido partidoCapitalistaDoBrasil = new Partido("Partido Capitalista do Brasil", NumerosPartidos.PARTIDO_CAPITALISTA_DO_BRASIL);
        Partido partidoConservador = new Partido("Partido Conservador", NumerosPartidos.PARTIDO_CONSERVADOR);

        this.partidos.add(pLUA);
        this.partidos.add(partidoVelho);
        this.partidos.add(partidoDosDesempregados);
        this.partidos.add(partidoCapitalistaDoBrasil);
        this.partidos.add(partidoConservador);
    }

    private void gerarCandidatos() {

        this.candidatos = new HashSet<>();

        for (Partido partido : this.partidos) {

            if (partido.getNumeroPartido() == NumerosPartidos.PARTIDO_DOS_DESEMPREGADOS) {

                Candidato presidente = new Candidato("Polvo", 'M', 60, "PE", partido, CargosCandidatos.PRESIDENTE);
                this.candidatos.add(presidente);

                Candidato dep_federal = new Candidato("Tiririca", 'M', 55, "PE", partido, CargosCandidatos.DEPUTADO_FEDERAL);
                this.candidatos.add(dep_federal);

            } else if (partido.getNumeroPartido() == NumerosPartidos.VELHO) {

                Candidato presidente = new Candidato("Tonhão da Esfirra", 'M', 60, "PE", partido, CargosCandidatos.PRESIDENTE);
                this.candidatos.add(presidente);

                Candidato dep_federal = new Candidato("Paula", 'F', 35, "PE", partido, CargosCandidatos.DEPUTADO_FEDERAL);
                this.candidatos.add(dep_federal);

            }

        }

    }

    public HashSet<Partido> getPartidos() {
        return this.partidos;
    }

    public HashSet<Candidato> getCandidatos() {
        return this.candidatos;
    }

    public void imprimirInformacoesDosCandidatos() {

        for (Candidato candidato : this.candidatos) {
            System.out.println("Nome: " + candidato.getNome());
            System.out.println("Partido: " + candidato.getPartido().getNomePartido());
            System.out.println("Número: " + candidato.getNumero());
            System.out.println("Cargo: " + candidato.getCargo());
        }

    }

}
