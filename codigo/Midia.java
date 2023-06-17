import java.util.HashMap;

import Util.Data;

abstract public class Midia {

    String nome;
    String idioma;
    String genero;
    Data DataLancamento;
    String ID;
    int visualizacoes;
    HashMap<Cliente, Avaliacao> Avaliacoes;

    /**
     * Retorna as informações principais da midia.
     * 
     * @return Retorna uma String contendo as informações
     */
    public String toString() {
        String retorno = "\n Nome: " + nome + "\n Quantidade de visualizações: " + visualizacoes
                + "\n Média de avaliações: " + this.MediaAvaliacoes();
        return retorno;
    }

    public Midia() {
        Avaliacoes = new HashMap<Cliente, Avaliacao>();
        DataLancamento = new Data();
    }

    public void AdicionarAvaliacao(Avaliacao Avaliacao) {

        if (!this.Avaliacoes.containsKey(Avaliacao.Avaliador)) {
            this.Avaliacoes.put(Avaliacao.Avaliador, Avaliacao);
        }

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

        for (Avaliacao avaliacao : Avaliacoes.values()) {
            media += avaliacao.Nota;
        }

        if (Avaliacoes.size() > 0) {
            media /= Avaliacoes.size();
        }

        return media;
    }

}
