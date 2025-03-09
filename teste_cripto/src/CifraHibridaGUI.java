import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class CifraHibridaGUI extends JFrame {
    private CifraHibrida cifra;

    // Componentes da aba "Cifrar"
    private JTextArea areaTextoClaro;
    private JButton botaoCifrar;
    private JTextField campoChaveSessaoCifrada;
    private JTextField campoIV;
    private JTextArea areaTextoCifrado;

    // Componentes da aba "Decifrar"
    private JTextArea areaChaveSessaoCifrada;
    private JTextArea areaIV;
    private JTextArea areaTextoCifradoDecifrar;
    private JButton botaoDecifrar;
    private JTextArea areaTextoDecifrado;

    public CifraHibridaGUI() {
        super("Cifra Híbrida - Interface Moderna");
        try {
            cifra = new CifraHibrida();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao iniciar o sistema de criptografia: " + e.getMessage());
            System.exit(1);
        }
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        
        Font fontePadrao = new Font("Segoe UI", Font.PLAIN, 14);
        UIManager.put("Label.font", fontePadrao);
        UIManager.put("Button.font", fontePadrao);
        UIManager.put("TextArea.font", fontePadrao);
        UIManager.put("TextField.font", fontePadrao);

        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // Painel de abas
        JTabbedPane abas = new JTabbedPane();
        abas.setBackground(new Color(245, 245, 245));

        // ----- Aba "Cifrar" -----
        JPanel painelCifrar = new JPanel(new BorderLayout());
        painelCifrar.setBackground(new Color(245, 245, 245));
        painelCifrar.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Área de entrada do texto claro
        JPanel painelEntrada = new JPanel(new BorderLayout());
        painelEntrada.setBorder(BorderFactory.createTitledBorder("Texto Claro"));
        painelEntrada.setBackground(new Color(245, 245, 245));
        areaTextoClaro = new JTextArea(5, 40);
        areaTextoClaro.setLineWrap(true);
        areaTextoClaro.setWrapStyleWord(true);
        JScrollPane scrollTextoClaro = new JScrollPane(areaTextoClaro);
        painelEntrada.add(scrollTextoClaro, BorderLayout.CENTER);
        painelCifrar.add(painelEntrada, BorderLayout.NORTH);

        // Botão de cifrar
        botaoCifrar = new JButton("Cifrar Texto");
        botaoCifrar.setBackground(new Color(66, 133, 244));
        botaoCifrar.setForeground(Color.WHITE);
        botaoCifrar.setFocusPainted(false);
        JPanel painelBotao = new JPanel();
        painelBotao.setBackground(new Color(245, 245, 245));
        painelBotao.add(botaoCifrar);
        painelCifrar.add(painelBotao, BorderLayout.CENTER);

        // Área de saída dos dados cifrados
        JPanel painelSaida = new JPanel(new GridLayout(3, 1, 5, 5));
        painelSaida.setBorder(BorderFactory.createTitledBorder("Dados Cifrados (Hexadecimal)"));
        painelSaida.setBackground(new Color(245, 245, 245));

        // Chave de sessão cifrada
        JPanel painelChaveSessao = new JPanel(new BorderLayout());
        painelChaveSessao.setBackground(new Color(245, 245, 245));
        JLabel labelChaveSessao = new JLabel("Chave de Sessão Cifrada:");
        painelChaveSessao.add(labelChaveSessao, BorderLayout.NORTH);
        campoChaveSessaoCifrada = new JTextField();
        campoChaveSessaoCifrada.setEditable(false);
        painelChaveSessao.add(campoChaveSessaoCifrada, BorderLayout.CENTER);

        // IV (Nonce)
        JPanel painelIV = new JPanel(new BorderLayout());
        painelIV.setBackground(new Color(245, 245, 245));
        JLabel labelIV = new JLabel("IV (Nonce):");
        painelIV.add(labelIV, BorderLayout.NORTH);
        campoIV = new JTextField();
        campoIV.setEditable(false);
        painelIV.add(campoIV, BorderLayout.CENTER);

        // Texto cifrado
        JPanel painelTextoCifrado = new JPanel(new BorderLayout());
        painelTextoCifrado.setBackground(new Color(245, 245, 245));
        JLabel labelTextoCifrado = new JLabel("Texto Cifrado:");
        painelTextoCifrado.add(labelTextoCifrado, BorderLayout.NORTH);
        areaTextoCifrado = new JTextArea(5, 40);
        areaTextoCifrado.setLineWrap(true);
        areaTextoCifrado.setWrapStyleWord(true);
        areaTextoCifrado.setEditable(false);
        JScrollPane scrollTextoCifrado = new JScrollPane(areaTextoCifrado);
        painelTextoCifrado.add(scrollTextoCifrado, BorderLayout.CENTER);

        painelSaida.add(painelChaveSessao);
        painelSaida.add(painelIV);
        painelSaida.add(painelTextoCifrado);
        painelCifrar.add(painelSaida, BorderLayout.SOUTH);

        abas.addTab("Cifrar", painelCifrar);

        // ----- Aba "Decifrar" -----
        JPanel painelDecifrar = new JPanel(new BorderLayout());
        painelDecifrar.setBackground(new Color(245, 245, 245));
        painelDecifrar.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Área de entrada dos dados cifrados
        JPanel painelEntradaDecifrar = new JPanel(new GridLayout(3, 1, 5, 5));
        painelEntradaDecifrar.setBorder(BorderFactory.createTitledBorder("Dados Cifrados (Hexadecimal)"));
        painelEntradaDecifrar.setBackground(new Color(245, 245, 245));

        // Campo para chave de sessão cifrada
        JPanel painelChaveSessaoDecifrada = new JPanel(new BorderLayout());
        painelChaveSessaoDecifrada.setBackground(new Color(245, 245, 245));
        JLabel labelChaveSessaoDecifrada = new JLabel("Chave de Sessão Cifrada:");
        painelChaveSessaoDecifrada.add(labelChaveSessaoDecifrada, BorderLayout.NORTH);
        areaChaveSessaoCifrada = new JTextArea(2, 40);
        areaChaveSessaoCifrada.setLineWrap(true);
        areaChaveSessaoCifrada.setWrapStyleWord(true);
        JScrollPane scrollChaveSessaoDecifrada = new JScrollPane(areaChaveSessaoCifrada);
        painelChaveSessaoDecifrada.add(scrollChaveSessaoDecifrada, BorderLayout.CENTER);

        // Campo para IV
        JPanel painelIVDecifrar = new JPanel(new BorderLayout());
        painelIVDecifrar.setBackground(new Color(245, 245, 245));
        JLabel labelIVDecifrar = new JLabel("IV (Nonce):");
        painelIVDecifrar.add(labelIVDecifrar, BorderLayout.NORTH);
        areaIV = new JTextArea(2, 40);
        areaIV.setLineWrap(true);
        areaIV.setWrapStyleWord(true);
        JScrollPane scrollIVDecifrar = new JScrollPane(areaIV);
        painelIVDecifrar.add(scrollIVDecifrar, BorderLayout.CENTER);

        // Campo para texto cifrado
        JPanel painelTextoCifradoDecifrar = new JPanel(new BorderLayout());
        painelTextoCifradoDecifrar.setBackground(new Color(245, 245, 245));
        JLabel labelTextoCifradoDecifrar = new JLabel("Texto Cifrado:");
        painelTextoCifradoDecifrar.add(labelTextoCifradoDecifrar, BorderLayout.NORTH);
        areaTextoCifradoDecifrar = new JTextArea(5, 40);
        areaTextoCifradoDecifrar.setLineWrap(true);
        areaTextoCifradoDecifrar.setWrapStyleWord(true);
        JScrollPane scrollTextoCifradoDecifrar = new JScrollPane(areaTextoCifradoDecifrar);
        painelTextoCifradoDecifrar.add(scrollTextoCifradoDecifrar, BorderLayout.CENTER);

        painelEntradaDecifrar.add(painelChaveSessaoDecifrada);
        painelEntradaDecifrar.add(painelIVDecifrar);
        painelEntradaDecifrar.add(painelTextoCifradoDecifrar);
        painelDecifrar.add(painelEntradaDecifrar, BorderLayout.NORTH);

        // Botão de decifrar
        botaoDecifrar = new JButton("Decifrar Texto");
        botaoDecifrar.setBackground(new Color(66, 133, 244));
        botaoDecifrar.setForeground(Color.WHITE);
        botaoDecifrar.setFocusPainted(false);
        JPanel painelBotaoDecifrar = new JPanel();
        painelBotaoDecifrar.setBackground(new Color(245, 245, 245));
        painelBotaoDecifrar.add(botaoDecifrar);
        painelDecifrar.add(painelBotaoDecifrar, BorderLayout.CENTER);

        // Área de saída do texto decifrado
        JPanel painelSaidaDecifrar = new JPanel(new BorderLayout());
        painelSaidaDecifrar.setBorder(BorderFactory.createTitledBorder("Texto Decifrado"));
        painelSaidaDecifrar.setBackground(new Color(245, 245, 245));
        areaTextoDecifrado = new JTextArea(5, 40);
        areaTextoDecifrado.setLineWrap(true);
        areaTextoDecifrado.setWrapStyleWord(true);
        areaTextoDecifrado.setEditable(false);
        JScrollPane scrollTextoDecifrado = new JScrollPane(areaTextoDecifrado);
        painelSaidaDecifrar.add(scrollTextoDecifrado, BorderLayout.CENTER);
        painelDecifrar.add(painelSaidaDecifrar, BorderLayout.SOUTH);

        abas.addTab("Decifrar", painelDecifrar);

        add(abas);

        // Ações dos botões
        botaoCifrar.addActionListener(e -> acaoCifrar());
        botaoDecifrar.addActionListener(e -> acaoDecifrar());

        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Ação para cifrar o texto.
     */
    private void acaoCifrar() {
        String textoClaro = areaTextoClaro.getText();
        if (textoClaro == null || textoClaro.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Digite um texto para cifrar.");
            return;
        }

        try {
            CifraHibrida.DadosCifrados dados = cifra.cifrar(textoClaro);
            campoChaveSessaoCifrada.setText(CifraHibrida.bytesParaHex(dados.getChaveSessaoCifrada()));
            campoIV.setText(CifraHibrida.bytesParaHex(dados.getIv()));
            areaTextoCifrado.setText(CifraHibrida.bytesParaHex(dados.getTextoCifrado()));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao cifrar: " + ex.getMessage());
        }
    }

    /**
     * Ação para decifrar o texto.
     */
    private void acaoDecifrar() {
        String chaveSessaoHex = areaChaveSessaoCifrada.getText().trim();
        String ivHex = areaIV.getText().trim();
        String textoCifradoHex = areaTextoCifradoDecifrar.getText().trim();

        if (chaveSessaoHex.isEmpty() || ivHex.isEmpty() || textoCifradoHex.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos com os dados cifrados.");
            return;
        }

        try {
            byte[] chaveSessaoCifrada = CifraHibrida.hexParaBytes(chaveSessaoHex);
            byte[] iv = CifraHibrida.hexParaBytes(ivHex);
            byte[] textoCifrado = CifraHibrida.hexParaBytes(textoCifradoHex);

            CifraHibrida.DadosCifrados dados = new CifraHibrida.DadosCifrados(chaveSessaoCifrada, iv, textoCifrado);
            String textoDecifrado = cifra.decifrar(dados);
            areaTextoDecifrado.setText(textoDecifrado);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao decifrar: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(() -> new CifraHibridaGUI().setVisible(true));
    }
}
