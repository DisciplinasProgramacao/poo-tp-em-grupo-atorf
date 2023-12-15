package codigo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Esta classe representa o aplicativo principal para gerenciamento de uma frota
 * de veículos.
 * Ela oferece funcionalidades para interação com o usuário através de um
 * console,
 * permitindo a realização de diversas operações relacionadas ao gerenciamento
 * de veículos.
 */
public class App {
    static Scanner scanner = new Scanner(System.in);
    static Frota frota = new Frota(200);

    /**
     * Limpa a tela do console.
     * Este método utiliza códigos de terminal VT-100 para limpar a tela,
     * proporcionando uma interface de usuário mais limpa e organizada.
     */
    public static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * O ponto de entrada principal do aplicativo.
     * Este método inicia a execução do aplicativo, limpa a tela, lê os dados dos
     * veículos de um arquivo,
     * e processa as opções do menu principal.
     *
     * @param args Argumentos da linha de comando (não utilizados neste método).
     * 
     */
    public static void main(String[] args) {
        limparTela();

        String nomeArquivoVeiculos = "baseAtorf";
        lerArquivoVeiculos(nomeArquivoVeiculos);

        String arqNome = "menuAtorf";
        int opcao;
        do {
            opcao = menu(arqNome);
        } while (opcao != 0 && opcao != -1);
        scanner.close();
    }

    /**
     * Lê os dados dos veículos a partir de um arquivo.
     * Este método abre um arquivo com o nome especificado e lê as informações dos
     * veículos,
     * como tipo, placa, número de rotas, capacidade do tanque, total reabastecido,
     * quilometragem,
     * e tipo de combustível. Essas informações são utilizadas para criar e
     * configurar os veículos.
     *
     * @param nomeArquivo O nome do arquivo contendo os dados dos veículos.
     * @throws FileNotFoundException Se o arquivo especificado não for encontrado.
     */
    public static void lerArquivoVeiculos(String nomeArquivo) {
        try {
            File arquivo = new File(nomeArquivo);
            Scanner scannerArquivo = new Scanner(arquivo);

            while (scannerArquivo.hasNextLine()) {
                String linha = scannerArquivo.nextLine();
                String[] dados = linha.split("-");

                if (dados.length >= 7) {
                    String tipoVeiculoStr = dados[0];
                    String placa = dados[1];
                    int quantRotas = Integer.parseInt(dados[2]);
                    double capacidadeTanque = Double.parseDouble(dados[3]);
                    double totalReabastecido = Double.parseDouble(dados[4]);
                    double quilometragem = Double.parseDouble(dados[5]);
                    Combustivel tipoCombustivel = Combustivel.valueOf(dados[7]); // Lendo o tipo de combustível

                    TipoVeiculo tipoVeiculo = TipoVeiculo.valueOf(tipoVeiculoStr);
                    Tanque tq = new Tanque(tipoCombustivel, tipoVeiculo.getTamanhoTanque(), totalReabastecido);
                    tq.setCapacidadeAtual(totalReabastecido);
                    Manutencao manutencao = null;

                    switch (tipoVeiculo) {
                        case CAMINHAO:
                            manutencao = new MCaminhao();
                            break;
                        case CARRO:
                            manutencao = new MCarro();
                            break;
                        case FURGAO:
                            manutencao = new MFurgao();
                            break;
                        case VAN:
                            manutencao = new MVan();
                            break;
                    }

                    Veiculo novoVeiculo = new Veiculo(placa, tipoVeiculo, tipoCombustivel, manutencao, tq,
                            quilometragem);
                    frota.adicionarVeiculo(novoVeiculo);
                }
            }
            scannerArquivo.close();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado: " + e.getMessage());
        }
    }

