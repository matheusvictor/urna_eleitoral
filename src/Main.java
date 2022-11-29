import constantes.CargosCandidatos;
import excecoes.CandidatoNaoEncontradoException;
import modelos.*;
import service.GeradorDeCandidatosEPartidosService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final GeradorDeCandidatosEPartidosService service = GeradorDeCandidatosEPartidosService.getInstance();
    private static Urna urnaEleitoral = null;

    public static void imprimirMenuPrincipal() {
        System.out.println("============================================================");
        if (urnaEleitoral == null) {
            System.out.println("0 - Iniciar eleição");
        }
        if (urnaEleitoral != null) {
            System.out.println("1 - Imprimir zerésima");
            System.out.println("2 - Iniciar votações");
            System.out.println("3 - Ver lista de candidatos a dep. estadual");
            System.out.println("4 - Ver lista de candidatos a dep. federal");
            System.out.println("5 - Ver lista de candidatos a senador");
            System.out.println("6 - Ver lista de candidatos a governador");
            System.out.println("7 - Ver lista de candidatos a presidente");
            System.out.println("8 - Imprimir relatório");
            System.out.println("9 - Zerar urna");
        }
        System.out.println("99 - Encerrar o programa");
        System.out.println("============================================================");
    }

    public static void imprimirMenuConfirmacaoDeVoto() {
        System.out.print("Digite S para confimar seu voto ou N para corrigir: ");
    }

    public static void executarMenuPrincipal() {

        int opcao = 0;

        do {

            do {
                imprimirMenuPrincipal();
                System.out.print("Digite uma opção: ");

                try {
                    opcao = Integer.parseInt(scanner.next());

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

                            int indiceCargos = 0;

                            if (urnaEleitoral != null) {

                                String pararVotacao;
                                boolean votacaoEmAndamento = true;

                                String[] cargos = new String[]{
                                        CargosCandidatos.DEPUTADO_ESTADUAL,
                                        CargosCandidatos.DEPUTADO_FEDERAL,
                                        CargosCandidatos.SENADOR,
                                        CargosCandidatos.GOVERNADOR,
                                        CargosCandidatos.PRESIDENTE
                                };

                                int numeroCandidato;
                                Candidato candidato;
                                String opcaoDeConfirmacao;
                                String numeroCanditatoString;

                                do {

                                    do {

                                        do {

                                            do {

                                                candidato = null;
                                                System.out.println("Caso queira votar em branco digite 0!");
                                                System.out.print("Digite o número do candidato a " + cargos[indiceCargos].toUpperCase() + ": ");

                                                try {
                                                    numeroCandidato = scanner.nextInt();
                                                    numeroCanditatoString = Integer.toString(numeroCandidato);

                                                    switch (indiceCargos) {
                                                        case 0 -> {
                                                            while (numeroCanditatoString.length() != 5 && !numeroCanditatoString.equals("0")) {
                                                                do {
                                                                    System.out.println("O número deve ter 5 digitos!");
                                                                    System.out.print("Digite o número do candidato a " + cargos[indiceCargos].toUpperCase() + ": ");
                                                                    try {
                                                                        numeroCandidato = scanner.nextInt();
                                                                        numeroCanditatoString = Integer.toString(numeroCandidato);
                                                                        break;
                                                                    } catch (InputMismatchException e) {
                                                                        System.out.println("Entrada inválida!");
                                                                        scanner.nextLine();
                                                                    }
                                                                } while (true);
                                                            }
                                                        }
                                                        case 1 -> {
                                                            while (numeroCanditatoString.length() != 4 && !numeroCanditatoString.equals("0")) {
                                                                do {
                                                                    System.out.println("O número deve ter 4 digitos!");
                                                                    System.out.print("Digite o número do candidato a " + cargos[indiceCargos].toUpperCase() + ": ");
                                                                    try {
                                                                        numeroCandidato = scanner.nextInt();
                                                                        numeroCanditatoString = Integer.toString(numeroCandidato);
                                                                        System.out.println(numeroCanditatoString.length());
                                                                        break;
                                                                    } catch (InputMismatchException e) {
                                                                        System.out.println("Entrada inválida!");
                                                                        scanner.nextLine();
                                                                    }
                                                                } while (true);
                                                            }
                                                        }
                                                        case 2 -> {
                                                            while (numeroCanditatoString.length() != 3 && !numeroCanditatoString.equals("0")) {
                                                                do {
                                                                    System.out.println("O número deve ter 3 digitos!");
                                                                    System.out.print("Digite o número do candidato a " + cargos[indiceCargos].toUpperCase() + ": ");
                                                                    try {
                                                                        numeroCandidato = scanner.nextInt();
                                                                        numeroCanditatoString = Integer.toString(numeroCandidato);
                                                                        break;
                                                                    } catch (InputMismatchException e) {
                                                                        System.out.println("Entrada inválida!");
                                                                        scanner.nextLine();
                                                                    }
                                                                } while (true);
                                                            }
                                                        }
                                                        case 3, 4 -> {
                                                            while (numeroCanditatoString.length() != 2 && !numeroCanditatoString.equals("0")) {
                                                                do {
                                                                    System.out.println("O número deve ter 2 digitos!");
                                                                    System.out.print("Digite o número do candidato a " + cargos[indiceCargos].toUpperCase() + ": ");
                                                                    try {
                                                                        numeroCandidato = scanner.nextInt();
                                                                        numeroCanditatoString = Integer.toString(numeroCandidato);
                                                                        System.out.println(numeroCanditatoString.length());
                                                                        break;
                                                                    } catch (InputMismatchException e) {
                                                                        System.out.println("Entrada inválida!");
                                                                        scanner.nextLine();
                                                                    }
                                                                } while (true);
                                                            }
                                                        }
                                                    }
                                                    break;
                                                } catch (InputMismatchException e) {
                                                    System.out.println("Entrada inválida!");
                                                    scanner.nextLine();
                                                }
                                            } while (true);

                                            if (numeroCandidato == 0) {
                                                System.out.println("Voto será contabilizado como em branco!");
                                                imprimirMenuConfirmacaoDeVoto();
                                                opcaoDeConfirmacao = scanner.next();
                                                opcaoDeConfirmacao = opcaoDeConfirmacao.toUpperCase();

                                                if (opcaoDeConfirmacao.equals("S")) {
                                                    urnaEleitoral.incrementarVotosEmBranco(indiceCargos);
                                                    System.out.println("Voto em Branco computado!");
                                                }

                                            } else {

                                                try {
                                                    candidato = urnaEleitoral.encontrarCandidato(numeroCandidato, cargos[indiceCargos]);
                                                } catch (CandidatoNaoEncontradoException naoEncontradoException) {
                                                    System.out.println(naoEncontradoException.getMessage());
                                                }
                                                if (candidato != null) {
                                                    System.out.println("Candidato: " + candidato.getNome() + "\n" +
                                                            candidato.getPartido().getNomePartido() + " - " + candidato.getPartido().getSiglaPartido());
                                                }

                                                imprimirMenuConfirmacaoDeVoto();
                                                opcaoDeConfirmacao = scanner.next();
                                                opcaoDeConfirmacao = opcaoDeConfirmacao.toUpperCase();

                                                if (opcaoDeConfirmacao.equals("S")) {

                                                    if (candidato != null) {
                                                        urnaEleitoral.addVotoAoCandidato(candidato);

                                                        switch (indiceCargos) {
                                                            case 0 -> {
                                                                urnaEleitoral.incrementarVotosTotaisDepEstadual();
                                                            }
                                                            case 1 -> {
                                                                urnaEleitoral.incrementarVotosTotaisDepFederal();
                                                            }
                                                            case 2 -> {
                                                                urnaEleitoral.incrementarVotosTotaisSenador();
                                                            }
                                                            case 3 -> {
                                                                urnaEleitoral.incrementarVotosTotaisGovernador();
                                                            }
                                                            case 4 -> {
                                                                urnaEleitoral.incrementarVotosTotaisPresidente();
                                                            }
                                                        }
                                                        System.out.println("Voto no candidato " + candidato.getNome() + " computado!");
                                                    } else {
                                                        urnaEleitoral.incrementarVotosNulos(indiceCargos);
                                                        System.out.println("Voto Nulo computado!");
                                                    }
                                                }
                                            }
                                        } while (opcaoDeConfirmacao.equals("N"));
                                        indiceCargos++;

                                    } while (indiceCargos < 5);
                                    System.out.print("Continuar [S/n]: ");
                                    pararVotacao = scanner.next().toUpperCase();

                                    if (pararVotacao.startsWith("N")) {
                                        votacaoEmAndamento = false;
                                    } else {
                                        indiceCargos = 0;
                                    }

                                } while (votacaoEmAndamento);
                            }

                        }

                        case 3, 4, 5, 6, 7 -> {
                            if (urnaEleitoral != null) {
                                //FIXME: As listas podem ser impressas de um jeito mais bem formatado
                                if (opcao == 3) {
                                    System.out.print("\n");
                                    urnaEleitoral.imprimirListaCandidatosADepEstadual();
                                } else if (opcao == 4) {
                                    System.out.print("\n");
                                    urnaEleitoral.imprimirListaCandidatosADepFederal();
                                } else if (opcao == 5) {
                                    System.out.print("\n");
                                    urnaEleitoral.imprimirListaCandidatosASenador();
                                } else if (opcao == 6) {
                                    System.out.print("\n");
                                    urnaEleitoral.imprimirListaCandidatosAGovernador();
                                } else {
                                    System.out.print("\n");
                                    urnaEleitoral.imprimirListaCandidatosAPresidente();
                                }
                            }
                        }
                        case 8 -> {

                            int total = urnaEleitoral.getVotosTotaisPresidente();
                            if (total == 0) total = 1; // FIXME
                            float perc;

                            System.out.println("\nA P U R A Ç Ã O");
                            System.out.println("================");

                            System.out.println("Votos para presidente:");
                            for (Presidente i : urnaEleitoral.apuracaoPresidente()) {
                                perc = (float) i.getNumeroVotos() / total;
                                System.out.printf("%s - %s - %d voto(s) (%.1f %%)\n", i.getNome(), i.getPartido().getNomePartido(),
                                        i.getNumeroVotos(), perc * 100);
                            }

                            if (urnaEleitoral.getVotosTotaisPresidente() > 0) {
                                System.out.println("\nPresidente eleito: ");
                                System.out.println(urnaEleitoral.apuracaoPresidente().get(0).getNome());

                            } else {
                                System.out.println("\nNao há Presidente eleito!");
                            }

                            System.out.println("===============================");


                            System.out.println("Votos para Governador:");
                            total = urnaEleitoral.getVotosTotaisGovernador();
                            if (total == 0) total = 1;

                            for (Governador i : urnaEleitoral.apuracaoGov()) {
                                perc = (float) i.getNumeroVotos() / total;
                                System.out.printf("%s - %s - %d votos (%.1f %%)\n", i.getNome(), i.getPartido().getNomePartido(),
                                        i.getNumeroVotos(), perc * 100);
                            }
                            if (urnaEleitoral.getVotosTotaisGovernador() > 0) {
                                System.out.println("\nGovernador eleito: ");
                                System.out.println(urnaEleitoral.apuracaoPresidente().get(0).getNome());

                            } else {
                                System.out.println("\nNao há Governador eleito!");
                            }
                            System.out.println("===============================");

                            total = urnaEleitoral.getVotosTotaisSenador();
                            if (total == 0) total = 1;

                            System.out.println("\nSenadores eleitos: ");
                            for (Senador i : urnaEleitoral.apuracaoSenador()) {
                                perc = (float) i.getNumeroVotos() / total;
                                System.out.printf("%s - %s - %d voto(s) (%.1f %%)\n", i.getNome(), i.getPartido().getNomePartido(),
                                        i.getNumeroVotos(), perc * 100);
                            }
                            System.out.println("===============================");
                            total = urnaEleitoral.getVotosTotaisDepFederal();
                            if (total == 0) total = 1;

                            System.out.println("\nDeputados Federais eleitos: ");
                            for (DeputadoFederal i : urnaEleitoral.apuracaoDF()) {
                                perc = (float) i.getNumeroVotos() / total;
                                System.out.printf("%s - %s - %d voto(s) (%.1f %%)\n", i.getNome(), i.getPartido().getNomePartido(),
                                        i.getNumeroVotos(), perc * 100);
                            }
                            System.out.println("===============================");
                            total = urnaEleitoral.getVotosTotaisDepEstadual();
                            if (total == 0) total = 1;

                            System.out.println("\nDeputados Estaduais eleitos: ");
                            for (DeputadoEstadual i : urnaEleitoral.apuracaoDE()) {
                                perc = (float) i.getNumeroVotos() / total;
                                System.out.printf("%s - %s - %d voto(s) (%.1f %%)\n", i.getNome(), i.getPartido().getNomePartido(),
                                        i.getNumeroVotos(), perc * 100);
                            }
                            System.out.println("===============================");
                            System.out.println("Votos NULOS: " + urnaEleitoral.getTotalVotosNulos());
                            System.out.println("Votos em BRANCO: " + urnaEleitoral.getTotalVotosEmBranco());
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
                } catch (NumberFormatException numberFormatException) {
                    System.out.println("A opção digitada precisa ser um dos número listados no menu.");
                }

                if (urnaEleitoral == null && opcao != 99 && opcao != 0) {
                    System.out.println("Opção inválida!");
                }

            } while (opcao != 99 && opcao != 0);

        } while (true);
    }

    public static void main(String[] args) {
        executarMenuPrincipal();
    }

}
