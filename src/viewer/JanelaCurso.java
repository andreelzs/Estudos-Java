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
import controller.curso.CtrlAbstratoCurso;
import controller.curso.CtrlExcluirCurso;
import model.Curso;

public class JanelaCurso extends JanelaAbstrata {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfCodigo;
	private JTextField tfNome;
	private JTextField tfCargaHoraria;

	/**
	 * Sobrecarga (Overload) de construtores na classe, pois
	 * terá dois construtores
	 */
	public JanelaCurso(CtrlAbstrato ctrl, Curso curso) {
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
		if (curso != null) {
			tfCodigo.setText(Integer.toString(curso.getCodigo()));
			tfNome.setText(curso.getNome());
			tfCargaHoraria.setText(Integer.toString(curso.getCargaHoraria()));
		}
		
		// Se a operação é de Exclusão, vou inabilitar os textfields
		if(ctrl instanceof CtrlExcluirCurso) {
			tfCodigo.setEnabled(false);
			tfNome.setEnabled(false);
			tfCargaHoraria.setEnabled(false);
			
			JLabel lbMsg = new JLabel("Deseja excluir esse Curso?");
			lbMsg.setForeground(new Color(255, 0, 0));
			lbMsg.setFont(new Font("Calibri", Font.PLAIN, 14));
			lbMsg.setBounds(139, 150, 187, 14);
			contentPane.add(lbMsg);
		}
	}
	
	
	/**
	 * @wbp.parser.constructor
	 */
	public JanelaCurso(CtrlAbstrato ctrl) {
		super(ctrl);
		setTitle("Curso");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 252);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Código:");
		lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblNewLabel.setBounds(33, 40, 46, 14);
		contentPane.add(lblNewLabel);

		tfCodigo = new JTextField();
		tfCodigo.setBounds(89, 35, 111, 20);
		contentPane.add(tfCodigo);
		tfCodigo.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Nome:");
		lblNewLabel_1.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(33, 80, 46, 14);
		contentPane.add(lblNewLabel_1);

		tfNome = new JTextField();
		tfNome.setBounds(89, 75, 311, 20);
		contentPane.add(tfNome);
		tfNome.setColumns(10);

		JButton btOk = new JButton("Ok");
		btOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Mando para 'tfCodigo' a mensagem 'getText()'
				// Que retorna o conteúdo digitado dentro do textfield
				String aux = tfCodigo.getText();
				int codigo;
				try {
					// Convertendo a string lida para inteiro
					codigo = Integer.parseInt(aux);
				} catch (NumberFormatException nfe) {
					// Se não conseguiu fazer a conversão, então colocamos a dialog
					// com o texto de erro e saímos do método
					JOptionPane.showMessageDialog(btOk, "Valor não numérico no campo código!");
					return;
				}
				// Mando para 'tfNome' a mensagem 'getText()'
				// Que retorna o conteúdo digitado dentro do textfield
				String nome = tfNome.getText();

				// Mando para 'tfCodigo' a mensagem 'getText()'
				// Que retorna o conteúdo digitado dentro do textfield
				aux = tfCargaHoraria.getText();
				int cargaHoraria;
				try {
					// Convertendo a string lida para inteiro
					cargaHoraria = Integer.parseInt(aux);
				} catch (NumberFormatException nfe) {
					// Se não conseguiu fazer a conversão, então colocamos a dialog
					// com o texto de erro e saímos do método
					JOptionPane.showMessageDialog(btOk, "Valor não numérico no campo carga horária!");
					return;
				}
				//
				// Após ter recuperado o que o usuário definiu, vamos
				// acionar o controlador de caso de uso associado à janela
				//
				CtrlAbstratoCurso ctrl = (CtrlAbstratoCurso) getCtrl();
				ctrl.efetuar(codigo, nome, cargaHoraria);
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

		JLabel lblNewLabel_1_1 = new JLabel("CH:");
		lblNewLabel_1_1.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(33, 124, 46, 14);
		contentPane.add(lblNewLabel_1_1);

		tfCargaHoraria = new JTextField();
		tfCargaHoraria.setColumns(10);
		tfCargaHoraria.setBounds(89, 119, 111, 20);
		contentPane.add(tfCargaHoraria);		
	}
}
