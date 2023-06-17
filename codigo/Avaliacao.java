public class Avaliacao {
    int Nota;
    Cliente Avaliador;
    static int NOTA_MAXIMA = 5;

    /**
     * Cria uma avaliação e deve conter uma nota de 0 até 5
     * 
     * @param Nota Número inteiro de 0 a 5
     */
    public Avaliacao(int Nota, Cliente Avaliador) {
        if (Nota <= NOTA_MAXIMA && Nota >= 0) {
            this.Nota = Nota;
            this.Avaliador = Avaliador;
        } else {
            throw new IllegalArgumentException("A nota fornecida está fora do intervalo válido.");
        }
    }

    public String ImprimirAval() {
        return "Nota: " + Nota + "\n";
    }
}
