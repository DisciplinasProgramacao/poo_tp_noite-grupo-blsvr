import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class App {
    public static void main(String[] args) {
        Cliente cliente;
        String arquivoCSV = "../arquivos/POO_Audiencia.csv";
        String linha;
        String separador = ";";
        try (BufferedReader br = new BufferedReader(new FileReader(arquivoCSV))) {
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(separador);
                for (String dado : dados) {
                    cliente = new Cliente(dados[0], dados[1], dados[2]);
                    System.out.print(dado + " ");
                }
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
