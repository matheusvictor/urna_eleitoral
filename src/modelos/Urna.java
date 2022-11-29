package modelos;

import excecoes.CandidatoNaoEncontradoException;
import service.GeradorDeCandidatosEPartidosService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class Urna {

    private HashSet<Candidato> candidatos = new HashSet<>();
    private HashSet<Senador> senadores = new HashSet<>();
    private HashSet<Presidente> presidentes = new HashSet<>();
    private HashSet<Governador> governadores = new HashSet<>();
    private HashSet<DeputadoFederal> deputadosFederais = new HashSet<>();
    private HashSet<DeputadoEstadual> deputadosEstaduais = new HashSet<>();
    private int votosValidos = 0;
    private int votosEmBranco[] = new int[5];
    private int votosNulos[] = new int[5];

    private int votosTotaisPresidente = 0;
    private int votosTotaisGovernador = 0;
    private int votosTotaisSenador = 0;
    private int votosTotaisDepFederal = 0;
    private int votosTotaisDepEstadual = 0;


    public void habilitarUrnaParaVotacao(GeradorDeCandidatosEPartidosService service) {
        this.setCandidatos(service.getCandidatos());
        filtrarCandidatosPorCargo();
    }

    public String getZeresima() {
        int votosNulosGeral = this.votosNulos[0] + this.votosNulos[1] + this.votosNulos[2] + this.votosNulos[3] + this.votosNulos[4];
        int votosEmBrancoGeral = this.votosEmBranco[0] + this.votosEmBranco[1] + this.votosEmBranco[2] + this.votosEmBranco[3] + this.votosEmBranco[4];
        return "Votos válidos: " + this.votosValidos + "\n" +
                "Votos nulos em geral: " + votosNulosGeral + "\n" +
                "Votos em branco em geral: " + votosEmBrancoGeral + "\n" +
                "Votos nulos para Deputado(a) Estadual: " + this.votosNulos[0] + "\n" +
                "Votos nulos para Deputado(a) Federal: " + this.votosNulos[1] + "\n" +
                "Votos nulos para Senador(a): " + this.votosNulos[2] + "\n" +
                "Votos nulos para Governador(a): " + this.votosNulos[3] + "\n" +
                "Votos nulos para Presidente: " + this.votosNulos[4] + "\n" +
                "Votos em branco para Deputado(a) Estadual: " + this.votosEmBranco[0] + "\n" +
                "Votos em branco para Deputado(a) Federal: " + this.votosEmBranco[1] + "\n" +
                "Votos em branco para Senador(a): " + this.votosEmBranco[2] + "\n" +
                "Votos em branco para Governador(a): " + this.votosEmBranco[3] + "\n" +
                "Votos em branco para Presidente: " + this.votosEmBranco[4];
    }

    public void incrementarVotosNulos(int i) {
        this.votosNulos[i]++;
    }

    public void incrementarVotosEmBranco(int i) {
        this.votosEmBranco[i]++;
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
            } else if (candidato instanceof Governador) {
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
            throw new CandidatoNaoEncontradoException();
        }

        return candidato;
    }

    public void addVotoAoCandidato(Candidato candidato) {
        candidato.receberVoto();
    }

    public void imprimirInformacoesCandidato(Candidato candidato) {
        System.out.println(candidato);
    }

    public void imprimirListaCandidatosASenador() {
        if (!this.senadores.isEmpty()) {
            for (Candidato c : this.senadores) {
                System.out.println(c.getDetalhesCandidato() + "\n");
            }

        } else
            System.out.println("Nao ha candidatos a Senador a exibir!");
    }

    public void imprimirListaCandidatosAPresidente() {
        if (!this.presidentes.isEmpty()) {
            for (Presidente p : this.presidentes) {
                System.out.println(p.getDetalhesCandidato() + "\n");
            }

        } else
            System.out.println("Nao ha candidatos a Presidente a exibir!");
    }

    public void imprimirListaCandidatosAGovernador() {
        if (!this.governadores.isEmpty()) {
            for (Candidato c : this.governadores) {
                System.out.println(c.getDetalhesCandidato() + "\n");
            }

        } else
            System.out.println("Nao ha candidatos a Governador a exibir!");
    }

    public void imprimirListaCandidatosADepFederal() {
        if (!this.deputadosFederais.isEmpty()) {
            for (Candidato c : this.deputadosFederais) {
                System.out.println(c.getDetalhesCandidato() + "\n");
            }

        } else
            System.out.println("Nao ha candidatos a Dep. Federal a exibir!");
    }

    public void imprimirListaCandidatosADepEstadual() {
        if (!this.deputadosEstaduais.isEmpty()) {
            for (Candidato c : this.deputadosEstaduais) {
                System.out.println(c.getDetalhesCandidato() + "\n");
            }

        } else {
            System.out.println("Não há candidatos a Dep. Estadual a exibir!");
        }
    }

    public HashSet<Presidente> getPresidentes() {
        return presidentes;
    }

    public void setCandidatos(HashSet<Candidato> candidatos) {
        this.candidatos = candidatos;
    }

    public List<Presidente> apuracaoPresidente() {

        Presidente presidenteEleito = null; //FIXME: Essa variável nem é utilizada denro do escopo.

        List<Presidente> listCandidatos = new ArrayList<Presidente>();
        for (Presidente i : this.presidentes) {
            listCandidatos.add(i);
        }

        Collections.sort(listCandidatos);  //marcos

        return listCandidatos;
    }

    public List<Governador> apuracaoGov() {

        List<Governador> listCandidatos = new ArrayList<Governador>();

        for (Governador i : this.governadores) {
            listCandidatos.add(i);
        }

        Collections.sort(listCandidatos);

        return listCandidatos;
    }

    public List<Senador> apuracaoSenador() {


        List<Senador> listCandidatos = new ArrayList<Senador>();
        for (Senador i : this.senadores) {
            listCandidatos.add(i);
        }
        Collections.sort(listCandidatos);

        return listCandidatos;
    }

    public List<DeputadoFederal> apuracaoDF() {

        List<DeputadoFederal> listCandidatos = new ArrayList<DeputadoFederal>(deputadosFederais);

        for (DeputadoFederal i : this.deputadosFederais) {
            listCandidatos.add(i);
        }


        Collections.sort(listCandidatos);

        if (listCandidatos.size() > 3) {
            for (int i = 3; i < listCandidatos.size(); i++)  // deleta excesso da lista
                listCandidatos.remove(i);
        }

        return listCandidatos;
    }

    public List<DeputadoEstadual> apuracaoDE() {

        List<DeputadoEstadual> listCandidatos = new ArrayList<DeputadoEstadual>();

        for (DeputadoEstadual i : this.deputadosEstaduais) {
            listCandidatos.add(i);
        }

        Collections.sort(listCandidatos);

        if (listCandidatos.size() > 3) {
            for (int i = 3; i < listCandidatos.size(); i++)  // deleta excesso da lista
                listCandidatos.remove(i);
        }

        return listCandidatos;
    }

    public int getVotosTotaisPresidente() {
        return this.votosTotaisPresidente;
    }

    public int getVotosTotaisGovernador() {
        return this.votosTotaisGovernador;
    }

    public int getVotosTotaisSenador() {
        return this.votosTotaisSenador;
    }

    public int getVotosTotaisDepFederal() {
        return this.votosTotaisDepFederal;
    }

    public int getVotosTotaisDepEstadual() {
        return this.votosTotaisDepEstadual;
    }

    public void incrementarVotosTotaisPresidente() {
        this.votosTotaisPresidente++;
    }

    public void incrementarVotosTotaisGovernador() {
        this.votosTotaisGovernador++;
    }

    public void incrementarVotosTotaisSenador() {
        this.votosTotaisSenador++;
    }

    public void incrementarVotosTotaisDepFederal() {
        this.votosTotaisDepFederal++;
    }

    public void incrementarVotosTotaisDepEstadual() {
        this.votosTotaisDepEstadual++;
    }

    public int getTotalVotosNulos() {
        int total = 0;
        for (int votosNulo : this.votosNulos) {
            total += votosNulo;
        }
        return total;
    }

    public int getTotalVotosEmBranco() {
        int total = 0;
        for (int votosEmBranco : this.votosEmBranco) {
            total += votosEmBranco;
        }
        return total;
    }

}
