package codigo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class App {
    static Scanner scanner = new Scanner(System.in);
    static Frota frota = new Frota(20);

    public static void main(String[] args) {
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
                Veiculo veiculo = new Veiculo();
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
