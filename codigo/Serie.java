import Util.Data;

public class Serie extends Midia {

    private int quantidadeEpisodios;

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

    @Override
    public String toString() {
        String retorno = "Nome: " + nome + "\n Quantidade de visualizações: " + visualizacoes
                + "\n Média de avaliações: " + this.MediaAvaliacoes() + "\n Número de episódios: " + quantidadeEpisodios
                + " episódios " + "\n Código de mídia: " + this.ID;
        return retorno;
    }

}
