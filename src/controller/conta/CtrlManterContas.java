package controller.conta;

import controller.CtrlAbstrato;
import controller.CtrlPrograma;
import model.Conta;
import model.dao.DaoConta;
import viewer.JanelaConsultarContas;

public class CtrlManterContas extends CtrlAbstrato {

	//
	// ATRIBUTOS
	//
	private JanelaConsultarContas janela;
	private CtrlAbstratoConta     ctrlConta;

	//
	// MÉTODOS
	//
	public CtrlManterContas(CtrlAbstrato ctrlPai) {
		// Chamando o construtor de CtrlAbstrato para determinar
		// quem é o controlador que está solicitando a execução
		// do caso de uso "Consultar Contas"
		super(ctrlPai);
		// Instanciando um DaoConta
		DaoConta dao = new DaoConta();
		// Recuperando os objetos Conta existentes
		Conta[] conjContas = dao.obterTodos();
		// Instancio a JanelaConsultarContas, passando a lista inicial de Contas
		this.janela = new JanelaConsultarContas(this, conjContas);
		// Torno a janela visível
		this.janela.setVisible(true);
	}

	/* *********************************************************** */

	/**
	 * Iniciando a execução do caso de uso 'Incluir Conta' a partir deste caso de
	 * uso de consulta de Contas
	 */
	public void iniciarIncluirConta() {
		// Iniciando a execução do caso de uso 'Incluir Conta' a partir
		// da instanciação do objeto CtrlIncluirConta. Observe que vamos
		// guardar como atributo desta classe a referência para o controlador
		// do caso de uso 'Incluir Conta'
		this.ctrlConta = new CtrlIncluirConta(this);
	}

	/* *********************************************************** */

	/**
	 * Iniciando a execução do caso de uso 'Alterar Conta' a partir deste caso de
	 * uso de consulta de Contas
	 */
	public void iniciarAlterarConta(Conta contaSelecionada) {
		if (contaSelecionada == null)
			this.janela.notificar("Selecione uma conta para alteração");
		else
			// Iniciando a execução do caso de uso 'Alterar Conta' a partir
			// da instanciação do objeto CtrlAlterarConta. Observe que vamos
			// guardar como atributo desta classe a referência para o controlador
			// do caso de uso 'Alterar Conta'
			this.ctrlConta = new CtrlAlterarConta(this, contaSelecionada);
	}

	/* *********************************************************** */

	/**
	 * Iniciando a execução do caso de uso 'Excluir Conta' a partir deste caso de
	 * uso de consulta de Contas
	 */
	public void iniciarExcluirConta(Conta contaSelecionada) {
		if (contaSelecionada == null)
			this.janela.notificar("Selecione uma conta para exclusão");
		else
			// Iniciando a execução do caso de uso 'Excluir Conta' a partir
			// da instanciação do objeto CtrlExcluirConta. Observe que vamos
			// guardar como atributo desta classe a referência para o controlador
			// do caso de uso 'Excluir Conta'
			this.ctrlConta = new CtrlExcluirConta(this, contaSelecionada);
	}

	/**
	 * Método que será executado quando o caso de uso 'Excluir Conta' for finalizado
	 */
	public void fimEditarConta() {
		this.ctrlConta = null;
		// Instanciando um DaoConta
		DaoConta dao = new DaoConta();
		// Recuperando os objetos Contas existentes
		Conta[] conjContas = dao.obterTodos();
		// Informo à janela quais são os objetos para exibição
		this.janela.atualizarDados(conjContas);
	}

	/* *********************************************************** */

	@Override
	public void encerrar() {
		CtrlPrograma ctrl = (CtrlPrograma) getCtrlPai();
		ctrl.fimConsultarContas();
	}

	@Override
	public Object getBemTangivel() {
		DaoConta dao = new DaoConta();
		// Recuperando os objetos Conta existentes
		return dao.obterTodos();
	}

}
