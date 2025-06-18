package controller.curso;

import controller.CtrlAbstrato;
import model.Curso;
import model.ModelException;
import model.dao.DaoCurso;
import viewer.JanelaCurso;

public class CtrlExcluirCurso extends CtrlAbstratoCurso {
	//
	// MÉTODOS
	//

	/**
	 * Quando instanciarmos um objeto CtrlExcluirCurso, nós desejamos iniciar a
	 * execução do caso de uso 'Excluir Curso'
	 */
	public CtrlExcluirCurso(CtrlAbstrato ctrlPai, Curso cursoASerExcluido) {
		// Guardando que é o controlador pai do CtrlAlterarCurso; ou seja,
		// estamos guardando quem solicitou a execução deste caso de uso
		super(ctrlPai, cursoASerExcluido);
	}

	/**
	 * Método que recebe a confirmação para excluirmos um curso
	 */
	public void efetuar(int codigo, String nome, int cargaHoraria) {
		// Solicitando a um DaoCurso para excluir o objeto
		DaoCurso dao = new DaoCurso();
		dao.remover(this.curso);
		// Mando a notificação de sucesso para o usuário
		this.janela.notificar("Curso " + this.curso.getNome() + " foi excluído com sucesso!");
		// Encerro o caso de uso
		this.encerrar();
	}
}
