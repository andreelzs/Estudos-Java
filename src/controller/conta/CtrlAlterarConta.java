package controller.conta;

import controller.CtrlAbstrato;
import model.Agencia;
import model.Conta;
import model.ModelException;
import model.dao.DaoConta;

public class CtrlAlterarConta extends CtrlAbstratoConta {
	//
	// MÉTODOS
	//

	/**
	 * Quando instanciarmos um objeto CtrlAlterarConta, nós desejamos iniciar a
	 * execução do caso de uso 'Alterar Conta'
	 */
	public CtrlAlterarConta(CtrlAbstrato ctrlPai, Conta contaASerAlterada) {
		super(ctrlPai, contaASerAlterada);
	}

	/**
	 * Método que recebe os dados do viewer para alteração do Conta
	 */
	public void efetuar(int numero, String nomeCorrentista, String chavePix, double saldo, double limite, Agencia agencia) {
		try {
			// Alterando o estado do objeto que guardamos no construtor
			this.conta.setNumero(numero);
			this.conta.setNomeCorrentista(nomeCorrentista);
			this.conta.setChavePix(chavePix);
			this.conta.setSaldo(saldo);
			this.conta.setLimite(limite);
			this.conta.setAgencia(agencia);
			// Solicitando a um DaoConta para guardar o objeto
			DaoConta dao = new DaoConta();
			dao.alterar(this.conta); 
			// Mando a notificação de sucesso para o usuário
			this.janela.notificar("Conta corrente " + this.conta + " foi alterada com sucesso!");
			// Encerro o caso de uso
			this.encerrar();
		} catch (ModelException me) {
			// Informo ao usuário que ocorreu algum problema
			// na instanciação do aluno
			this.janela.notificar(me.getMessage());
		}
	}
}
