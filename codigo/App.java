import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        Streaming sesao = new Streaming();

        sesao.cadastrarVariosUsuarios("arquivos/POO_Espectadores.csv");
        sesao.cadastrarVariasMidias("arquivos/POO_Series.csv", "arquivos/POO_Filmes.csv");
        sesao.cadastrarAudiencia("arquivos/POO_Audiencia.csv");

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
                    break;

            }
        }

        boolean sair = false;
        while (!sair) {

            System.out.println("BEM-VINDO");
            System.out.println("ESCOLHA SUA AÇÃO:");

        }

        ler1.close();
    }
}
