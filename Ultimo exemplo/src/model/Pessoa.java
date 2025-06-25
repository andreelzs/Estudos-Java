package model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Pessoa {
	//
	// CONSTANTES
	//
	final public static int TAM_CPF         = 14;
	final public static int TAM_MAXIMO_NOME = 40;
	final public static int IDADE_MAXIMA    = 150;
	
	// 
	// ATRIBUTOS
	//
	@Id @GeneratedValue
	private int    id;
	@Column(length=TAM_CPF,unique=true)
	private String cpf;
	@Column(length=TAM_MAXIMO_NOME)
	private String nome;
	@Column
	private int    idade;
		
	//
	// ATRIBUTOS DE RELACIONAMENTO
	//
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY) 
	@JoinTable(name = "pertence_a", 
	           joinColumns = @JoinColumn(name = "id_conta"),
			   inverseJoinColumns = @JoinColumn(name = "id_pessoa"))
	private Set<Conta> conjContas; 
	
	//
	// MÉTODOS
	//
	/**
	 * O JPA requer que toda classe "@Entity" tenha um construtor vazio!
	 */
	public Pessoa() {	
		System.out.println("Construtor de Pessoa com assinatura vazia foi chamado");
	}
	
	public Pessoa(String cpf, String nome, int idade) throws ModelException {
		super();
		this.setCpf(cpf);
		this.setNome(nome);
		this.setIdade(idade);
		this.setConjContas(new HashSet<Conta>());
	}

	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Como esse get se refere a um atributo de relacionamento e é uma 
	 * coleção, então sempre vamos passar uma cópia do atributo
	 */
	public Set<Conta> getConjContas() {
		// Vamos devolver uma cópia do conjContas
		return new HashSet<Conta>(this.conjContas);
	}
	
	public void setConjContas(Set<Conta> conjContas) throws ModelException {
		Pessoa.validarConjContas(conjContas);
		this.conjContas = conjContas;
	}
	
	public static void validarConjContas(Set<Conta> conjContas) throws ModelException {
		if(conjContas == null)
			throw new ModelException("O conjunto de contas não pode ser nulo!");
	}
	
	public void adicionarConta(Conta conta) throws ModelException{
		Pessoa.validarConta(conta);
		this.conjContas.add(conta);
	}
	
	public String getCpf() {
		return this.cpf;
	}

	public void setCpf(String cpf) throws ModelException {
		Pessoa.validarCpf(cpf);
		this.cpf = cpf;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) throws ModelException {
		Pessoa.validarNome(nome);
		this.nome = nome;
	}

	public int getIdade() {
		return this.idade;
	}

	public void setIdade(int idade) throws ModelException {
		Pessoa.validarIdade(idade);
		this.idade = idade;
	}

	public String toString() {
		return this.nome;
	}
	//
	// Métodos de Validação
	//
	public static void validarCpf(String cpf) throws ModelException {
		if(cpf == null || cpf.length() == 0)
			throw new ModelException("O CPF não pode ser nulo!");
		if(cpf.length() != TAM_CPF)
			throw new ModelException("O CPF deve ter " + TAM_CPF + " caracteres!");
	}
	
	public static void validarNome(String nome) throws ModelException {
		if(nome == null || nome.length() == 0)
			throw new ModelException("O nome da Pessoa não pode ser nulo!");
		if(nome.length() > TAM_MAXIMO_NOME)
			throw new ModelException("O nome da Pessoa deve ter até " + 
		                             TAM_MAXIMO_NOME + " caracteres!");
	}
	
	public static void validarIdade(int idade) throws ModelException {
		if(idade < 0 || idade > IDADE_MAXIMA)
			throw new ModelException("A idade indicada é inválida: " + idade);
	}

	public static void validarConta(Conta conta) throws ModelException {
		if(conta == null)
			throw new ModelException("A conta bancária não pode ser nula");
	}

}
