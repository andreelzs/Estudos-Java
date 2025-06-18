package viewer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import controller.CtrlConsultarAlunos;
import controller.curso.CtrlManterCursos;
import model.Aluno;
import model.Curso;

public class JanelaConsultarCursos extends JanelaAbstrata<CtrlManterCursos> {
	//
	// ATRIBUTOS
	//
	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTable tabela;
	private Curso[] listaCursos;

	/**
	 * Create the frame.
	 */
	public JanelaConsultarCursos(CtrlManterCursos c, Curso[] conjCursos) {
		super(c);
		setTitle("Cursos");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btSair = new JButton("Sair");
		btSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btSair.setBounds(335, 227, 89, 23);
		contentPane.add(btSair);

		// A chamada a 'atualizarDados' precisa vir antes
		// da criação do JScrollPane
		this.atualizarDados(conjCursos);

		JScrollPane scrollPane = new JScrollPane(tabela);
		scrollPane.setBounds(10, 11, 414, 200);
		contentPane.add(scrollPane);

		JButton btIncluir = new JButton("Incluir");
		btIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Notifico ao controlador que o usuário quer iniciar o caso de uso
				// Incluir Aluno
				getCtrl().iniciarIncluirCurso();
			}
		});
		btIncluir.setBounds(10, 227, 89, 23);
		contentPane.add(btIncluir);

		JButton btExcluir = new JButton("Excluir");
		btExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Verificando se o usuário selecionou algum aluno
				Curso c = obterLinhaSelecionada();
				// Se o usuário selecionou algum curso
				if (c != null) {
					// Realizamos o casting para que o compilador entenda que o 'ctrl'
					// aponta para um CtrlConsultarAlunos
					CtrlManterCursos ctrlConsultarAlunos = (CtrlManterCursos) getCtrl();
					// Notifico ao controlador que o usuário quer iniciar o caso de uso
					// Excluir Aluno
					getCtrl().iniciarExcluirCurso(c);
				} else
					notificar("Selecione um curso para exclusão");
			}
		});
		btExcluir.setBounds(121, 227, 89, 23);
		contentPane.add(btExcluir);

		JButton btAlterar = new JButton("Alterar");
		btAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Verificando se o usuário selecionou algum aluno
				Curso c = obterLinhaSelecionada();
				// Se o usuário selecionou algum curso
				if (c != null) {
					// Notifico ao controlador que o usuário quer iniciar o caso de uso
					// Alterar Curso
					getCtrl().iniciarAlterarCurso(c);
				} else
					notificar("Selecione um curso para alteração");
			}
		});
		btAlterar.setBounds(231, 227, 89, 23);
		contentPane.add(btAlterar);

		this.setVisible(true);
	}

	/**
	 * Atualiza os dados apresentados no JTable da janela
	 */
	public void atualizarDados(Curso[] conjCursos) {
		this.listaCursos = conjCursos;
		HelperTableModel h = new HelperTableModel(this.listaCursos);
		if (this.tabela == null)
			this.tabela = new JTable(h.getTableModel());
		else
			this.tabela.setModel(h.getTableModel());
	}

	/**
	 * Retorna qual objeto
	 * 
	 * @return
	 */
	public Curso obterLinhaSelecionada() {
		int numLinhaSelecionada = this.tabela.getSelectedRow();
		if (numLinhaSelecionada != -1)
			return this.listaCursos[numLinhaSelecionada];
		return null;
	}
}