package model;

import java.io.Serializable;
import java.util.Set;

public class Conta implements Serializable {
	//
	// CONSTANTES
	//
	final public static int VALOR_MIN_NUM_CONTA = 1000;
	final public static int VALOR_MAX_NUM_CONTA = 99999;
	final public static double VALOR_MAX_LIMITE = 30000.00;
	final public static int TAM_MAX_NOME_CORRENTISTA = 40;
	final public static int TAM_MAX_CHAVE_PIX = 20;
	//
	// ATRIBUTOS
	//
	private int numero;
	private String nomeCorrentista;
	private String chavePix;
	private double saldo;
	private double limite;

	//
	// ATRIBUTO DE RELACIONAMENTO
	//
	private Agencia agencia;

	//
	// MÉTODOS
	//
	public Conta(int numero, String nomeCorrentista, String chavePix,
			     double saldo, double limite, Agencia agencia) throws ModelException {
		super();
		this.setNumero(numero);
		this.setNomeCorrentista(nomeCorrentista);
		this.setChavePix(chavePix);
		this.setSaldo(saldo);
		this.setLimite(limite);
		this.setAgencia(agencia);
	}

	public int getNumero() {
		return this.numero;
	}

	public void setNumero(int num) throws ModelException {
		Conta.validarNumero(num);
		this.numero = num;
	}

	public String getNomeCorrentista() {
		return this.nomeCorrentista;
	}

	public void setNomeCorrentista(String nomeCorrentista) throws ModelException {
		Conta.validarNomeCorrentista(nomeCorrentista);
		this.nomeCorrentista = nomeCorrentista;
	}

	public String getChavePix() {
		return this.chavePix;
	}

	public void setChavePix(String chavePix) throws ModelException {
		Conta.validarChavePix(chavePix);
		this.chavePix = chavePix;
	}

	public double getLimite() {
		return this.limite;
	}

	public void setLimite(double limite) throws ModelException {
		Conta.validarLimite(limite);
		this.limite = limite;
	}

	public double getSaldo() {
		return this.saldo;
	}

	public void setSaldo(double saldo) throws ModelException {
		Conta.validarSaldo(saldo, this.limite);
		this.saldo = saldo;
	}

	public Agencia getAgencia() {
		return this.agencia;
	}

	public void setAgencia(Agencia agencia) throws ModelException {
		Conta.validarAgencia(agencia);
		this.agencia = agencia;
	}

	public String toString() {
		return this.numero + " - Agência:" + this.agencia.getBairro();
	}

	//
	// Métodos de Validação
	//
	public static void validarNumero(int numero) throws ModelException {
		if (numero < VALOR_MIN_NUM_CONTA || numero > VALOR_MAX_NUM_CONTA)
			throw new ModelException("Número de Conta Inválido: " + numero);
	}
	
	public static void validarNomeCorrentista(String nome) throws ModelException {
		if(nome == null || nome.length() == 0)
			throw new ModelException("O nome não pode ser nulo!");
		if(nome.length() > TAM_MAX_NOME_CORRENTISTA)
			throw new ModelException("O nome deve ter até " + TAM_MAX_NOME_CORRENTISTA + " caracteres!");
		for(int i = 0; i < nome.length(); i++) {
			char c = nome.charAt(i);
			if(!Character.isAlphabetic(c) && !Character.isSpaceChar(c))
				throw new ModelException("O nome tem um caracter inválido na posição " + i + ": " + c);	
		}
	}
	
	public static void validarChavePix(String chavePix) throws ModelException {
		if(chavePix == null || chavePix.length() == 0)
			throw new ModelException("A chave do pix não pode ser nula!");
		if(chavePix.length() > TAM_MAX_CHAVE_PIX)
			throw new ModelException("A chave do pix deve ter até " + TAM_MAX_CHAVE_PIX + " caracteres!");
		for(int i = 0; i < chavePix.length(); i++) {
			char c = chavePix.charAt(i);
			if(!Character.isAlphabetic(c) && !Character.isSpaceChar(c) && !Character.isDigit(c) &&
				c != '.' && c != '-' && c != '@')
				throw new ModelException("A chave do pix tem um caracter inválido na posição " + i + ": " + c);	
		}
	}
	
	public static void validarLimite(double limite) throws ModelException {
		if (limite < 0 || limite > VALOR_MAX_LIMITE)
			throw new ModelException("Valor Inválido para o limite: " + limite);
	}

	public static void validarSaldo(double saldo, double limite) throws ModelException {
		if (saldo < -VALOR_MAX_LIMITE)
			throw new ModelException(
					"O saldo (" + saldo + ") não pode ser menor que valor do limite da conta: " + limite);
	}

	public static void validarConjCorrentistas(Set<Pessoa> conjCorrentistas) throws ModelException {
		if (conjCorrentistas == null || conjCorrentistas.size() == 0)
			throw new ModelException("É obrigatório indicar quem é os correntistas da conta");
	}

	public static void validarAgencia(Agencia agencia) throws ModelException {
		if (agencia == null)
			throw new ModelException("É obrigatório indicar qual é a agência da conta");
	}
}
