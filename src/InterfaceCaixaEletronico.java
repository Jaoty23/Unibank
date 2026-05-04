import javax.swing.*;
import java.awt.*;


	// Estrutura da Classe 
public class InterfaceCaixaEletronico extends JFrame {
    private CaixaEletronico caixa;
    private JLabel saldoLabel;
 
    // Construtor:
    public InterfaceCaixaEletronico() {
        // Inicializa o caixa com 10 notas de cada tipo
        caixa = new CaixaEletronico(100, 200, 300, 350, 450, 500);					
        
        setTitle("UniBank");
        setSize(420, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(45, 45, 45)); // fundo cinza escuro

        // Componentes da Interface
        
        // Título Estilizado - Mostra o Nome do banco no topo da Janela.
        JLabel titulo = new JLabel("UniBank", SwingConstants.CENTER); 
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 30));
        titulo.setForeground(new Color(0, 200, 150)); // verde elegante
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        add(titulo, BorderLayout.NORTH);

        // Painel de saldo - Exibe o valor total disponível no caixa.
        saldoLabel = new JLabel(caixa.pegaValorTotalDisponivel(), SwingConstants.CENTER);
        saldoLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        saldoLabel.setForeground(Color.WHITE);
        saldoLabel.setOpaque(true);
        saldoLabel.setBackground(new Color(55, 55, 65)); // painel de saldo
        saldoLabel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        add(saldoLabel, BorderLayout.CENTER);

        
        
        // Painel de botões - Organiza os botões em uma grade vertical.
        JPanel botoes = new JPanel(new GridLayout(6, 1, 10, 10));
        botoes.setBackground(new Color(45, 45, 45));
        botoes.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        // Botão Saque.
        JButton btnSaque = criarBotao("Efetuar Saque", new Color(25, 50, 100), "/icons/saque.png");
        btnSaque.addActionListener(e -> {
            String valorStr = JOptionPane.showInputDialog(this, "Digite o valor do saque:");
            try {
                int valor = Integer.parseInt(valorStr);
                JOptionPane.showMessageDialog(this, caixa.sacar(valor));
                atualizarSaldo();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Digite apenas números válidos!");
            }
        });
        
        // Botão do Relatório de Cédulas.
        JButton btnRelatorio = criarBotao("Relatório de Cédulas", new Color(25, 50, 100), "/icons/relatorio.png");
        btnRelatorio.addActionListener(e -> JOptionPane.showMessageDialog(this, caixa.pegaRelatorioCedulas()));

        // Botão Valor Total Disponível.
        JButton btnValorTotal = criarBotao("Valor total disponível", new Color(25, 50, 100), "/icons/valor.png");
        btnValorTotal.addActionListener(e -> JOptionPane.showMessageDialog(this, caixa.pegaValorTotalDisponivel()));
        
        // Botão Reposição de Cédulas.
        JButton btnReposicao = criarBotao("Reposição de Cédulas", new Color(25, 50, 100), "/icons/reposicao.png");
        btnReposicao.addActionListener(e -> {
            try {
                String cedulaStr = JOptionPane.showInputDialog(this, "Digite o valor da cédula:");
                String qtdStr = JOptionPane.showInputDialog(this, "Digite a quantidade:");
                int cedula = Integer.parseInt(cedulaStr);
                int qtd = Integer.parseInt(qtdStr);
                JOptionPane.showMessageDialog(this, caixa.reposicaoCedulas(cedula, qtd));
                atualizarSaldo();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Digite apenas números válidos!");
            }
        });
        
        
        // Botão Cota Mínima.
        JButton btnCotaMinima = criarBotao("Cota Mínima", new Color(25, 50, 100), "/icons/cota.png");
        btnCotaMinima.addActionListener(e -> {
            try {
                String minimoStr = JOptionPane.showInputDialog(this, "Digite a cota mínima:");
                int minimo = Integer.parseInt(minimoStr);
                JOptionPane.showMessageDialog(this, caixa.armazenaCotaMinima(minimo));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Digite apenas números válidos!");
            }
        });
        
        // Botão de Sair - Gera o extrato de operações no final.
        JButton btnSair = criarBotao("Sair", new Color(70, 70, 70), "/icons/sair.png");
        btnSair.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, caixa.getExtratoFinal());
            System.exit(0);
        });

        // Adiciona os botões ao painel
        botoes.add(btnSaque);
        botoes.add(btnRelatorio);
        botoes.add(btnValorTotal);
        botoes.add(btnReposicao);
        botoes.add(btnCotaMinima);
        botoes.add(btnSair);

        add(botoes, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Método Auxiliar - Cria botões estilizados com cor, fonte e ícone.
    private JButton criarBotao(String texto, Color cor, String caminhoIcone) {
        JButton botao = new JButton(texto);
        botao.setFont(new Font("Segoe UI", Font.BOLD, 14));
        botao.setForeground(Color.WHITE);
        botao.setBackground(cor);
        botao.setFocusPainted(false);
        botao.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(cor.darker(), 2),
            BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Se tiver ícone adiciona a esquerda.
        java.net.URL iconURL = getClass().getResource(caminhoIcone);
        if (iconURL != null) {
            botao.setIcon(new ImageIcon(iconURL));
            botao.setHorizontalAlignment(SwingConstants.LEFT);
            botao.setIconTextGap(15);
        }

        return botao;
    }
    
    
    // Atualiza o Saldo no fim.
    private void atualizarSaldo() {
        saldoLabel.setText(caixa.pegaValorTotalDisponivel());
    }

    // Método Principal - Cria e exibe a janela do CaixaEletronico.
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new InterfaceCaixaEletronico());
    }
}

    