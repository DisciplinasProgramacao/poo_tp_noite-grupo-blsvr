import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.io.File;
import java.io.FileNotFoundException;

public class Streaming {
    public HashMap<String, Cliente> clientesCadastrados;
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
                    if (dados[1].equals("A")) {
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

    /**
     * Analisa quais são as 10 mídias com 100 ou mais vizualizações com melhor
     * avaliação
     * 
     * @return Uma String com todas as 10 mídias com 100 ou mais vizualizações e
     *         melhor avaliação
     */
    public String Top10Midias() {

        List<Midia> top10Midias = this.midiasCadastradas.listaDeMidias.values().stream()
                .filter(Midia -> Midia.visualizacoes >= 100)
                .sorted(Comparator.comparingDouble(Midia::MediaAvaliacoes).reversed())
                .limit(10).collect(Collectors.toList());

        String retorno = "As 10 mídias com a melhor avaliação e pelo menos 100 avaliações são: \n";

        int contador = 1;

        for (Midia analisada : top10Midias) {

            retorno += contador + "º Lugar -> " + analisada.nome + " com uma nota média de "
                    + analisada.MediaAvaliacoes()
                    + "\n";

            contador++;

        }

        return retorno;

    }

    /**
     * Retorna a porcentagem total com de clientes que assistiram pelo menos 15
     * mídias
     * 
     * @return Uma string contendo a informação da porcentagem total
     */
    public String porcentagemClientes15Aval() {

        long clientesComQuinzeAvaliacoes = this.clientesCadastrados.values().stream()
                .filter(cliente -> cliente.MidiasAvaliadas.tamanhoLista() >= 15).count();

        long todosClientes = this.clientesCadastrados.size();

        double porcentagem = (clientesComQuinzeAvaliacoes * 100.0) / todosClientes;

        return "Porcentagem de clientes com pelo menos 15 avaliações: " + porcentagem
                + "%";
    }

    /**
     * Retorna o cliente com mais avaliações dentro dos clientes cadastrados
     * 
     * @return Uma String com a informação do nome do usuário e quantas mídias ele
     *         avaliou
     */
    public String clienteComMaisAvaliacoes() {

        Cliente maisAvaliacoes = this.clientesCadastrados.values().stream().max(
                (o1, o2) -> o1.MidiasAvaliadas.tamanhoLista()
                        - o2.MidiasAvaliadas.tamanhoLista())
                .get();

        return "Cliente que realizou mais avaliações: " + maisAvaliacoes.nome
                + " Avaliou: " + maisAvaliacoes.MidiasAvaliadas.tamanhoLista() + " mídias";
    }

    /**
     * Retorna o cliente com mais mídias vistas dentro dos clientes cadastrados
     * 
     * @return Uma String com a informação do nome do usuário e quantas mídias ele
     *         assistiu
     */
    public String clienteComMaisMidiasVistas() {

        Cliente clienteMax = this.clientesCadastrados.values().stream().max(
                (o1, o2) -> o1.MidiasAssistidas.tamanhoLista()
                        - o2.MidiasAssistidas.tamanhoLista())
                .get();

        return "Cliente que assistiu mais mídias: " + clienteMax.nome
                + " Assistiu: " + clienteMax.MidiasAssistidas.tamanhoLista() + " mídias";

    }

    /**
     * Analisa qual as top 10 mídias com melhor avaliação
     * 
     * @return Uma String com as informações das top 10 mídias com melhor avaliação
     */
    public String midiasComMaisViews() {

        List<Midia> topViewsMidias = this.midiasCadastradas.listaDeMidias.values().stream()
                .sorted(Comparator.comparingInt(Midia::getViews).reversed())
                .limit(10).collect(Collectors.toList());

        String retorno = "As 10 mídias mais vizualisações são: \n";

        int contador = 1;

        for (Midia analisada : topViewsMidias) {

            retorno += contador + "º Lugar -> " + analisada.nome + " com um total de "
                    + analisada.getViews() + " visualizações"
                    + "\n";

            contador++;

        }

        return retorno;
    }

    /**
     * Filtra a lista de mídias cadastradas para determinado gênero
     * 
     * @param metodoDeSeparacao Método que define o parâmetro pelo qual a lista será
     *                          filtrada
     * @param Genero            Gênero para filtrar a lista
     * @return Retorna uma lista de mídia separada pelo gênero passado por parâmetro
     */
    private List<Midia> separarMidiaPorGenero(Function<Midia, ? extends Number> metodoDeSeparacao, String Genero) {
        return midiasCadastradas.listaDeMidias.values().stream()
                .filter(Midia -> Midia.genero.equals(Genero))
                .filter(mediaItem -> mediaItem.getViews() >= 100)
                .sorted(Comparator.comparing(m -> metodoDeSeparacao.apply(m).doubleValue(), Comparator.reverseOrder()))
                .limit(10)
                .collect(Collectors.toList());

    }

    /**
     * Analisa quais são as 10 mídias com a melhor média de avaliações e que tenham
     * sido vistas pelo menos 100 vezes, separados por gênero
     * 
     * @param Genero Gênero pelo qual a Lista será separada
     * @return Uma String com todas as informações do rank da lista
     */
    public String midiascomMelhorAvaliacoesPorGenero(String Genero) {

        List<Midia> ListaMidiaPorGenero = separarMidiaPorGenero(Midia::MediaAvaliacoes, Genero);

        String retorno = "As 10 mídias com a melhor avaliações do gênero " + Genero + " são: \n";

        int contador = 1;

        for (Midia analisada : ListaMidiaPorGenero) {

            retorno += contador + "º Lugar -> " + analisada.nome + " com um total de "
                    + analisada.getViews() + " visualizações"
                    + "\n";

            contador++;

        }

        return retorno;
    }

    /**
     * Analisa quais são as 10 mídias com mais visualizações, separados por gênero.
     * 
     * @param Genero Gênero pelo qual a Lista será separada
     * @return Uma String com todas as informações do rank da lista
     */
    public String maisVistasPorGenero(String Genero) {

        List<Midia> ListaMidiaPorGenero = separarMidiaPorGenero(Midia::getViews, Genero);

        String retorno = "As 10 mídias com mais vizualizações do gênero " + Genero + " são: \n";

        int contador = 1;

        for (Midia analisada : ListaMidiaPorGenero) {

            retorno += contador + "º Lugar -> " + analisada.nome + " com um total de "
                    + analisada.getViews() + " visualizações"
                    + "\n";

            contador++;

        }

        return retorno;

    }

}