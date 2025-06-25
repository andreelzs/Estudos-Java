package controller.curso;

import controller.CtrlAbstrato;
import model.Curso;
import model.ModelException;
import model.dao.DaoCurso;
import viewer.JanelaCurso;

abstract public class CtrlAbstratoCurso extends CtrlAbstrato {
	//
	// ATRIBUTOS
	//
	protected JanelaCurso janela;
	protected Curso       curso;

	//
	// MÉTODOS
	//
	
	/**
	 * Quando instanciarmos um objeto CtrlAbstratoCurso, nós desejamos iniciar a
	 * execução do caso de uso 'Incluir' OU 'Alterar' OU 'Excluir' Curso	
	 */
	public CtrlAbstratoCurso(CtrlAbstrato ctrlPai, Curso curso) {
		super(ctrlPai);
		// Guardando o objeto a ser alterado
		this.curso = curso;
		// Instanciando o viewer associado ao caso de uso
		this.janela = new JanelaCurso(this, curso);
		this.janela.setVisible(true);
	}

	/**
	 * Sobrecarga do construtor para o caso da Inclusão
	 * @param ctrlPai
	 */
	public CtrlAbstratoCurso(CtrlAbstrato ctrlPai) {
		// Guardando que é o controlador pai do CtrlAlterarCurso; ou seja,
		// estamos guardando quem solicitou a execução deste caso de uso
		this(ctrlPai, null);
	}

	/**
	 * Método promove a efetivação da ação do controlador
	 */
	abstract public void efetuar(int codigo, String nome, int cargaHoraria);

	/**
	 * Método que indica o encerramento do caso de uso
	 */
	public void encerrar() {
		// Fechando a janela de alteração de Curso
		this.janela.setVisible(false);

		// Recuperando o controlador pai de 'Alterar Curso'
		CtrlManterCursos ctrl = (CtrlManterCursos) this.getCtrlPai();
		ctrl.fimEditarCurso();		
	}

	/**
	 * Retorna o bem tangível produzido pelo caso de uso
	 */
	public Object getBemTangivel() {
		return this.curso;
	}

}
