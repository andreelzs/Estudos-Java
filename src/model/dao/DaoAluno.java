package model.dao;

import model.Aluno;
import model.Curso;
import model.Empregado;

public class DaoAluno {
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
	private static Aluno[] arrayDeElementos = new Aluno[TAM_INICIAL_ELEMENTOS];
	
	//
	// MÉTODOS
	// 
	public DaoAluno() {
		super();
	}
	
	/**
	 * Inclui um novo Aluno no array de elementos do Dao
	 */
	public boolean incluir(Aluno novo) {
		// Não podemos adicionar se o parâmetro recebido for nulo
		if(novo == null)
			return false;
		// Se o array de elementos já estiver completo, 
		// Vamos criar um novo array maior
		int tamanho = DaoAluno.arrayDeElementos.length;
		if(DaoAluno.numElementos == tamanho) {
			// Criamos um array novo com tamanho maior
			Aluno[] novoArray = new Aluno[tamanho + FATOR_CRESCIMENTO];
			// copiamos os elementos do array antigo para o novo
			for(int i = 0; i < tamanho; i++)
				novoArray[i] = DaoAluno.arrayDeElementos[i];
			// Determinamos que o array novo é o arrayDeElementos 
			DaoAluno.arrayDeElementos = novoArray;
		}
		// Incluindo o novo Aluno no array de elementos do Dao
		DaoAluno.arrayDeElementos[ DaoAluno.numElementos ] = novo;
		// Incrementamos numElementos
		DaoAluno.numElementos++;
		// retornamos true informando que incluímos o novo Aluno
		return true;		
	}

	/**
	 * Altera um Aluno no array de elementos do Dao. Não será preciso
	 * realizar nada específico, pois o objeto já deverá estar presente
	 * no array
	 */
	public boolean alterar(Aluno alunoAlterado) {
		if(this.posicaoDe(alunoAlterado) == NAO_ESTA_PRESENTE)
			return false;		
		return true;		
	}
	
	/**
	 * Informa a posição do objeto no arrayDeElementos. Se não estiver
	 * presente, vamos retornar -1 (NAO_ESTA_PRESENTE)
	 */
	public int posicaoDe(Aluno a) {
		for(int i = 0; i < DaoAluno.numElementos; i++)
			if(DaoAluno.arrayDeElementos[i] == a)
				return i;
		return -1;
	}
		
	/**
	 * Remove um objeto do arrayDeElementos, caso ele esteja presente  
	 */
	public boolean remover(Aluno ex) {
		int pos;
		// Varrendo o arrayDeElementos para sabermos em que posição
		// o objeto apontado por 'ex' está
		for(pos = 0; pos < DaoAluno.numElementos; pos++) 
			if(DaoAluno.arrayDeElementos[pos] == ex) 
				break;
		// Se pos é igual ao numElementos, é porque o objeto apontado
		// por 'ex' não está no arrayDeElementos, logo retornamos false
		if(pos == DaoAluno.numElementos)
			return false;
		// Deslocando os elementos que estão à frente, uma posição para trás
		for(int i = pos; i < DaoAluno.numElementos-1; i++)
			DaoAluno.arrayDeElementos[i] = DaoAluno.arrayDeElementos[i++];  
		// Colocando null na antiga última posição
		DaoAluno.arrayDeElementos[numElementos - 1] = null;
		// Decrementando o número de elementos do Dao
		DaoAluno.numElementos--;
		// retornamos true, informando que efetuamos a operação
		return true;		
	}
	
	/**
	 * Retorna o Aluno cujo código foi passado por parâmetro. Caso não tenha, 
	 * retornamos null
	 */
	public Aluno obterAlunoPelaMatricula(String matricula) {
		// Para cada Aluno presente dentro do array de elementos
		for(int i = 0; i < DaoAluno.numElementos; i++) {
			String matrDoAluno = DaoAluno.arrayDeElementos[i].getMatricula();
			if(matrDoAluno.equals(matricula))
				return DaoAluno.arrayDeElementos[i];
		}
		return null;
	}
	/**
	 * Retorna o Aluno cujo nome foi passado por parâmetro. Caso não tenha, 
	 * retornamos null
	 */
	public Aluno obterAlunoPeloNome(String nome) {
		// Para cada Aluno presente dentro do array de elementos
		for(int i = 0; i < DaoAluno.numElementos; i++) {
			String nomeDoAluno = DaoAluno.arrayDeElementos[i].getNome();
			if(nomeDoAluno.equals(nome))
				return DaoAluno.arrayDeElementos[i];
		}
		return null;
	}
	
	/**
	 * Retorna todos os objetos Aluno gerenciados pelo DAO
	 */
	public static Aluno[] obterTodos() {
		return DaoAluno.arrayDeElementos;
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
	static void recuperarTodos(Aluno[] array) {
		// Determinando que o array recebido por parâmetro passa a ser
		// o arrayDeElementos do DAO
		DaoAluno.arrayDeElementos = array;
		// Contando quantos objetos Empregado estão no array recebido
		for(numElementos = 0; numElementos < array.length; numElementos++)
			if(array[numElementos] == null)
				break;
	}

}
