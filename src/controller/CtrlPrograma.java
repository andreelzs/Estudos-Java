package controller;

import controller.curso.CtrlManterCursos;
import controller.curso.CtrlIncluirCurso;
import model.dao.Serializador;
import viewer.JanelaPrincipal;

public class CtrlPrograma extends CtrlAbstrato {
	//
	// ATRIBUTOS
	//
	private JanelaPrincipal     janela; 
	private CtrlIncluirAluno    ctrlIncluirAluno;
	private CtrlConsultarAlunos ctrlConsultarAlunos;
	private CtrlManterCursos ctrlConsultarCursos;
	
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
	 * Iniciando a execução do caso de uso 'Incluir Aluno'
	 */
	public void iniciarIncluirAluno() {
		// Iniciando a execução do caso de uso 'Incluir Aluno' a partir
		// da instanciação do objeto CtrlIncluirAluno. Observe que vamos
		// guardar como atributo desta classe a referência para o controlador
		// do caso de uso 'Incluir Aluno'
		this.ctrlIncluirAluno = new CtrlIncluirAluno(this);
	}
	
	/**
	 * Método que será executado quando o caso de uso 'Incluir Aluno'
	 * for finalizado
	 */
	public void fimIncluirAluno() {
		this.ctrlIncluirAluno = null;
	}

	/* *********************************************************** */

	/**
	 * Iniciando a execução do caso de uso 'Consultar Alunos'
	 */
	public void iniciarConsultarAlunos() {
		this.ctrlConsultarAlunos = new CtrlConsultarAlunos(this);
	}
	
	/**
	 * Método que será executado quando o caso de uso 'Consultar Alunos'
	 * for finalizado
	 */
	public void fimConsultarAlunos() {
		this.ctrlIncluirAluno = null;
	}
	
	/* *********************************************************** */

	/**
	 * Iniciando a execução do caso de uso 'Consultar Cursos'
	 */
	public void iniciarConsultarCursos() {
		this.ctrlConsultarCursos = new CtrlManterCursos(this);
	}
	
	/**
	 * Método que será executado quando o caso de uso 'Consultar Cursos'
	 * for finalizado
	 */
	public void fimConsultarCursos() {
		this.ctrlConsultarCursos = null;
	}
	
	/* *********************************************************** */
	/**
	 * Método main
	 */
	public static void main(String[] args) {
		new CtrlPrograma();
	}
}
