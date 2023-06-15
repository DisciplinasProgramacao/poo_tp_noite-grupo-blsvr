import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        Streaming sesao = new Streaming();

        sesao.cadastrarVariosUsuarios("arquivos/POO_Espectadores.csv");
        sesao.cadastrarVariasMidias("arquivos/POO_Series.csv", "arquivos/POO_Filmes.csv");
        sesao.cadastrarAudiencia("arquivos/POO_Audiencia.csv");
        // #region LOGIN / CADASTRO
        Scanner ler1 = new Scanner(System.in);
        int escolha;
        String login = "";
        String senha = "";
        Cliente Logado = new Cliente("", "", "");
        boolean logado = false;
        while (!logado) {
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
                    } else
                        logado = true;
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
        // #endregion

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

                    if (ListaBuscada.size() == 0) {
                        System.out.println("Não foram encontradas nenhuma mídia com esse nome");
                    } else {
                        System.out.println("Mídias encontradas:\n");
                        ListaBuscada.stream().forEach(Midia -> System.out
                                .println("Número de identificação: " + Midia.ID + ": " + Midia.nome + "\n"));

                        boolean escolhaValida = false;
                        Midia midiaSelecionada = null;
                        while (!escolhaValida) {
                            System.out.println("Deseja saber detalhes sobre alguma série?");
                            System.out.println("Caso sim digite o número de identificação da mídia mostrada na lista");
                            System.out.println("Caso deseje sair digite -1");

                            try {
                                escolha = ler1.nextInt();
                            } catch (InputMismatchException e) {
                                System.out.println("Escolha inválida");
                            }

                            if (sesao.midiasCadastradas.Contem(String.valueOf(escolha))) {

                                System.out.println("Dados da mídia:");

                                midiaSelecionada = sesao.midiasCadastradas.Buscar(String.valueOf(escolha));
                                System.out.println(midiaSelecionada.dadosMidia());

                                System.out.println("1 - inserir essa mídia em sua lista para assistir");
                                System.out.println("2 - Assistir a série");
                                System.out.println("3 - Sair");

                                escolha = ler1.nextInt();

                                switch (escolha) {
                                    case 1:

                                        Logado.planejarParaAssistir(midiaSelecionada);
                                        System.out.println(midiaSelecionada.nome + " planejado para assistir.");

                                        break;

                                    case 2:
                                        Logado.assistir(midiaSelecionada);
                                        System.out.println("Você acabou de assistir " + midiaSelecionada.nome);

                                        break;

                                    default:
                                        break;
                                }

                                escolhaValida = true;

                            } else {
                                System.out.println("Código inválido");
                            }

                        }
                    }
            }

        }

        ler1.close();
    }
}
