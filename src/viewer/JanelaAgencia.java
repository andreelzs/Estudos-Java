package viewer;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import controller.agencia.CtrlAbstratoAgencia;
import controller.agencia.CtrlExcluirAgencia;
import model.Agencia;

public class JanelaAgencia extends JanelaAbstrata<CtrlAbstratoAgencia> {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField tfNumero;
    private JTextField tfBairro;
    private JTextField tfCidade;

    public JanelaAgencia(CtrlAbstratoAgencia ctrl, Agencia agencia) {
        this(ctrl);
        if (agencia != null) {
            this.tfNumero.setText(String.valueOf(agencia.getNumero()));
            this.tfBairro.setText(agencia.getBairro());
            this.tfCidade.setText(agencia.getCidade());
        }

        if (ctrl instanceof CtrlExcluirAgencia) {
            this.tfNumero.setEnabled(false);
            this.tfBairro.setEnabled(false);
            this.tfCidade.setEnabled(false);
            JLabel lblMsg = new JLabel("Deseja realmente excluir esta agência?");
            lblMsg.setForeground(Color.RED);
            lblMsg.setFont(new Font("Calibri", Font.BOLD, 14));
            lblMsg.setBounds(80, 110, 280, 20);
            contentPane.add(lblMsg);
        }
    }

    public JanelaAgencia(CtrlAbstratoAgencia ctrl) {
        super(ctrl);
        setTitle("Agência");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 220);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNumero = new JLabel("Número:");
        lblNumero.setBounds(20, 20, 80, 20);
        contentPane.add(lblNumero);

        tfNumero = new JTextField();
        tfNumero.setBounds(100, 20, 150, 20);
        contentPane.add(tfNumero);
        tfNumero.setColumns(10);

        JLabel lblBairro = new JLabel("Bairro:");
        lblBairro.setBounds(20, 50, 80, 20);
        contentPane.add(lblBairro);

        tfBairro = new JTextField();
        tfBairro.setBounds(100, 50, 300, 20);
        contentPane.add(tfBairro);
        tfBairro.setColumns(10);

        JLabel lblCidade = new JLabel("Cidade:");
        lblCidade.setBounds(20, 80, 80, 20);
        contentPane.add(lblCidade);

        tfCidade = new JTextField();
        tfCidade.setBounds(100, 80, 300, 20);
        contentPane.add(tfCidade);
        tfCidade.setColumns(10);

        JButton btnOk = new JButton("OK");
        btnOk.setBounds(100, 140, 100, 25);
        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int numero = Integer.parseInt(tfNumero.getText());
                    String bairro = tfBairro.getText();
                    String cidade = tfCidade.getText();
                    getCtrl().efetuar(numero, bairro, cidade);
                } catch (NumberFormatException ex) {
                    notificar("O número da agência deve ser um valor numérico.");
                }
            }
        });
        contentPane.add(btnOk);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(250, 140, 100, 25);
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getCtrl().encerrar();
            }
        });
        contentPane.add(btnCancelar);
    }
}