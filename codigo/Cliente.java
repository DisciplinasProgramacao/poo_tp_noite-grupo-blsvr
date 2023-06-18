public class Cliente {
    String nome, login, senha;
    ListaMidia MidiasAssistidas;
    ListaMidia MidiasFuturas;
    ListaMidia MidiasAvaliadas;
    private int QUANTIDADE_PARA_ESPECIALISTA = 5;

    private void init(String nome, String login, String senha) {
        this.login = login;
        this.senha = senha;
        this.nome = nome;
        this.MidiasAssistidas = new ListaMidia();
        this.MidiasFuturas = new ListaMidia();
        this.MidiasAvaliadas = new ListaMidia();

    }

    public Cliente(String nome, String login, String senha) {
        init(nome, login, senha);
    }

    /**
     * Verifica se o cliente atingiu o necessário para se tornar um Cliente
     * Especialista
     * 
     * @return Verdadeiro caso ele possa ser um cliente especialista, falso caso ele
     *         não seja um cliente especialista ainda
     */
    public boolean podeSerEspecialista() {
        return this.MidiasAvaliadas.tamanhoLista() >= QUANTIDADE_PARA_ESPECIALISTA;
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

    /**
     * Faz com que o cliente avalie uma mídia criando uma avaliação normal.
     * 
     * @param MidiaAvaliada Mídia que está sendo avaliada
     * @param nota          Nota que o usuário deu para mídia
     * @param Descricao     Descrição que o usuário possa ter(Caso ele seja um
     *                      Cliente normal nenhuma descrição será salva)
     */
    public void Avaliar(Midia MidiaAvaliada, int nota, String Descricao) {
        if (!this.MidiasAvaliadas.Contem(MidiaAvaliada.ID)) {
            Avaliacao novaAval = new Avaliacao(nota, this);
            MidiaAvaliada.AdicionarAvaliacao(novaAval);
            this.MidiasAvaliadas.AdicionarMidia(MidiaAvaliada.ID, MidiaAvaliada);
        }

    }

    /**
     * Imprime todas as avaliações desse cliente
     * 
     * @return String com todas as informações das avaliações desse cliente
     */
    public String imprimirAvaliacoes() {
        return this.MidiasAvaliadas.imprimirAvaliacoes(this);
    }

}
