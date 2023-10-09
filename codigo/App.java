package codigo;

/**
 * Classe App contém a função principal para interação com o usuário e operações do sistema de gestão da frota de veículos.
 * App apresenta um menu com várias opções que executão diferentes funcionalidades do programa.
 */
public class App {
    public static void main(String[] args) throws Exception {// <-- Ver com o grupo se temos que usar Arquivo 
    static Scanner scanner = new Scanner(System.in);
    static Frota frota;
    /**
    * A função menu é chamada pelo método main, realizando as ações das opções do Menu,
    * permitindo interação com o que é mostrado na função menuText -> {@link #menuText()}.
    * 
    * @return Opção para o método main. Assim, o do-while no main fará o tratamento se sai ou continua executando.
    */
    public static int menu(){

        frota = new Frota(20);

        menuText(); // Chama a função menuText-> {@link #menuText()} , a qual mostrará as opções.
        System.out.print("Escolha uma opção: ");
        opcao = scanner.nextInt();

            /**
            * O switch case captura esse valor da opção e compara com os cases -> (1, 2, 3, 4, 5, 6, 7, 0)
            * caso a opção for um caractere ou um número inválido, ele irá entrar no default que irá avisar o erro.
            * 
            * @param opcao A opção a ser comparada no switch case.
            * @return O switch case irá retornar a opção -> (1, 2, 3, 4, 5, 6, 7, 0). Caso seja passado um valor inválido, o switch case irá retornar -1 para sair.
            */
            switch (opcao) {// Capitura esse valor da opcao , e compara com os cases -> (1 , 2 , 3 , 4 , 5 ,6 , 7 , 0)
                case 1:// Exibe relatório da frota
                    System.out.println(frota.relatorio());
                    return opcao;
                    break;
                case 2:// Localiza veículo por placa
                    System.out.println("Digite a placa: ");
                    String placa = scanner.next();
                    Veiculo veiculoEncontrado = frota.localizarVeiculo(placa);
                    if (veiculoEncontrado != null) {//Confere se realmente o Veiculo foi encontrado.
                        System.out.println("Veículo encontrado:\n" + veiculoEncontrado.toString());
                    } else {
                        System.out.println("Não encontrado. ＞﹏＜");
                    }
                    return opcao;
                    break;
                case 3:// Adiciona rota
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
                case 4:// Mostra quilometragem total da frota
                    System.out.println("Quilometragem total da frota : " + frota.quilometragemTotal() + " km.");
                    return opcao;
                    break;
                case 5:// Mostra veículo com maior quilometragem total
                    Veiculo veiculoMaiorKmTotal = frota.maiorKmTotal();
                    if (veiculoMaiorKmTotal != null) {//Confere se veiculoMaiorKmTotal não é nulo, se há veículos para mostrar a maior quilometragem total.
                        System.out.println("O veículo com a maior quilometragem total é:\n" + veiculoMaiorKmTotal.toString());
                    } else {
                        System.out.println("Nenhum veículo na frota. ＞﹏＜");
                    }
                    return opcao;
                    break;
                case 6:// Mostra veículo com maior quilometragem média
                    Veiculo veiculoMaiorKmMedia = frota.maiorKmMedia();
                    if (veiculoMaiorKmMedia != null) {//Confere se veiculoMaiorKmMedia não é nulo, se há veículos para mostrar a maior quilometragem média.
                        System.out.println("O veículo com a maior quilometragem média é:\n" + veiculoMaiorKmMedia.toString());
                    } else {
                        System.out.println("Nenhum veículo na frota. ＞﹏＜");
                    }
                    return opcao;
                    break;
                case 7:// Abastecer veículo
                    System.out.print("Digite a placa do veículo: ");
                    String placaVeiculo = scanner.next();
                    Veiculo veiculoParaAbastecer = frota.localizarVeiculo(placaVeiculo);
                    if (veiculoParaAbastecer != null) {//Confere se veiculoParaAbastecer não é nulo, se há veículo para Abastecer.
                        System.out.print("Digite a quantidade de litros a abastecer: ");
                        double litrosAbastecimento = scanner.nextDouble();
                        veiculoParaAbastecer.abastecer(litrosAbastecimento);
                    } else {
                        System.out.println("Veículo não encontrado. ＞﹏＜");
                    }
                    return opcao;
                    break;
                case 0:// Sair
                    System.out.println("Saindo...");
                    return opcao;
                    break;
                default:
                    return opcao;// valor inválido, o switch case irá retornar -1 para sair.
                    System.out.println("Opção inválida- ＞﹏＜");
            }

        return -1    
    }


    public static void main(String[] args) {
        int opcao = 1;
        do {
            opcao = menu();
        } while (opcao != 0 && opcao != -1);// Enquanto a opção for diferente de 0 ou de -1, o sistema continuará sendo executado.
        scanner.close();
    }



    /**
	 * A função menuText mostra pelo terminal as opições possiveis á serem feitas no Systema.
	 */
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

