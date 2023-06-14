import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        Streaming sesao = new Streaming();

        sesao.cadastrarVariosUsuarios("arquivos/POO_Espectadores.csv");
        sesao.cadastrarVariasMidias("arquivos/POO_Series.csv", "arquivos/POO_Filmes.csv");
        // sesao.cadastrarAudiencia("arquivos/POO_Audiencia.csv");

        Scanner ler1 = new Scanner(System.in);
        int escolha;
        String login = "";
        String senha = "";
        Cliente Logado = null;
        while (Logado == null) {
            System.out.println("Você deseja:");
            System.out.println("1: Entrar na conta");
            System.out.println("2: Criar uma conta");
            try {
                escolha = ler1.nextInt();
            } catch (Exception e) {
                System.out.println("Digite apenas números inteiros.");
                escolha = -1;
                ler1.nextLine();
            }
            switch (escolha) {
                case 1:

                    System.out.println("Digite seu login");
                    login = ler1.next();
                    System.out.println("Digite sua senha");
                    senha = ler1.next();
                    Logado = sesao.entrar(login, senha);
                    if (Logado == null) {
                        System.out.println("Nome ou senha inválidos");
                    }

                    break;

                case 2:

                    System.out.println("Digite seu nome");
                    String nome = ler1.next();
                    System.out.println("Digite seu login");
                    login = ler1.next();
                    System.out.println("Digite sua senha");
                    senha = ler1.next();
                    try {
                        sesao.cadastrar(login, senha, nome);

                    } catch (Exception e) {
                        System.out.println(e.toString());
                    }

                    break;

                default:
                    System.out.println("Escolha inválida.");
                    break;
            }
        }

        boolean sair = false;
        while (!sair) {

            System.out.println("Bem-vindo");
            System.out.println("Escolha sua ação: ");

            System.out.println("1 - Pesquisar midias cadastradas no sistema");
            System.out.println("2 - Verificar sua lista assistida");
            System.out.println("3 - Verificar sua lista para assistir");

            escolha = ler1.nextInt();

            switch (escolha) {
                case 1:

                    System.out.println("Digite o nome da mídia que deseja buscar");
                    String NomeBuscado = ler1.next();
                    List<Midia> ListaBuscada = sesao.midiasCadastradas.Buscar(NomeBuscado, "", "", "");
                    int contador = 1;
                    if (ListaBuscada.size() == 0) {
                        System.out.println("Não foram encontradas nenhuma mídia com esse nome");
                    } else {
                        System.out.println("Mídias encontradas:\n");
                        ListaBuscada.stream().forEach(Midia -> System.out
                                .println("Série encontrada número: " + contador + Midia.nome + "\n"));

                        System.out.println("Deseja saber detalhes sobre alguma série?");
                        System.out.println("Caso sim digite o número da mídia mostrada na lista");
                        System.out.println("Caso deseje sair digite -1");

                        escolha = ler1.nextInt();

                        System.out.println("Dados da mídia:");


                        


                    }
                    break;

                case 2:

                    break;
                default:
                    break;
            }

        }

        ler1.close();
    }
}
