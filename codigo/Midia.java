import java.util.Date;

abstract public class Midia {

    String nome;
    String idioma;
    String genero;
    Date DataLancamento;
    int ID;
    int visualizacoes;
    int MediaAvaliacoes;
    int totalAvaliacoes;

    /**
     * Adiciona uma visualização a midia
     */
    public void AdicionarView() {
        this.visualizacoes++;
    }

    public void Avaliar(int Nota) {
        if (Nota <= 5) {
            totalAvaliacoes += Nota;
            MediaAvaliacoes = visualizacoes / totalAvaliacoes;
            return;
        }
    }

}
