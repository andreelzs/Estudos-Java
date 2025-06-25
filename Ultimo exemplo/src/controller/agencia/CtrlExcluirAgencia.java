package controller.agencia;

import controller.CtrlAbstrato;
import model.Agencia;
import model.dao.DaoAgencia;

public class CtrlExcluirAgencia extends CtrlAbstratoAgencia {
	//
	// MÉTODOS
	//

	/**
	 * Quando instanciarmos um objeto CtrlExcluirAgencia, nós desejamos iniciar a
	 * execução do caso de uso 'Excluir Agencia'
	 */
	public CtrlExcluirAgencia(CtrlAbstrato ctrlPai, Agencia agenciaASerExcluida) {
		// Guardando que é o controlador pai do CtrlAlterarAgencia; ou seja,
		// estamos guardando quem solicitou a execução deste caso de uso
		super(ctrlPai, agenciaASerExcluida);
	}

	/**
	 * Método que recebe a confirmação para excluirmos uma Agência
	 */
	public void efetuar(int numero, String bairro, String cidade) {
		// Solicitando a um DaoAgencia para excluir o objeto
		DaoAgencia dao = new DaoAgencia();
		dao.remover(this.agencia);
		// Mando a notificação de sucesso para o usuário
		this.janela.notificar("Agência " + this.agencia + " foi excluída com sucesso!");
		// Encerro o caso de uso
		this.encerrar();
	}
}
