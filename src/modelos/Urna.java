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
    //private int votosNulos = 0;
    private int votosValidos = 0;
    //private int votosEmBranco = 0;
    private int votosEmBranco[] = new int[5];
    private int votosNulos[] = new int[5];

    private static int votosTotaisPresidente = 0, votosTotaisGovernador = 0,
            votosTotaisSenador = 0, votosTotaisDepFed = 0, votosTotaisDepEst = 0;

    public static int ttPresid() {
        return votosTotaisPresidente;
    }

    public static int ttGov() {
        return votosTotaisGovernador;
    }

    public static int ttSenad() {
        return votosTotaisSenador;
    }

    public static int ttDepFed() {
        return votosTotaisDepFed;
    }

    public static int ttDepEst() {
        return votosTotaisDepEst;
    }

    //*******
    public static void incPresid() {
        votosTotaisPresidente++;
    }

    public static void incGov() {
        votosTotaisGovernador++;
    }

    public static void incSenad() {
        votosTotaisSenador++;
    }

    public static void incDepFed() {
        votosTotaisDepFed++;
    }

    public static void incDepEst() {
        votosTotaisPresidente++;
    }
    //***************************************************

    public String getZeresima() {
        int votosNulosGeral = this.votosNulos[0]+this.votosNulos[1]+this.votosNulos[2]+this.votosNulos[3]+this.votosNulos[4];
        int votosEmBrancoGeral = this.votosEmBranco[0]+this.votosEmBranco[1]+this.votosEmBranco[2]+this.votosEmBranco[3]+this.votosEmBranco[4];
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

    public void habilitarUrnaParaVotacao(GeradorDeCandidatosEPartidosService service) {
        this.setCandidatos(service.getCandidatos());
        filtrarCandidatosPorCargo();
    }

    public void incrementarVotosNulos(int i) {
        this.votosNulos[i]++;
    }

    public int[] getVotosNulos() {
        return votosNulos;
    }

    public void incrementarVotosEmBranco(int i) {
        this.votosEmBranco[i]++;
    }

    public int[] getVotosEmBranco() {
        return votosEmBranco;
    }

    //***********************************************************************************
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
        if (!this.senadores.isEmpty()) {                      //acrescentado por Marcos (null -> is_empty) 
            for (Candidato c : this.senadores) {
                System.out.println(c.getDetalhesCandidato() + "\n");
            }

        } else
            System.out.println("Nao ha candidatos a Senador a exibir!");  //acrescentado por Marcos (null -> is_empty)

    }

    public void imprimirListaCandidatosAPresidente() {
        if (!this.presidentes.isEmpty()) {
            for (Presidente p : this.presidentes) {
                System.out.println(p.getDetalhesCandidato() + "\n");
            }

        } else
            System.out.println("Nao ha candidatos a Presidente a exibir!");  //acrescentado por Marcos
    }

    public void imprimirListaCandidatosAGovernador() {
        if (!this.governadores.isEmpty()) {
            for (Candidato c : this.governadores) {
                System.out.println(c.getDetalhesCandidato() + "\n");
            }

        } else
            System.out.println("Nao ha candidatos a Governador a exibir!"); //acrescentado por marcos
    }

    public void imprimirListaCandidatosADepFederal() {
        if (!this.deputadosFederais.isEmpty()) {        //acrescentado por Marcos (null -> is_empty)
            for (Candidato c : this.deputadosFederais) {
                System.out.println(c.getDetalhesCandidato() + "\n");
            }

        } else
            System.out.println("Nao ha candidatos a Dep. Federal a exibir!"); //acrescentado por marcos
    }

    //*******************************************************************************************
    public void imprimirListaCandidatosADepEstadual() {
        if (!this.deputadosEstaduais.isEmpty()) {              //acrescentado por Marcos (null -> is_empty)
            for (Candidato c : this.deputadosEstaduais) {
                System.out.println(c.getDetalhesCandidato() + "\n");
            }

        } else
            System.out.println("Nao ha candidatos a Dep. Estadual a exibir!"); //acrescentado por marcos
    }

    //**********************************************************************
    public HashSet<Presidente> getPresidentes() {
        return presidentes;
    }

//**********************************************************************************

    public void setCandidatos(HashSet<Candidato> candidatos) {
        this.candidatos = candidatos;
    }

    //*************************************************************************


    public List<Presidente> apuracaoPresidente() {

        Presidente presidenteEleito = null;

        List<Presidente> listCandidatos = new ArrayList<Presidente>();
        for (Presidente i : this.presidentes) {
            listCandidatos.add(i);
        }

        Collections.sort(listCandidatos);  //marcos

        return listCandidatos;
    }
//********************************************************************************

    public List<Governador> apuracaoGov() {

        List<Governador> listCandidatos = new ArrayList<Governador>();

        for (Governador i : this.governadores) {
            listCandidatos.add(i);
        }

        Collections.sort(listCandidatos);

        return listCandidatos;
    }

    //************************************************************
    public List<Senador> apuracaoSenador() {


        List<Senador> listCandidatos = new ArrayList<Senador>();
        for (Senador i : this.senadores) {
            listCandidatos.add(i);
        }
        //*****************************************
        Collections.sort(listCandidatos);

        return listCandidatos;
    }

    //*********************************************************************
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

    //**********************************************************************************
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
//*******************************************************************************
}
