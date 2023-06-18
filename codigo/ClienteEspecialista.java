public class ClienteEspecialista extends Cliente {

    public ClienteEspecialista(Cliente clienteNormal) {

        super(clienteNormal.nome, clienteNormal.login, clienteNormal.senha);

        CopiarMidias(this.MidiasAvaliadas, clienteNormal.MidiasAvaliadas);
        CopiarMidias(this.MidiasAssistidas, clienteNormal.MidiasAssistidas);
        CopiarMidias(this.MidiasFuturas, clienteNormal.MidiasFuturas);

    }

    /**
     * Cria uma cópia de cada lista para que as referências sejam ligadas ao novo
     * Cliente Especialista
     * 
     * @param NovaLista   Lista nova que receberá os valores
     * @param AntigaLista Lista antiga que dará os valores
     */
    private void CopiarMidias(ListaMidia NovaLista, ListaMidia AntigaLista) {

        for (Midia Analisada : AntigaLista.listaDeMidias.values()) {
            NovaLista.listaDeMidias.put(Analisada.ID, Analisada);
        }

    }

    /**
     * Avaliação com uma descrição que deve ser feita por um especialista
     * 
     * @param Avaliada  Mídia que será avaliada
     * @param Nota      Nota dada para a mídia
     * @param Descricao Descrição da avaliação dada pelo especialista
     */
    public void Avaliar(Midia Avaliada, int Nota, String Descricao) {
        if (MidiasAssistidas.Contem(Avaliada.ID) && !MidiasAvaliadas.Contem(Avaliada.ID)) {
            AvaliacaoEspecialista novaAvaliacao = new AvaliacaoEspecialista(Nota, Descricao, this);
            Avaliada.AdicionarAvaliacao(novaAvaliacao);
            MidiasAvaliadas.AdicionarMidia(Avaliada.ID, Avaliada);

        }
    }

}
