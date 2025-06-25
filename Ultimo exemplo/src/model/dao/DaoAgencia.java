package model.dao;

import model.Agencia;

public class DaoAgencia {
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
	private static Agencia[] arrayDeElementos = new Agencia[TAM_INICIAL_ELEMENTOS];
	
	//
	// MÉTODOS
	// 
	public DaoAgencia() {
		super();
	}
	
	/**
	 * Inclui um novo Agencia no array de elementos do Dao
	 */
	public boolean incluir(Agencia novo) {
		// Não podemos adicionar se o parâmetro recebido for nulo
		if(novo == null)
			return false;
		// Se o array de elementos já estiver completo, 
		// Vamos criar um novo array maior
		int tamanho = DaoAgencia.arrayDeElementos.length;
		if(DaoAgencia.numElementos == tamanho) {
			// Criamos um array novo com tamanho maior
			Agencia[] novoArray = new Agencia[tamanho + FATOR_CRESCIMENTO];
			// copiamos os elementos do array antigo para o novo
			for(int i = 0; i < tamanho; i++)
				novoArray[i] = DaoAgencia.arrayDeElementos[i];
			// Determinamos que o array novo é o arrayDeElementos 
			DaoAgencia.arrayDeElementos = novoArray;
		}
		// Incluindo a nova Agencia no array de elementos do Dao
		DaoAgencia.arrayDeElementos[ DaoAgencia.numElementos ] = novo;
		// Incrementamos numElementos
		DaoAgencia.numElementos++;
		// retornamos true informando que incluímos a nova Agencia
		return true;		
	}
	
	
	/**
	 * Altera um Agencia no array de elementos do Dao. Não será preciso
	 * realizar nada específico, pois o objeto já deverá estar presente
	 * no array
	 */
	public boolean alterar(Agencia agAlterada) {
		if(this.posicaoDe(agAlterada) == NAO_ESTA_PRESENTE)
			return false;		
		return true;		
	}
	
	/**
	 * Informa a posição do objeto no arrayDeElementos. Se não estiver
	 * presente, vamos retornar -1 (NAO_ESTA_PRESENTE)
	 */
	public int posicaoDe(Agencia a) {
		for(int i = 0; i < DaoAgencia.numElementos; i++)
			if(DaoAgencia.arrayDeElementos[i] == a)
				return i;
		return -1;
	}
	
	/**
	 * Remove um objeto do arrayDeElementos, caso ele esteja presente  
	 */
	public boolean remover(Agencia ex) {
		int pos;
		// Varrendo o arrayDeElementos para sabermos em que posição
		// o objeto apontado por 'ex' está
		for(pos = 0; pos < DaoAgencia.numElementos; pos++) 
			if(DaoAgencia.arrayDeElementos[pos] == ex) 
				break;
		// Se pos é igual ao numElementos, é porque o objeto apontado
		// por 'ex' não está no arrayDeElementos, logo retornamos false
		if(pos == DaoAgencia.numElementos)
			return false;
		// Deslocando os elementos que estão à frente, uma posição para trás
		for(int i = pos; i < DaoAgencia.numElementos-1; i++)
			DaoAgencia.arrayDeElementos[i] = DaoAgencia.arrayDeElementos[i++];  
		// Colocando null na antiga última posição
		DaoAgencia.arrayDeElementos[numElementos - 1] = null;
		// Decrementando o número de elementos do Dao
		DaoAgencia.numElementos--;
		// retornamos true, informando que efetuamos a operação
		return true;
	}
	/**
	 * Retorna o Agencia cujo código foi passado por parâmetro. Caso não tenha, 
	 * retornamos null
	 */
	public Agencia obterAgenciaPeloNumero(int numero) {
		// Para cada Agencia presente dentro do array de elementos
		for(int i = 0; i < DaoAgencia.numElementos; i++) {
			int numDaAgencia = DaoAgencia.arrayDeElementos[i].getNumero();
			if(numDaAgencia == numero)
				return DaoAgencia.arrayDeElementos[i];
		}
		return null;
	}

	/**
	 * Retorna todos os objetos Agencia gerenciados pelo DAO
	 */
	public static Agencia[] obterTodos() {
		return DaoAgencia.arrayDeElementos;
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
	static void recuperarTodos(Agencia[] array) {
		// Determinando que o array recebido por parâmetro passa a ser
		// o arrayDeElementos do DAO
		DaoAgencia.arrayDeElementos = array;
		// Contando quantos objetos Agencia estão no array recebido
		for(numElementos = 0; numElementos < array.length; numElementos++)
			if(array[numElementos] == null)
				break;
	}
}
