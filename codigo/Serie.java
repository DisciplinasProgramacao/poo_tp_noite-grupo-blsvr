public class Serie {
    String nome, idioma, genero;
    int visualizacoes;

    private void init(String nome, String idioma, String genero){
        this.nome = nome;
        this.idioma = idioma;
        this.genero = genero;
    }

    public Serie(String nome, String idioma, String genero){
        init(nome, idioma, genero);
    }

    public void adcionarVisualizacao(){
        visualizacoes++;
    }
}
