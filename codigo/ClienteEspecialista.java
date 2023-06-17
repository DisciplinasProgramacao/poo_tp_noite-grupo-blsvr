public class ClienteEspecialista extends Cliente {
    public ClienteEspecialista(String nome, String login, String senha, ListaMidia midiasAssistidas,
            ListaMidia midiasFuturas, ListaMidia midiasAvaliadas) {
        super(nome, login, senha);
        this.MidiasAssistidas = midiasAssistidas;
        this.MidiasFuturas = midiasFuturas;
        this.MidiasAvaliadas = midiasAvaliadas;
    }

    /**
     * Avaliação com uma descrição que deve ser feita por um especialista
     * 
     * @param Avaliada  Mídia que será avaliada
     * @param Nota      Nota dada para a mídia
     * @param Descricao Descrição da avaliação dada pelo especialista
     */
    public void Avaliar(Midia Avaliada, int Nota, String Descricao) {
        if (MidiasAssistidas.Contem(Avaliada.ID)) {
            Avaliacao novaAvaliacao = new AvaliacaoEspecialista(Nota, Descricao, this);
            Avaliada.AdicionarAvaliacao(novaAvaliacao);
        }
    }
}
