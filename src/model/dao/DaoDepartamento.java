package model.dao;

import model.Aluno;
import model.Departamento;
import model.Departamento;
import model.Empregado;

public class DaoDepartamento {
	//
	// CONSTANTES
	//
	final public static int TAM_INICIAL_ELEMENTOS = 5;
	final public static int FATOR_CRESCIMENTO = 3;
	final public static int NAO_ESTA_PRESENTE = -1;
	
	//
	// ATRIBUTOS
	//
	private static int numElementos = 0;
	private static Departamento[] arrayDeElementos = new Departamento[TAM_INICIAL_ELEMENTOS];
	
	//
	// MÉTODOS
	// 
	public DaoDepartamento() {
		super();
	}
	
	/**
	 * Inclui um novo Departamento no array de elementos do Dao
	 */
	public boolean incluir(Departamento novo) {
		// Não podemos adicionar se o parâmetro recebido for nulo
		if(novo == null)
			return false;
		// Se o array de elementos já estiver completo, 
		// Vamos criar um novo array maior
		int tamanho = DaoDepartamento.arrayDeElementos.length;
		if(DaoDepartamento.numElementos == tamanho) {
			// Criamos um array novo com tamanho maior
			Departamento[] novoArray = new Departamento[tamanho + FATOR_CRESCIMENTO];
			// copiamos os elementos do array antigo para o novo
			for(int i = 0; i < tamanho; i++)
				novoArray[i] = DaoDepartamento.arrayDeElementos[i];
			// Determinamos que o array novo é o arrayDeElementos 
			DaoDepartamento.arrayDeElementos = novoArray;
		}
		// Incluindo o novo Departamento no array de elementos do Dao
		DaoDepartamento.arrayDeElementos[ DaoDepartamento.numElementos ] = novo;
		// Incrementamos numElementos
		DaoDepartamento.numElementos++;
		// retornamos true informando que incluímos o novo Departamento
		return true;		
	}
	
	/**
	 * Altera um Departamento no array de elementos do Dao. Não será preciso
	 * realizar nada específico, pois o objeto já deverá estar presente
	 * no array
	 */
	public boolean alterar(Departamento deptoAlterado) {
		if(this.posicaoDe(deptoAlterado) == NAO_ESTA_PRESENTE)
			return false;		
		return true;		
	}
	
	/**
	 * Informa a posição do objeto no arrayDeElementos. Se não estiver
	 * presente, vamos retornar -1 (NAO_ESTA_PRESENTE)
	 */
	public int posicaoDe(Departamento d) {
		for(int i = 0; i < DaoDepartamento.numElementos; i++)
			if(DaoDepartamento.arrayDeElementos[i] == d)
				return i;
		return -1;
	}
	
	/**
	 * Remove um objeto do arrayDeElementos, caso ele esteja presente  
	 */
	public boolean remover(Departamento ex) {
		int pos;
		// Varrendo o arrayDeElementos para sabermos em que posição
		// o objeto apontado por 'ex' está
		for(pos = 0; pos < DaoDepartamento.numElementos; pos++) 
			if(DaoDepartamento.arrayDeElementos[pos] == ex) 
				break;
		// Se pos é igual ao numElementos, é porque o objeto apontado
		// por 'ex' não está no arrayDeElementos, logo retornamos false
		if(pos == DaoDepartamento.numElementos)
			return false;
		// Deslocando os elementos que estão à frente, uma posição para trás
		for(int i = pos; i < DaoDepartamento.numElementos-1; i++)
			DaoDepartamento.arrayDeElementos[i] = DaoDepartamento.arrayDeElementos[i++];  
		// Colocando null na antiga última posição
		DaoDepartamento.arrayDeElementos[numElementos - 1] = null;
		// Decrementando o número de elementos do Dao
		DaoDepartamento.numElementos--;
		// retornamos true, informando que efetuamos a operação
		return true;
	}

	/**
	 * Retorna o Departamento cujo código foi passado por parâmetro. Caso não tenha, 
	 * retornamos null
	 */
	public Departamento obterDepartamentoPelaSigla(String sigla) {
		// Para cada Departamento presente dentro do array de elementos
		for(int i = 0; i < DaoDepartamento.numElementos; i++) {
			String siglaDoDepartamento = DaoDepartamento.arrayDeElementos[i].getSigla();
			if(siglaDoDepartamento.equals(sigla))
				return DaoDepartamento.arrayDeElementos[i];
		}
		return null;
	}
	/**
	 * Retorna o Departamento cujo nome foi passado por parâmetro. Caso não tenha, 
	 * retornamos null
	 */
	public Departamento obterDepartamentoPeloNome(String nome) {
		// Para cada Departamento presente dentro do array de elementos
		for(int i = 0; i < DaoDepartamento.numElementos; i++) {
			String nomeDoDepartamento = DaoDepartamento.arrayDeElementos[i].getNome();
			if(nomeDoDepartamento.equals(nome))
				return DaoDepartamento.arrayDeElementos[i];
		}
		return null;
	}
	
	/**
	 * Retorna todos os objetos Departamento gerenciados pelo DAO
	 */
	public static Departamento[] obterTodos() {
		return DaoDepartamento.arrayDeElementos;
	}
	
	/**
	 * Método que determina o novo arrayDeElementos a ser gerenciado
	 * pelo DAO. Observe que não há indicação de visibilidade (public,
	 * private ou protected). Isso em Java indica que a visibilidade é
	 * "package"; ou seja, somente as classes que pertencem ao mesmo 
	 * pacote sabem da existência deste método. Como somente a classe
	 * Serializador vai usar (e ela faz parte de model.dao), optamos
	 * por deixar a visibilidade package.
	 */
	static void recuperarTodos(Departamento[] array) {
		// Determinando que o array recebido por parâmetro passa a ser
		// o arrayDeElementos do DAO
		DaoDepartamento.arrayDeElementos = array;
		// Contando quantos objetos Empregado estão no array recebido
		for(numElementos = 0; numElementos < array.length; numElementos++)
			if(array[numElementos] == null)
				break;
	}
}
