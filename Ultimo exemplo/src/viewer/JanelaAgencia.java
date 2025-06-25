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

import controller.CtrlAbstrato;
import controller.agencia.CtrlAbstratoAgencia;
import controller.agencia.CtrlExcluirAgencia;
import model.Agencia;

public class JanelaAgencia extends JanelaAbstrata {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfNumero;
	private JTextField tfBairro;
	private JTextField tfCidade;

	/**
	 * Sobrecarga (Overload) de construtores na classe, pois
	 * terá dois construtores
	 */
	public JanelaAgencia(CtrlAbstrato ctrl, Agencia agencia) {
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
		if (agencia != null) {
			tfNumero.setText(Integer.toString(agencia.getNumero()));
			tfBairro.setText(agencia.getBairro());
			tfCidade.setText(agencia.getCidade());
		}
		
		// Se a operação é de Exclusão, vou inabilitar os textfields
		if(ctrl instanceof CtrlExcluirAgencia) {
			tfNumero.setEnabled(false);
			tfBairro.setEnabled(false);
			tfCidade.setEnabled(false);
			
			JLabel lbMsg = new JLabel("Deseja excluir essa Agência?");
			lbMsg.setForeground(new Color(255, 0, 0));
			lbMsg.setFont(new Font("Calibri", Font.PLAIN, 14));
			lbMsg.setBounds(139, 150, 187, 14);
			contentPane.add(lbMsg);
		}
	}
	
	/**
	 * @wbp.parser.constructor
	 */
	public JanelaAgencia(CtrlAbstrato ctrl) {
		super(ctrl);
		setTitle("Agencia");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 252);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Número");
		lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblNewLabel.setBounds(33, 40, 46, 14);
		contentPane.add(lblNewLabel);

		tfNumero = new JTextField();
		tfNumero.setBounds(89, 35, 111, 20);
		contentPane.add(tfNumero);
		tfNumero.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Bairro:");
		lblNewLabel_1.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(33, 80, 46, 14);
		contentPane.add(lblNewLabel_1);

		tfBairro = new JTextField();
		tfBairro.setBounds(89, 75, 311, 20);
		contentPane.add(tfBairro);
		tfBairro.setColumns(10);

		JButton btOk = new JButton("Ok");
		btOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Mando para 'tfCodigo' a mensagem 'getText()'
				// Que retorna o conteúdo digitado dentro do textfield
				String aux = tfNumero.getText();
				int numero;
				try {
					// Convertendo a string lida para inteiro
					numero = Integer.parseInt(aux);
				} catch (NumberFormatException nfe) {
					// Se não conseguiu fazer a conversão, então colocamos a dialog
					// com o texto de erro e saímos do método
					JOptionPane.showMessageDialog(btOk, "Valor não numérico no campo número da agência!");
					return;
				}
				// Mando para 'tfBairro' a mensagem 'getText()'
				// Que retorna o conteúdo digitado dentro do textfield
				String bairro = tfBairro.getText();

				// Mando para 'tfCidade' a mensagem 'getText()'
				// Que retorna o conteúdo digitado dentro do textfield
				String cidade = tfCidade.getText();

				//
				// Após ter recuperado o que o usuário definiu, vamos
				// acionar o controlador de caso de uso associado à janela
				//
				CtrlAbstratoAgencia ctrl = (CtrlAbstratoAgencia) getCtrl();
				ctrl.efetuar(numero, bairro, cidade);
			}
		});
		btOk.setBounds(103, 167, 89, 23);
		contentPane.add(btOk);

		JButton btCancelar = new JButton("Cancelar");
		btCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getCtrl().encerrar();
			}
		});
		btCancelar.setBounds(237, 167, 89, 23);
		contentPane.add(btCancelar);

		JLabel lblNewLabel_1_1 = new JLabel("Cidade:");
		lblNewLabel_1_1.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(33, 124, 46, 14);
		contentPane.add(lblNewLabel_1_1);

		tfCidade = new JTextField();
		tfCidade.setColumns(10);
		tfCidade.setBounds(89, 119, 311, 20);
		contentPane.add(tfCidade);		
	}
}
