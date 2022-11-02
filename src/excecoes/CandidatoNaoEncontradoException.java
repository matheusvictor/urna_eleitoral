package excecoes;

public class CandidatoNaoEncontradoException extends Exception {

    public CandidatoNaoEncontradoException(String mensagem) {
        super("Candidato não encontrado!");
    }

}
