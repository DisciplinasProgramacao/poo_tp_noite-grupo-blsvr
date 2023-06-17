public class Cliente {
    String nome, login, senha;
    ListaMidia MidiasAssistidas;
    ListaMidia MidiasFuturas;
    ListaMidia MidiasAvaliadas;
    int QUANTIDADE_PARA_ESPECIALISTA = 5;

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

    // /**
    //  * Transforma o cliente em especialista
    //  */
    // public void VirarEspecialista() {

    //     if (this.MidiasAvaliadas.tamanhoLista() >= QUANTIDADE_PARA_ESPECIALISTA) {

    //     }

    // }

    /**
     * Adiciona a midia na lista das series planejadas para assistir
     * 
     * @param planejada Mídia planejada para assistir
     */
    public void planejarParaAssistir(Midia planejada) {
        if (!MidiasFuturas.Contem(planejada.ID))
            MidiasFuturas.AdicionarMidia(planejada.ID, planejada);
    }

}