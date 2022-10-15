package modelos;
import java.util.HashSet;

public class Urna {
	
    private HashSet<Candidato> presidentes;
    private HashSet<Candidato> governadores;
    private HashSet<Candidato> prefeitos;
    private HashSet<Candidato> senadores;
    private HashSet<Candidato> deputadosFederais;
    private HashSet<Candidato> deputadosEstaduais;
    private HashSet<Candidato> vereadores;
    
    
    public void inserirCandidato(Candidato c) {
    	
    }
    
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
