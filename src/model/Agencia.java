package model;

import java.io.Serializable;

public class Agencia implements Serializable {
	//
	// CONSTANTES
	//
	final public static int TAM_BAIRRO = 25;
	final public static int TAM_CIDADE   = 25;
	final public static int NUM_MAX_AGENCIA = 10000;
	
	//
	// ATRIBUTOS
	//
	private int    numero;
	private String bairro;
	private String cidade;
	
	//
	// MÉTODOS
	//
	public Agencia(int numero, String bairro, String cidade) throws ModelException {
		super();
		this.setNumero(numero);
		this.setBairro(bairro);
		this.setCidade(cidade);
	}

	public int getNumero() {
		return this.numero;
	}

	public void setNumero(int numero) throws ModelException{
		Agencia.validarNumero(numero);
		this.numero = numero;
	}

	public String getBairro() {
		return this.bairro;
	}

	public void setBairro(String bairro) throws ModelException {
		Agencia.validarBairro(bairro);
		this.bairro = bairro;
	}

	public String getCidade() {
		return this.cidade;
	}

	public void setCidade(String cidade) throws ModelException{
		Agencia.validarCidade(cidade);
		this.cidade = cidade;
	}

	public String toString() {
		return this.numero + ":" + this.bairro + "-" + this.cidade;
	}
	//
	// Métodos de Validação
	//
	public static void validarBairro(String bairro) throws ModelException {
		if(bairro == null || bairro .length() == 0)
			throw new ModelException("O bairro não pode ser nulo!");
		if(bairro.length() > TAM_BAIRRO)
			throw new ModelException("O bairro deve ter até " + TAM_BAIRRO + " caracteres!");
		for(int i = 0; i < bairro.length(); i++) {
			char c = bairro.charAt(i);
			if(!Character.isAlphabetic(c) && !Character.isSpaceChar(c))
				throw new ModelException("O bairro tem um caracter inválido na posição " + i + ": " + c);	
		}
	}
	
	public static void validarCidade(String cidade) throws ModelException {
		if(cidade == null || cidade.length() == 0)
			throw new ModelException("A cidade não pode ser nula!");
		if(cidade.length() > TAM_CIDADE)
			throw new ModelException("A cidade deve ter até " + TAM_BAIRRO + " caracteres!");
		for(int i = 0; i < cidade.length(); i++) {
			char c = cidade.charAt(i);
			if(!Character.isAlphabetic(c) && !Character.isSpaceChar(c))
				throw new ModelException("A cidade tem um caracter inválido na posição " + i + ": " + c);	
		}
	}
	
	public static void validarNumero(int numero) throws ModelException {
		if(numero < 0 || numero > NUM_MAX_AGENCIA)
			throw new ModelException("O número da agência é inválido: " + numero);
	}
}
