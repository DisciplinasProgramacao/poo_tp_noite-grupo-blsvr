import java.util.Scanner;
public class App {
    public static void main(String[] args) {
        Streaming sesao = new Streaming();
        
        sesao.cadastrarVariasMidias(null);
        sesao.cadastrarVariosUsuarios(null);
        sesao.cadastrarAudiencia(null);

        Scanner ler1 = new Scanner(System.in);
        int escolha;
        String login = "";
        String senha = "";
        do {
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

                    break;

                default:
                    break;

            }
        } while (sesao.entrar(senha, login));

        boolean sair = false;
        while (!sair) {

            System.out.println("BEM-VINDO");

        }

        ler1.close();
    }

    public void incluirEspectadores() {

    }
}
