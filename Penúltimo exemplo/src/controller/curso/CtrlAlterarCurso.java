package controller.curso;

import controller.CtrlAbstrato;
import model.Curso;
import model.ModelException;
import model.dao.DaoCurso;
import viewer.JanelaCurso;

public class CtrlAlterarCurso extends CtrlAbstratoCurso {
	//
	// MÉTODOS
	//

	/**
	 * Quando instanciarmos um objeto CtrlAlterarCurso, nós desejamos iniciar a
	 * execução do caso de uso 'Alterar Curso'
	 */
	public CtrlAlterarCurso(CtrlAbstrato ctrlPai, Curso cursoASerAlterado) {
		super(ctrlPai, cursoASerAlterado);
	}

	/**
	 * Método que recebe os dados do viewer para alteração do curso
	 */
	public void efetuar(int codigo, String nome, int cargaHoraria) {
		try {
			// Alterando o estado do objeto que guardamos no construtor
			this.curso.setCodigo(codigo);
			this.curso.setNome(nome);
			this.curso.setCargaHoraria(cargaHoraria);
			// Solicitando a um DaoCurso para guardar o objeto
			DaoCurso dao = new DaoCurso();
			dao.alterar(this.curso); 
			// Mando a notificação de sucesso para o usuário
			this.janela.notificar("Curso " + nome + " foi alterado com sucesso!");
			// Encerro o caso de uso
			this.encerrar();
		} catch (ModelException me) {
			// Informo ao usuário que ocorreu algum problema
			// na instanciação do aluno
			this.janela.notificar(me.getMessage());
		}
	}
}
