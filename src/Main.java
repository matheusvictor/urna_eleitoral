import modelos.Urna;
import service.GeradorDeCandidatosEPartidosService;

import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final GeradorDeCandidatosEPartidosService service =
            GeradorDeCandidatosEPartidosService.getInstance();
    private static Urna urnaEleitoral = null;

    public static void imprimirMenuPrincipal() {
        System.out.println("============================================================");
        if (urnaEleitoral == null) {
            System.out.println("1 - Iniciar eleição");
        }
        if (urnaEleitoral != null) {
            System.out.println("2 - Ver lista de candidatos a senador");
            System.out.println("3 - Ver lista de candidatos a dep. estadual");
            System.out.println("4 - Ver lista de candidatos a dep. federal");
            System.out.println("5 - Ver lista de candidatos a presidente");
            System.out.println("6 - Imprimir relatório");
            System.out.println("7 - Zerar urna");
        }
        System.out.println("99 - Encerrar o programa");
        System.out.println("============================================================");
    }

    public static void executarMenuPrincipal() {
        int opcao;
        do {
            try {
                imprimirMenuPrincipal();

                do {
                    try {
                        System.out.print("Digite uma opção: ");
                        opcao = Integer.parseInt(scanner.next());
                        break;
                    } catch (NumberFormatException err) {
                        System.out.println("Entrada inválida. Digite novamente.");
                    }
                } while (true);

                switch (opcao) {
                    case 1 -> {
                        if (urnaEleitoral == null) {
                            urnaEleitoral = new Urna(service);

                            String pararVotacao;
                            boolean votacaoEmAndamento = true;
                            do {
                                System.out.print("Digite o número do candidato a senador");
                                int numeroSenador = scanner.nextInt();

                                System.out.print("Digite o número do candidato a dep. estadual: ");
                                int numeroEstadual = scanner.nextInt();

                                System.out.print("Digite o número do candidato a dep. federal: ");
                                int numeroFederal = scanner.nextInt();

                                System.out.print("Digite o número do candidato a presidente: ");
                                int numeroPresidente = scanner.nextInt();
                                urnaEleitoral.votarParaPresidente(numeroPresidente);

                                System.out.print("Continuar [s/N]: ");
                                pararVotacao = scanner.next().toUpperCase();

                                if (pararVotacao.startsWith("N")) {
                                    votacaoEmAndamento = false;
                                }

                            } while (votacaoEmAndamento);

                        }
                    }
                    case 3 -> {
                        if (urnaEleitoral == null) {
                            System.out.println("As votações precisam ser iniciadas para visualizar a lista de candidatos!");
                        } else urnaEleitoral.imprimirListaCandidatosAPresidente();
                    }
                    case 10 -> {
                        urnaEleitoral = null;
                        System.out.println("Urna zerada com sucesso!");
                    }
                    case 99 -> {
                        System.out.println("Programa encerrado!");
                        System.exit(0);
                    }
                    default -> {
                        System.out.println("Opção inválida!");
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                break;
            }

        } while (true);
    }

    public static void main(String[] args) {
        executarMenuPrincipal();
    }
}
