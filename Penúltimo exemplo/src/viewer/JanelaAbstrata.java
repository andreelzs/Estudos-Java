package viewer;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import controller.CtrlAbstrato;

abstract public class JanelaAbstrata<T extends CtrlAbstrato> extends JFrame {
	//
	// ATRIBUTOS
	//
	
	/**
	 * Referência para o controlador de caso de uso
	 * responsável pela janela
	 */
	final private T ctrl;
	
	//
	// MÉTODOS
	// 
	public JanelaAbstrata(T c) {
		// Guardo quem é o controlador de caso de uso. Será obrigatório
		// que o construtor de suas subclasses informem que é esse controlador
		this.ctrl = c;
	}

	/**
	 * Retorna o controlador da janela
	 */
	public T getCtrl() {
		return this.ctrl;
	}
	
	/** 
	 * Método para apresentar notificações na interface
	 * @param texto
	 */
	public void notificar(String texto) {
		JOptionPane.showMessageDialog(null, texto);
	}
	
}
