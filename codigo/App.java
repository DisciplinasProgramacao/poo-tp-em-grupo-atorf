package codigo;

public class App {
    public static void main(String[] args) throws Exception {// <-- Ver com o grupo se temos que usar Arquivo 
    static Scanner scanner = new Scanner(System.in);
    static Frota frota;

    public static int menu(){

        frota = new Frota(20);

        menuText();
        System.out.print("Escolha uma opção: ");
        opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.println(frota.relatorio());
                    return opcao;
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
                    return opcao;
                    break;
                case 3:
                    Veiculo veiculo = new Veiculo();
                    System.out.println("Escreva a Quilometragem:");
                    quilometragem = scanner.nextdouble(); 
                    System.out.println("Escreva a Data :");
                    int dia = scanner.nextInt();
                    int mes = scanner.nextInt();
                    int ano = scanner.nextInt();
                    Data dataRota = new Data(dia,mes,ano);
                    Rota rota = new Rota(quilometragem, dataRota);
                    veiculo.addRota(rota);
                    return opcao;
                    break;
                case 4:
                    System.out.println("Quilometragem total da frota : " + frota.quilometragemTotal() + " km.");
                    return opcao;
                    break;
                case 5:
                    Veiculo veiculoMaiorKmTotal = frota.maiorKmTotal();
                    if (veiculoMaiorKmTotal != null) {
                        System.out.println("O veículo com a maior quilometragem total é:\n" + veiculoMaiorKmTotal.toString());
                    } else {
                        System.out.println("Nenhum veículo na frota. ＞﹏＜");
                    }
                    return opcao;
                    break;
                case 6:
                    Veiculo veiculoMaiorKmMedia = frota.maiorKmMedia();
                    if (veiculoMaiorKmMedia != null) {
                        System.out.println("O veículo com a maior quilometragem média é:\n" + veiculoMaiorKmMedia.toString());
                    } else {
                        System.out.println("Nenhum veículo na frota. ＞﹏＜");
                    }
                    return opcao;
                    break;
                case 7:
                    // falta  a funcionalidade de abastecer um veículo
                    return opcao;
                    break;
                case 0:
                    System.out.println("Saindo...");
                    return opcao;
                    break;
                default:
                    return opcao;
                    System.out.println("-Opção inválida- ＞﹏＜");
            }
        return -1    
    }


    public static void main(String[] args) {
        int opcao = 1;
        do {
            opcao = menu();
        } while (opcao != 0 && opcao != -1);
        scanner.close();
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
