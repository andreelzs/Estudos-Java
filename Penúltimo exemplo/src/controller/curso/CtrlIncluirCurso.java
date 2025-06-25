package controller.curso;

import controller.CtrlAbstrato;
import model.Aluno;
import model.Curso;
import model.ModelException;
import model.dao.DaoAluno;
import model.dao.DaoCurso;
import viewer.JanelaCurso;

public class CtrlIncluirCurso extends CtrlAbstratoCurso {

	//
	// MÉTODOS
	//

	/**
	 * Quando instanciarmos um objeto CtrlIncluirCurso, nós desejamos iniciar a
	 * execução do caso de uso 'Incluir Curso'
	 */
	public CtrlIncluirCurso(CtrlAbstrato ctrlPai) {
		// Guardando que é o controlador pai do CtrlIncluirCurso; ou seja,
		// estamos guardando quem solicitou a execução deste caso de uso
		super(ctrlPai);
	}

	/**
	 * Método que recebe os dados do viewer para inclusão do curso
	 */
	public void efetuar(int codigo, String nome, int cargaHoraria) {
		try {
			// Instanciando o curso
			Curso c = new Curso(codigo, nome, cargaHoraria);
			// Solicitando a um DaoCurso para guardar o objeto
			DaoCurso dao = new DaoCurso();
			dao.incluir(c); 
			// Mando a notificação de sucesso para o usuário
			this.janela.notificar("Curso " + nome + " incluído com sucesso!");
			// Encerro o caso de uso
			this.encerrar();
		} catch (ModelException me) {
			// Informo ao usuário que ocorreu algum problema
			// na instanciação do aluno
			this.janela.notificar(me.getMessage());
		}
	}
}
