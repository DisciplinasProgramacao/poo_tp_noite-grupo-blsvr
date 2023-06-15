import java.util.List;

import Util.Data;

abstract public class Midia {

    String nome;
    String idioma;
    String genero;
    Data DataLancamento;
    String ID;
    int visualizacoes;
    List<Avaliacao> Avaliacoes;

    /**
     * Retorna as informações principais da midia.
     * 
     * @return Retorna uma String contendo as informações
     */
    public String dadosMidia() {
        String retorno = "Nome: " + nome + "\n Quantidade de visualizações: " + visualizacoes;
        return retorno;
    }

    /**
     * Adiciona uma visualização a midia
     */
    public void AdicionarView() {
        this.visualizacoes++;
    }

    /**
     * Calcula a média de todas as avaliações presentes na mídia
     * 
     * @return Retorna o valor da média das avaliações
     */
    public double MediaAvaliacoes() {
        double media = 0;

        for (Avaliacao avaliacao : Avaliacoes) {
            media += avaliacao.Nota;
        }

        if (Avaliacoes.size() > 0) {
            media /= Avaliacoes.size();
        }

        return media;
    }

}
