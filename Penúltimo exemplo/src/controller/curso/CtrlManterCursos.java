package controller.curso;

import controller.CtrlAbstrato;
import controller.CtrlPrograma;
import model.Curso;
import model.dao.DaoCurso;
import viewer.JanelaConsultarCursos;

public class CtrlManterCursos extends CtrlAbstrato {

	//
	// ATRIBUTOS
	//
	private JanelaConsultarCursos janela;
	private CtrlAbstratoCurso     ctrlCurso;

	//
	// MÉTODOS
	//
	public CtrlManterCursos(CtrlAbstrato ctrlPai) {
		// Chamando o construtor de CtrlAbstrato para determinar
		// quem é o controlador que está solicitando a execução
		// do caso de uso "Consultar Cursos"
		super(ctrlPai);
		// Instanciando um DaoCurso
		DaoCurso dao = new DaoCurso();
		// Recuperando os objetos Curso existentes
		Curso[] conjCursos = dao.obterTodos();
		// Instancio a JanelaConsultarCursos, passando a lista inicial de cursos
		this.janela = new JanelaConsultarCursos(this, conjCursos);
		// Torno a janela visível
		this.janela.setVisible(true);
	}

	/* *********************************************************** */

	/**
	 * Iniciando a execução do caso de uso 'Incluir Curso' a partir 
	 * deste caso de uso de consulta de cursos
	 */
	public void iniciarIncluirCurso() {
		// Iniciando a execução do caso de uso 'Incluir Curso' a partir
		// da instanciação do objeto CtrlIncluirCurso. Observe que vamos
		// guardar como atributo desta classe a referência para o controlador
		// do caso de uso 'Incluir Curso'
		this.ctrlCurso = new CtrlIncluirCurso(this);
	}

	/* *********************************************************** */

	/**
	 * Iniciando a execução do caso de uso 'Alterar Curso' a partir 
	 * deste caso de uso de consulta de cursos
	 */
	public void iniciarAlterarCurso(Curso cursoSelecionado) {
		if(cursoSelecionado == null)
			this.janela.notificar("Selecione um curso para alteração");
		else
			// Iniciando a execução do caso de uso 'Alterar Curso' a partir
			// da instanciação do objeto CtrlAlterarCurso. Observe que vamos
			// guardar como atributo desta classe a referência para o controlador
			// do caso de uso 'Alterar Curso'
			this.ctrlCurso = new CtrlAlterarCurso(this, cursoSelecionado);
	}

	/* *********************************************************** */

	/**
	 * Iniciando a execução do caso de uso 'Excluir Curso' a partir 
	 * deste caso de uso de consulta de cursos
	 */
	public void iniciarExcluirCurso(Curso cursoSelecionado) {
		if(cursoSelecionado == null)
			this.janela.notificar("Selecione um curso para exclusão");
		else
			// Iniciando a execução do caso de uso 'Excluir Curso' a partir
			// da instanciação do objeto CtrlExcluirCurso. Observe que vamos
			// guardar como atributo desta classe a referência para o controlador
			// do caso de uso 'Excluir Curso'
			this.ctrlCurso = new CtrlExcluirCurso(this, cursoSelecionado);
	}

	/**
	 * Método que será executado quando o caso de uso 'Excluir Curso' for finalizado
	 */
	public void fimEditarCurso() {
		this.ctrlCurso = null;
		// Instanciando um DaoCurso
		DaoCurso dao = new DaoCurso();
		// Recuperando os objetos Cursos existentes
		Curso[] conjCursos = dao.obterTodos();
		// Informo à janela quais são os objetos para exibição
		this.janela.atualizarDados(conjCursos);
	}

	/* *********************************************************** */

	@Override
	public void encerrar() {
		CtrlPrograma ctrl = (CtrlPrograma) getCtrlPai();
		ctrl.fimConsultarCursos();
	}

	@Override
	public Object getBemTangivel() {
		DaoCurso dao = new DaoCurso();
		// Recuperando os objetos Curso existentes
		return dao.obterTodos();
	}

}
