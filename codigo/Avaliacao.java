public class Avaliacao {
    int Nota;
    static int NOTA_MAXIMA = 5;

    public Avaliacao(int Nota) {
        if (Nota <= NOTA_MAXIMA && Nota >= 0)
            this.Nota = Nota;
    }

}
