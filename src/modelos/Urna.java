package modelos;

import constantes.CargosCandidatos;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class Urna {

    private HashSet<Candidato> presidentes;
    private HashSet<Candidato> governadores;
    private HashSet<Candidato> senadores;
    private HashSet<Candidato> deputadosFederais;
    private HashSet<Candidato> deputadosEstaduais;

    private int votosNulos;
    private int votosEmBranco;

    public Urna() {
        this.votosNulos = 0;
        this.votosEmBranco = 0;
    }

    public void incrementarVotoEmBranco() {
        this.votosEmBranco++;
    }

    public int getVotosEmBranco() {
        return this.votosEmBranco;
    }

    public void incrementarVotoNulo() {
        this.votosNulos++;
    }

    public int getVotosNulos() {
        return votosNulos;
    }

    public HashSet<Candidato> getPresidentes() {  //serão responsáveis por lidar com a votação
        return presidentes;                      // o Main chamará um dos gets para haver votação
    }

    public HashSet<Candidato> getGovernadores() {
        return governadores;
    }

    public HashSet<Candidato> getSenadores() {
        return senadores;
    }

    public HashSet<Candidato> getDepFederais() {
        return deputadosFederais;
    }

    public HashSet<Candidato> getDepestaduais() {
        return deputadosEstaduais;
    }

    public void inserirCandidatos(HashSet<Candidato> c) {

        for (Candidato i : c) {

            if (i.getCargo() == CargosCandidatos.PRESIDENTE) {   //verifica cada cargo da lista e insere numa
                presidentes.add(i);                            //hash acima
                break;
            } else if (i.getCargo() == CargosCandidatos.GOVERNADOR) {
                governadores.add(i);
                break;
            } else if (i.getCargo() == CargosCandidatos.SENADOR) {
                senadores.add(i);
                break;

            } else if (i.getCargo() == CargosCandidatos.DEPUTADO_FEDERAL) {
                deputadosFederais.add(i);
                break;

            } else if (i.getCargo() == CargosCandidatos.DEPUTADO_ESTADUAL) {
                deputadosEstaduais.add(i);
                break;
            }
        }
    }

    public void votar(HashSet<Candidato> cd, int numeroInserido) { //uma rotina anterior devera tratar o numero inserido

        if (numeroInserido == 0) {  //valor escolhido para branco
            incrementarVotoEmBranco();
        } else {
            if (cd != null) {
                boolean consta = false;

                for (Candidato i : cd) {
                    if (i.getNumero() == numeroInserido) { //candidato encontrado, recebe voto
                        i.setVoto();
                        consta = true;
                        break;
                    }
                }
                if (!consta) {
                    incrementarVotoNulo();  //numero inserido não encontrado
                }
            }
        }

    }

    public HashSet<Candidato> apurarVotos(HashSet<Candidato> c) {            //recebe uma lista de candidatos de mesmo cargo

        //LinkedList <Candidato> ranking = new LinkedList <Candidato>();        // manter ordem na inserção

        if (c != null) {
            Collections.sort((List<Candidato>) c); //ordena por numero de votos maior para menor
        }
        return c;
    }

}
