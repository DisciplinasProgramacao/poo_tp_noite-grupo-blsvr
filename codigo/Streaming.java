import java.util.List;

public class Streaming {
    List<Cliente> clientesCadastrados;
    ListaMidia seriesCadastradas;

    public void incluirEspectadores() {

    }

    /**
     * Verifica se o usuário inseriu sua senha e login corretamente
     * 
     * @param Senha Senha inserida a ser comparada
     * @param Login Login inserido a ser comparado
     * @return Retorna verdadeiro se o usuário foi encontrado ou falso se o usuário
     *         não foi encontrado
     */
    public boolean entrar(String Senha, String Longin) {

        for (int i = 0; i < clientesCadastrados.size(); i++) {
            if (clientesCadastrados.get(i).login.equals(Senha) && clientesCadastrados.get(i).senha.equals(Longin)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Cria um usuário e insere ele na lista de usuários cadastrados caso o usuário
     * já não esteja presente na lista de usuários cadastrados
     * 
     * @param Login Login inserido pelo cliente a ser adicionado
     * @param Senha Senha inserida pelo cliente a ser adicionado
     * @param Nome Nome inserido pelo cliente a ser adicionado
     */
    public void cadastrar(String Login, String Senha, String Nome) {
        Cliente novoCliente = new Cliente(Senha, Login, Nome);
        if (!entrar(Senha, Login)) {
            clientesCadastrados.add(novoCliente);
        }
    }

}
