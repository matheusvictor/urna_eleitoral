import constantes.CargosCandidatos;
import excecoes.CandidatoNaoEncontradoException;
import modelos.*;
import service.GeradorDeCandidatosEPartidosService;

import java.util.InputMismatchException;
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

    public static void imprimirMenuConfirmacaoDeVoto() {
        System.out.print("Digite S para confimar seu voto ou N para corrigir: ");
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

                        String[] cargos = new String[]
                                {
                                        CargosCandidatos.DEPUTADO_ESTADUAL.toUpperCase(),
                                        CargosCandidatos.DEPUTADO_FEDERAL.toUpperCase(),
                                        CargosCandidatos.SENADOR.toUpperCase(),
                                        CargosCandidatos.GOVERNADOR.toUpperCase(),
                                        CargosCandidatos.PRESIDENTE.toUpperCase()
                                };

                        int indiceCargos = 0;
                        int numeroCandidato;
                        Candidato candidato = null;
                        String opcaoDeConfirmacao = "";

                        do {

                            do {
                              
                              do{
                                System.out.println("Caso queira votar em branco digite 0!");
                                System.out.print("Digite o número do candidato a " + cargos[indiceCargos] + ": ");

                                try {
                                    numeroCandidato = scanner.nextInt();
                                    if (numeroCandidato == 0){
                                      System.out.println("Voto será contabilizado como em branco!");
                                      imprimirMenuConfirmacaoDeVoto();
                                      opcaoDeConfirmacao = scanner.next();
                                      opcaoDeConfirmacao = opcaoDeConfirmacao.toUpperCase();
  
                                      if (opcaoDeConfirmacao.equals("S")) {
                                          urnaEleitoral.incrementarVotosEmBranco(indiceCargos);
                                          System.out.println("Voto em Branco computado!");
                                      }
                                    }else{
                                        try {
                                          candidato = urnaEleitoral.encontrarCandidato(numeroCandidato, cargos[indiceCargos]);
                                      } catch (CandidatoNaoEncontradoException naoEncontradoException) {
                                          System.out.println(naoEncontradoException.getMessage());
                                      }
  
                                      imprimirMenuConfirmacaoDeVoto();
                                      opcaoDeConfirmacao = scanner.next();
                                      opcaoDeConfirmacao = opcaoDeConfirmacao.toUpperCase();
  
                                      if (opcaoDeConfirmacao.equals("S")) {
  
                                          if (candidato != null) {
                                              urnaEleitoral.addVotoAoCandidato(candidato);
                                              System.out.println("Voto no candidato " + candidato.getNome() + " computado!");
                                          } else {
                                              urnaEleitoral.incrementarVotosNulos(indiceCargos);
                                            System.out.println("Voto Nulo computado!");
                                          }
                                      }
                                    }
                                  
                                } catch (InputMismatchException e) {
                                    System.out.println("Entrada inválida!");
                                }
                              }while(opcaoDeConfirmacao.equals("N"));
                            indiceCargos++;

                            } while (indiceCargos < 5);
                          System.out.print("Continuar [S/n]: ");
                          pararVotacao = scanner.next().toUpperCase();

                          if (pararVotacao.startsWith("N")) {
                              votacaoEmAndamento = false;
                          }else{
                            indiceCargos=0;
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

                        int total = urnaEleitoral.ttPresid();
                        if (total == 0) total = 1;
                        float perc;

                        System.out.println("\nA P U R A Ç Ã O");
                        System.out.println("================");

                        System.out.println("Votos para presidente:");
                        for (Presidente i : urnaEleitoral.apuracaoPresidente()) {
                            perc = (float) i.getNumeroVotos() / total;
                            System.out.printf("%s - %s - %d voto(s) (%.1f %%)\n", i.getNome(), i.getPartido().getNomePartido(),
                                    i.getNumeroVotos(), perc * 100);
                        }

                        if (urnaEleitoral.ttPresid() > 0) {
                            System.out.println("\nPresidente eleito: ");
                            System.out.println(urnaEleitoral.apuracaoPresidente().get(0).getNome());

                        } else {
                            System.out.println("\nNao há Presidente eleito!");
                        }

                        System.out.println("===============================");

                        System.out.println("Votos para Governador:");
                        total = urnaEleitoral.ttGov();
                        if (total == 0) total = 1;

                        for (Governador i : urnaEleitoral.apuracaoGov()) {
                            perc = (float) i.getNumeroVotos() / total;
                            System.out.printf("%s - %s - %d votos (%.1f %%)\n", i.getNome(), i.getPartido().getNomePartido(),
                                    i.getNumeroVotos(), perc * 100);
                        }
                        if (urnaEleitoral.ttGov() > 0) {
                            System.out.println("\nGovernador eleito: ");
                            System.out.println(urnaEleitoral.apuracaoPresidente().get(0).getNome());

                        } else {
                            System.out.println("\nNao há Governador eleito!");
                        }
                        System.out.println("===============================");

                        total = urnaEleitoral.ttSenad();
                        if (total == 0) total = 1;

                        System.out.println("\nSenadores eleitos: ");
                        for (Senador i : urnaEleitoral.apuracaoSenador()) {
                            perc = (float) i.getNumeroVotos() / total;
                            System.out.printf("%s - %s - %d voto(s) (%.1f %%)\n", i.getNome(), i.getPartido().getNomePartido(),
                                    i.getNumeroVotos(), perc * 100);
                        }
                        System.out.println("===============================");
                        total = urnaEleitoral.ttDepFed();
                        if (total == 0) total = 1;

                        System.out.println("\nDeputados Federais eleitos: ");
                        for (DeputadoFederal i : urnaEleitoral.apuracaoDF()) {
                            perc = (float) i.getNumeroVotos() / total;
                            System.out.printf("%s - %s - %d voto(s) (%.1f %%)\n", i.getNome(), i.getPartido().getNomePartido(),
                                    i.getNumeroVotos(), perc * 100);
                        }
                        System.out.println("===============================");
                        total = urnaEleitoral.ttDepEst();
                        if (total == 0) total = 1;

                        System.out.println("\nDeputados Estaduais eleitos: ");
                        for (DeputadoEstadual i : urnaEleitoral.apuracaoDE()) {
                            perc = (float) i.getNumeroVotos() / total;
                            System.out.printf("%s - %s - %d voto(s) (%.1f %%)\n", i.getNome(), i.getPartido().getNomePartido(),
                                    i.getNumeroVotos(), perc * 100);
                        }
                        System.out.println("===============================");
                        System.out.println("Votos NULOS: " + urnaEleitoral.getVotosNulos());
                        System.out.println("Votos em BRANCO: " + urnaEleitoral.getVotosEmBranco());


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
