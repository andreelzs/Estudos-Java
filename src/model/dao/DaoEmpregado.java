package model.dao;

import model.Departamento;
import model.Empregado;
import model.Empregado;

public class DaoEmpregado {
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
	private static Empregado[] arrayDeElementos = new Empregado[TAM_INICIAL_ELEMENTOS];
	
	//
	// MÉTODOS
	// 
	public DaoEmpregado() {
		super();
	}
	
	/**
	 * Inclui um novo Empregado no array de elementos do Dao
	 */
	public boolean incluir(Empregado novo) {
		// Não podemos adicionar se o parâmetro recebido for nulo
		if(novo == null)
			return false;
		// Se o array de elementos já estiver completo, 
		// Vamos criar um novo array maior
		int tamanho = DaoEmpregado.arrayDeElementos.length;
		if(DaoEmpregado.numElementos == tamanho) {
			// Criamos um array novo com tamanho maior
			Empregado[] novoArray = new Empregado[tamanho + FATOR_CRESCIMENTO];
			// copiamos os elementos do array antigo para o novo
			for(int i = 0; i < tamanho; i++)
				novoArray[i] = DaoEmpregado.arrayDeElementos[i];
			// Determinamos que o array novo é o arrayDeElementos 
			DaoEmpregado.arrayDeElementos = novoArray;
		}
		// Incluindo o novo Empregado no array de elementos do Dao
		DaoEmpregado.arrayDeElementos[ DaoEmpregado.numElementos ] = novo;
		// Incrementamos numElementos
		DaoEmpregado.numElementos++;
		// retornamos true informando que incluímos o novo Empregado
		return true;		
	}
	
	
	/**
	 * Altera um Empregado no array de elementos do Dao. Não será preciso
	 * realizar nada específico, pois o objeto já deverá estar presente
	 * no array
	 */
	public boolean alterar(Empregado empAlterado) {
		if(this.posicaoDe(empAlterado) == NAO_ESTA_PRESENTE)
			return false;		
		return true;		
	}
	
	/**
	 * Informa a posição do objeto no arrayDeElementos. Se não estiver
	 * presente, vamos retornar -1 (NAO_ESTA_PRESENTE)
	 */
	public int posicaoDe(Empregado e) {
		for(int i = 0; i < DaoEmpregado.numElementos; i++)
			if(DaoEmpregado.arrayDeElementos[i] == e)
				return i;
		return -1;
	}
	
	/**
	 * Remove um objeto do arrayDeElementos, caso ele esteja presente  
	 */
	public boolean remover(Empregado ex) {
		int pos;
		// Varrendo o arrayDeElementos para sabermos em que posição
		// o objeto apontado por 'ex' está
		for(pos = 0; pos < DaoEmpregado.numElementos; pos++) 
			if(DaoEmpregado.arrayDeElementos[pos] == ex) 
				break;
		// Se pos é igual ao numElementos, é porque o objeto apontado
		// por 'ex' não está no arrayDeElementos, logo retornamos false
		if(pos == DaoEmpregado.numElementos)
			return false;
		// Deslocando os elementos que estão à frente, uma posição para trás
		for(int i = pos; i < DaoEmpregado.numElementos-1; i++)
			DaoEmpregado.arrayDeElementos[i] = DaoEmpregado.arrayDeElementos[i++];  
		// Colocando null na antiga última posição
		DaoEmpregado.arrayDeElementos[numElementos - 1] = null;
		// Decrementando o número de elementos do Dao
		DaoEmpregado.numElementos--;
		// retornamos true, informando que efetuamos a operação
		return true;
	}
	/**
	 * Retorna o Empregado cujo código foi passado por parâmetro. Caso não tenha, 
	 * retornamos null
	 */
	public Empregado obterEmpregadoPelaMatrFunc(int matrFunc) {
		// Para cada Empregado presente dentro do array de elementos
		for(int i = 0; i < DaoEmpregado.numElementos; i++) {
			int matrFuncDoEmpregado = DaoEmpregado.arrayDeElementos[i].getMatrFuncional();
			if(matrFuncDoEmpregado == matrFunc)
				return DaoEmpregado.arrayDeElementos[i];
		}
		return null;
	}
	/**
	 * Retorna o Empregado cujo nome foi passado por parâmetro. Caso não tenha, 
	 * retornamos null
	 */
	public Empregado obterEmpregadoPeloNome(String nome) {
		// Para cada Empregado presente dentro do array de elementos
		for(int i = 0; i < DaoEmpregado.numElementos; i++) {
			String nomeDoEmpregado = DaoEmpregado.arrayDeElementos[i].getNome();
			if(nomeDoEmpregado.equals(nome))
				return DaoEmpregado.arrayDeElementos[i];
		}
		return null;
	}
	
	/**
	 * Retorna todos os objetos Empregado gerenciados pelo DAO
	 */
	public static Empregado[] obterTodos() {
		return DaoEmpregado.arrayDeElementos;
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
	static void recuperarTodos(Empregado[] array) {
		// Determinando que o array recebido por parâmetro passa a ser
		// o arrayDeElementos do DAO
		DaoEmpregado.arrayDeElementos = array;
		// Contando quantos objetos Empregado estão no array recebido
		for(numElementos = 0; numElementos < array.length; numElementos++)
			if(array[numElementos] == null)
				break;
	}
}
