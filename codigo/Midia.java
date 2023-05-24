import javafx.scene.chart.PieChart.Data;

abstract public class Midia {

    String nome;
    String idioma;
    String genero;
    Data DataLancamento;
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
