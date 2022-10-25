package modelos;

import constantes.CargosCandidatos;
import excecoes.CandidatoNaoEncontradoException;
import service.GeradorDeCandidatosEPartidosService;

import java.util.HashSet;

public class Urna {

    private HashSet<Candidato> senadores = new HashSet<>();
    private HashSet<Candidato> presidentes = new HashSet<>();
    private HashSet<Candidato> governadores = new HashSet<>();
    private HashSet<Candidato> deputadosFederais = new HashSet<>();
    private HashSet<Candidato> deputadosEstaduais = new HashSet<>();

    public Urna(GeradorDeCandidatosEPartidosService service) {
        HashSet<Candidato> candidatos = service.getCandidatos();
        for (Candidato c : candidatos) {
            if (c.getCargo().equals(CargosCandidatos.PRESIDENTE)) {
                this.presidentes.add(c);
            } else if (c.getCargo().equals(CargosCandidatos.SENADOR)) {
                this.senadores.add(c);
            } else if (c.getCargo().equals(CargosCandidatos.GOVERNADOR)) {
                this.governadores.add(c);
            } else if (c.getCargo().equals(CargosCandidatos.DEPUTADO_FEDERAL)) {
                this.deputadosEstaduais.add(c);
            } else {
                this.deputadosFederais.add(c);
            }
        }
    }

    private void filtrarCandidatosPorCargo(HashSet<Candidato> candidatos) {
        // TODO: a filtragem realizada no construtor de Urna poderá acontecer aqui
    }


    public Candidato encontrarCandidato(int numeroCandidato) throws CandidatoNaoEncontradoException {
        // TODO: implementação ainda incompleta
        try {
            for (Candidato c : this.presidentes) {
                if (c.getNumero() == numeroCandidato) {
                    System.out.println("OK");
                }
            }
        } catch (Exception e) {
            throw new CandidatoNaoEncontradoException("N achei");
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
