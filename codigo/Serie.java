public class Serie extends Midia {

    int quantidadeEpisodios;

    // 3489;Yellow County;14/09/2016

    public Serie(String nome, String idioma, String genero, int DataLancamento, int ID, int visualizacoes,
            int MediaAvaliacoes, int totalAvaliacoes, int quantidadeEpisodios) {

        this.nome = nome;
        this.MediaAvaliacoes = MediaAvaliacoes;
        this.genero = genero;
        this.DataLancamento.setTime(DataLancamento);
        this.ID = ID;
        this.visualizacoes = visualizacoes;
        this.totalAvaliacoes = totalAvaliacoes;
        this.quantidadeEpisodios = quantidadeEpisodios;

    }

}
