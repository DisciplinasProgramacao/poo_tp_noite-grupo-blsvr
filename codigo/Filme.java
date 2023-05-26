public class Filme extends Midia {
    int duracaoMinutos;

    public Filme(String nome, String idioma, String genero, int DataLancamento, int ID, int visualizacoes,
    int MediaAvaliacoes, int totalAvaliacoes, int duracaoMinutos) {

this.nome = nome;
this.MediaAvaliacoes = MediaAvaliacoes;
this.genero = genero;
this.DataLancamento.setTime(DataLancamento);
this.ID = ID;
this.visualizacoes = visualizacoes;
this.totalAvaliacoes = totalAvaliacoes;
this.duracaoMinutos = duracaoMinutos;

}
}
