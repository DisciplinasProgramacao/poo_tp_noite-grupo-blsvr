import Util.Data;

public class Filme extends Midia {
    private String duracaoMinutos;

    public Filme(String nome, String idioma, String genero, String DataLancamento, String ID, int visualizacoes,
            String duracaoMinutos) {

        this.nome = nome;
        this.genero = genero;
        this.DataLancamento = new Data(DataLancamento);
        this.ID = ID;
        this.visualizacoes = visualizacoes;
        this.duracaoMinutos = duracaoMinutos;

    }

    @Override
    public String toString() {
        return "Nome: " + nome + "\n Quantidade de visualizações: " + visualizacoes
                + "\n Média de avaliações: " + this.MediaAvaliacoes() + "\n Duração: " + duracaoMinutos + " minutos"
                + "\n Código de mídia: " + this.ID;
    }

}
