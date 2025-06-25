package model;

import java.io.Serializable;

public class Empregado extends Pessoa implements Serializable{
	//
	// CONSTANTE
	//
	final public static int NUM_MAX_MATR = 9999;
	
	//
	// ATRIBUTOS
	//
	private int matrFuncional;
	
	//
	// ATRIBUTOS DE RELACIONAMENTO
	//
	private Departamento depto;
	
	//
	// MÉTODOS
	//
	public Empregado(String cpf, String nome, int idade, int matrFuncional, Departamento depto) throws ModelException {
		super(cpf, nome, idade);
		this.setMatrFuncional(matrFuncional);
		this.setDepto(depto);		
	}

	public int getMatrFuncional() {
		return this.matrFuncional;
	}

	public void setMatrFuncional(int matrFuncional) throws ModelException {
		Empregado.validarMatrFuncional(matrFuncional);
		this.matrFuncional = matrFuncional;
	}

	public Departamento getDepto() {
		return this.depto;
	}

	public void setDepto(Departamento depto) throws ModelException {
		Empregado.validarDepto(depto);
		this.depto = depto;
	}
	
	public static void validarMatrFuncional(int matrFuncional) throws ModelException {
		if(matrFuncional <= 0 || matrFuncional > NUM_MAX_MATR)
			throw new ModelException("Valor inválido para a matrícula funcional: " + matrFuncional);
	}

	public static void validarDepto(Departamento depto) throws ModelException {
		if(depto == null)
			throw new ModelException("O Departamento do empregado não pode ser nulo!");
	}

	
	
}
