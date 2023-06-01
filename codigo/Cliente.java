public class Cliente {
    String nome, login, senha;
    ListaMidia MidiasAssistidas;
    ListaMidia MidiasFuturas;

    private void init(String nome, String login, String senha) {
        this.login = login;
        this.senha = senha;
        this.nome = nome;
        this.MidiasAssistidas = new ListaMidia();
        this.MidiasFuturas = new ListaMidia();
    }

    public Cliente(String nome, String login, String senha) {
        init(nome, login, senha);
    }

    /**
     * Adiciona a midia a lista das midias assistidas
     * 
     * @param assistida Midia que foi assistida
     */
    public void assistir(Midia assistida) {
        MidiasAssistidas.AdicionarMidia(assistida);
        assistida.AdicionarView();
        MidiasFuturas.RemoverMidia(assistida);
    }

    /**
     * Adiciona a midia na lista das series planejadas para assistir
     * 
     * @param planejada Mídia planejada para assistir
     */
    public void planejarParaAssistir(Serie planejada) {
        if (!MidiasFuturas.Contem(planejada))
            MidiasFuturas.AdicionarMidia(planejada);
    }

    public void Avaliar(Midia Avaliar, int Nota) {
        if (MidiasAssistidas.Contem(Avaliar)) {
            Avaliacao novaAvaliacao = new Avaliacao(Nota);
            Avaliar.Avaliacoes.add(novaAvaliacao);
        }
    }
}