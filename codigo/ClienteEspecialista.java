public class ClienteEspecialista extends Cliente {
    public ClienteEspecialista(String nome, String login, String senha) {
        super(nome, login, senha);
    }

    /**
     * Avaliação com uma descrição que deve ser feita por um especialista
     * @param Avaliada Mídia que será avaliada
     * @param Nota Nota dada para a mídia
     * @param Descricao Descrição da avaliação dada pelo especialista
     */
    public void Avaliar(Midia Avaliada, int Nota, String Descricao) {
        if (MidiasAssistidas.Contem(Avaliada)) {
            Avaliacao novaAvaliacao = new AvaliacaoEspecialista(Nota, Descricao);
            Avaliada.Avaliacoes.add(novaAvaliacao);
        }
    }
}
