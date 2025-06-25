package viewer;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import controller.CtrlAbstrato;
import controller.conta.CtrlAbstratoConta;
import controller.conta.CtrlExcluirConta;
import model.Agencia;
import model.Conta;
import model.dao.DaoAgencia;

public class JanelaConta extends JanelaAbstrata {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfNumero;
	private JTextField tfNomeCorrentista;
	private JTextField tfChavePix;
	private JTextField tfSaldo;
	private JTextField tfLimite;
	private JComboBox  cbAgencia;

	/**
	 * Sobrecarga (Overload) de construtores na classe, pois
	 * terá dois construtores
	 */
	public JanelaConta(CtrlAbstrato ctrl, Conta conta) {
		//
		// Chamada ao construtor DESTA CLASSE que recebe somente 
		// um parâmetro. Para isso usamos a INSTRUÇÃO this(...)
		//
		this(ctrl);
		
		//
		// Se a operação é de "Alteração" ou "Exclusão", vou preencher
		// os campos do formulário para o usuário ver os dados do objeto 
		// a ser alterado/excluído
		//
		if (conta != null) {
			tfNumero.setText(Integer.toString(conta.getNumero()));
			tfNomeCorrentista.setText(conta.getNomeCorrentista());
			tfChavePix.setText(conta.getChavePix());
			tfSaldo.setText(Double.toString(conta.getSaldo()));
			tfLimite.setText(Double.toString(conta.getLimite()));
			cbAgencia.setSelectedItem(conta.getAgencia());			
		}
		
		// Se a operação é de Exclusão, vou inabilitar os textfields
		if(ctrl instanceof CtrlExcluirConta) {
			tfNumero.setEnabled(false);
			tfNomeCorrentista.setEnabled(false);
			tfChavePix.setEnabled(false);
			tfSaldo.setEnabled(false);
			tfLimite.setEnabled(false);
			cbAgencia.setEnabled(false);
			
			JLabel lbMsg = new JLabel("Deseja excluir essa Conta?");
			lbMsg.setForeground(new Color(255, 0, 0));
			lbMsg.setFont(new Font("Calibri", Font.BOLD, 16));
			lbMsg.setBounds(135, 257, 187, 14);
			contentPane.add(lbMsg);
		}
	}
	
