import java.util.ArrayList;
import java.util.List;

public class ListaMidia {
    private List<Midia> listaDeMidias;

    public ListaMidia() {
        listaDeMidias = new ArrayList<Midia>();
    }

    /**
     * Parâmetros vazios serão ignorados durante a busca. Esse método retorna uma
     * lista de séries com todas as séries que contenham a busca.
     * 
     * @param nome   Nome do filme buscado.
     * @param idioma Idioma do filme buscado.
     * @param genero Gênero do filme buscado.
     * @return
     */

    public List<Midia> Buscar(String nome, String idioma, String genero) {

        List<Midia> ListaRetorno = new ArrayList<Midia>();

        for (Midia Midia : listaDeMidias) {

            boolean nomeMatch = nome.isEmpty() || Midia.nome.contains(nome);
            boolean idiomaMatch = idioma.isEmpty() || Midia.idioma.contains(idioma);
            boolean generoMatch = genero.isEmpty() || Midia.genero.contains(genero);

            if (nomeMatch && idiomaMatch && generoMatch) {
                ListaRetorno.add(Midia);
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
     * @param Pesquisada Midia que será pesquisada
     * @return Retorna verdadeiro se a midia foi encontrada e falso caso a midia não foi encontrada
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
