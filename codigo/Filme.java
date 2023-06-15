import Util.Data;

public class Filme extends Midia {
    String duracaoMinutos;

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
    public String dadosMidia() {
        String retorno = "Nome: " + nome + "\n Quantidade de visualizações: " + visualizacoes
                + "\n Média de avaliações: " + this.MediaAvaliacoes() + "\n Duração: " + duracaoMinutos + " minutos";
        return retorno;
    }

}
