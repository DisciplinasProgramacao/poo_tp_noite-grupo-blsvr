public class Serie {
    String nome, idioma, genero;
    int visualizacoes, id;

    private void init(String nome, String idioma, String genero, int id) {
        this.nome = nome;
        this.idioma = idioma;
        this.genero = genero;
        this.id = id;
    }

    public Serie(String nome, String idioma, String genero, int id) {
        init(nome, idioma, genero, id);
    }

    public void adcionarVisualizacao() {
        visualizacoes++;
    }
}
