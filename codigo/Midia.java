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
     * Adiciona uma visualização a midia
     */
    public void AdicionarView() {
        this.visualizacoes++;
    }

    public double MediaAvaliacoes() {
        double media = 0;
        
        for (Avaliacao avaliacao : Avaliacoes) {
            media += avaliacao.Nota;
        }

        return media / Avaliacoes.size();

    }

}
