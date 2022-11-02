package modelos;

import constantes.CargosCandidatos;
import excecoes.CandidatoNaoEncontradoException;
import service.GeradorDeCandidatosEPartidosService;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class Urna {

    private HashSet<Candidato> senadores = new HashSet<>();
    private HashSet<Candidato> presidentes = new HashSet<>();
    private HashSet<Candidato> governadores = new HashSet<>();
    private HashSet<Candidato> deputadosFederais = new HashSet<>();
    private HashSet<Candidato> deputadosEstaduais = new HashSet<>();

    private int votosNulos;
    private int votosEmBranco;

    public Urna(GeradorDeCandidatosEPartidosService service) {
        HashSet<Candidato> candidatos = service.getCandidatos();
        filtrarCandidatosPorCargo(candidatos);

        this.votosNulos = 0;
        this.votosEmBranco = 0;
    }

    public void incrementarVotosNulos() {
        this.votosNulos++;
    }

    public int getVotosNulos() {
        return votosNulos;
    }

    public void incrementarVotosEmBranco() {
        this.votosEmBranco++;
    }

    public int getVotosEmBranco() {
        return votosEmBranco;
    }

    private void filtrarCandidatosPorCargo(HashSet<Candidato> candidatos) {
        for (Candidato c : candidatos) {
            switch (c.getCargo()) {
                case CargosCandidatos.PRESIDENTE -> this.presidentes.add(c);
                case CargosCandidatos.SENADOR -> this.senadores.add(c);
                case CargosCandidatos.GOVERNADOR -> this.governadores.add(c);
                case CargosCandidatos.DEPUTADO_FEDERAL -> this.deputadosEstaduais.add(c);
                default -> this.deputadosFederais.add(c);
            }
        }
    }

    public void votarParaPresidente(int numero) {
        // TODO: verificar como lidar com Exception
        try {
            encontrarCandidatoAPresidente(numero);
        } catch (CandidatoNaoEncontradoException e) {
            throw new RuntimeException(e);
        }

    }

    private Candidato encontrarCandidatoAPresidente(int numeroCandidato) throws CandidatoNaoEncontradoException {
        // TODO: implementação ainda incompleta
        for (Candidato c : this.presidentes) {
            if (c.getNumero() == numeroCandidato) {
                System.out.println("OK");
            }
        }
        return null;
    }

    public void imprimirListaCandidatosAPresidente() {
        if (this.presidentes != null) {
            for (Candidato c : this.presidentes) {
                System.out.println(c.getNome() + " -- " + c.getPartido().getNomePartido());
            }
        }
    }

    // implementações de Marcos:

    public void votar(HashSet<Candidato> cd, int numeroInserido) { //uma rotina anterior devera tratar o numero inserido

        if (numeroInserido == 0) {  //valor escolhido para branco
            incrementarVotosEmBranco();
        } else {
            if (cd != null) {
                boolean consta = false;

                for (Candidato i : cd) {

                    if (i.getNumero() == numeroInserido) { //candidato encontrado, recebe voto
                        // TODO: incrementar número de votos no candidato
                        consta = true;
                        break;
                    }

                }
                if (!consta) {
                    incrementarVotosNulos();  //numero inserido não encontrado
                }
            }
        }

    }

    public LinkedList<Candidato> apurarVotos(HashSet<Candidato> c) {            //recebe uma lista de candidatos de mesmo cargo

        LinkedList<Candidato> ranking = new LinkedList<Candidato>();        // manter ordem na inserção

        if (!c.isEmpty() && c.size() > 1) {                             //se  lista tiver mais de 1 candidato

            while (c.size() > 0) { //enquanto houver candidatos na lista recebida

                Candidato maisVotado = ((List<Candidato>) c).get(0);   //pega primeiro candidato e o assume como mais votado

                for (int i = 1; i < c.size(); i++) {

                    if (((List<Candidato>) c).get(i).getNumeroVotos() > maisVotado.getNumeroVotos()) {  //compara com o proximo

                        maisVotado = ((List<Candidato>) c).get(i);
                    }
                }
                ranking.add(maisVotado);
                c.remove(maisVotado);
            }
        }
        return ranking;
    }


}
