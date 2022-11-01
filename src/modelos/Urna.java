package modelos;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import constantes.CargosCandidatos;

import constantes.CargosCandidatos;

public class Urna {
	
    private HashSet<Candidato> presidentes;
    private HashSet<Candidato> governadores;      // cargos disponíveis
    private HashSet<Candidato> senadores;
    private HashSet<Candidato> deputadosFederais;
    private HashSet<Candidato> deputadosEstaduais;
    
    private int brancos, nulos;
    
    public Urna() {
    	brancos  = 0;            //construtor
    	nulos = 0;
    } 
//*************************************************    
	public int getBrancos() {
		return brancos;
	}

	public void incBrancos() {    //getters e setters brancos e nulos
		this.brancos++;
	}      
    
	public int getNulos() {
		return nulos;
	}

	public void incNulos() {
		this.nulos++;
	}    
 //************************************************   
    
    public HashSet<Candidato> getPresidentes(){  //serão responsáveis por lidar com a votação
    	return presidentes;                      // o Main chamará um dos gets para haver votação
    }
    public HashSet<Candidato> getGovernadores(){
    	return governadores;
    }
    public HashSet<Candidato> getSenadores(){
    	return senadores;
    }
    public HashSet<Candidato> getDepFederais(){
    	return deputadosFederais;
    }
    public HashSet<Candidato> getDepestaduais(){
    	return deputadosEstaduais;
    }
 //******************************************************   
    
    public void inserirCandidatos(HashSet<Candidato> c) {
    	
    	for(Candidato i: c) {
    		
    		if(i.getCargo() == CargosCandidatos.PRESIDENTE) {   //verifica cada cargo da lista e insere numa
    			presidentes.add(i);                            //hash acima
    			break;
    		}
    		
    		else if(i.getCargo() == CargosCandidatos.GOVERNADOR) {
    			governadores.add(i);
    			break;
    		}
    		
    		else if(i.getCargo() == CargosCandidatos.SENADOR) {
    			senadores.add(i);
    			break;
    			
    		}
    		else if(i.getCargo() == CargosCandidatos.DEPUTADO_FEDERAL) {
    			deputadosFederais.add(i);
    			break;
    			
    		}
    		else if(i.getCargo() == CargosCandidatos.DEPUTADO_ESTADUAL) {
    			deputadosEstaduais.add(i);
    			break;
    		}
    		
    	}	
    	
    }
 //*******************************************************************  
    
    public void votar(HashSet<Candidato> cd, int numeroInserido) { //uma rotina anterior devera tratar o numero inserido
    	
    	if(numeroInserido == 0) {  //valor escolhido para branco
    		incBrancos();
    	}
    	
    	else {
    		if(cd != null) {
            	boolean consta = false;
            	
            	for(Candidato i: cd) {
            		
            		if(i.getNumero() == numeroInserido) { //candidato encontrado, recebe voto
            			i.setVoto();
            			consta = true;
            			break;
            		}
            		
            	}
            	if(!consta) {
            		incNulos();  //numero inserido não encontrado
            	}    			
    		}

       }
    	
    }
//*****************************************************************
    
    public HashSet <Candidato> apurarVotos(HashSet<Candidato> c) {            //recebe uma lista de candidatos de mesmo cargo
    	
    	//LinkedList <Candidato> ranking = new LinkedList <Candidato>();        // manter ordem na inserção
    	
    	
    	if(c!=null)
    		Collections.sort((List<Candidato>) c); //ordena por numero de votos maior para menor
    	
    	

    	return c;

    	
    }
    
 //****************************************************************************************   
    
    
    //incluir candidatos:
    //verificar cargo e inserir numa das coleções acima
    
     
     /*  
	VOTO MAJORITÁRIO (aquele que atigir 50% dos votos + 1 leva no primeiro turno)

	-prefeito
	-governadores
	-senadores
	-presidente


	VOTO PROPORCIONAL (ocupação de cadeiras de acordo com votos)
	
	Todos candidatos ingressam em maior ou menor número.
	*Na prática, independente de o voto ser individual ou de legenda,
	o voto é contabilizado por partido.

	-vereadores
	-deps ests
	-deps feds
     
     */
    

}
