package controller.agencia;

import controller.CtrlAbstrato;
import model.Agencia;
import model.ModelException;
import model.dao.DaoAgencia;
import viewer.JanelaAgencia;

public class CtrlAlterarAgencia extends CtrlAbstratoAgencia {
	//
	// MÉTODOS
	//

	/**
	 * Quando instanciarmos um objeto CtrlAlterarAgencia, nós desejamos iniciar a
	 * execução do caso de uso 'Alterar Agencia'
	 */
	public CtrlAlterarAgencia(CtrlAbstrato ctrlPai, Agencia agenciaASerAlterada) {
		super(ctrlPai, agenciaASerAlterada);
	}

	/**
	 * Método que recebe os dados do viewer para alteração do Agencia
	 */
	public void efetuar(int numero, String bairro, String cidade) {
		try {
			// Alterando o estado do objeto que guardamos no construtor
			this.agencia.setNumero(numero);
			this.agencia.setBairro(bairro);
			this.agencia.setCidade(cidade);
			// Solicitando a um DaoAgencia para guardar o objeto
			DaoAgencia dao = new DaoAgencia();
			dao.alterar(this.agencia); 
			// Mando a notificação de sucesso para o usuário
			this.janela.notificar("Agência " + this.agencia + " foi alterada com sucesso!");
			// Encerro o caso de uso
			this.encerrar();
		} catch (ModelException me) {
			// Informo ao usuário que ocorreu algum problema
			// na instanciação do aluno
			this.janela.notificar(me.getMessage());
		}
	}
}
