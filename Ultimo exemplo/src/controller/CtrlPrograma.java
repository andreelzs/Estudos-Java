package controller;

import controller.agencia.CtrlManterAgencias;
import controller.conta.CtrlManterContas;
import model.dao.Serializador;
import viewer.JanelaPrincipal;

public class CtrlPrograma extends CtrlAbstrato {
	//
	// ATRIBUTOS
	//
	private JanelaPrincipal    janela; 
	private CtrlManterAgencias ctrlConsultarAgencias;
	private CtrlManterContas   ctrlConsultarContas;
	
	//
	// MÉTODOS
	//
	public CtrlPrograma() {
		// Chamada ao construtor de CtrlAbstrato. Como o CtrlPrograma é o
		// único que não tem um CtrlPai, passamos 'null' como parâmetro
		super(null);
		// Recuperando os objetos da Serialização
		Serializador.recuperarObjetos();
		// Instanciando a JanelaPrincipal
		this.janela = new JanelaPrincipal(this);
		// Estamos mandando a mensagem 'setVisible(true)' para
		// o objeto apontado por 'janela'
		this.janela.setVisible(true);
	}
	
	public Object getBemTangivel() {
		// Como é o CtrlPrograma, não temos um bem tangível para retornar
		return null;
	}
	
	/**
	 * Define o que deve ser feito ao encerrar o controlador
	 */
	public void encerrar() {
		// Notifico ao cliente que o sistema será encerrado
		this.janela.notificar("Encerrando a execução do sistema");
		// Fecho a janela principal
		this.janela.setVisible(false);
		// Solicito a persistência dos objetos 
		Serializador.salvarObjetos();
		// Encerro o programa
		System.exit(0);
	}
	
	/* *********************************************************** */

	/**
	 * Iniciando a execução do caso de uso 'Consultar Agencias'
	 */
	public void iniciarConsultarAgencias() {
		this.ctrlConsultarAgencias = new CtrlManterAgencias(this);
	}
	
	/**
	 * Método que será executado quando o caso de uso 'Consultar Agencias'
	 * for finalizado
	 */
	public void fimConsultarAgencias() {
		this.ctrlConsultarAgencias = null;
	}
	
	/* *********************************************************** */

	/**
	 * Iniciando a execução do caso de uso 'Consultar Contas'
	 */
	public void iniciarConsultarContas() {
		this.ctrlConsultarContas = new CtrlManterContas(this);
	}
	
	/**
	 * Método que será executado quando o caso de uso 'Consultar Contas'
	 * for finalizado
	 */
	public void fimConsultarContas() {
		this.ctrlConsultarContas = null;
	}
	
	/* *********************************************************** */
	/**
	 * Método main
	 */
	public static void main(String[] args) {
		new CtrlPrograma();
	}
}
