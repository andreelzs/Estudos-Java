package model;

import java.io.Serializable;

public class Disciplina implements Serializable{
	//
	// CONSTANTES
	//
	final public static int VALOR_MAX_NUM_CREDITOS = 8;
	final public static int TAM_CODIGO = 7;
	
	//
	// ATRIBUTOS
	//
	private String codigo;
	private String nome;
	private int    numCreditos;
	
	//
	// MÉTODOS
	//
	/**
	 * Método construtor de Departamento
	 */
	public Disciplina(String codigo, String nome, int numCreditos) throws ModelException {
		super();
		this.setCodigo(codigo);
		this.setNome(nome);
		this.setNumCreditos(numCreditos);
	}

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) throws ModelException {
		Disciplina.validarCodigo(codigo);
		this.codigo = codigo;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) throws ModelException {
		Disciplina.validarNome(nome);
		this.nome = nome;
	} 

	public int getNumCreditos() {
		return this.numCreditos;
	}

	public void setNumCreditos(int numCreditos) throws ModelException {
		Disciplina.validarNumCreditos(numCreditos);
		this.numCreditos = numCreditos;
	} 

	@Override
	public String toString() {
		return this.codigo + "-" + this.nome;
	}

	public static void validarCodigo(String codigo) throws ModelException {
		if(codigo == null || codigo.length() == 0)
			throw new ModelException("O código não pode ser nulo!"); 
		if(codigo.length() != TAM_CODIGO)
			throw new ModelException("O código deve ter " + TAM_CODIGO + " caracteres!"); 
		for(int i = 0; i < codigo.length(); i++) {
			char c = codigo.charAt(i);
			if( ! Character.isAlphabetic(c))
				throw new ModelException("Na posição " + i + " do código, há um caracter não alfabético: " + c);
		}
	}
	
	public static void validarNome(String nome) throws ModelException {
		if(nome == null || nome.length() == 0)
			throw new ModelException("O nome não pode ser nulo!");
		for(int i = 0; i < nome.length(); i++) {
			char c = nome.charAt(i);
			if( !Character.isAlphabetic(c) && !Character.isSpaceChar(c) )
				throw new ModelException("No nome, há um caracterer inválido '" + c + "' na posição " + i);
		}
	}	

	public static void validarNumCreditos(int numCreditos) throws ModelException {
		if(numCreditos < 0 || numCreditos > VALOR_MAX_NUM_CREDITOS)
			throw new ModelException("Número de Créditos inválido: " + numCreditos);
	}	
}
