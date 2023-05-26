import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Streaming {
    List<Cliente> clientesCadastrados;
    ListaMidia midiasCadastradas;

    public void incluirEspectadores() {

    }

    /**
     * Verifica se o usuário inseriu sua senha e login corretamente
     * 
     * @param Senha Senha inserida a ser comparada
     * @param Login Login inserido a ser comparado
     * @return Retorna verdadeiro se o usuário foi encontrado ou falso se o usuário
     *         não foi encontrado
     */
    public boolean entrar(String Senha, String Longin) {

        for (int i = 0; i < clientesCadastrados.size(); i++) {
            if (clientesCadastrados.get(i).login.equals(Senha) && clientesCadastrados.get(i).senha.equals(Longin)) {
                return true;
            }
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
    public void cadastrar(String Login, String Senha, String Nome) {
        Cliente novoCliente = new Cliente(Senha, Login, Nome);
        if (!entrar(Senha, Login)) {
            clientesCadastrados.add(novoCliente);
        }
    }

    public void cadastrarVariosUsuarios(String CaminhoArquivo) {

        String arquivoCSVEspectadores = "../arquivos/POO_Espectadores.csv";
        String linha;
        String separador = ";";
        try (BufferedReader br = new BufferedReader(new FileReader(arquivoCSVEspectadores))) {
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(separador);

                // Ada A N Gaiman;Ada15;ABro14420

                Cliente novoCliente = new Cliente(dados[0], dados[1], dados[2]);
                clientesCadastrados.add(novoCliente);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void cadastrarVariasMidias(String CaminhoArquivo) {

        String arquivoCSVSerie = "../arquivos/POO_Series.csv";
        String arquivoCSVFilme = "../arquivos/POO_Filmes.csv";
        String linha;
        String separador = ";";
        try (BufferedReader br = new BufferedReader(new FileReader(arquivoCSVSerie))) {
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(separador);
                // 3489;Yellow County;14/09/2016

                int ID = Integer.parseInt(dados[0]);
                int DataLancamento = Integer.parseInt(dados[2]);
                Serie novaSeria = new Serie(dados[1], "", "", DataLancamento, ID, 0, 0, 0, 0);
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

                int ID = Integer.parseInt(dados[0]);
                int DataLancamento = Integer.parseInt(dados[2]);
                Filme novoFilme = new Filme("", "", "", DataLancamento, ID, 0, 0, 0, 0);
                midiasCadastradas.AdicionarMidia(novoFilme);

            }
        } catch (IOException x) {
            x.printStackTrace();
        }

    }

    public void cadastrarAudiencia(String CaminhoArquivo) {

        String arquivoCSV = "../arquivos/POO_Audiencia.csv";
        String linha;
        String separador = ";";
        try (BufferedReader br = new BufferedReader(new FileReader(arquivoCSV))) {
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(separador);

                Midia midiaAchada = midiasCadastradas.Buscar("", "", "", dados[2]).get(0);
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
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
