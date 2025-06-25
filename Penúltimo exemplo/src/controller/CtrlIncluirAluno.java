package controller;

import model.Aluno;
import model.Curso;
import model.ModelException;
import model.dao.DaoAluno;
import viewer.JanelaAluno;

public class CtrlIncluirAluno extends CtrlAbstrato {
	//
	// ATRIBUTO
	//
	private JanelaAluno janela;
	private Aluno       alunoCriado;

	//
	// MÉTODOS
	//

	/**
	 * Quando instanciarmos um objeto CtrlIncluirAluno, nós desejamos 
	 * iniciar a execução do caso de uso 'Incluir Aluno'
	 */
	public CtrlIncluirAluno(CtrlAbstrato ctrlPai) {
		// Guardando que é o controlador pai do CtrlIncluirAluno; ou seja,
		// estamos guardando quem solicitou a execução deste caso de uso
		super(ctrlPai);
		// Instanciando o viewer associado ao caso de uso
		this.janela = new JanelaAluno(this);
		this.janela.setVisible(true);
	}

	/**
	 * Método que recebe os dados do viewer para inclusão do aluno 
	 */
	public void incluirAluno(String cpf, String nome, int idade, String matricula, Curso curso) {
		try {
			// Instancio o objeto aluno e o guardo no atributo 'alunoCriado'
			// para guardar o bem tangível do caso de uso
			this.alunoCriado = new Aluno(cpf, nome, idade, matricula, curso);
			// Instancio um DAO para guardar o aluno
			DaoAluno dao = new DaoAluno();
			// Solicito a persistência do aluno
			dao.incluir(this.alunoCriado);
			// Mando a notificação de sucesso para o usuário
			this.janela.notificar("Aluno " + nome + " incluído com sucesso!");
			// Encerro o caso de uso
			this.encerrar();
		} catch (ModelException me) {
			// Informo ao usuário que ocorreu algum problema 
			// na instanciação do aluno
			this.janela.notificar(me.getMessage());
		}
	}
	
	/**
	 * Método que indica o encerramento do caso de uso
	 */
	public void encerrar() {
		// Fechando a janela de inclusão de Aluno
		this.janela.setVisible(false);
		
		// Recuperando o controlador pai de 'Incluir Aluno'
		CtrlAbstrato ctrlPai = this.getCtrlPai();
		if(ctrlPai instanceof CtrlPrograma) {
			CtrlPrograma ctrl = (CtrlPrograma)ctrlPai;
			ctrl.fimIncluirAluno();
		} else if(ctrlPai instanceof CtrlConsultarAlunos) {
			CtrlConsultarAlunos ctrl = (CtrlConsultarAlunos)ctrlPai;
			ctrl.fimIncluirAluno();
		}
	}
	
	/**
	 * Retorna o bem tangível produzido pelo caso de uso
	 */
	public Object getBemTangivel() {
		return this.alunoCriado;
	}

}
