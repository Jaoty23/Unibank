import java.util.ArrayList;
import java.util.List;


// Estrutura Principal:

public class CaixaEletronico implements ICaixaEletronico {
	
	// Atributos 
    private int[][] notas; // matriz que guarda os valores das cédulas e suas quantidades. [100, 10] significa 10 notas de 100.
    private int cotaMinima; // Valor Mínimo que o caixa deve manter.
    private List<String> extrato; // reprenta o extrato, é a lista de operações realizadas (saques, reposições, tentativas inválidas).
    
    
    // Método Construtor:
    public CaixaEletronico(int qtd100, int qtd50, int qtd20, int qtd10, int qtd5, int qtd2) {
        notas = new int[6][2];
        notas[0][0] = 100; notas[0][1] = qtd100;
        notas[1][0] = 50;  notas[1][1] = qtd50;
        notas[2][0] = 20;  notas[2][1] = qtd20;
        notas[3][0] = 10;  notas[3][1] = qtd10;
        notas[4][0] = 5;   notas[4][1] = qtd5;
        notas[5][0] = 2;   notas[5][1] = qtd2;

        extrato = new ArrayList<>();
        cotaMinima = 0;
    }
    
    
    // Métodos Principais:
    
    
    // 1. Valor Total Disponível.
    @Override
    public String pegaValorTotalDisponivel() {
        int total = 0;
        for (int[] nota : notas) {
            total += nota[0] * nota[1];
        }
        return "Valor total disponível: R$ " + total;
    }
    
    
    // 2. Saque.
    @Override
    public String sacar(Integer valor) {
        if (valor <= 0) return "Valor inválido!";
        int restante = valor;
        int[] usadas = new int[6];
        int totalNotas = 0;

        for (int i = 0; i < notas.length; i++) {
            int cedula = notas[i][0];
            int disponivel = notas[i][1];
            int qtd = Math.min(restante / cedula, disponivel);
            usadas[i] = qtd;
            restante -= qtd * cedula;
            totalNotas += qtd;
        }

        if (restante > 0) {
            extrato.add("Tentativa de saque R$" + valor + " - Não temos notas suficientes");
            return "Não Temos Notas Para Este Saque";
        }
        if (totalNotas > 30) {
            extrato.add("Tentativa de saque R$" + valor + " - Excedeu limite de 30 notas");
            return "Limite de 30 notas excedido!";
        }

        for (int i = 0; i < notas.length; i++) {
            notas[i][1] -= usadas[i];
        }

        StringBuilder sb = new StringBuilder("Saque realizado: R$" + valor + "\n");
        for (int i = 0; i < notas.length; i++) {
            if (usadas[i] > 0) {
                sb.append(usadas[i]).append(" nota(s) de R$").append(notas[i][0]).append("\n");
            }
        }

        extrato.add("Saque R$" + valor);
        if (getTotal() < cotaMinima) {
            sb.append("Caixa Vazio: Chame o Operador\n");
        }
        return sb.toString();
    }

    
    // 3. Relatório de Cédulas.
    @Override
    public String pegaRelatorioCedulas() {
        StringBuilder sb = new StringBuilder("Relatório de Cédulas:\n");
        for (int[] nota : notas) {
            sb.append("R$").append(nota[0]).append(" - ").append(nota[1]).append(" cédulas\n");
        }
        return sb.toString();
    }
    
    
    // 4. Reposição de Cédulas.
    @Override
    public String reposicaoCedulas(Integer cedula, Integer quantidade) {
        for (int[] nota : notas) {
            if (nota[0] == cedula) {
                nota[1] += quantidade;
                String msg = "Reposição: " + quantidade + " cédulas de R$" + cedula;
                extrato.add(msg);
                return msg;
            }
        }
        return "Cédula inválida!";
    }
    
    
    // 5. Armazenar Cota Mínima.
    @Override
    public String armazenaCotaMinima(Integer minimo) {
        this.cotaMinima = minimo;
        String msg = "Cota mínima definida: R$" + minimo;
        extrato.add(msg);
        return msg;
    }
    
    
    // 6. Extrato Final.
    public String getExtratoFinal() {
        StringBuilder sb = new StringBuilder("Extrato Final:\n");
        for (String linha : extrato) {
            sb.append(linha).append("\n");
        }
        sb.append(pegaValorTotalDisponivel());
        return sb.toString();
    }
    
    
    // 7. Método Auxiliar.
    private int getTotal() {
        int total = 0;
        for (int[] nota : notas) {
            total += nota[0] * nota[1];
        }
        return total;
    }
}

