package viewer;

import java.awt.EventQueue;
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

import model.Departamento;
import model.ModelException;
import model.dao.DaoDepartamento;

public class JanelaDepartamento extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfSigla;
	private JTextField tfNome;

	/**
	 * Create the frame.
	 */
	public JanelaDepartamento() {
		setTitle("Departamento");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 223);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Sigla:");
		lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblNewLabel.setBounds(33, 40, 46, 14);
		contentPane.add(lblNewLabel);
		
		tfSigla = new JTextField();
		tfSigla.setBounds(89, 35, 111, 20);
		contentPane.add(tfSigla);
		tfSigla.setColumns(10);
		
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
				// Mando para 'tfSigla' a mensagem 'getText()'
				// Que retorna o conteúdo digitado dentro do textfield
				String sigla = tfSigla.getText();
				// Mando para 'tfNome' a mensagem 'getText()'
				// Que retorna o conteúdo digitado dentro do textfield
				String nome = tfNome.getText();
				
				try {
					Departamento d1 = new Departamento(sigla,nome);
					DaoDepartamento dao = new DaoDepartamento();
					dao.incluir(d1);
					JOptionPane.showMessageDialog(btOk, "Objeto Departamento Criado: " + d1);
				} catch (ModelException e1) {
					// Exibe uma 'Dialog' posicionada próximo ao botão 
					// btOk com a mensagem de erro da exceção
					JOptionPane.showMessageDialog(btOk, e1.getMessage());
					// Imprime na console o traçado de execução da Stack
					e1.printStackTrace();
				}
				
			}
		});
		btOk.setBounds(99, 132, 89, 23);
		contentPane.add(btOk);
		
		JButton btCancelar = new JButton("Cancelar");
		btCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Exibindo a Dialog
				JOptionPane.showMessageDialog(btCancelar, "Fechando a janela...");
				// Tornando a janela invisível
				setVisible(false);
			}
		});
		btCancelar.setBounds(233, 132, 89, 23);
		contentPane.add(btCancelar);
	}
}
