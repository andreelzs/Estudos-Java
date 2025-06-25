package model.dao;

import model.Conta;

public class DaoConta {
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
	private static Conta[] arrayDeElementos = new Conta[TAM_INICIAL_ELEMENTOS];
	
	//
	// MÉTODOS
	// 
	public DaoConta() {
		super();
	}
	
	/**
	 * Inclui um novo Conta no array de elementos do Dao
	 */
	public boolean incluir(Conta nova) {
		// Não podemos adicionar se o parâmetro recebido for nulo
		if(nova == null)
			return false;
		// Se o array de elementos já estiver completo, 
		// Vamos criar um novo array maior
		int tamanho = DaoConta.arrayDeElementos.length;
		if(DaoConta.numElementos == tamanho) {
			// Criamos um array novo com tamanho maior
			Conta[] novoArray = new Conta[tamanho + FATOR_CRESCIMENTO];
			// copiamos os elementos do array antigo para o novo
			for(int i = 0; i < tamanho; i++)
				novoArray[i] = DaoConta.arrayDeElementos[i];
			// Determinamos que o array novo é o arrayDeElementos 
			DaoConta.arrayDeElementos = novoArray;
		}
		// Incluindo a nova Conta no array de elementos do Dao
		DaoConta.arrayDeElementos[ DaoConta.numElementos ] = nova;
		// Incrementamos numElementos
		DaoConta.numElementos++;
		// retornamos true informando que incluímos a nova Conta
		return true;		
	}
	
	
	/**
	 * Altera uma Conta no array de elementos do Dao. Não será preciso
	 * realizar nada específico, pois o objeto já deverá estar presente
	 * no array
	 */
	public boolean alterar(Conta contaAlterada) {
		if(this.posicaoDe(contaAlterada) == NAO_ESTA_PRESENTE)
			return false;		
		return true;		
	}
	
	/**
	 * Informa a posição do objeto no arrayDeElementos. Se não estiver
	 * presente, vamos retornar -1 (NAO_ESTA_PRESENTE)
	 */
	public int posicaoDe(Conta c) {
		for(int i = 0; i < DaoConta.numElementos; i++)
			if(DaoConta.arrayDeElementos[i] == c)
				return i;
		return -1;
	}
	
	/**
	 * Remove um objeto do arrayDeElementos, caso ele esteja presente  
	 */
	public boolean remover(Conta ex) {
		int pos;
		// Varrendo o arrayDeElementos para sabermos em que posição
		// o objeto apontado por 'ex' está
		for(pos = 0; pos < DaoConta.numElementos; pos++) 
			if(DaoConta.arrayDeElementos[pos] == ex) 
				break;
		// Se pos é igual ao numElementos, é porque o objeto apontado
		// por 'ex' não está no arrayDeElementos, logo retornamos false
		if(pos == DaoConta.numElementos)
			return false;
		// Deslocando os elementos que estão à frente, uma posição para trás
		for(int i = pos; i < DaoConta.numElementos-1; i++)
			DaoConta.arrayDeElementos[i] = DaoConta.arrayDeElementos[i++];  
		// Colocando null na antiga última posição
		DaoConta.arrayDeElementos[numElementos - 1] = null;
		// Decrementando o número de elementos do Dao
		DaoConta.numElementos--;
		// retornamos true, informando que efetuamos a operação
		return true;
	}
	
	/**
	 * Retorna o Conta cujo código foi passado por parâmetro. Caso não tenha, 
	 * retornamos null
	 */
	public Conta obterContaPeloNumero(int numero) {
		// Para cada Conta presente dentro do array de elementos
		for(int i = 0; i < DaoConta.numElementos; i++) {
			int numDaConta = DaoConta.arrayDeElementos[i].getNumero();
			if(numDaConta == numero)
				return DaoConta.arrayDeElementos[i];
		}
		return null;
	}

	/**
	 * Retorna todos os objetos Conta gerenciados pelo DAO
	 */
	public static Conta[] obterTodos() {
		return DaoConta.arrayDeElementos;
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
	static void recuperarTodos(Conta[] array) {
		// Determinando que o array recebido por parâmetro passa a ser
		// o arrayDeElementos do DAO
		DaoConta.arrayDeElementos = array;
		// Contando quantos objetos Conta estão no array recebido
		for(numElementos = 0; numElementos < array.length; numElementos++)
			if(array[numElementos] == null)
				break;
	}
}
