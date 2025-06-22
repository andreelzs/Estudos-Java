package controller.conta;

import controller.CtrlAbstrato;
import model.Agencia;
import model.Conta;
import viewer.JanelaConta;

abstract public class CtrlAbstratoConta extends CtrlAbstrato {
	//
	// ATRIBUTOS
	//
	protected JanelaConta janela;
	protected Conta       conta;

	//
	// MÉTODOS
	//
	
	/**
	 * Quando instanciarmos um objeto CtrlAbstratoConta, nós desejamos iniciar a
	 * execução do caso de uso 'Incluir' OU 'Alterar' OU 'Excluir' Conta	
	 */
	public CtrlAbstratoConta(CtrlAbstrato ctrlPai, Conta conta) {
		super(ctrlPai);
		// Guardando o objeto a ser alterado
		this.conta = conta;
		// Instanciando o viewer associado ao caso de uso
		this.janela = new JanelaConta(this, conta);
		this.janela.setVisible(true);
	}

	/**
	 * Sobrecarga do construtor para o caso da Inclusão
	 * @param ctrlPai
	 */
	public CtrlAbstratoConta(CtrlAbstrato ctrlPai) {
		// Guardando que é o controlador pai do CtrlAlterarConta; ou seja,
		// estamos guardando quem solicitou a execução deste caso de uso
		this(ctrlPai, null);
	}

	/**
	 * Método promove a efetivação da ação do controlador
	 */
	abstract public void efetuar(int numero, String nomeCorrentista, String chavePix, double saldo, double limite, Agencia agencia);

	/**
	 * Método que indica o encerramento do caso de uso
	 */
	public void encerrar() {
		// Fechando a janela de alteração de Conta
		this.janela.setVisible(false);

		// Recuperando o controlador pai de 'Alterar Conta'
		CtrlManterContas ctrl = (CtrlManterContas) this.getCtrlPai();
		ctrl.fimEditarConta();		
	}

	/**
	 * Retorna o bem tangível produzido pelo caso de uso
	 */
	public Object getBemTangivel() {
		return this.conta;
	}

}
