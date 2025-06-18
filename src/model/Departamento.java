package model;

import java.io.Serializable;

public class Departamento implements Serializable{
	//
	// CONSTANTES
	//
	final public static int TAM_SIGLA = 2;
	final public static int TAM_MIN_NOME = 5;
	final public static int TAM_MAX_NOME = 20;

	//
	// ATRIBUTOS
	//
	private String sigla;
	private String nome;

	//
	// MÉTODOS
	//
	/**
	 * Método construtor de Departamento
	 */
	public Departamento(String sigla, String nome) throws ModelException {
		super();
		// Enviamos a mensagem 'setSigla' para o objeto
		// que estiver executando este construtor.
		this.setSigla(sigla);
		// Enviamos a mensagem 'setNome' para o objeto
		// que estiver executando este construtor.
		this.setNome(nome);
	}

	/**
	 * Retorna a sigla do Deparamento
	 */
	public String getSigla() {
		return this.sigla;
	}

	/**
	 * Altera a sigla do Departamento, desde que o parâmetro seja válido
	 */
	public void setSigla(String sigla) throws ModelException{
		Departamento.validarSigla(sigla);
		this.sigla = sigla;
	}

	/**
	 * Retorna o nome do Departamento
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Altera o nome do Departamento, desde que o parâmetro seja válido
	 */
	public void setNome(String nome) throws ModelException {
		// Fazendo a testagem do parâmetro com o método de validação
		Departamento.validarNome(nome);
		this.nome = nome;
	}
	
	@Override
	public String toString() {
		return "("+ this.sigla + ") " + this.nome;
	}

	/**
	 * Verifica se o parâmetro passado corresponde a uma sigla válida
	 */
	public static void validarSigla(String sigla) throws ModelException {
		// Se 'sigla' aponta para null OU se o tamanho da String apontado por 'sigla'
		// é zero, retornamos 'false'. Observe que 'sigla.length()' corresponde ao
		// envio da mensagem 'length()' para o objeto apontado por 'sigla'
		if (sigla == null || sigla.length() == 0)
			throw new ModelException("A sigla do departamento não pode ser nula!");
		// Se 'sigla' não tiver exatamente TAM_SIGLA de caracteres, lançamos exceção
		if (sigla.length() != TAM_SIGLA)
			throw new ModelException("A sigla do departamento deve ter exatamente " + TAM_SIGLA + " caracteres!");
		// Vamos verificar cada caracter na String apontada por sigla
		for (int i = 0; i < sigla.length(); i++) {
			// A mensagem 'charAt(i)' retorna o caracter presente na posição 'i'
			char c = sigla.charAt(i);
			// Se o caracter NÃO é alfabético, retornamos 'false'
			if (!Character.isAlphabetic(c))
				throw new ModelException("Na posição " + i + " da sigla há um caracter inválido: " + c);
		}
	}

	/**
	 * Verifica se o parâmetro passado corresponde a um nome válido
	 */
	public static void validarNome(String nome) throws ModelException {
		// Se 'nome' aponta para null OU se o tamanho da String apontado por 'nome'
		// é zero, retornamos 'false'. Observe que 'nome.length()' corresponde ao
		// envio da mensagem 'length()' para o objeto apontado por 'nome'
		if (nome == null || nome.length() == 0)
			throw new ModelException("O nome do departamento não pode ser nulo!");
		// Se 'nome' tiver menos que TAM_MIN_NOME caracteres ou mais que
		// TAM_MAX_NOME caracteres lançamos exceção
		if (nome.length() < TAM_MIN_NOME || nome.length() > TAM_MAX_NOME)
			throw new ModelException(
					"O nome do departamento deve ter entre" + TAM_MIN_NOME + " e " + TAM_MAX_NOME + " caracteres!");
		// Vamos verificar cada caracter na String apontada por 'nome'
		for (int i = 0; i < nome.length(); i++) {
			// A mensagem 'charAt(i)' retorna o caracter presente na posição 'i'
			char c = nome.charAt(i);
			// Se o caracter NÃO é alfabético E também não é espaço em branco
			// lançamos exceção
			if (!Character.isAlphabetic(c) && !Character.isSpaceChar(c))
				throw new ModelException("Na posição " + i + " do nome há um caracter inválido: " + c);
		}
	}
	//
	// Observação: Se você gerar o método construtor e os métodos gets/sets com o
	// Eclipse, não deixe de fazer as seguintes alterações:
	// (1) Acrescente o "this." nos métodos get na linha onde tem o return.
	// (2) No construtor, troque as atribuições pelo uso dos métodos 'set'
	// (3) Nos métodos 'set' só faça a atribuição após testar o parâmetro
	// usando o método 'validar' adequado.
}
