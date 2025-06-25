package controller.agencia;

import controller.CtrlAbstrato;
import model.Agencia;
import viewer.JanelaAgencia;

abstract public class CtrlAbstratoAgencia extends CtrlAbstrato {
	//
	// ATRIBUTOS
	//
	protected JanelaAgencia janela;
	protected Agencia       agencia;

	//
	// MÉTODOS
	//
	
	/**
	 * Quando instanciarmos um objeto CtrlAbstratoAgencia, nós desejamos iniciar a
	 * execução do caso de uso 'Incluir' OU 'Alterar' OU 'Excluir' Agencia	
	 */
	public CtrlAbstratoAgencia(CtrlAbstrato ctrlPai, Agencia agencia) {
		super(ctrlPai);
		// Guardando o objeto a ser alterado
		this.agencia = agencia;
		// Instanciando o viewer associado ao caso de uso
		this.janela = new JanelaAgencia(this, agencia);
		this.janela.setVisible(true);
	}

	/**
	 * Sobrecarga do construtor para o caso da Inclusão
	 * @param ctrlPai
	 */
	public CtrlAbstratoAgencia(CtrlAbstrato ctrlPai) {
		// Guardando que é o controlador pai do CtrlAlterarAgencia; ou seja,
		// estamos guardando quem solicitou a execução deste caso de uso
		this(ctrlPai, null);
	}

	/**
	 * Método promove a efetivação da ação do controlador
	 */
	abstract public void efetuar(int codigo, String nome, String cidade);

	/**
	 * Método que indica o encerramento do caso de uso
	 */
	public void encerrar() {
		// Fechando a janela de alteração de Agencia
		this.janela.setVisible(false);

		// Recuperando o controlador pai de 'Alterar Agencia'
		CtrlManterAgencias ctrl = (CtrlManterAgencias) this.getCtrlPai();
		ctrl.fimEditarAgencia();		
	}

	/**
	 * Retorna o bem tangível produzido pelo caso de uso
	 */
	public Object getBemTangivel() {
		return this.agencia;
	}

}
