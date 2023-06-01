import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ListaMidia {
    private List<Midia> listaDeMidias;

    public ListaMidia() {
        listaDeMidias = new ArrayList<Midia>();
    }

    public void imprimirLista() {
        System.out.println(listaDeMidias.size());
        listaDeMidias.stream().forEach(Midia -> System.out.println(Midia.ID));
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
        
        for (Midia analisada : listaDeMidias) {
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
    public void AdicionarMidia(Midia Adicionada) {
        this.listaDeMidias.add(Adicionada);
    }

    /**
     * Remove da lista a midia passada como parâmetro
     * 
     * @param Adicionada Mídia a ser removida
     */
    public void RemoverMidia(Midia Adicionada) {
        this.listaDeMidias.remove(Adicionada);
    }

    /**
     * Pesquisa se a Midia está contida dentro da Lista
     * 
     * @param Pesquisada Midia que será pesquisada
     * @return Retorna verdadeiro se a midia foi encontrada e falso caso a midia não
     *         foi encontrada
     */
    public boolean Contem(Midia Pesquisada) {

        for (int i = 0; i < listaDeMidias.size(); i++) {

            if (listaDeMidias.get(i).equals(Pesquisada)) {
                return true;
            }
        }

        return false;

    }

}
