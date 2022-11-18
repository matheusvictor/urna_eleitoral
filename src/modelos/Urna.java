package modelos;

import excecoes.CandidatoNaoEncontradoException;
import service.GeradorDeCandidatosEPartidosService;

import java.util.*;

public class Urna {

    private HashSet<Candidato> candidatos = new HashSet<>();
    private HashSet<Senador> senadores = new HashSet<>();
    private HashSet<Presidente> presidentes = new HashSet<>();
    private HashSet<Governador> governadores = new HashSet<>();
    private HashSet<DeputadoFederal> deputadosFederais = new HashSet<>();
    private HashSet<DeputadoEstadual> deputadosEstaduais = new HashSet<>();
    private int votosNulos = 0;
    private int votosValidos = 0;
    private int votosEmBranco = 0;

    public String getZeresima() {
        return "Votos válidos: " + this.votosValidos + "\n" +
                "Votos nulos: " + this.votosNulos + "\n" +
                "Votos em branco: " + this.votosEmBranco;
    }

    public void habilitarUrnaParaVotacao(GeradorDeCandidatosEPartidosService service) {
        this.setCandidatos(service.getCandidatos());
        filtrarCandidatosPorCargo();
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

    private void filtrarCandidatosPorCargo() {
        for (Candidato candidato : this.candidatos) {
            if (candidato instanceof Presidente) {
                this.presidentes.add((Presidente) candidato);
            } else if (candidato instanceof Senador) {
                this.senadores.add((Senador) candidato);
            } else if (candidato instanceof DeputadoFederal) {
                this.deputadosFederais.add((DeputadoFederal) candidato);
            } else if (candidato instanceof DeputadoEstadual) {
                this.deputadosEstaduais.add((DeputadoEstadual) candidato);
            } else {
                this.governadores.add((Governador) candidato);
            }
        }
    }

    public Candidato encontrarCandidato(int numeroCandidato, String cargo) throws CandidatoNaoEncontradoException {
        Candidato candidato = this.candidatos.stream()
                .filter(p -> p.getNumero() == numeroCandidato && p.getCargo().equals(cargo))
                .findFirst()
                .orElse(null);

        if (candidato == null) {
//            incrementarVotosNulos();
            throw new CandidatoNaoEncontradoException();
        }

        return candidato;
    }

    public void addVotoAoCandidato2(Candidato candidato) {
        candidato.receberVoto();
    }

    public void addVotoAoCandidato(int numero, String cargo) {
        Candidato candidato;
        try {
            candidato = encontrarCandidato(numero, cargo);
            candidato.receberVoto();
        } catch (CandidatoNaoEncontradoException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    public void imprimirInformacoesCandidato(Candidato candidato) {
        System.out.println(candidato);
    }

    public void imprimirListaCandidatosASenador() {
        if (this.senadores != null) {
            for (Candidato c : this.senadores) {
                System.out.println(c.getNome() + " -- " + c.getPartido().getNomePartido());
            }
        }
    }

    public void imprimirListaCandidatosAPresidente() {
        if (this.presidentes != null) {
            for (Presidente p : this.presidentes) {
                System.out.println(p.getDetalhesCandidato());
            }
        }
    }

    public void imprimirListaCandidatosAGovernador() {
        if (this.governadores != null) {
            for (Candidato c : this.governadores) {
                System.out.println(c.getNome() + " -- " + c.getNumero() + " -- " + c.getPartido().getNomePartido());
            }
        }
    }

    public void imprimirListaCandidatosADepFederal() {
        if (this.deputadosFederais != null) {
            for (Candidato c : this.deputadosFederais) {
                System.out.println(c.getNome() + " -- " + c.getNumero() + " -- " + c.getPartido().getNomePartido());
            }
        }
    }

    public void imprimirListaCandidatosADepEstadual() {
        if (this.deputadosEstaduais != null) {
            for (Candidato c : this.deputadosEstaduais) {
                System.out.println(c.getNome() + " -- " + c.getNumero() + " -- " + c.getPartido().getNomePartido());
            }
        }
    }

    public HashSet<Presidente> getPresidentes() {
        return presidentes;
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

    public void setCandidatos(HashSet<Candidato> candidatos) {
        this.candidatos = candidatos;
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

    public Presidente apuracaoPresidente(int maxCadeiras) {
        Presidente presidenteEleito = null;

        int numVotos[] = new int[presidentes.size()];
        List<Candidato> listCandidatos = new ArrayList<Candidato>(presidentes);
        for (int i = 0; i < presidentes.size(); i++) {
            numVotos[i] = listCandidatos.get(i).getNumeroVotos();
        }

        Arrays.sort(numVotos);
        int minVotos = numVotos[numVotos.length - maxCadeiras];

        for (Presidente cc : presidentes) {
            if (cc.getNumeroVotos() >= minVotos) {
                presidenteEleito = cc;
            }
        }

        System.out.println(presidenteEleito.getNome() + " -- " + String.valueOf(presidenteEleito.getNumeroVotos()) + " votos");

        return presidenteEleito;
    }

    public HashSet<Senador> apuracaoSenador(int maxCadeiras) {
        HashSet<Senador> senadoresEleitos = new HashSet<Senador>();

        int numVotos[] = new int[senadores.size()];
        List<Senador> listCandidatos = new ArrayList<Senador>(senadores);
        for (int i = 0; i < senadores.size(); i++) {
            numVotos[i] = listCandidatos.get(i).getNumeroVotos();
        }

        Arrays.sort(numVotos);
        int minVotos = numVotos[numVotos.length - maxCadeiras];

        for (Senador cc : senadores) {
            if (cc.getNumeroVotos() >= minVotos) {
                senadoresEleitos.add(cc);
                System.out.println(cc.getNome() + " -- " + String.valueOf(cc.getNumeroVotos()) + " votos");
            }
        }

        return senadoresEleitos;
    }

    public HashSet<DeputadoFederal> apuracaoDF(int maxCadeiras) {
        HashSet<DeputadoFederal> dfsEleitos = new HashSet<DeputadoFederal>();

        int numVotos[] = new int[deputadosFederais.size()];
        List<DeputadoFederal> listCandidatos = new ArrayList<DeputadoFederal>(deputadosFederais);
        for (int i = 0; i < deputadosFederais.size(); i++) {
            numVotos[i] = listCandidatos.get(i).getNumeroVotos();
        }

        Arrays.sort(numVotos);
        int minVotos = numVotos[numVotos.length - maxCadeiras];

        for (DeputadoFederal cc : deputadosFederais) {
            if (cc.getNumeroVotos() >= minVotos) {
                dfsEleitos.add(cc);
                System.out.println(cc.getNome() + " -- " + String.valueOf(cc.getNumeroVotos()) + " votos");
            }
        }

        return dfsEleitos;
    }

    public HashSet<DeputadoEstadual> apuracaoDE(int maxCadeiras) {
        HashSet<DeputadoEstadual> desEleitos = new HashSet<DeputadoEstadual>();

        int numVotos[] = new int[deputadosEstaduais.size()];
        List<DeputadoEstadual> listCandidatos = new ArrayList<DeputadoEstadual>(deputadosEstaduais);
        for (int i = 0; i < deputadosEstaduais.size(); i++) {
            numVotos[i] = listCandidatos.get(i).getNumeroVotos();
        }

        Arrays.sort(numVotos);
        int minVotos = numVotos[numVotos.length - maxCadeiras];

        for (DeputadoEstadual cc : deputadosEstaduais) {
            if (cc.getNumeroVotos() >= minVotos) {
                desEleitos.add(cc);
                System.out.println(cc.getNome() + " -- " + String.valueOf(cc.getNumeroVotos()) + " votos");
            }
        }

        return desEleitos;
    }

}
