package codigo;

import java.util.Scanner;

public class App {
    static Scanner scanner = new Scanner(System.in);
    static Frota frota = new Frota(20);

    public static void main(String[] args) {
        int opcao;
        do {
            opcao = menu();
        } while (opcao != 0 && opcao != -1);
        scanner.close();
    }

    public static int menu() {
        menuText();
        System.out.print("Escolha uma opção: ");
        int opcao = scanner.nextInt();

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
                // Substitua 'new Veiculo()' pelo construtor correto de Veiculo
                Veiculo veiculo = new Veiculo(/* parâmetros do construtor */);
                System.out.println("Escreva a Quilometragem:");
                double quilometragem = scanner.nextDouble();
                System.out.println("Escreva a Data:");
                int dia = scanner.nextInt();
                int mes = scanner.nextInt();
                int ano = scanner.nextInt();
                Data dataRota = new Data(dia, mes, ano);
                Rota rota = new Rota(quilometragem, dataRota);
                veiculo.addRota(rota);
                break;
            case 4:
                System.out.println("Quilometragem total da frota: " + frota.quilometragemTotal() + " km.");
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
                System.out.print("Digite a placa do veículo: ");
                String placaVeiculo = scanner.next();
                Veiculo veiculoParaAbastecer = frota.localizarVeiculo(placaVeiculo);
                if (veiculoParaAbastecer != null) {
                    System.out.print("Digite a quantidade de litros a abastecer: ");
                    double litrosAbastecimento = scanner.nextDouble();
                    veiculoParaAbastecer.abastecer(litrosAbastecimento);
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

    private static void menuText() {
        System.out.println("0-Sair");
        System.out.println("1-Exibir relatório da frota");
        System.out.println("2-Localizar veículo por placa");
        System.out.println("3-Adicionar rota");
        System.out.println("4-Quilometragem total da frota");
        System.out.println("5-Veículo com maior quilometragem total");
        System.out.println("6-Veículo com maior quilometragem média");
        System.out.println("7-Abastecer");
    }
}
