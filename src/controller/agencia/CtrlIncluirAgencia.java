package controller.agencia;

import controller.CtrlAbstrato;
import model.Agencia;
import model.ModelException;
import model.dao.DaoAgencia;

public class CtrlIncluirAgencia extends CtrlAbstratoAgencia {

	//
	// MÉTODOS
	//

	/**
	 * Quando instanciarmos um objeto CtrlIncluirAgencia, nós desejamos iniciar a
	 * execução do caso de uso 'Incluir Agencia'
	 */
	public CtrlIncluirAgencia(CtrlAbstrato ctrlPai) {
		// Guardando que é o controlador pai do CtrlIncluirAgencia; ou seja,
		// estamos guardando quem solicitou a execução deste caso de uso
		super(ctrlPai);
	}

	/**
	 * Método que recebe os dados do viewer para inclusão do Agencia
	 */
	public void efetuar(int numero, String bairro, String cidade) {
		try {
			// Instanciando a Agência
			Agencia a = new Agencia(numero, bairro, cidade);
			// Solicitando a um DaoAgencia para guardar o objeto
			DaoAgencia dao = new DaoAgencia();
			dao.incluir(a); 
			// Mando a notificação de sucesso para o usuário
			this.janela.notificar("Agência " + a + " incluído com sucesso!");
			// Encerro o caso de uso
			this.encerrar();
		} catch (ModelException me) {
			// Informo ao usuário que ocorreu algum problema
			// na instanciação do aluno
			this.janela.notificar(me.getMessage());
		}
	}
}
