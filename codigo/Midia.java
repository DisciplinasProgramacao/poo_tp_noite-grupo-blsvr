import javafx.scene.chart.PieChart.Data;

abstract public class Midia {
    
    String nome;
    String idioma;
    String genero;
    Data DataLancamento;
    int ID;
    int visualizacoes;    

    
    /**
     * Adiciona uma visualização a midia
     */
    public void AdicionarView(){
        this.visualizacoes++;
    }

}
