

// Esta classe é responsável por solicitar saques e receber o resultado da operação.


// Estrutura da Classe:
public class Cliente {
    private String nome; //Identifica o Nome.
    private int numeroConta; //Numero da Conta do Cliente.

    
    // Método Construtor.
    public Cliente(String nome, int numeroConta) {
        this.nome = nome;
        this.numeroConta = numeroConta;
    }

    
    //Métodos de Acesso: Permitem consultar os dados do cliente sem expor diretamente os atributos.
    public String getNome() {
        return nome;
    }

    public int getNumeroConta() {
        return numeroConta;
    }
    
    
    // Método de Interação com o caixa:
    public void solicitarSaque(CaixaEletronico caixa, int valor) {
        String resultado = caixa.sacar(valor);
        System.out.println("Cliente " + nome + ": " + resultado);
    }
}