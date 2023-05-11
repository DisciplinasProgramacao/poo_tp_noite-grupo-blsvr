import java.util.Hashtable;


public class Cliente {
    String nome, login, senha;
    Hashtable <Serie, String> seriesAssistidas = new Hashtable<>();
    Hashtable <Serie, String> seriesFuturas = new Hashtable<>();


    private void init(String nome, String login, String senha){
        this.login = login;
        this.senha = senha;
    }
    
    public Cliente(String nome, String login, String senha){
        init(nome, login, senha);
    }
   
  
    public void assistir(Serie assistida){
        seriesAssistidas.put(assistida, assistida.nome);
    }

    public void planejarParaAssistir(Serie planejada){
        seriesAssistidas.put(planejada, planejada.nome);
    }
}