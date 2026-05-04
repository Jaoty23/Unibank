
// Essa Classe é responsável pela manutenção e supervisão do sistema, ou seja, Repõe dinheiro, gera relatórios,
// verifica saldo total, controla e define a cota minima.

public class Administrador {
    private String nome;
    private int id;

    
    // Construtor
    public Administrador(String nome, int id) {
        
    	// Atributos principais:
    	this.nome = nome; // Guarda o nome do administrador.
        this.id = id; // Identifica o administrador com o número único.
    }

    
    // Métodos de acesso: Utilizados para consultar os valores de "nome" e "id"
    public String getNome() {
        return nome;
    }

    public int getId() {
        return id;
    }

    //Métodos de Operação
    
    
    // 1. Reposição das Cédulas
    public void reporCedulas(CaixaEletronico caixa, int cedula, int quantidade) {
        String resultado = caixa.reposicaoCedulas(cedula, quantidade);
        System.out.println("Administrador " + nome + ": " + resultado);
    }
    // 2. Relatório de Cédulas
    public void gerarRelatorio(CaixaEletronico caixa) {
        System.out.println("Relatório solicitado pelo administrador " + nome + ":");
        System.out.println(caixa.pegaRelatorioCedulas());
    }
    // 3. Verificação do Valor Total
    public void verificarValorTotal(CaixaEletronico caixa) {
        System.out.println(caixa.pegaValorTotalDisponivel());
    }
    // 4. Responsável pela Cota Mínima
    public void verificarCotaMinima(CaixaEletronico caixa, int limite) {
        String resultado = caixa.armazenaCotaMinima(limite);
        System.out.println("Administrador " + nome + ": " + resultado);
    }
}
