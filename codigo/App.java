package codigo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Esta classe representa o aplicativo principal para gerenciamento de uma frota
 * de veículos.
 */
public class App {
    static Scanner scanner = new Scanner(System.in);
    static Frota frota = new Frota(20);

    /**
     * "Limpa" a tela (códigos de terminal VT-100)
     */
    public static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

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
     *
     * @param nomeArquivo O nome do arquivo contendo os dados dos veículos.
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
    
                    Tanque tq = new Tanque(capacidadeTanque, totalReabastecido);
                    TipoVeiculo tipoVeiculo = TipoVeiculo.valueOf(tipoVeiculoStr);
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
    
                    Veiculo novoVeiculo = new Veiculo(placa, tipoVeiculo, tipoCombustivel, manutencao, tq, quilometragem);
                    frota.adicionarVeiculo(novoVeiculo);
                }
            }
            scannerArquivo.close();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado: " + e.getMessage());
        }
    }
    
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
     *
     * @param nomeArquivo O nome do arquivo contendo as opções do menu.
     * @return A opção escolhida pelo usuário.
     */
    public static int menu(String nomeArquivo) {
        lerMenu(nomeArquivo);
        System.out.println("==========================");
        System.out.print("Escolha uma opção: ");
        int opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao) {
            case 1:
                System.out.println(frota.relatorio());
                break;

            case 2:
                System.out.println("Digite a placa: ");
                String placa = scanner.next();
                Veiculo veiculoEncontrado = frota.localizarVeiculo(placa);
                if (veiculoEncontrado != null) {
                    System.out.println("Veículo encontrado:\n" + veiculoEncontrado.toString());
                } else {
                    System.out.println("Não encontrado. ＞﹏＜");
                }
                break;

            case 3:
                System.out.println("Digite a placa do veículo:");
                String placaVeiculoRota = scanner.nextLine();
                System.out.println("Escreva a Quilometragem:");
                double quilometragem = scanner.nextDouble();
                scanner.nextLine();

                System.out.println("Escreva a Data (no formato DD/MM/AAAA):");
                String dataInput = scanner.nextLine();
                String[] dataSplit = dataInput.split("/");
                int dia = Integer.parseInt(dataSplit[0]);
                int mes = Integer.parseInt(dataSplit[1]);
                int ano = Integer.parseInt(dataSplit[2]);

                Data dataRota = new Data(dia, mes, ano);

                Veiculo veiculoExistente = frota.localizarVeiculo(placaVeiculoRota);
                if (veiculoExistente != null) {
                    Rota rota = new Rota(quilometragem, dataRota);
                    if (!veiculoExistente.addRota(rota)) {
                        System.out.println(
                                "Erro: Não foi possível adicionar a rota. Verifique o limite de rotas do veículo.");
                    } else {
                        System.out.println("Rota adicionada com sucesso ao veículo " + placaVeiculoRota);
                    }
                } else {
                    System.out.println("Veículo não encontrado na frota.");
                }
                break;

            case 4:
                System.out.println("Placa: ");
                String placaNova = scanner.nextLine();
                String arquivoLer = "menuTipoVeiculo";
                lerMenu(arquivoLer);
                int tipoVeiculoEscolha = scanner.nextInt();
                TipoVeiculo tipoVeiculo;
                Combustivel tipoCombustivel = Combustivel.GASOLINA; // Valor padrão

                System.out.println("Escolha o tipo de combustível (1: Álcool, 2: Diesel, 3: Gasolina):");
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
                        System.out.println("Tipo de combustível inválido. Usando Gasolina como padrão.");
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
                        throw new IllegalArgumentException("Tipo de veículo inválido.");

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
                        throw new IllegalArgumentException("Tipo de veículo inválido.");
                }

                Tanque tanqueNovo = new Tanque(tipoVeiculo.getTamanhoTanque(), 0); // Capacidade do tanque baseada no
                                                                                   // tipo de veículo
                Veiculo novoVeiculo = new Veiculo(placaNova, tipoVeiculo, tipoCombustivel, manutencao, tanqueNovo, 0);
                frota.adicionarVeiculo(novoVeiculo);
                System.out.println("Veiculo adicionado com sucesso.");
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
                    if (veiculoParaAbastecer.podeAbastecer(litrosAbastecimento)) {
                        veiculoParaAbastecer.abastecer(litrosAbastecimento);
                        System.out.println("Veículo abastecido com sucesso!");
                    } else {
                        System.out.println("Quantidade de litros inválida ou excede a capacidade do tanque.");
                    }
                } else {
                    System.out.println("Veículo não encontrado. ＞﹏＜");
                }
                break;

            // ... dentro do switch-case da classe App ...

            case 9:
                System.out.println("Digite a placa do veículo:");
                String placa2 = scanner.nextLine();
                Veiculo veiculo = frota.localizarVeiculo(placa2);
                if (veiculo != null) {
                    System.out.println(veiculo.getManutencao().informarManutencao());
                } else {
                    System.out.println("Veículo não encontrado.");
                }
                break;

            

            case 10:
                System.out.println("Digite a placa do veículo:");
                String placa3 = scanner.nextLine();
                Veiculo veiculo2 = frota.localizarVeiculo(placa3);
                if (veiculo2 != null) {
                    double despesaTotal = veiculo2.calcularDespesaTotal();
                    System.out.println("Despesa total do veículo: " + despesaTotal);
                } else {
                    System.out.println("Veículo não encontrado.");
                }
                break;

            case 11:
                System.out.println("Informe a placa do veículo: ");
                String placaVeiculoAchar = scanner.next(); // Renomeando a variável
                Veiculo veiculoEncontrado2 = frota.localizarVeiculo(placaVeiculoAchar); // Renomeando a variável
                if (veiculoEncontrado2 != null) {
                    double quilometragemTotal = veiculoEncontrado2.getQuilometragem();
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
     * Exibe o menu para seleção do tipo de manutenção.
     *
     * @param nomeArquivo O nome do arquivo contendo as opções de manutenção.
     * @return O tipo de manutenção escolhido.
     */
    public static Manutencao menuManutencao(String nomeArquivo) {
        limparTela();
        lerMenu(nomeArquivo);
        System.out.println("==========================");
        System.out.print("Escolha um tipo de veículo: ");
        int opcaoVeiculo = scanner.nextInt();
        scanner.nextLine();

        switch (opcaoVeiculo) {
            case 1:
                menuManutencaoCaminhao();
                break;

            case 2:
                menuManutencaoCarro();
                break;

            case 3:
                menuManutencaoFurgao();
                break;

            case 4:
                menuManutencaoVan();
                break;

            default:
                System.out.println("Opção inválida");
                break;
        }
        return null;
    }

    public static void menuManutencaoCaminhao() {
        limparTela();
        String nomeArquivo = "menuManutencao";
        System.out.println("==========================");
        System.out.println("Informe a placa: ");
        String placaCam = scanner.nextLine();
        lerMenu(nomeArquivo);
        System.out.println("==========================");
        System.out.print("Escolha o tipo de manutenção para caminhão: ");
        int opcaoManutencao = scanner.nextInt();
        scanner.nextLine();

        switch (opcaoManutencao) {
            case 1:
                System.out.println("Informe a KM atual: ");
                double kmAtual = scanner.nextDouble();

                MCaminhao mPeriodicaCaminhao = new MCaminhao();
                mPeriodicaCaminhao.registrarManutencaoPeriodica(kmAtual);
                System.out.println("Manutenção realizada com sucesso!");
                break;

            case 2:
                System.out.println("Informe a KM atual: ");
                kmAtual = scanner.nextDouble();

                MCaminhao mTrocaPneuCaminhao = new MCaminhao();
                mTrocaPneuCaminhao.registrarManutencaoPeriodica(kmAtual);
                System.out.println("Troca realizada com sucesso!");
                break;

            default:
                System.out.println("Opção inválida.");
                break;
        }
    }

    public static void menuManutencaoCarro() {
        limparTela();
        String nomeArquivo = "menuManutencao";
        System.out.println("==========================");
        System.out.println("Informe a placa: ");
        String placaCarro = scanner.nextLine();
        lerMenu(nomeArquivo);
        System.out.println("==========================");
        System.out.print("Escolha o tipo de manutenção para o carro: ");
        int opcaoManutencao = scanner.nextInt();
        scanner.nextLine();

        switch (opcaoManutencao) {
            case 1:
                System.out.println("Informe a KM atual: ");
                double kmAtual = scanner.nextDouble();

                MCarro mPeriodicaCarro = new MCarro();
                mPeriodicaCarro.registrarManutencaoPeriodica(kmAtual);
                System.out.println("Manutenção realizada com sucesso!");
                break;

            case 2:
                System.out.println("Informe a KM atual: ");
                kmAtual = scanner.nextDouble();

                MCarro mTrocaPneuCarro = new MCarro();
                mTrocaPneuCarro.registrarManutencaoPeriodica(kmAtual);
                System.out.println("Troca realizada com sucesso!");
                break;

            default:
                System.out.println("Opção inválida.");
                break;
        }
    }

    public static void menuManutencaoFurgao() {
        limparTela();
        String nomeArquivo = "menuManutencao";
        System.out.println("==========================");
        System.out.println("Informe a placa: ");
        String placaFurgao = scanner.nextLine();
        lerMenu(nomeArquivo);
        System.out.println("==========================");
        System.out.print("Escolha o tipo de manutenção para o furgão: ");
        int opcaoManutencao = scanner.nextInt();
        scanner.nextLine();

        switch (opcaoManutencao) {
            case 1:
                System.out.println("Informe a KM atual: ");
                double kmAtual = scanner.nextDouble();

                MFurgao mPeriodicaFurgao = new MFurgao();
                mPeriodicaFurgao.registrarManutencaoPeriodica(kmAtual);
                System.out.println("Manutenção realizada com sucesso!");
                break;

            case 2:
                System.out.println("Informe a KM atual: ");
                kmAtual = scanner.nextDouble();

                MFurgao mTrocaPneuFurgao = new MFurgao();
                mTrocaPneuFurgao.registrarManutencaoPeriodica(kmAtual);
                System.out.println("Troca realizada com sucesso!");
                break;

            default:
                System.out.println("Opção inválida.");
                break;
        }
    }

    public static void menuManutencaoVan() {
        limparTela();
        String nomeArquivo = "menuManutencao";
        System.out.println("==========================");
        System.out.println("Informe a placa: ");
        String placaVan = scanner.nextLine();
        lerMenu(nomeArquivo);
        System.out.println("==========================");
        System.out.print("Escolha o tipo de manutenção para a van: ");
        int opcaoManutencao = scanner.nextInt();
        scanner.nextLine();

        switch (opcaoManutencao) {
            case 1:
                System.out.println("Informe a KM atual: ");
                double kmAtual = scanner.nextDouble();

                MVan mPeriodicaVan = new MVan();
                mPeriodicaVan.registrarManutencaoPeriodica(kmAtual);
                System.out.println("Manutenção realizada com sucesso!");
                break;

            case 2:
                System.out.println("Informe a KM atual: ");
                kmAtual = scanner.nextDouble();

                MVan mTrocaPneuVan = new MVan();
                mTrocaPneuVan.registrarManutencaoPeriodica(kmAtual);
                System.out.println("Troca realizada com sucesso!");
                break;

            default:
                System.out.println("Opção inválida.");
                break;
        }
    }

    /**
     * Lê o conteúdo de um arquivo e exibe na tela.
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