    /**
     * Cria rotas aleatórias para cada veículo na frota.
     * Este método percorre cada veículo na frota fornecida e gera rotas com
     * quilometragem,
     * data e outros detalhes gerados aleatoriamente.
     *
     * @param frota A frota de veículos para a qual as rotas serão criadas.
     * 
     */
    public static void criarRotasParaVeiculos(Frota frota) {
        for (Veiculo veiculo : frota.getVeiculos()) {
            for (int i = 0; i < 15; i++) {
                double quilometragem = Math.random() * 100; // Gerando uma quilometragem aleatória
                int dia = (int) (1 + Math.random() * 28); // Gerando um dia aleatório entre 1 e 28
                int mes = (int) (1 + Math.random() * 12); // Gerando um mês aleatório entre 1 e 12
                int ano = 2023;

                Data dataRota = new Data(dia, mes, ano);
                Rota rota = new Rota(quilometragem, dataRota);

                veiculo.addRota(rota);
            }
        }
    }

    /**
     * Exibe o menu principal e processa as opções escolhidas pelo usuário.
     * Este método lê as opções de menu de um arquivo e permite ao usuário escolher
     * uma opção.
     * As ações correspondentes são executadas com base na escolha do usuário,
     * incluindo exibição de relatórios,
     * localização de veículos, entre outras funções.
     *
     * @param nomeArquivo O nome do arquivo contendo as opções do menu.
     * @return A opção escolhida pelo usuário.
     * 
     */
    public static int menu(String nomeArquivo) {
        lerMenu(nomeArquivo);
        System.out.println("==========================");
        System.out.print("Escolha uma opção: ");
        int opcao;
        try {
            opcao = scanner.nextInt();
        } catch (java.util.InputMismatchException e) {
            scanner.nextLine();
            System.out.println("Opção inválida, Coloque uma opção válida!!");
            return 1;
        }

        switch (opcao) {
            case 1:
                System.out.println(frota.relatorio());
                break;

            case 2:
                System.out.print("Digite a placa: ");
                String placa = scanner.next();
                Veiculo veiculoEncontrado = frota.localizarVeiculo(placa);
                if (veiculoEncontrado != null) {
                    System.out.println("Veículo encontrado:\n" + veiculoEncontrado.toString());
                } else {
                    System.out.println("Não encontrado. ＞﹏＜");
                }
                break;

            case 3:
                System.out.print("Digite a placa do veículo: ");
                String placaVeiculoRota = scanner.next();

                System.out.print("Escreva a Quilometragem: ");
                String quilometragemStr = scanner.next();
                double quilometragem;
                try {
                    quilometragem = Double.parseDouble(quilometragemStr);
                } catch (NumberFormatException e) {
                    System.out.println("Quilometragem inválida, digite um número válido!");
                    return 1;
                }
                scanner.nextLine();

                System.out.print("Escreva a Data (no formato DD/MM/AAAA): ");
                try {
                    String dataInput = scanner.nextLine();
                    String[] dataSplit = dataInput.split("/");
                    int dia = Integer.parseInt(dataSplit[0]);
                    int mes = Integer.parseInt(dataSplit[1]);
                    int ano = Integer.parseInt(dataSplit[2]);

                    Data dataRota = new Data(dia, mes, ano);

                    Veiculo veiculoExistente = frota.localizarVeiculo(placaVeiculoRota);
                    if (veiculoExistente != null) {
                        Rota rota = new Rota(quilometragem, dataRota);
                        String gasto = veiculoExistente.addRota(rota);
                        if (gasto != null) {
                            System.out.println(veiculoExistente.addRota(rota).toString());
                            veiculoExistente.getManutencao().registrarManutencaoPeriodica(veiculoExistente.getQuilometragem());
                            veiculoExistente.getManutencao().registrarTrocaPecas(veiculoExistente.getQuilometragem());
                        } else {
                            System.out.println("Rota adicionada com sucesso para o veículo com placa "
                                    + veiculoExistente.getPlaca());
                        }
                    } else {
                        System.out.println("Veículo não encontrado na frota.");
                    }

                } catch (NumberFormatException e) {
                    System.out.println("Formato de data inválido. Use o formato DD/MM/AAAA.");
                    return 1;
                }

                break;
            case 4:
                System.out.print("Placa: ");
                String placaNova = scanner.next();
                String arquivoLer = "menuTipoVeiculo";
                lerMenu(arquivoLer);
                int tipoVeiculoEscolha = scanner.nextInt();
                TipoVeiculo tipoVeiculo;
                Combustivel tipoCombustivel = null;

                String arqLerComb = "menuCombustivel";
                lerMenu(arqLerComb);
                int escolhaCombustivel = scanner.nextInt();
                switch (escolhaCombustivel) {
                    case 1:
                        tipoCombustivel = Combustivel.ALCOOL;
                        break;
                    case 2:
                        tipoCombustivel = Combustivel.DIESEL;
                        break;
                    case 3:
                        tipoCombustivel = Combustivel.GASOLINA;
                        break;
                    default:
                        System.out.println("Tipo de combustível inválido.");
                        break;
                }

                switch (tipoVeiculoEscolha) {
                    case 1:
                        tipoVeiculo = TipoVeiculo.CARRO;
                        break;
                    case 2:
                        tipoVeiculo = TipoVeiculo.CAMINHAO;
                        break;
                    case 3:
                        tipoVeiculo = TipoVeiculo.FURGAO;
                        break;
                    case 4:
                        tipoVeiculo = TipoVeiculo.VAN;
                        break;
                    default:
                        System.out.println("Tipo de veículo inválido.");
                        return 1;

                }

                Manutencao manutencao;
                switch (tipoVeiculo) {
                    case CARRO:
                        manutencao = new MCarro();
                        break;
                    case CAMINHAO:
                        manutencao = new MCaminhao();
                        break;
                    case FURGAO:
                        manutencao = new MFurgao();
                        break;
                    case VAN:
                        manutencao = new MVan();
                        break;
                    default:
                        System.out.println("Tipo de veículo inválido.");
                        return 1;
                }

                Veiculo veiculoJaExiste = frota.localizarVeiculo(placaNova);
                if (veiculoJaExiste == null && tipoCombustivel != null) {
                    Tanque tanqueNovo = new Tanque(tipoCombustivel, tipoVeiculo.getTamanhoTanque(), 0); // Capacidade do
                                                                                                        // tanque
                                                                                                        // baseada no
                    // tipo de veículo
                    Veiculo novoVeiculo = new Veiculo(placaNova, tipoVeiculo, tipoCombustivel, manutencao, tanqueNovo,
                            0);
                    frota.adicionarVeiculo(novoVeiculo);
                    System.out.println("Veiculo adicionado com sucesso.");
                } else {
                    System.out.println("Erro ao cadastrar na frota.");
                }
                break;

            case 5:
                Veiculo veiculoMaiorKmTotal = frota.maiorKmTotal();
                if (veiculoMaiorKmTotal != null) {
                    System.out
                            .println("O veículo com a maior quilometragem total é:\n" + veiculoMaiorKmTotal.toString());
                } else {
                    System.out.println("Nenhum veículo na frota. ＞﹏＜");
                }
                break;

            case 6:
                Veiculo veiculoMaiorKmMedia = frota.maiorKmMedia();
                if (veiculoMaiorKmMedia != null) {
                    System.out
                            .println("O veículo com a maior quilometragem média é:\n" + veiculoMaiorKmMedia.toString());
                } else {
                    System.out.println("Nenhum veículo na frota. ＞﹏＜");
                }
                break;

            case 7:
                System.out.println("Quilometragem total da frota: " + frota.quilometragemTotal() + " km.");
                break;

            case 8:
                System.out.print("Digite a placa do veículo: ");
                String placaVeiculo = scanner.next();
                Veiculo veiculoParaAbastecer = frota.localizarVeiculo(placaVeiculo);
                if (veiculoParaAbastecer != null) {
                    System.out.print("Digite a quantidade de litros a abastecer: ");
                    double litrosAbastecimento = scanner.nextDouble();
                    String mensagemAbastecimento = veiculoParaAbastecer.abastecer(litrosAbastecimento);
                    System.out.println(mensagemAbastecimento);
                } else {
                    System.out.println("Veículo não encontrado. ＞﹏＜");
                }
                break;

            case 9:
                System.out.print("Digite a placa do veículo: ");
                String placa2 = scanner.next();
                Veiculo veiculo = frota.localizarVeiculo(placa2);
                if (veiculo != null) {
                    System.out.println(veiculo.getManutencao().informarManutencao());
                } else {
                    System.out.println("Veículo não encontrado.");
                }
                break;

            case 10:
                System.out.print("Digite a placa do veículo: ");
                String placa3 = scanner.next();
                Veiculo veiculo2 = frota.localizarVeiculo(placa3);
                if (veiculo2 != null) {
                    double despesaTotal = veiculo2.calcularDespesaTotal();
                    System.out.println(veiculo2.relatorioDespesas());
                } else {
                    System.out.println("Veículo não encontrado.");
                }
                break;

            case 11:
                System.out.print("Informe a placa do veículo: ");
                String placaVeiculoAchar = scanner.next(); // Renomeando a variável
                Veiculo veiculoEncontrado2 = frota.localizarVeiculo(placaVeiculoAchar); // Renomeando a variável
                if (veiculoEncontrado2 != null) {
                    double quilometragemTotal = veiculoEncontrado2.getQuilometragem();
                    System.out.println("==========================");
                    System.out
                            .println("A quilometragem total percorrida pelo veículo é: " + quilometragemTotal + " km");
                } else {
                    System.out.println("Veículo não encontrado.");
                }
                break;

            case 0:
                System.out.println("Saindo...");
                break;

            default:
                System.out.println("Opção inválida- ＞﹏＜");
                break;
        }

        return opcao;
    }

