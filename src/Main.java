import constantes.CargosCandidatos;
import modelos.Presidente;
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
            System.out.println("0 - Iniciar eleição");
        }
        if (urnaEleitoral != null) {
            System.out.println("1 - Imprimir zeréssima");
            System.out.println("2 - Iniciar votações");
            System.out.println("3 - Ver lista de candidatos a senador");
            System.out.println("4 - Ver lista de candidatos a dep. estadual");
            System.out.println("5 - Ver lista de candidatos a dep. federal");
            System.out.println("6 - Ver lista de candidatos a dep. governador");
            System.out.println("7 - Ver lista de candidatos a presidente");
            System.out.println("8 - Imprimir relatório");
            System.out.println("9 - Zerar urna");
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
                    case 0 -> {
                        if (urnaEleitoral == null) {
                            urnaEleitoral = new Urna();
                            urnaEleitoral.habilitarUrnaParaVotacao(service);
                        }
                    }
                    case 1 -> {
                        if (urnaEleitoral != null) {
                            System.out.println("============================================================");
                            System.out.println(urnaEleitoral.getZeresima());
                        }
                    }
                    case 2 -> {

                        String pararVotacao;
                        boolean votacaoEmAndamento = true;
                        do {

                            //FIXME: Tentar encontrar uma maneira que faça o mesmo com menos repetições de código
                            //TODO: Adicionar Exception para o caso do usuário digitar uma letra ao invés de número

                            int numeroCandidato;
                            System.out.print("Digite o número do candidato a senador");
                            numeroCandidato = scanner.nextInt();

                            //TODO: Antes de contabilizar o voto, é interessante perguntar se o eleitor quer confirmar
                            urnaEleitoral.addVotoAoCandidato(numeroCandidato, CargosCandidatos.SENADOR);

                            System.out.print("Digite o número do candidato a dep. estadual: ");
                            numeroCandidato = scanner.nextInt();
                            urnaEleitoral.addVotoAoCandidato(numeroCandidato, CargosCandidatos.DEPUTADO_ESTADUAL);

                            System.out.print("Digite o número do candidato a dep. federal: ");
                            numeroCandidato = scanner.nextInt();
                            urnaEleitoral.addVotoAoCandidato(numeroCandidato, CargosCandidatos.DEPUTADO_FEDERAL);

                            System.out.print("Digite o número do candidato a presidente: ");
                            numeroCandidato = scanner.nextInt();
                            urnaEleitoral.addVotoAoCandidato(numeroCandidato, CargosCandidatos.PRESIDENTE);

                            System.out.print("Continuar [S/n]: ");
                            pararVotacao = scanner.next().toUpperCase();

                            if (pararVotacao.startsWith("N")) {
                                votacaoEmAndamento = false;
                            }

                        } while (votacaoEmAndamento);
                    }

                    //TODO: case 3 a 7 são muito parecidos. Será que encontramos outra forma de escrever isso?
                    //FIXME: As listas podem ser impressas de um jeito mais bem formatado
                    case 3, 4, 5, 6, 7 -> {
                        if (urnaEleitoral == null) {
                            System.out.println("As votações precisam ser iniciadas para visualizar a lista de candidatos!");
                        } else {
                            if (opcao == 3) {
                                urnaEleitoral.imprimirListaCandidatosASenador();
                            } else if (opcao == 4) {
                                urnaEleitoral.imprimirListaCandidatosADepEstadual();
                            } else if (opcao == 5) {
                                urnaEleitoral.imprimirListaCandidatosADepFederal();
                            } else if (opcao == 6) {
                                urnaEleitoral.imprimirListaCandidatosAGovernador();
                            } else {
//                                urnaEleitoral.imprimirListaCandidatosAPresidente();
                                if (urnaEleitoral.getPresidentes().isEmpty()) {
                                    System.out.println("vazio");
                                } else {
                                    for (Presidente presidente : urnaEleitoral.getPresidentes()) {
                                        System.out.println(presidente);
                                    }
                                }
                            }
                        }
                    }
                    case 8 -> {
                        // TODO
                    }
                    case 9 -> {
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
