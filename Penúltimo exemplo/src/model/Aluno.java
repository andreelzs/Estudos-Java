package model;

import java.io.Serializable;

public class Aluno extends Pessoa implements Serializable {
	//
	// CONSTANTES
	//
	final public static int TAM_MATRICULA = 10;
	
	//
	// ATRIBUTOS
	//
	private String matricula;
	
	//
	// ATRIBUTOS DE RELACIONAMENTO
	//
	private Curso cursoDoAluno;
	
	//
	// MÉTODOS
	//
	public Aluno(String cpf, String nome, int idade, String matricula, Curso curso) throws ModelException {
		// Em todas as linguagens Orientadas a Objetos, a primeira instrução
		// do construtor de uma SUBCLASSE deve obrigatoriamente chamar o 
		// construtor da sua SUPERCLASSE. Isso é obrigatório para que a 
		// subclasse seja forçada a seguir as regras de inicialização previstas
		// para objetos da superclasse. Assim, a inicialização de um objeto
		// Aluno vai ter que seguir as regras de inicialização de uma Pessoa.
		// Em Java, chamamos o construtor da superclasse com a construção
		// "super(...)"
		
		// Chamando a execução do construtor de Pessoa
		super(cpf, nome, idade);		
		
		// Inicializando a matrícula
		this.setMatricula(matricula);
		// Indicando o curso do aluno
		this.setCursoDoAluno(curso);
	}

	@Override
	public String toString() {
		return this.getCpf() + "-" + this.getNome() + "(" + this.matricula + ")";
	}
	
	public String getMatricula() {
		return this.matricula;
	}

	public void setMatricula(String matricula) throws ModelException {
		Aluno.validarMatricula(matricula);
		this.matricula = matricula;
	}
	
	public Curso getCursoDoAluno() {
		return this.cursoDoAluno;
	}
	
	public void setCursoDoAluno(Curso curso) throws ModelException {
		Aluno.validarCursoDoAluno(curso);
		this.cursoDoAluno = curso;
	}

	public static void validarMatricula(String matricula) throws ModelException {
		if(matricula == null || matricula.length() == 0)
			throw new ModelException("A matrícula não pode ser nulo!");
		if(matricula.length() != TAM_MATRICULA)
			throw new ModelException("A matrícula deve ter " + TAM_MATRICULA + " dígitos");
		for(int i = 0; i < matricula.length(); i++) {
			char c = matricula.charAt(i);
			if( !Character.isDigit(c) )
				throw new ModelException("Dígito inválido na posição " + i + " da matrícula: " + c);
		}
	}
	
	public static void validarCursoDoAluno(Curso curso) throws ModelException {
		if(curso == null)
			throw new ModelException("O aluno precisa estar vinculado a um curso!");
	}
}
