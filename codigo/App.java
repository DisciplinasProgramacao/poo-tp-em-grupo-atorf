package codigo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

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
        String arqNome = "menuAtorf";
        int opcao;
        do {
            opcao = menu(arqNome);
        } while (opcao != 0 && opcao != -1);
        scanner.close();
    }

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
                System.out.println("Digite a placa do veiculo:");
                String placaVeiculoRota = scanner.nextLine();
                System.out.println("Escreva a Quilometragem:");
                double quilometragem = scanner.nextDouble();
                System.out.println("Escreva a Data:");
                int dia = scanner.nextInt();
                int mes = scanner.nextInt();
                int ano = scanner.nextInt();
                Data dataRota = new Data(dia, mes, ano);
            
                Veiculo veiculoExistente = frota.localizarVeiculo(placaVeiculoRota);
                if (veiculoExistente != null) {
                    Rota rota = new Rota(quilometragem, dataRota);
                    if (!veiculoExistente.addRota(rota)) {
                        System.out.println("Erro: Não foi possível adicionar a rota. Verifique o limite de rotas do veículo.");
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
                Veiculo novoVeiculo = new Veiculo(placaNova, 4, null, 0);
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
                Veiculo procurarVeiculo = frota.localizarVeiculo(placaVeiculo);
                Veiculo veiculoParaAbastecer = new Veiculo(placaVeiculo, 0, null, 0);
                if (procurarVeiculo != null) {
                    System.out.print("Digite a quantidade de litros a abastecer: ");
                    double litrosAbastecimento = scanner.nextDouble();
                    veiculoParaAbastecer.abastecer(litrosAbastecimento);
                } else {
                    System.out.println("Veículo não encontrado. ＞﹏＜");
                }
                break;

            case 9:
                String nomeArqM = "menuTipoVeiculo";
                menuManutencao(nomeArqM);
                break;

            case 10:
                String nomeArqCombustivel = "menuCombustivel";
                Combustivel tipoCombustivel = menuCombustivel(nomeArqCombustivel);
                if (tipoCombustivel == null) {
                    System.out.println("Tipo de combustível inválido");
                    break;
                }

                String nomeArqManutencao = "menuTipoVeiculo";
                Manutencao tipoManutencao = menuManutencao(nomeArqManutencao);
                if (tipoManutencao == null) {
                    System.out.println("Tipo de manutenção inválido");
                    break;
                }

                System.out.println("Informe a placa do veículo: ");
                String placaVeiculoCalc = scanner.next();
                Veiculo procurarVeiculoCalc = frota.localizarVeiculo(placaVeiculoCalc);
                Veiculo veiculoCalc = new Veiculo(placaVeiculoCalc, 0, null, 0);
                if (procurarVeiculoCalc != null) {
                    System.out.println("Informe a quilometragem atual do veículo: ");
                    double kmAtual = scanner.nextDouble();

                    double despesas = veiculoCalc.calculaDespesas(tipoCombustivel, tipoManutencao, kmAtual);
                    System.out.println("O custo total das despesas é: " + despesas);
                } else {
                    System.out.println("Veículo não encontrado. ＞﹏＜");
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
