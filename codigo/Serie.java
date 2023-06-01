import Util.Data;

public class Serie extends Midia {

    int quantidadeEpisodios;

    // 3489;Yellow County;14/09/2016

    public Serie(String nome, String idioma, String genero, String DataLancamento, String ID, int visualizacoes,
            int quantidadeEpisodios) {

        this.nome = nome;
        this.genero = genero;
        this.DataLancamento = new Data(DataLancamento);
        this.ID = ID;
        this.visualizacoes = visualizacoes;
        this.quantidadeEpisodios = quantidadeEpisodios;

    }

}
