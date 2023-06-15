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

        if (!MidiasAssistidas.Contem(assistida.ID)) {
            assistida.AdicionarView();
            MidiasAssistidas.AdicionarMidia(assistida.ID, assistida);
            
            if (MidiasFuturas.Contem(assistida.ID)) {
                MidiasFuturas.RemoverMidia(assistida.ID, assistida);
            }
        }

    }

    /**
     * Adiciona a midia na lista das series planejadas para assistir
     * 
     * @param planejada Mídia planejada para assistir
     */
    public void planejarParaAssistir(Midia planejada) {
        if (!MidiasFuturas.Contem(planejada.ID))
            MidiasFuturas.AdicionarMidia(planejada.ID, planejada);
    }

    public void Avaliar(Midia Avaliar, int Nota) {
        if (MidiasAssistidas.Contem(Avaliar.ID)) {
            Avaliacao novaAvaliacao = new Avaliacao(Nota);
            MidiasAssistidas.RemoverMidia(Avaliar.ID, Avaliar);
            Avaliar.Avaliacoes.add(novaAvaliacao);
            MidiasAssistidas.AdicionarMidia(Avaliar.ID, Avaliar);
        }
    }
}