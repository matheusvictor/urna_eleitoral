package excecoes;

public class CandidatoNaoEncontradoException extends Exception {

    public CandidatoNaoEncontradoException() {
        super("Candidato não encontrado! Voto será contabilizado como nulo.");
    }

}
