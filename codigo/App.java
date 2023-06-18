import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        // #region PREENCHIMENTO DO SISTEMA
        Streaming sesao = new Streaming();
        sesao.cadastrarVariosUsuarios("arquivos/POO_Espectadores.csv");
        sesao.cadastrarVariasMidias("arquivos/POO_Series.csv", "arquivos/POO_Filmes.csv");
        sesao.cadastrarAudiencia("arquivos/POO_Audiencia.csv");
        Scanner ler1 = new Scanner(System.in);
        boolean fechar = false;
        // #endregion

        while (!fechar) {

            // #region LOGIN / CADASTRO
            int escolha;
            String login = "";
            String senha = "";
            Cliente Logado = new Cliente("", "", "");
            boolean logado = false;
            while (!logado) {
                System.out.println("Você deseja:");
                System.out.println("1: Entrar na conta");
                System.out.println("2: Criar uma conta");
                System.out.println("3: Fechar o sistemas");
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
                            System.out.println(e.getMessage());
                        }

                        break;

                    case 3:
                        logado = true;
                        fechar = true;
                        break;
                    default:
                        System.out.println("Escolha inválida.");
                        break;
                }
            }
            // #endregion

            boolean sair = false;
            while (!sair && !fechar) {

                System.out.println("Bem-vindo ao menu principal " + Logado.nome);
                System.out.println("Escolha sua ação: ");

                System.out.println("1 - Pesquisar midias cadastradas no sistema");
                System.out.println("2 - Verificar sua lista assistida");
                System.out.println("3 - Verificar sua lista para assistir");
                System.out.println("4 - Avaliar uma mídia assistida");
                System.out.println("5 - Mostrar suas avaliações");
                System.out.println("6 - Sair da conta");

                escolha = ler1.nextInt();

                switch (escolha) {

                    // #region BUSCA DE MÍDIA
                    case 1:

                        System.out.println("Digite o nome da mídia que deseja buscar");
                        String NomeBuscado = ler1.next();
                        List<Midia> ListaBuscada = sesao.midiasCadastradas.Buscar(NomeBuscado, "", "", "");

                        if (ListaBuscada.size() <= 0) {
                            System.out.println("Não foram encontradas nenhuma mídia com esse nome");
                        } else {
                            System.out.println("Mídias encontradas:\n");
                            ListaBuscada.stream().forEach(Midia -> System.out
                                    .println("Número de identificação: " + Midia.ID + ": " + Midia.nome + "\n"));

                            boolean escolhaValida = false;
                            Midia midiaSelecionada = null;
                            while (!escolhaValida) {
                                System.out.println("Deseja saber detalhes sobre alguma série?");
                                System.out.println(
                                        "Caso sim digite o número de identificação da mídia mostrada na lista");
                                System.out.println("Caso deseje sair digite -1");
                                try {
                                    escolha = ler1.nextInt();
                                } catch (InputMismatchException e) {
                                    System.out.println("Escolha inválida");
                                }

                                if (sesao.midiasCadastradas.Contem(String.valueOf(escolha))) {

                                    System.out.println("Dados da mídia:");

                                    midiaSelecionada = sesao.midiasCadastradas.Buscar(String.valueOf(escolha));
                                    System.out.println(midiaSelecionada.toString());

                                    System.out.println("1 - inserir essa mídia em sua lista para assistir");
                                    System.out.println("2 - Assistir a série");
                                    System.out.println("3 - Sair");

                                    try {
                                        escolha = ler1.nextInt();
                                    } catch (InputMismatchException e) {
                                        System.out.println("Escolha inválida");
                                    }

                                    switch (escolha) {
                                        case 1:

                                            if (Logado.MidiasAssistidas.Contem(midiaSelecionada.ID)) {
                                                System.out.println("Mídia já está em sua lista");
                                            } else {
                                                Logado.planejarParaAssistir(midiaSelecionada);
                                                System.out.println(midiaSelecionada.nome + " planejado para assistir.");
                                            }
                                            break;

                                        case 2:
                                            if (Logado.MidiasAssistidas.Contem(midiaSelecionada.ID)) {
                                                System.out.println("Mídia já assistida");
                                            } else {

                                                Logado.assistir(midiaSelecionada);
                                                System.out.println("Você acabou de assistir " + midiaSelecionada.nome);
                                            }
                                            break;

                                        default:
                                            break;
                                    }

                                } else {
                                    System.out.println("Código inválido");
                                }

                                escolhaValida = true;

                            }
                            break;
                        }
                        break;
                    // #endregion

                    // #region Verificar assistidas
                    case 2:

                        if (!Logado.MidiasAssistidas.isVazia()) {

                            System.out.println("As seguintes midias estão em sua lista de assistidas:");

                            System.out.println(Logado.MidiasAssistidas.toString());

                        } else {
                            System.out.println("Sua lista está vazia");
                        }

                        break;
                    // #endregion

                    // #region Verificar para assistir
                    case 3:

                        if (!Logado.MidiasFuturas.isVazia()) {
                            System.out.println("As seguintes midias estão em sua lista para assistir futuramente:");
                            System.out.println(Logado.MidiasFuturas.toString());
                            System.out.println("Caso deseje remover alguma mídia, digite seu código");
                            System.out.println("Caso contrário digite -1");

                            try {
                                escolha = ler1.nextInt();
                            } catch (InputMismatchException e) {
                                System.out.println("Escolha inválida");
                            }
                            String Id = String.valueOf(escolha);

                            if (Logado.MidiasFuturas.Contem(Id)) {
                                Logado.MidiasFuturas.RemoverMidia(Id, Logado.MidiasFuturas.Buscar(Id));
                                System.out.println("\nVocê acabou de remover uma mídia de sua lista\n");
                            }

                        } else {
                            System.out.println("\nSua lista está vazia\n");
                        }

                        break;
                    // #endregion

                    // #region Avaliar Midia
                    case 4:

                        if (!Logado.MidiasAssistidas.isVazia()) {
                            System.out
                                    .println("As seguintes mídias estão em sua lista de mídias e podem ser avaliadas:");
                            System.out.println(Logado.MidiasAssistidas.toString());

                            System.out.println("\n Digite o código da mídia que deseje avaliar: \n");

                            try {
                                escolha = ler1.nextInt();
                            } catch (InputMismatchException e) {
                                System.out.println("Escolha inválida");
                            }
                            if (Logado.MidiasAvaliadas.Contem(String.valueOf(escolha))) {

                                System.out.println("Você já avaliou essa mídia.");

                            } else if (Logado.MidiasAssistidas.Contem(String.valueOf(escolha))) {

                                Midia Analisada = Logado.MidiasAssistidas.Buscar(String.valueOf(escolha));

                                System.out.println(Analisada.toString());

                                System.out.println("Digite uma nota de 0 a 5 para " + Analisada.nome);

                                escolha = ler1.nextInt();

                                if (Logado instanceof ClienteEspecialista) {
                                    System.out.println("Digite uma descrição para sua avaliação");
                                    String DescricaoAvaliacao = ler1.nextLine();
                                    DescricaoAvaliacao = ler1.nextLine(); // Duplicado para não pular a leitura
                                    try {
                                        Logado.Avaliar(Analisada, escolha, DescricaoAvaliacao);
                                    } catch (IllegalArgumentException e) {
                                        System.out.println(e.getMessage());
                                    }
                                } else {
                                    try {
                                        Logado.Avaliar(Analisada, escolha, "");
                                        System.out.println("Midia Avaliada.");
                                        
                                        if (Logado.podeSerEspecialista()) {
                                            ClienteEspecialista especialista = new ClienteEspecialista(Logado);
                                            Logado = especialista;
                                            System.out.println("Parabéns, você agora é um cliente especialista.");
                                        }
                                    } catch (IllegalArgumentException e) {
                                        System.out.println(e.getMessage());
                                    }
                                }

                            } else {
                                System.out.println("Mídia não está na sua lista de assistidos.");
                            }

                        } else {
                            System.out.println("Sua lista está vazia");
                        }

                        break;
                    // #endregion

                    case 5:

                        if (Logado.MidiasAvaliadas.isVazia()) {

                            System.out.println("Você não possui mídias avaliadas.");

                        } else {

                            System.out.println("Suas avaliações: ");

                            System.out.println(Logado.imprimirAvaliacoes());
                        }

                        break;

                    // #region Deslogar
                    case 6:
                        System.out.println("Obrigado por utilizar nosso sistema.");
                        sair = true;
                        logado = false;
                        break;
                    // #endregion

                }

            }

        }
        ler1.close();

    }
}
