package controller;

import model.Aluno;
import model.dao.DaoAluno;
import viewer.JanelaConsultarAlunos;

public class CtrlConsultarAlunos extends CtrlAbstrato {

	//
	// ATRIBUTOS
	//
	private JanelaConsultarAlunos janela;
	private CtrlIncluirAluno ctrlIncluirAluno;

	//
	// MÉTODOS
	//
	public CtrlConsultarAlunos(CtrlAbstrato ctrlPai) {
		// Chamando o construtor de CtrlAbstrato para determinar
		// quem é o controlador que está solicitando a execução
		// do caso de uso "Consultar Alunos"
		super(ctrlPai);
		// Instanciando um DaoAluno
		DaoAluno dao = new DaoAluno();
		// Recuperando os objetos Aluno existentes
		Aluno[] conjAlunos = dao.obterTodos();
		// Instancio a JanelaConsultarAlunos, passando a lista inicial de alunos
		this.janela = new JanelaConsultarAlunos(this, conjAlunos);
		// Torno a janela visível
		this.janela.setVisible(true);
	}

	/* *********************************************************** */

	/**
	 * Iniciando a execução do caso de uso 'Incluir Aluno' a partir deste caso de
	 * uso de consulta de alunos
	 */
	public void iniciarIncluirAluno() {
		// Iniciando a execução do caso de uso 'Incluir Aluno' a partir
		// da instanciação do objeto CtrlIncluirAluno. Observe que vamos
		// guardar como atributo desta classe a referência para o controlador
		// do caso de uso 'Incluir Aluno'
		this.ctrlIncluirAluno = new CtrlIncluirAluno(this);
	}

	/**
	 * Método que será executado quando o caso de uso 'Incluir Aluno' for finalizado
	 */
	public void fimIncluirAluno() {
		this.ctrlIncluirAluno = null;
		// Instanciando um DaoAluno
		DaoAluno dao = new DaoAluno();
		// Recuperando os objetos Aluno existentes
		Aluno[] conjAlunos = dao.obterTodos();
		// Informo à janela quais são os objetos para exibição
		this.janela.atualizarDados(conjAlunos);
	}

	@Override
	public void encerrar() {
		CtrlPrograma ctrl = (CtrlPrograma) getCtrlPai();
		ctrl.fimConsultarAlunos();
	}

	@Override
	public Object getBemTangivel() {
		DaoAluno dao = new DaoAluno();
		// Recuperando os objetos Aluno existentes
		Aluno[] conjAlunos = dao.obterTodos();

		return conjAlunos;
	}

}
