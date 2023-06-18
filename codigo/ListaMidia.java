import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ListaMidia {
    protected HashMap<String, Midia> listaDeMidias;

    public ListaMidia() {
        listaDeMidias = new HashMap<>();
    }

    /**
     * Imprime os dados de cada item mídia da lista
     */
    public String toString() {

        String retorno = "";

        for (Midia atual : listaDeMidias.values()) {
            retorno += "\n Nome: " + atual.nome + " || Identificação: " + atual.ID + "\n";
        }

        return retorno;

    }

    /**
     * Retorna se a lista está vazia ou não
     * 
     * @return Retorna verdadeiro se a lista estiver vazia e falso se a lista não
     *         estiver vazia
     */
    public Boolean isVazia() {
        return listaDeMidias.size() <= 0 ? true : false;
    }

    /**
     * Busca uma mídia pelo seu ID diretamente
     * 
     * @param ID ID da mídia buscada
     * @return Retorna a mídia buscada e nulo caso nenhuma mídia seja achada
     */
    public Midia Buscar(String ID) {
        if (listaDeMidias.containsKey(ID)) {
            return listaDeMidias.get(ID);
        }
        return null;
    }

    /**
     * Retorna o tamanho da lista
     * 
     * @return número inteiro com o total de itens na lista
     */
    public int tamanhoLista() {
        return listaDeMidias.size();
    }

    /**
     * Parâmetros vazios serão ignorados durante a busca. Esse método retorna uma
     * lista de mídia com todas as mídias que contenham a busca retornará uma lista
     * vazia caso nada seja encontrado.
     * 
     * @param nome   Nome da mídia buscado.
     * @param idioma Idioma da mídia buscado.
     * @param genero Gênero da mídia buscado.
     * @param id     ID da mídia buscada.
     * @return Retorna uma lista com as mídias encontradas. Retornando nulo caso não
     *         sejam encontradas nenhuma mídia com os parâmetros passados
     */

    public List<Midia> Buscar(String nome, String idioma, String genero, String id) {

        List<Midia> ListaRetorno = new ArrayList<Midia>();

        for (Midia analisada : listaDeMidias.values()) {
            boolean nomeMatch = nome.isEmpty() || analisada.nome.contains(nome);
            boolean idiomaMatch = idioma.isEmpty() || analisada.idioma.contains(idioma);
            boolean generoMatch = genero.isEmpty() || analisada.genero.contains(genero);
            boolean idmatch = id.isEmpty() || analisada.ID.equals(id);
            if (nomeMatch && idiomaMatch && generoMatch && idmatch) {

                ListaRetorno.add(analisada);
            }
        }
        return ListaRetorno;
    }

    /**
     * Adiciona da lista a midia passada como parâmetro
     * 
     * @param Adicionada Mídia a ser adicionada
     */
    public void AdicionarMidia(String Id, Midia Adicionada) {
        this.listaDeMidias.put(Id, Adicionada);
    }

    /**
     * Remove da lista a midia passada como parâmetro
     * 
     * @param Removida Mídia a ser removida
     */
    public void RemoverMidia(String Id, Midia Adicionada) {
        this.listaDeMidias.remove(Id, Adicionada);
    }

    /**
     * Pesquisa se a Midia está contida dentro da Lista
     * 
     * @param Pesquisada Midia que será pesquisada
     * @return Retorna verdadeiro se a midia foi encontrada e falso caso a midia não
     *         foi encontrada
     */
    public boolean Contem(String Id) {
        return listaDeMidias.containsKey(Id);
    }

    /**
     * Imprime todas as avaliações das mídias contidas nessa lista
     * 
     * @param avaliador Avaliador que as avaliações serão pegas
     * @return Retorna uma String com as informações de todas as avaliações do
     *         avaliador
     */
    public String imprimirAvaliacoes(Cliente avaliador) {

        String retorno = "";

        for (Midia Midia : listaDeMidias.values()) {

            if (Midia.Avaliacoes.containsKey(avaliador.login)) {
                retorno += "\n Mídia: " + Midia.nome + " || Avaliação: "
                        + Midia.Avaliacoes.get(avaliador.login).ImprimirAval();
            }

        }

        return retorno;
    }

}
