import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Streaming {
    HashMap<String, Cliente> clientesCadastrados;
    ListaMidia midiasCadastradas;

    public Streaming() {
        this.clientesCadastrados = new HashMap<String, Cliente>();
        this.midiasCadastradas = new ListaMidia();
    }

    /**
     * Verifica se o cliente existe e retorna o cliente
     * 
     * @param Login Login do cliente
     * @param Senha Senha do cliente
     * @return Retorna o cliente se ele estiver registrado ou nulo se o cliente não
     *         for encontrado.
     */
    public Cliente entrar(String Login, String Senha) {

        if (clientesCadastrados.containsKey(Login)) {

            Cliente cliente = clientesCadastrados.get(Login);

            return cliente;
        }
        return null;

    }

    /**
     * Verifica se o usuário inseriu sua senha e login corretamente
     * 
     * @param Senha Senha inserida a ser comparada
     * @param Login Login inserido a ser comparado
     * @return Retorna verdadeiro se o usuário foi encontrado ou falso se o usuário
     *         não foi encontrado
     */
    public boolean Confirmar(String Senha, String Login) {

        if (entrar(Login, Senha) != null) {
            return true;
        }
        return false;
    }

    /**
     * Cria um usuário e insere ele na lista de usuários cadastrados caso o usuário
     * já não esteja presente na lista de usuários cadastrados
     * 
     * @param Login Login inserido pelo cliente a ser adicionado
     * @param Senha Senha inserida pelo cliente a ser adicionado
     * @param Nome  Nome inserido pelo cliente a ser adicionado
     */
    public void cadastrar(String Login, String Senha, String Nome) throws Exception {
        Cliente novoCliente = new Cliente(Nome, Login, Senha);
        if (!Confirmar(Senha, Login)) {
            clientesCadastrados.putIfAbsent(Login, novoCliente);
        } else {
            throw new Exception("Cliente já existente");
        }

    }

    // #region Cadastros vindo de arquivos

    /**
     * Cadastra diversos espectadores dentro da lista de clientes cadastrados.
     * 
     * @param CaminhoArquivoEspectadores Caminho no qual o arquivo .csv para leitura
     *                                   está salvo
     */
    public void cadastrarVariosUsuarios(String CaminhoArquivoEspectadores) {

        try {
            // Ada A N Gaiman;Ada15;ABro14420

            File arquivo = new File(CaminhoArquivoEspectadores);
            Scanner ler1 = new Scanner(arquivo);

            while (ler1.hasNextLine()) {
                String[] dados = ler1.nextLine().split(";");
                if (dados != null) {
                    Cliente novoCliente = new Cliente(dados[0], dados[1], dados[2]);
                    clientesCadastrados.put(dados[1], novoCliente);
                }
            }
            ler1.close();
        } catch (FileNotFoundException x) {
            System.out.println("File not found");
        }

    }

    /**
     * Cadastra diversas mídias dentro da lista de mídias cadastradas.
     * 
     * @param CaminhoArquivo
     */
    public void cadastrarVariasMidias(String CaminhoArquivoSeries, String CaminhoFilmes) {

        String arquivoCSVSerie = CaminhoArquivoSeries;
        String arquivoCSVFilme = CaminhoFilmes;
        String linha;
        String separador = ";";
        try (BufferedReader br = new BufferedReader(new FileReader(arquivoCSVSerie))) {
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(separador);
                // 3489;Yellow County;14/09/2016
                Serie novaSerie = new Serie(dados[1], "", "", dados[2], dados[0], 0, 0);
                midiasCadastradas.AdicionarMidia(novaSerie.ID, novaSerie);
            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader br = new BufferedReader(new FileReader(arquivoCSVFilme))) {
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(separador);
                // 9752;Defenders Of Our Future;17/12/2016;112
                Filme novoFilme = new Filme(dados[1], "", "", dados[2], dados[0], 0, dados[3]);
                midiasCadastradas.AdicionarMidia(novoFilme.ID, novoFilme);
            }
        } catch (IOException x) {
            x.printStackTrace();
        }

    }

    /**
     * Cadastra audiência cadastrando a visualização da mídia apenas se a mídia já
     * tiver sido assistida pelo usuário além de adicionar a mídia para o respectivo
     * espectador.
     * 
     * @param CaminhoArquivo
     */

    public void cadastrarAudiencia(String CaminhoArquivo) {

        String arquivoCSV = CaminhoArquivo;
        String linha;
        String separador = ";";
        try (BufferedReader br = new BufferedReader(new FileReader(arquivoCSV))) {
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(separador);

                // Nei144706;F;3465

                Midia assistida = midiasCadastradas.Buscar(dados[2]);
                if (clientesCadastrados.containsKey(dados[0]) && midiasCadastradas.Contem(dados[2])) {
                    if (dados[1].equals("F")) {
                        clientesCadastrados.get(dados[0]).assistir(assistida);
                    } else {
                        clientesCadastrados.get(dados[0]).planejarParaAssistir(assistida);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // #endregion

}