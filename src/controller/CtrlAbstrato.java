package controller;

abstract public class CtrlAbstrato {
	// 
	// ATRIBUTOS
	//
	
	/**
	 * Representa o controlador que disparou a
	 * execução do controlador em questão
	 */
	final private CtrlAbstrato ctrlPai;
	
	
	//
	// MÉTODOS
	//
	public CtrlAbstrato(CtrlAbstrato pai) {
		this.ctrlPai = pai;
	}
	
	/**
	 * Retorna quem é o controlador pai que disparou a execução 
	 * do caso de uso em questão. 
	 */
	public CtrlAbstrato getCtrlPai() {
		return this.ctrlPai;
	}
	/**
	 * Método que define o que é para ser feito ao final do caso de uso
	 */
	abstract public void encerrar();
	
	/**
	 * Método que define qual é o bem tangível produzido 
	 * ao final do caso de uso
	 */
	abstract public Object getBemTangivel();	
}
