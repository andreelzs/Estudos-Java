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
import model.Aluno;

public class JanelaConsultarAlunos extends JanelaAbstrata<CtrlConsultarAlunos> {
	//
	// ATRIBUTOS
	//
	private JPanel      contentPane;
	private JScrollPane scrollPane;
	private JTable      tabela;
	private Aluno[]     listaAlunos;

	/**
	 * Create the frame.
	 */
	public JanelaConsultarAlunos(CtrlConsultarAlunos c, Aluno[] conjAlunos) {
		super(c);
		setTitle("Alunos");
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

		this.atualizarDados(conjAlunos);
		
		JScrollPane scrollPane = new JScrollPane(tabela);
		scrollPane.setBounds(10, 11, 414, 200);
		contentPane.add(scrollPane);		

		JButton btIncluir = new JButton("Incluir");
		btIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Notifico ao controlador que o usuário quer iniciar o caso de uso
				// Incluir Aluno
				getCtrl().iniciarIncluirAluno();
			}
		});
		btIncluir.setBounds(10, 227, 89, 23);
		contentPane.add(btIncluir);
		
		JButton btExcluir = new JButton("Excluir");
		btExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Verificando se o usuário selecionou algum aluno
				Aluno a = obterLinhaSelecionada();
				// Se o usuário selecionou algum aluno
				if(a != null) {
					// Realizamos o casting para que o compilador entenda que o 'ctrl'
					// aponta para um CtrlConsultarAlunos
					CtrlConsultarAlunos ctrlConsultarAlunos = (CtrlConsultarAlunos)getCtrl();
					// Notifico ao controlador que o usuário quer iniciar o caso de uso
					// Excluir Aluno
					//ctrlConsultarAlunos.iniciarExcluirAluno(a);
				}
				else
					notificar("Selecione um curso para exclusão");			}
		});
		btExcluir.setBounds(121, 227, 89, 23);
		contentPane.add(btExcluir);
		
		JButton btAlterar = new JButton("Alterar");
		btAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Verificando se o usuário selecionou algum aluno
				Aluno a = obterLinhaSelecionada();
				// Se o usuário selecionou algum aluno
				if(a != null) {
					// Realizamos o casting para que o compilador entenda que o 'ctrl'
					// aponta para um CtrlConsultarAlunos
					CtrlConsultarAlunos ctrlConsultarAlunos = (CtrlConsultarAlunos)getCtrl();
					// Notifico ao controlador que o usuário quer iniciar o caso de uso
					// Excluir Aluno
					//ctrlConsultarAlunos.iniciarAlterarAluno(a);
				}
				else
					notificar("Selecione um curso para exclusão");	

				
			}
		});
		btAlterar.setBounds(231, 227, 89, 23);
		contentPane.add(btAlterar);
		
		this.setVisible(true);
	}

	/**
	 * Atualiza os dados apresentados no JTable da janela
	 */
	public void atualizarDados(Aluno[] conjAlunos) {
		this.listaAlunos = conjAlunos;
		HelperTableModel h = new HelperTableModel(this.listaAlunos);
		if(this.tabela == null)
			this.tabela = new JTable(h.getTableModel());
		else 
			this.tabela.setModel(h.getTableModel());
	}

	/**
	 * Retorna qual objeto 
	 * @return
	 */
	public Aluno obterLinhaSelecionada() {
		int numLinhaSelecionada = this.tabela.getSelectedRow();
		if(numLinhaSelecionada != -1)
			return this.listaAlunos[numLinhaSelecionada];
		return null;
	}	
}