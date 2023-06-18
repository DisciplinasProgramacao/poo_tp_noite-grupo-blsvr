import java.util.HashMap;

import Util.Data;

abstract public class Midia {

    String nome;
    String idioma;
    String genero;
    Data DataLancamento;
    String ID;
    int visualizacoes;
    HashMap<String, Avaliacao> Avaliacoes;

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
        Avaliacoes = new HashMap<String, Avaliacao>();
        DataLancamento = new Data();
    }

    /**
     * Adiciona uma avaliação a lista de avaliações do cliente
     * 
     * @param Avaliacao Avaliação que será adicionada
     */
    public void AdicionarAvaliacao(Avaliacao Avaliacao) {

        if (!this.Avaliacoes.containsKey(Avaliacao.Avaliador.login)) {
            this.Avaliacoes.put(Avaliacao.Avaliador.login, Avaliacao);
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
