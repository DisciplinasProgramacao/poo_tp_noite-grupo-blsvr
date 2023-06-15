public class Avaliacao {
    int Nota;
    static int NOTA_MAXIMA = 5;

    /**
     * Cria uma avaliação e deve conter uma nota de 0 até 5
     * 
     * @param Nota Número inteiro de 0 a 5
     */
    public Avaliacao(int Nota) {
        if (Nota <= NOTA_MAXIMA && Nota >= 0) {
            this.Nota = Nota;
        } else {
            throw new IllegalArgumentException("A nota fornecida está fora do intervalo válido.");
        }
    }
}
