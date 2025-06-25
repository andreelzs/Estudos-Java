package model.dao;

import model.Curso;
import model.Curso;
import model.Empregado;

public class DaoCurso {
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
	private static Curso[] arrayDeElementos = new Curso[TAM_INICIAL_ELEMENTOS];
	
	//
	// MÉTODOS
	// 
	public DaoCurso() {
		super();
	}
	
	/**
	 * Inclui um novo Curso no array de elementos do Dao
	 */
	public boolean incluir(Curso novo) {
		// Não podemos adicionar se o parâmetro recebido for nulo
		if(novo == null)
			return false;
		// Se o array de elementos já estiver completo, 
		// Vamos criar um novo array maior
		int tamanho = DaoCurso.arrayDeElementos.length;
		if(DaoCurso.numElementos == tamanho) {
			// Criamos um array novo com tamanho maior
			Curso[] novoArray = new Curso[tamanho + FATOR_CRESCIMENTO];
			// copiamos os elementos do array antigo para o novo
			for(int i = 0; i < tamanho; i++)
				novoArray[i] = DaoCurso.arrayDeElementos[i];
			// Determinamos que o array novo é o arrayDeElementos 
			DaoCurso.arrayDeElementos = novoArray;
		}
		// Incluindo o novo Curso no array de elementos do Dao
		DaoCurso.arrayDeElementos[ DaoCurso.numElementos ] = novo;
		// Incrementamos numElementos
		DaoCurso.numElementos++;
		// retornamos true informando que incluímos o novo Curso
		return true;		
	}
	
	/**
	 * Altera um Curso no array de elementos do Dao. Não será preciso
	 * realizar nada específico, pois o objeto já deverá estar presente
	 * no array
	 */
	public boolean alterar(Curso cursoAlterado) {
		if(this.posicaoDe(cursoAlterado) == NAO_ESTA_PRESENTE)
			return false;		
		return true;		
	}
	
	/**
	 * Remove um objeto do arrayDeElementos, caso ele esteja presente  
	 */
	public boolean remover(Curso ex) {
		int pos;
		// Varrendo o arrayDeElementos para sabermos em que posição
		// o objeto apontado por 'ex' está
		for(pos = 0; pos < DaoCurso.numElementos; pos++) 
			if(DaoCurso.arrayDeElementos[pos] == ex) 
				break;
		// Se pos é igual ao numElementos, é porque o objeto apontado
		// por 'ex' não está no arrayDeElementos, logo retornamos false
		if(pos == DaoCurso.numElementos)
			return false;
		// Deslocando os elementos que estão à frente, uma posição para trás
		for(int i = pos; i < DaoCurso.numElementos-1; i++)
			DaoCurso.arrayDeElementos[i] = DaoCurso.arrayDeElementos[i++];  
		// Colocando null na antiga última posição
		DaoCurso.arrayDeElementos[numElementos - 1] = null;
		// Decrementando o número de elementos do Dao
		DaoCurso.numElementos--;
		// retornamos true, informando que efetuamos a operação
		return true;
	}

	/**
	 * Retorna o curso cujo código foi passado por parâmetro. Caso não tenha, 
	 * retornamos null
	 */
	public Curso obterCursoPeloCodigo(int codigo) {
		// Para cada curso presente dentro do array de elementos
		for(int i = 0; i < DaoCurso.numElementos; i++) {
			int codigoDoCurso = DaoCurso.arrayDeElementos[i].getCodigo();
			if(codigoDoCurso == codigo)
				return DaoCurso.arrayDeElementos[i];
		}
		return null;
	}
	/**
	 * Retorna o curso cujo nome foi passado por parâmetro. Caso não tenha, 
	 * retornamos null
	 */
	public Curso obterCursoPeloNome(String nome) {
		// Para cada curso presente dentro do array de elementos
		for(int i = 0; i < DaoCurso.numElementos; i++) {
			String nomeDoCurso = DaoCurso.arrayDeElementos[i].getNome();
			if(nomeDoCurso.equals(nome))
				return DaoCurso.arrayDeElementos[i];
		}
		return null;
	}
	
	/**
	 * Informa a posição do objeto no arrayDeElementos. Se não estiver
	 * presente, vamos retornar -1 (NAO_ESTA_PRESENTE)
	 */
	public int posicaoDe(Curso c) {
		for(int i = 0; i < DaoCurso.numElementos; i++)
			if(DaoCurso.arrayDeElementos[i] == c)
				return i;
		return -1;
	}
	
	/**
	 * Retorna todos os objetos Curso gerenciados pelo DAO
	 */
	public static Curso[] obterTodos() {
		return DaoCurso.arrayDeElementos;
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
	static void recuperarTodos(Curso[] array) {
		// Determinando que o array recebido por parâmetro passa a ser
		// o arrayDeElementos do DAO
		DaoCurso.arrayDeElementos = array;
		// Contando quantos objetos Empregado estão no array recebido
		for(numElementos = 0; numElementos < array.length; numElementos++)
			if(array[numElementos] == null)
				break;
	}
}
