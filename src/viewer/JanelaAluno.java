package viewer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.CtrlIncluirAluno;
import model.Aluno;
import model.Curso;
import model.ModelException;
import model.dao.DaoCurso;

public class JanelaAluno extends JanelaAbstrata<CtrlIncluirAluno> {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfCpf;
	private JTextField tfNome;
	private JLabel lblNewLabel_2;
	private JTextField tfIdade;
	private JLabel lblNewLabel_3;
	private JComboBox cbCurso;
	private JLabel lblNewLabel_4;
	private JTextField tfMatricula;
	/**
	 * Create the frame.
	 */
	public JanelaAluno(CtrlIncluirAluno ctrl) {
		// Guardando quem é o controlador responsável pela janela
		super(ctrl);
		setTitle("Aluno");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 332);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Cpf:");
		lblNewLabel.setBounds(26, 33, 46, 14);
		contentPane.add(lblNewLabel);
		
		tfCpf = new JTextField();
		tfCpf.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				//
				// Estamos mantendo esse método nesta classe e não 
				// no seu controlador, pois ele não altera o estado
				// dos objetos Model. 
				//
				
				// Recuperamos o que o usuário digitou no cpf
				String cpf = tfCpf.getText();
				// Se o usuário ainda não digitou nada, saímos do método
				if(cpf == null || cpf.length() == 0)
					return;
				// Verificando se o que o usuário digitou está correto
				try {
					Aluno.validarCpf(cpf);
				} catch (ModelException e1) {
					// Se estiver inválido, já aviso ao usuário o seu erro
					JOptionPane.showMessageDialog(null, e1.getMessage());
					// Coloco o foco no tfCpf
					tfCpf.requestFocus();
				}
			}
		});
		tfCpf.setBounds(75, 30, 154, 20);
		contentPane.add(tfCpf);
		tfCpf.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Nome:");
		lblNewLabel_1.setBounds(26, 77, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		tfNome = new JTextField();
		tfNome.setBounds(75, 74, 246, 20);
		contentPane.add(tfNome);
		tfNome.setColumns(10);
		
		lblNewLabel_2 = new JLabel("Idade:");
		lblNewLabel_2.setBounds(26, 117, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		tfIdade = new JTextField();
		tfIdade.setBounds(75, 114, 101, 20);
		contentPane.add(tfIdade);
		tfIdade.setColumns(10);
		
		lblNewLabel_3 = new JLabel("Curso:");
		lblNewLabel_3.setBounds(26, 199, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		JButton btOk = new JButton("Ok");
		btOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cpf = tfCpf.getText();
				String nome = tfNome.getText();
				String aux = tfIdade.getText();
				int idade;
				try {
					idade = Integer.parseInt(aux);
				} catch(NumberFormatException nfe) {
					JOptionPane.showMessageDialog(null, "Idade Inválida: " + aux);
					return;
				}
				String matricula = tfMatricula.getText();
				// Recuperando qual foi o objeto Curso selecionado na Combobox
				// Precisamos colocar um casting (Curso) pois o método está definido
				// para retornar Object. Assim, precisamos avisar ao compilador que
				// o objeto é mais do que um Object; ele é um objeto Curso
				Curso curso = (Curso)cbCurso.getSelectedItem();
				// Criamos a variável, pois precisamos fazer o Casting
				// de CtrlAbstrato para CtrlIncluirAluno
				CtrlIncluirAluno ctrl = getCtrl();
				// Solicito ao controlador que execute a ação indicada 
				ctrl.incluirAluno(cpf, nome, idade, matricula, curso);
			}
		});
		btOk.setBounds(63, 243, 89, 23);
		contentPane.add(btOk);
		
		JButton btCancelar = new JButton("Cancelar");
		btCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Informo ao controlador que o caso de uso deve ser encerrado.
				// Não precisamos fazer Casting, pois o método encerrar está 
				// prescrito na classe CtrlAbstrato
				getCtrl().encerrar();
			}
		});
		btCancelar.setBounds(285, 243, 89, 23);
		contentPane.add(btCancelar);
		
		// Para recuperarmos todos os objetos Curso, pedimos ao Dao que nos  
		// dê a referência para o array de referências para Curso que ele gerencia.
		DaoCurso dao = new DaoCurso();
		Curso[] conjCursos = dao.obterTodos();
		// Para que a combo box venha preenchida, passamos a referência do array
		cbCurso = new JComboBox( conjCursos );
		cbCurso.setBounds(75, 195, 246, 22);
		contentPane.add(cbCurso);
		
		lblNewLabel_4 = new JLabel("Matrícula:");
		lblNewLabel_4.setBounds(10, 160, 56, 14);
		contentPane.add(lblNewLabel_4);
		
		tfMatricula = new JTextField();
		tfMatricula.setBounds(75, 157, 154, 20);
		contentPane.add(tfMatricula);
		tfMatricula.setColumns(10);
	}
}
