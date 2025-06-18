package viewer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.CtrlPrograma;

public class JanelaPrincipal extends JanelaAbstrata {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public JanelaPrincipal(CtrlPrograma ctrl) {
		super(ctrl);
		setTitle("Janela Principal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 228);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btDepartamento = new JButton("Departamento");
		btDepartamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Estamos declarando a variável 'janela' cujo tipo é 
				// ponteiro para JanelaDepartamento. Com o operador new
				// estamos criando um objeto JanelaDepartamento e, com a 
				// atribuição, a variável 'janela' passará a apontar para esse
				// novo objeto.
				JanelaDepartamento janela = new JanelaDepartamento();
				// Estamos mandando a mensagem 'setVisible(true)' para
				// o objeto apontado por 'janela'
				janela.setVisible(true);
			}
		});
		btDepartamento.setBounds(149, 84, 119, 23);
		contentPane.add(btDepartamento);
		
		JButton btSair = new JButton("Sair");
		btSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getCtrl().encerrar();
			}
		});
		btSair.setBounds(149, 130, 119, 23);
		contentPane.add(btSair);
		
		JButton btDisciplina = new JButton("Disciplina");
		btDisciplina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JanelaDisciplina janela = new JanelaDisciplina();
				janela.setVisible(true);
			}
		});
		btDisciplina.setBounds(20, 84, 119, 23);
		contentPane.add(btDisciplina);
		
		JButton btIncluirEmpregado = new JButton("Empregado");
		btIncluirEmpregado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JanelaEmpregado janela = new JanelaEmpregado();
				janela.setVisible(true);
			}
		});
		btIncluirEmpregado.setBounds(278, 84, 119, 23);
		contentPane.add(btIncluirEmpregado);
		
		JButton btConsultarAlunos = new JButton("Alunos");
		btConsultarAlunos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Recuperando quem é o controlador de caso de uso
				// vinculado a esta janela
				CtrlPrograma ctrl = (CtrlPrograma)getCtrl();
				// Aviso ao controlador que o usuário deseja iniciar
				// o caso de uso "Consultar Alunos"				
				ctrl.iniciarConsultarAlunos();
			}
		});
		btConsultarAlunos.setBounds(149, 37, 119, 23);
		contentPane.add(btConsultarAlunos);
		
		JButton btConsultarCursos = new JButton("Cursos");
		btConsultarCursos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Recuperando quem é o controlador de caso de uso
				// vinculado a esta janela
				CtrlPrograma ctrl = (CtrlPrograma)getCtrl();
				// Aviso ao controlador que o usuário deseja iniciar
				// o caso de uso "Consultar Cursos"				
				ctrl.iniciarConsultarCursos();
			}
		});
		btConsultarCursos.setBounds(20, 37, 119, 23);
		contentPane.add(btConsultarCursos);
	}
}
