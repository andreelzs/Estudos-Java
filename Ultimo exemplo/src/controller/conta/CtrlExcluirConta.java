package controller.conta;

import controller.CtrlAbstrato;
import model.Agencia;
import model.Conta;
import model.dao.DaoConta;

public class CtrlExcluirConta extends CtrlAbstratoConta {
	//
	// MÉTODOS
	//

	/**
	 * Quando instanciarmos um objeto CtrlExcluirConta, nós desejamos iniciar a
	 * execução do caso de uso 'Excluir Conta'
	 */
	public CtrlExcluirConta(CtrlAbstrato ctrlPai, Conta contaASerExcluida) {
		// Guardando que é o controlador pai do CtrlAlterarConta; ou seja,
		// estamos guardando quem solicitou a execução deste caso de uso
		super(ctrlPai, contaASerExcluida);
	}

	/**
	 * Método que recebe a confirmação para excluirmos uma Agência
	 */
	public void efetuar(int numero, String nomeCorrentista, String chavePix, double saldo, double limite, Agencia agencia) {
		// Solicitando a um DaoConta para excluir o objeto
		DaoConta dao = new DaoConta();
		dao.remover(this.conta);
		// Mando a notificação de sucesso para o usuário
		this.janela.notificar("Conta corrente " + this.conta + " foi excluída com sucesso!");
		// Encerro o caso de uso
		this.encerrar();
	}
}
