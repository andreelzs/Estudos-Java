package viewer;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import controller.conta.CtrlAbstratoConta;
import controller.conta.CtrlExcluirConta;
import model.Agencia;
import model.Conta;
import model.dao.DaoAgencia;

public class JanelaConta extends JanelaAbstrata<CtrlAbstratoConta> {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField tfNumero;
    private JTextField tfNomeCorrentista;
    private JTextField tfChavePix;
    private JTextField tfSaldo;
    private JTextField tfLimiteCredito;
    private JComboBox<Agencia> cbAgencia;

    public JanelaConta(CtrlAbstratoConta ctrl, Conta conta) {
        this(ctrl);
        if (conta != null) {
            this.tfNumero.setText(String.valueOf(conta.getNumero()));
            this.tfNomeCorrentista.setText(conta.getNomeCorrentista());
            this.tfChavePix.setText(conta.getChavePix());
            this.tfSaldo.setText(String.valueOf(conta.getSaldo()));
            this.tfLimiteCredito.setText(String.valueOf(conta.getLimiteCredito()));
            this.cbAgencia.setSelectedItem(conta.getAgencia());
        }

        if (ctrl instanceof CtrlExcluirConta) {
            this.tfNumero.setEnabled(false);
            this.tfNomeCorrentista.setEnabled(false);
            this.tfChavePix.setEnabled(false);
            this.tfSaldo.setEnabled(false);
            this.tfLimiteCredito.setEnabled(false);
            this.cbAgencia.setEnabled(false);
            JLabel lblMsg = new JLabel("Deseja realmente excluir esta conta?");
            lblMsg.setForeground(Color.RED);
            lblMsg.setFont(new Font("Calibri", Font.BOLD, 14));
            lblMsg.setBounds(80, 230, 280, 20);
            contentPane.add(lblMsg);
        }
    }

    public JanelaConta(CtrlAbstratoConta ctrl) {
        super(ctrl);
        setTitle("Conta");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 320);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNumero = new JLabel("Número:");
        lblNumero.setBounds(20, 20, 120, 20);
        contentPane.add(lblNumero);

        tfNumero = new JTextField();
        tfNumero.setBounds(150, 20, 150, 20);
        contentPane.add(tfNumero);
        tfNumero.setColumns(10);

        JLabel lblNomeCorrentista = new JLabel("Nome Correntista:");
        lblNomeCorrentista.setBounds(20, 50, 120, 20);
        contentPane.add(lblNomeCorrentista);

        tfNomeCorrentista = new JTextField();
        tfNomeCorrentista.setBounds(150, 50, 250, 20);
        contentPane.add(tfNomeCorrentista);
        tfNomeCorrentista.setColumns(10);

        JLabel lblChavePix = new JLabel("Chave PIX:");
        lblChavePix.setBounds(20, 80, 120, 20);
        contentPane.add(lblChavePix);

        tfChavePix = new JTextField();
        tfChavePix.setBounds(150, 80, 250, 20);
        contentPane.add(tfChavePix);
        tfChavePix.setColumns(10);

        JLabel lblSaldo = new JLabel("Saldo:");
        lblSaldo.setBounds(20, 110, 120, 20);
        contentPane.add(lblSaldo);

        tfSaldo = new JTextField();
        tfSaldo.setBounds(150, 110, 150, 20);
        contentPane.add(tfSaldo);
        tfSaldo.setColumns(10);

        JLabel lblLimiteCredito = new JLabel("Limite de Crédito:");
        lblLimiteCredito.setBounds(20, 140, 120, 20);
        contentPane.add(lblLimiteCredito);

        tfLimiteCredito = new JTextField();
        tfLimiteCredito.setBounds(150, 140, 150, 20);
        contentPane.add(tfLimiteCredito);
        tfLimiteCredito.setColumns(10);

        JLabel lblAgencia = new JLabel("Agência:");
        lblAgencia.setBounds(20, 170, 120, 20);
        contentPane.add(lblAgencia);

        DaoAgencia daoAgencia = new DaoAgencia();
        cbAgencia = new JComboBox<>(daoAgencia.obterTodos());
        cbAgencia.setBounds(150, 170, 250, 20);
        contentPane.add(cbAgencia);

        JButton btnOk = new JButton("OK");
        btnOk.setBounds(100, 260, 100, 25);
        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int numero = Integer.parseInt(tfNumero.getText());
                    String nome = tfNomeCorrentista.getText();
                    String chavePix = tfChavePix.getText();
                    double saldo = Double.parseDouble(tfSaldo.getText());
                    double limite = Double.parseDouble(tfLimiteCredito.getText());
                    Agencia agencia = (Agencia) cbAgencia.getSelectedItem();
                    getCtrl().efetuar(numero, nome, chavePix, saldo, limite, agencia);
                } catch (NumberFormatException ex) {
                    notificar("Os campos numéricos devem conter valores válidos.");
                }
            }
        });
        contentPane.add(btnOk);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(250, 260, 100, 25);
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getCtrl().encerrar();
            }
        });
        contentPane.add(btnCancelar);
    }
}