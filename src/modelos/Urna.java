package modelos;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

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
    
    public LinkedList <Candidato> apurarVotos(HashSet<Candidato> c) {            //recebe uma lista de candidatos de mesmo cargo
    	
    	LinkedList <Candidato> ranking = new LinkedList <Candidato>();        // manter ordem na inserção
    	
    	
    	if(!c.isEmpty() && c.size()>1) {                             //se  lista tiver mais de 1 candidato
    		
    		while(c.size()>0) { //enquanto houver candidatos na lista recebida
    			
        		Candidato maisVotado = ((List<Candidato>) c).get(0);   //pega primeiro candidato e o assume como mais votado
        		
            	for(int i = 1; i<c.size(); i++ ) {
            		
            		if(((List<Candidato>) c).get(i).getNumeroVotos() > maisVotado.getNumeroVotos()) {  //compara com o proximo
            			
            			    maisVotado =   ((List<Candidato>) c).get(i);                                       
            		}
            	}
            	ranking.add(maisVotado);
            	c.remove(maisVotado);
            	
            	
        	}    			
    	}
    	return ranking;

    	/*           
    	O HashSet é o mais rápido de todos, este usa HashTable e seus elementos não são ordenados, 
    	a complexidade desta estrutura é O(1), em outras palavras, não importa o quanto você adicione, 
    	remova, retire, o tempo de execução sempre será o mesmo. E isso é extremamente crítico em processos 
    	onde temos uma situação crítica com milhões de dados a serem inseridos em um Set. Por outro lado, 
    	a garantia de continuidade na ordem dos elementos inseridos é zero, ou seja, esse tipo de estrutura é 
    	indicada se você precisa apenas garantir a alta performance sem se importar com a ordem com que os 
    	elementos estão ordenados. 
    	 
    	 */
    	//ACEITANDO SUGESTÕES DE IMPLEMENTAÇÃO
    	
    }
    
 //****************************************************************************************  
    
     
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

