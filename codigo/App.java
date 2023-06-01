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
        boolean Verificado = false;
        while (!Verificado) {
            System.out.println("Você deseja:");
            System.out.println("1: Entrar na conta");
            System.out.println("2: Criar uma conta");
            escolha = ler1.nextInt();

            switch (escolha) {
                case 1:

                    System.out.println("Digite seu login");
                    login = ler1.next();
                    System.out.println("Digite sua senha");
                    senha = ler1.next();
                    Verificado = sesao.entrar(senha, login);
                    break;

                default:

                case 2:
                    // CRIAR CONTA AQUI

                    break;

            }
        }

        boolean sair = false;
        while (!sair) {

            System.out.println("Bem-vindo");
            System.out.println("Escolha sua ação: ");

            System.out.println("1 - Pesquisar midia");
            System.out.println("2 - Verificar sua lista assistida");
            System.out.println("3 - Verificar sua lista para assistir");

            escolha = ler1.nextInt();

        }

        ler1.close();
    }
}
