package controller.agencia;

import controller.CtrlAbstrato;
import controller.CtrlPrograma;
import model.Agencia;
import model.dao.DaoAgencia;
import viewer.JanelaConsultarAgencias;

public class CtrlManterAgencias extends CtrlAbstrato {

	//
	// ATRIBUTOS
	//
	private JanelaConsultarAgencias janela;
	private CtrlAbstratoAgencia     ctrlAgencia;

	//
	// MÉTODOS
	//
	public CtrlManterAgencias(CtrlAbstrato ctrlPai) {
		// Chamando o construtor de CtrlAbstrato para determinar
		// quem é o controlador que está solicitando a execução
		// do caso de uso "Consultar Agencias"
		super(ctrlPai);
		// Instanciando um DaoAgencia
		DaoAgencia dao = new DaoAgencia();
		// Recuperando os objetos Agencia existentes
		Agencia[] conjAgencias = dao.obterTodos();
		// Instancio a JanelaConsultarAgencias, passando a lista inicial de Agencias
		this.janela = new JanelaConsultarAgencias(this, conjAgencias);
		// Torno a janela visível
		this.janela.setVisible(true);
	}

	/* *********************************************************** */

	/**
	 * Iniciando a execução do caso de uso 'Incluir Agencia' a partir deste caso de
	 * uso de consulta de Agencias
	 */
	public void iniciarIncluirAgencia() {
		// Iniciando a execução do caso de uso 'Incluir Agencia' a partir
		// da instanciação do objeto CtrlIncluirAgencia. Observe que vamos
		// guardar como atributo desta classe a referência para o controlador
		// do caso de uso 'Incluir Agencia'
		this.ctrlAgencia = new CtrlIncluirAgencia(this);
	}

	/* *********************************************************** */

	/**
	 * Iniciando a execução do caso de uso 'Alterar Agencia' a partir deste caso de
	 * uso de consulta de Agencias
	 */
	public void iniciarAlterarAgencia(Agencia agenciaSelecionada) {
		if (agenciaSelecionada == null)
			this.janela.notificar("Selecione uma Agência para alteração");
		else
			// Iniciando a execução do caso de uso 'Alterar Agencia' a partir
			// da instanciação do objeto CtrlAlterarAgencia. Observe que vamos
			// guardar como atributo desta classe a referência para o controlador
			// do caso de uso 'Alterar Agencia'
			this.ctrlAgencia = new CtrlAlterarAgencia(this, agenciaSelecionada);
	}

	/* *********************************************************** */

	/**
	 * Iniciando a execução do caso de uso 'Excluir Agencia' a partir deste caso de
	 * uso de consulta de Agencias
	 */
	public void iniciarExcluirAgencia(Agencia agenciaSelecionada) {
		if (agenciaSelecionada == null)
			this.janela.notificar("Selecione uma agência para exclusão");
		else
			// Iniciando a execução do caso de uso 'Excluir Agencia' a partir
			// da instanciação do objeto CtrlExcluirAgencia. Observe que vamos
			// guardar como atributo desta classe a referência para o controlador
			// do caso de uso 'Excluir Agencia'
			this.ctrlAgencia = new CtrlExcluirAgencia(this, agenciaSelecionada);
	}

	/**
	 * Método que será executado quando o caso de uso 'Excluir Agencia' for finalizado
	 */
	public void fimEditarAgencia() {
		this.ctrlAgencia = null;
		// Instanciando um DaoAgencia
		DaoAgencia dao = new DaoAgencia();
		// Recuperando os objetos Agencias existentes
		Agencia[] conjAgencias = dao.obterTodos();
		// Informo à janela quais são os objetos para exibição
		this.janela.atualizarDados(conjAgencias);
	}

	/* *********************************************************** */

	@Override
	public void encerrar() {
		CtrlPrograma ctrl = (CtrlPrograma) getCtrlPai();
		ctrl.fimConsultarAgencias();
	}

	@Override
	public Object getBemTangivel() {
		DaoAgencia dao = new DaoAgencia();
		// Recuperando os objetos Agencia existentes
		return dao.obterTodos();
	}

}
