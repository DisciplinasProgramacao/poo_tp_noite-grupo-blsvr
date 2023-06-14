import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Streaming {
    List<Cliente> clientesCadastrados;
    ListaMidia midiasCadastradas;

    public Streaming() {
        this.clientesCadastrados = new ArrayList<Cliente>();
        this.midiasCadastradas = new ListaMidia();
    }

    public Cliente entrar(String Login, String Senha) {

        for (int i = 0; i < clientesCadastrados.size(); i++) {
            if (clientesCadastrados.get(i).login.equals(Login) && clientesCadastrados.get(i).senha.equals(Senha)) {
                Cliente retorno = clientesCadastrados.get(i);
                return retorno;
            }
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
            clientesCadastrados.add(novoCliente);
        }
        else{
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
                Cliente novoCliente = new Cliente(dados[0], dados[1], dados[2]);
                clientesCadastrados.add(novoCliente);
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
                Serie novaSeria = new Serie(dados[1], "", "", dados[2], dados[0], 0, 0);
                midiasCadastradas.AdicionarMidia(novaSeria);
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
                midiasCadastradas.AdicionarMidia(novoFilme);

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
                List<Midia> ListamidiaAchada = midiasCadastradas.Buscar("", "", "", dados[2]);
                Midia midiaAchada;
                if (ListamidiaAchada.size() != 0) {
                    midiaAchada = ListamidiaAchada.get(0);
                    midiasCadastradas.RemoverMidia(midiaAchada);
                    midiaAchada.AdicionarView();
                    midiasCadastradas.AdicionarMidia(midiaAchada);
                    for (int i = 0; i < clientesCadastrados.size(); i++) {
                        if (clientesCadastrados.get(i).login.equals(dados[0])) {
                            if (dados[1].equals("F")) {
                                clientesCadastrados.get(i).MidiasFuturas.AdicionarMidia(midiaAchada);
                            } else {
                                clientesCadastrados.get(i).MidiasAssistidas.AdicionarMidia(midiaAchada);

                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // #endregion

}