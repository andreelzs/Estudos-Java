package model;

import java.io.Serializable;

public class Pessoa implements Serializable {
	//
	// CONSTANTES
	//
	public static int VALOR_MAX_IDADE = 150;
	public static int TAM_CPF = 14;

	//
	// ATRIBUTOS
	//
	private String cpf; // tipo do atributo cpf: Ponteiro para (um objeto) String
	private String nome; // tipo do atributo nome: Ponteiro para (um objeto) String
	private int idade; // tipo do atributo idade: int (tipo primitivo)

	//
	// MÉTODOS
	//
	/**
	 * MÉTODO CONSTRUTOR da classe Pessoa. Sempre que um objeto Pessoa for criado
	 * com o operador 'new', o novo objeto irá executar este método. É o método
	 * responsável pela inicialização do objeto recém-criado.
	 * 
	 * @param c ponteiro para String - Aponta para a String que determina o cpf da
	 *          Pessoa
	 * @param n ponteiro para String - Aponta para a String que determina o nome da
	 *          Pessoa
	 * @param i int - determina o valor da idade da Pessoa
	 */
	public Pessoa(String c, String n, int i) throws ModelException {
		// Chamada ao construtor da superclasse (Object) - tópico futuro
		super();
		this.setCpf(c);
		this.setNome(n);
		this.setIdade(i);
	}

	/**
	 * Método que retorna a referência para a String que contém o cpf da Pessoa
	 * 
	 * @return
	 */
	public String getCpf() {
		return this.cpf;
	}

	/**
	 * Método que altera o atributo 'cpf', fazendo com que aponte para outra String
	 * que contém o novo cpf
	 * 
	 * @param novoCpf
	 */
	public void setCpf(String novoCpf) throws ModelException {
		Pessoa.validarCpf(novoCpf);
		this.cpf = novoCpf;
	}

	/**
	 * Método que retorna a referência para a String que contém o nome da Pessoa
	 * 
	 * @return
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Método que altera o atributo 'nome', fazendo com que aponte para outra String
	 * que contém o novo nome
	 * 
	 * @param novoNome
	 */
	public void setNome(String novoNome) throws ModelException {
		Pessoa.validarNome(novoNome);
		this.nome = novoNome;
	}

	/**
	 * Método que retorna a idade da Pessoa
	 * 
	 * @return
	 */
	public int getIdade() {
		return this.idade;
	}

	/**
	 * Método que altera o atributo 'idade', fazendo com que armazene um novo valor
	 * para idade
	 * 
	 * @param novaIdade
	 */
	public void setIdade(int novaIdade) throws ModelException {
		Pessoa.validarIdade(novaIdade);
		this.idade = novaIdade;
	}

	@Override
	public String toString() {
		return this.cpf + "-" + this.nome;
	}

	public static void validarCpf(String cpf) throws ModelException {
		if (cpf == null || cpf.length() == 0)
			throw new ModelException("O cpf não pode ser nulo!");
		if (cpf.length() != TAM_CPF)
			throw new ModelException("O cpf deve ter " + TAM_CPF + " caracteres!");
		for (int i = 0; i < cpf.length(); i++) {
			char c = cpf.charAt(i);
			// if( !Character.isAlphabetic(c) && !Character.isSpaceChar(c) )
			// throw new ModelException("No nome, há um caracterer inválido '" + c + "' na
			// posição " + i);
		}
	}

	public static void validarNome(String nome) throws ModelException {
		if (nome == null || nome.length() == 0)
			throw new ModelException("O nome não pode ser nulo!");
		for (int i = 0; i < nome.length(); i++) {
			char c = nome.charAt(i);
			if (!Character.isAlphabetic(c) && !Character.isSpaceChar(c))
				throw new ModelException("No nome, há um caracterer inválido '" + c + "' na posição " + i);
		}
	}

	public static void validarIdade(int idade) throws ModelException {
		if (idade < 0 || idade > VALOR_MAX_IDADE)
			throw new ModelException("Idade Inválida: " + idade);
	}
}
