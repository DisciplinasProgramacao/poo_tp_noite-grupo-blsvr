import java.util.ArrayList;
import java.util.List;

public class ListaSerie {
    private List<Serie> listaDeSeries;

    public ListaSerie() {
        listaDeSeries = new ArrayList<Serie>();
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

    public List<Serie> buscar(String nome, String idioma, String genero) {

        List<Serie> ListaRetorno = new ArrayList<Serie>();

        for (Serie serie : listaDeSeries) {

            boolean nomeMatch = nome.isEmpty() || serie.nome.contains(nome);
            boolean idiomaMatch = idioma.isEmpty() || serie.idioma.contains(idioma);
            boolean generoMatch = genero.isEmpty() || serie.genero.contains(genero);

            if (nomeMatch && idiomaMatch && generoMatch) {
                ListaRetorno.add(serie);
            }
        }
        return ListaRetorno;
    }

}