	/**
	 * @wbp.parser.constructor
	 */
	public JanelaConta(CtrlAbstrato ctrl) {
		super(ctrl);
		setTitle("Conta");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 355);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Número:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 16, 69, 14);
		contentPane.add(lblNewLabel);

		tfNumero = new JTextField();
		tfNumero.setBounds(89, 11, 111, 20);
		contentPane.add(tfNumero);
		tfNumero.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Correntista:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(0, 56, 79, 14);
		contentPane.add(lblNewLabel_1);

		tfNomeCorrentista = new JTextField();
		tfNomeCorrentista.setBounds(89, 51, 311, 20);
		contentPane.add(tfNomeCorrentista);
		tfNomeCorrentista.setColumns(10);

		JButton btOk = new JButton("Ok");
		btOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Mando para 'tfNumero' a mensagem 'getText()'
				// Que retorna o conteúdo digitado dentro do textfield
				String aux = tfNumero.getText();
				int numero;
				try {
					// Convertendo a string lida para inteiro
					numero = Integer.parseInt(aux);
				} catch (NumberFormatException nfe) {
					// Se não conseguiu fazer a conversão, então colocamos a dialog
					// com o texto de erro e saímos do método
					JOptionPane.showMessageDialog(btOk, "Valor não numérico no campo número da conta!");
					return;
				}
				// Mando para 'tfNomeCorrentista' a mensagem 'getText()'
				// Que retorna o conteúdo digitado dentro do textfield
				String nomeCorrentista = tfNomeCorrentista.getText();

				// Mando para 'tfChavePix' a mensagem 'getText()'
				// Que retorna o conteúdo digitado dentro do textfield
				String chavePix = tfChavePix.getText();

				// Mando para 'tfSaldo' a mensagem 'getText()'
				// Que retorna o conteúdo digitado dentro do textfield
				aux = tfSaldo.getText();
				double saldo;
				try {
					// Convertendo a string lida para inteiro
					saldo = Double.parseDouble(aux);
				} catch (NumberFormatException nfe) {
					// Se não conseguiu fazer a conversão, então colocamos a dialog
					// com o texto de erro e saímos do método
					JOptionPane.showMessageDialog(btOk, "Valor não numérico no campo saldo da conta!");
					return;
				}
				
				// Mando para 'tfLimite' a mensagem 'getText()'
				// Que retorna o conteúdo digitado dentro do textfield
				aux = tfLimite.getText();
				double limite;
				try {
					// Convertendo a string lida para inteiro
					limite = Double.parseDouble(aux);
				} catch (NumberFormatException nfe) {
					// Se não conseguiu fazer a conversão, então colocamos a dialog
					// com o texto de erro e saímos do método
					JOptionPane.showMessageDialog(btOk, "Valor não numérico no campo limite da conta!");
					return;
				}

				// Mando para 'cbAgencia' a mensagem 'getSelectedItem()'
				// Que retorna a agência selecionada
				Agencia agencia = (Agencia)cbAgencia.getSelectedItem();

				//
				// Após ter recuperado o que o usuário definiu, vamos
				// acionar o controlador de caso de uso associado à janela
				//
				CtrlAbstratoConta ctrl = (CtrlAbstratoConta) getCtrl();
				ctrl.efetuar(numero, nomeCorrentista, chavePix, saldo, limite, agencia);
			}
		});
		btOk.setBounds(104, 282, 89, 23);
		contentPane.add(btOk);

		JButton btCancelar = new JButton("Cancelar");
		btCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getCtrl().encerrar();
			}
		});
		btCancelar.setBounds(238, 282, 89, 23);
		contentPane.add(btCancelar);

		JLabel lblNewLabel_1_1 = new JLabel("Chave Pix:");
		lblNewLabel_1_1.setBackground(new Color(240, 240, 240));
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(10, 100, 69, 14);
		contentPane.add(lblNewLabel_1_1);

		tfChavePix = new JTextField();
		tfChavePix.setColumns(10);
		tfChavePix.setBounds(89, 95, 311, 20);
		contentPane.add(tfChavePix);		
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Saldo:");
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1_1.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblNewLabel_1_1_1.setBackground(UIManager.getColor("Button.background"));
		lblNewLabel_1_1_1.setBounds(10, 143, 69, 14);
		contentPane.add(lblNewLabel_1_1_1);
		
		tfSaldo = new JTextField();
		tfSaldo.setColumns(10);
		tfSaldo.setBounds(89, 138, 311, 20);
		contentPane.add(tfSaldo);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Limite:");
		lblNewLabel_1_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1_1_1.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblNewLabel_1_1_1_1.setBackground(UIManager.getColor("Button.background"));
		lblNewLabel_1_1_1_1.setBounds(10, 184, 69, 14);
		contentPane.add(lblNewLabel_1_1_1_1);
		
		tfLimite = new JTextField();
		tfLimite.setColumns(10);
		tfLimite.setBounds(89, 179, 311, 20);
		contentPane.add(tfLimite);
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Agência:");
		lblNewLabel_1_1_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1_1_1_1.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblNewLabel_1_1_1_1_1.setBackground(UIManager.getColor("Button.background"));
		lblNewLabel_1_1_1_1_1.setBounds(10, 227, 69, 14);
		contentPane.add(lblNewLabel_1_1_1_1_1);
		
		DaoAgencia dao = new DaoAgencia();		
		cbAgencia = new JComboBox( dao.obterTodos() );
		cbAgencia.setBounds(89, 221, 311, 22);
		contentPane.add(cbAgencia);
	}
}
