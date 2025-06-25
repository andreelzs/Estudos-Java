package controller.conta;

import controller.CtrlAbstrato;
import model.Agencia;
import model.Conta;
import model.ModelException;
import model.dao.DaoConta;

public class CtrlIncluirConta extends CtrlAbstratoConta {

	//
	// MÉTODOS
	//

	/**
	 * Quando instanciarmos um objeto CtrlIncluirConta, nós desejamos iniciar a
	 * execução do caso de uso 'Incluir Conta'
	 */
	public CtrlIncluirConta(CtrlAbstrato ctrlPai) {
		// Guardando que é o controlador pai do CtrlIncluirConta; ou seja,
		// estamos guardando quem solicitou a execução deste caso de uso
		super(ctrlPai);
	}

	/**
	 * Método que recebe os dados do viewer para inclusão do Conta
	 */
	public void efetuar(int numero, String nomeCorrentista, String chavePix, double saldo, double limite, Agencia agencia) {
		try {
			// Instanciando a Agência
			Conta c = new Conta(numero, nomeCorrentista, chavePix, saldo, limite, agencia);
			// Solicitando a um DaoConta para guardar o objeto
			DaoConta dao = new DaoConta();
			dao.incluir(c); 
			// Mando a notificação de sucesso para o usuário
			this.janela.notificar("Conta Corrente " + c + " incluída com sucesso!");
			// Encerro o caso de uso
			this.encerrar();
		} catch (ModelException me) {
			// Informo ao usuário que ocorreu algum problema
			// na instanciação do aluno
			this.janela.notificar(me.getMessage());
		}
	}
}
