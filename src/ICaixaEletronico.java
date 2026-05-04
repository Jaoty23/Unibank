
// Esta classe define claramente todas as operações essenciais do caixa eletronico.


// Estrutura Principal:
public interface ICaixaEletronico {
	
    String pegaValorTotalDisponivel(); // Calcula e retorna o valor total disponivel no caixa eletronico.
    String sacar(Integer valor); // deve realizar a operação de saque verificando se há notas suficientes respeita o limite de 30 cédulas e retornando uma mensagem com o resultado.
    String pegaRelatorioCedulas(); // Gera um relatório mostrando a quantidade de quantas notas de qual valor estão disponíveis.
    String reposicaoCedulas(Integer cedula, Integer quantidade); // Permite ao administrador repor notas e volta mensagem confirmando operação.
    String armazenaCotaMinima(Integer minimo); // Ele armazena o limite e mostra um alerta quando o saldo estiver abaixo do valor fornecido.
}