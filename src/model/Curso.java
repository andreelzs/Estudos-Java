package model;

import java.io.Serializable;

public class Curso implements Serializable{
	//
	// CONSTANTES
	//
	public static int VALOR_MAX_CODIGO = 99;
	public static int TAM_NOME = 25;
	public static int VALOR_MIN_CARGA_HORARIA = 2000;
	public static int VALOR_MAX_CARGA_HORARIA = 8000;
	
	//
	// ATRIBUTOS
	//
	private int    codigo;
	private String nome;
	private int    cargaHoraria;
	
	//
	// MÉTODOS
	//
	/**	
	 * Método construtor de Departamento
	 */
	public Curso(int codigo, String nome, int cargaHoraria) throws ModelException {
		super();
		this.setCodigo(codigo);
		this.setNome(nome);
		this.setCargaHoraria(cargaHoraria);
	}

	public int getCodigo() {
		return this.codigo;
	}

	public void setCodigo(int codigo) throws ModelException {
		Curso.validarCodigo(codigo);
		this.codigo = codigo;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) throws ModelException {
		Curso.validarNome(nome);
		this.nome = nome;
	} 

	public int getCargaHoraria() {
		return this.cargaHoraria;
	}

	public void setCargaHoraria(int CargaHoraria) throws ModelException {
		Curso.validarCargaHoraria(CargaHoraria);
		this.cargaHoraria = CargaHoraria;
	} 

	@Override
	public String toString() {
		return this.codigo + "-" + this.nome;
	}
	
	public static void validarCodigo(int codigo) throws ModelException {
		if(codigo < 0 || codigo > VALOR_MAX_CODIGO)
			throw new ModelException("O código tem um valor inválido: " + codigo);
	}
	
	public static void validarNome(String nome) throws ModelException {
		if(nome == null || nome.length() == 0)
			throw new ModelException("O nome não pode ser nulo!"); 
		if(nome.length() > TAM_NOME)
			throw new ModelException("O nome deve ter até " + TAM_NOME + " caracteres!"); 
		for(int i = 0; i < nome.length(); i++) {
			char c = nome.charAt(i);
			if( !Character.isAlphabetic(c) && !Character.isSpaceChar(c) )
				throw new ModelException("Na posição " + i + " do nome há um caracter inválido: " + c);
		}
	}	

	public static void validarCargaHoraria(int cargaHoraria) throws ModelException {
		if(cargaHoraria < VALOR_MIN_CARGA_HORARIA || cargaHoraria > VALOR_MAX_CARGA_HORARIA)
			throw new ModelException("A carga horária tem um valor inválido: " + cargaHoraria);		
	}	
}