    /**
     * Exibe o menu para seleção dos tipos de veículos.
     *
     * @param nomeArquivo O nome do arquivo contendo as opções dos tipos de
     *                    veículos.
     */
    public static void menuTiposVeiculos(String nomeArquivo) {
        limparTela();
        lerMenu(nomeArquivo);

        String nomeArq = "menuCombustivel";
        System.out.println("==========================");
        System.out.print("Escolha um tipo de veículo: ");
        int opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao) {
            case 1:
                System.out.println("Tipo selecionado: Caminhão");

                break;

            case 2:
                System.out.println("Tipo selecionado: Carro");

                break;

            case 3:
                System.out.println("Tipo selecionado: Furgão");

                break;

            case 4:
                System.out.println("Tipo selecionado: Van");

                break;

            default:
                System.out.println("Opção inválida");
                break;
        }
    }

    /**
     * Exibe o menu para seleção do tipo de combustível.
     *
     * @param nomeArquivo O nome do arquivo contendo as opções de combustível.
     * @return O tipo de combustível escolhido.
     */
    public static Combustivel menuCombustivel(String nomeArquivo) {
        limparTela();
        lerMenu(nomeArquivo);
        System.out.println("==========================");
        System.out.print("Escolha um tipo de combustível: ");
        int opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao) {
            case 1:
                return Combustivel.ALCOOL;

            case 2:
                return Combustivel.DIESEL;

            case 3:
                return Combustivel.GASOLINA;

            default:
                System.out.println("Opção inválida.");
                return null;
        }
    }

    /**
     * Lê o conteúdo de um arquivo e exibe na tela.
     * Este método é utilizado para ler as opções de menu de um arquivo e exibi-las
     * ao usuário.
     *
     * @param nomeArquivo O nome do arquivo a ser lido.
     */
    public static void lerMenu(String nomeArquivo) {
        try {
            File arquivo = new File(nomeArquivo);
            Scanner scannerArquivo = new Scanner(arquivo, "UTF-8");

            while (scannerArquivo.hasNextLine()) {
                String linha = scannerArquivo.nextLine();
                System.out.println(linha);
            }

            scannerArquivo.close();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado: " + e.getMessage());
        }
    }
}
