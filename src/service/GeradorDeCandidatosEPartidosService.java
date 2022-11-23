package service;

import constantes.NomesESiglasPartidos;
import constantes.NomesEstados;
import constantes.NumerosPartidos;
import modelos.Candidato;
import modelos.DeputadoEstadual;
import modelos.DeputadoFederal;
import modelos.Governador;
import modelos.Partido;
import modelos.Presidente;
import modelos.Senador;

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

        Partido partidoVelho = new Partido(NomesESiglasPartidos.PARTIDO_VELHO, NumerosPartidos.VELHO);
        Partido pLUA = new Partido(NomesESiglasPartidos.PARTIDO_LOUVAR_UNGIR_AGRADECER, NumerosPartidos.PLUA);
        Partido partidoDosDesempregados = new Partido(NomesESiglasPartidos.PARTIDO_DOS_DESEMPREGADOS, NumerosPartidos.PARTIDO_DOS_DESEMPREGADOS);
        Partido partidoCapitalistaDoBrasil = new Partido(NomesESiglasPartidos.PARTIDO_CAPITALISTA_DO_BRASIL, NumerosPartidos.PARTIDO_CAPITALISTA_DO_BRASIL);
        Partido partidoConservador = new Partido(NomesESiglasPartidos.PARTIDO_CONSERVADOR, NumerosPartidos.PARTIDO_CONSERVADOR);

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

                Candidato presidente = new Presidente("Polvo", 'M', NomesEstados.TOCANTINS, partido);
                this.candidatos.add(presidente);

                Candidato dep_federal = new DeputadoFederal("Tiririca", 'M', NomesEstados.TOCANTINS, partido);
                this.candidatos.add(dep_federal);

            } else if (partido.getNumeroPartido() == NumerosPartidos.VELHO) {

                Candidato presidente = new Presidente("Tonhão da Esfirra", 'M', NomesEstados.PERNAMBUCO, partido);
                this.candidatos.add(presidente);

                Candidato dep_federal = new DeputadoFederal("Paula", 'F', NomesEstados.ACRE, partido);
                this.candidatos.add(dep_federal);
                
            	Candidato governador = new Governador("Jeremias", 'M', NomesEstados.CEARA, partido);
            	this.candidatos.add(governador);

            
            }else if(partido.getNumeroPartido() == NumerosPartidos.PARTIDO_CONSERVADOR) {
            	Candidato depEst = new DeputadoEstadual("Vildomar", 'M', NomesEstados.CEARA, partido);
            	this.candidatos.add(depEst);
            	
            	Candidato governador = new Governador("Militante", 'M', NomesEstados.CEARA, partido);
            	this.candidatos.add(governador);
            
            }else if(partido.getNumeroPartido() == NumerosPartidos.PARTIDO_CAPITALISTA_DO_BRASIL) {
            	Candidato depFed = new DeputadoEstadual("Hildebrando", 'M', NomesEstados.CEARA, partido);
            	this.candidatos.add(depFed);
            	
            	Candidato senador = new Senador("Julinho Maromba", 'M', NomesEstados.BAHIA, partido);
            	this.candidatos.add(senador);
            
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
