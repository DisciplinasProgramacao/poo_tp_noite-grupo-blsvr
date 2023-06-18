public class AvaliacaoEspecialista extends Avaliacao {

    private String Descricao;

    /**
     * Define a avaliação especialista deve conter uma nota de 0 a 5 e uma descrição
     * da avaliiação
     * 
     * @param nota      número inteiro de 0 a 5
     * @param Descricao String de descrição
     */
    public AvaliacaoEspecialista(int nota, String Descricao, Cliente Avaliador) {
        super(nota, Avaliador);
        this.Descricao = Descricao;
    }

    @Override
    public String ImprimirAval() {
        return "Nota: " + this.Nota + " Descrição: " + this.Descricao + "\n";
    }

}