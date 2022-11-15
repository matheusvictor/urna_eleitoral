package excecoes;

public class VotacaoNaoIniciadaException extends Exception {

    public VotacaoNaoIniciadaException() {
        super("As votações precisam ser iniciadas para visualizar a lista de candidatos!");
    }

}
