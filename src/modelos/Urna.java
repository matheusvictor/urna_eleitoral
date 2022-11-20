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
    private static void incPresid() {
    	 votosTotaisPresidente++;
    }
    private static void incGov() {
    	 votosTotaisGovernador++;
    }
    private static void incSenad() {
    	 votosTotaisSenador++;
    }
    private static void incDepFed() {
    	 votosTotaisDepFed++;
    }
    private static void incDepEst() {
    	votosTotaisPresidente++;
    }
    //***************************************************

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
            } else if (candidato instanceof Governador){
                this.governadores.add((Governador) candidato);
            }
        }
    }
//******************************************************************************
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
//*************************************************************************
    public Candidato addVotoAoCandidato(int numero, String cargo) {
        Candidato candidato = null;
        try {
            candidato = encontrarCandidato(numero, cargo);
            //candidato.receberVoto();
            
            if(candidato instanceof Presidente)
            	this.incPresid();
            else if(candidato instanceof Governador) 
            	this.incGov();
            
            else if(candidato instanceof Senador) 
            	this.incSenad();
            
            else if(candidato instanceof DeputadoFederal) 
            	this.incDepFed();
            
            else 
            	this.incDepEst();
            
        } catch (CandidatoNaoEncontradoException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
        return candidato;  //alteração de retorno
    }
//***************************************************************************
    public void imprimirInformacoesCandidato(Candidato candidato) {
        System.out.println(candidato);
    }
//********************************************************************************
    public void imprimirListaCandidatosASenador() {
        if (!this.senadores.isEmpty()) {                      //acrescentado por Marcos (null -> is_empty)
            for (Candidato c : this.senadores) {
                System.out.println(c.getNome() + " -- " + c.getPartido().getNomePartido());
                System.out.println("----------------------");
            }
        
        }else 
        	System.out.println("Nao ha candidatos a Senador a exibir!");  //acrescentado por Marcos (null -> is_empty)
        
    }
//******************************************************************************************
    public void imprimirListaCandidatosAPresidente() {
        if (!this.presidentes.isEmpty()) {
            for (Presidente p : this.presidentes) {
                System.out.println(p.getDetalhesCandidato());
                System.out.println("----------------------");
            }
        
        }else 
        	System.out.println("Nao ha candidatos a Presidente a exibir!");  //acrescentado por Marcos 
    }
//*********************************************************************************************
    public void imprimirListaCandidatosAGovernador() {
        if (!this.governadores.isEmpty()) {
            for (Candidato c : this.governadores) {
                System.out.println(c.getNome() + " -- " + c.getNumero() + " -- " + c.getPartido().getNomePartido());
                System.out.println("----------------------");
            }
        
        }else 
        	System.out.println("Nao ha candidatos a Governador a exibir!"); //acrescentado por marcos
    }
//*******************************************************************************************
    public void imprimirListaCandidatosADepFederal() {
        if (!this.deputadosFederais.isEmpty()) {        //acrescentado por Marcos (null -> is_empty)
            for (Candidato c : this.deputadosFederais) {
                System.out.println(c.getNome() + " -- " + c.getNumero() + " -- " + c.getPartido().getNomePartido());
                System.out.println("----------------------");
            }
        
        }else 
        	System.out.println("Nao ha candidatos a Dep. Federal a exibir!"); //acrescentado por marcos
    }
//*******************************************************************************************
    public void imprimirListaCandidatosADepEstadual() {
        if (!this.deputadosEstaduais.isEmpty()) {              //acrescentado por Marcos (null -> is_empty)
            for (Candidato c : this.deputadosEstaduais) {
                System.out.println(c.getNome() + " -- " + c.getNumero() + " -- " + c.getPartido().getNomePartido());
                System.out.println("----------------------");
            }
        
        }else 
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
        for (Presidente i: this.presidentes) {
            listCandidatos.add(i);
        }
        
        Collections.sort(listCandidatos);  //marcos

        return listCandidatos;
    }
//********************************************************************************
    
    public List<Governador> apuracaoGov() {

        List<Governador> listCandidatos = new ArrayList<Governador>();
        
        for (Governador i: this.governadores) {
            listCandidatos.add(i);
        }

        Collections.sort(listCandidatos);

        return listCandidatos;
    }
    
    //************************************************************
    public List<Senador> apuracaoSenador() {

    
        List<Senador> listCandidatos = new ArrayList<Senador>();
        for (Senador i: this.senadores) {
            listCandidatos.add(i);
        }  
        //*****************************************
        Collections.sort(listCandidatos);

        return listCandidatos;
    }
//*********************************************************************
    public List<DeputadoFederal> apuracaoDF() {

        List<DeputadoFederal> listCandidatos = new ArrayList<DeputadoFederal>(deputadosFederais);
        
        for (DeputadoFederal i: this.deputadosFederais) {
            listCandidatos.add(i);
        }
        
        
        Collections.sort(listCandidatos);
        
        if(listCandidatos.size()>3) {
        	for(int i = 3; i < listCandidatos.size(); i++)  // deleta excesso da lista
        		listCandidatos.remove(i);
        }

        return listCandidatos;
    }
//**********************************************************************************
    public List<DeputadoEstadual> apuracaoDE() {

        List<DeputadoEstadual> listCandidatos = new ArrayList<DeputadoEstadual>();
       
       for (DeputadoEstadual i: this.deputadosEstaduais) {
    	   listCandidatos.add(i);
        }
       
       Collections.sort(listCandidatos);
       
       if(listCandidatos.size()>3) {
       	for(int i = 3; i < listCandidatos.size(); i++)  // deleta excesso da lista
       		listCandidatos.remove(i);
       }

        return listCandidatos;
    }
//*******************************************************************************
}
