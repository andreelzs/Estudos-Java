package model.dao;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JOptionPane;

import model.Aluno;
import model.Curso;
import model.Departamento;
import model.Empregado;

public class Serializador {

	public static void salvarObjetos() {
		try {
			// Arquivo o arquivo "objetos.dat" para escrita
			FileOutputStream fos = new FileOutputStream("objetos.dat");
			// Instanciando um objeto de serialização vinculado ao arquivo
			ObjectOutputStream oos = new ObjectOutputStream(fos);

			// Para cada DAO presente em nosso projeto, recuperamos a
			// referência para o array que aponta para os objetos da classe
			// que ele gerencia o armazenamento
			oos.writeObject(DaoAluno.obterTodos());
			oos.writeObject(DaoCurso.obterTodos());
			oos.writeObject(DaoDepartamento.obterTodos());
			oos.writeObject(DaoEmpregado.obterTodos());

			// Efetiva a gravação do arquivo
			oos.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Problema no salvamento dos objetos: " + e.getMessage());
		}
	}

	public static void recuperarObjetos() {
		try {
			// Arquivo o arquivo "objetos.dat" para escrita
			FileInputStream fis = new FileInputStream("objetos.dat");
			// Instanciando um objeto de serialização vinculado ao arquivo
			ObjectInputStream ois = new ObjectInputStream(fis);

			// Para cada DAO presente em nosso projeto, recuperamos o 
			// array que contém os objetos salvos no arquivo. A ordem de 
			// leitura precisa ser exatamente a mesma com que os objetos 
			// foram salvos. 
			DaoAluno.recuperarTodos((Aluno[])ois.readObject());
			DaoCurso.recuperarTodos((Curso[])ois.readObject());
			DaoDepartamento.recuperarTodos((Departamento[])ois.readObject());
			DaoEmpregado.recuperarTodos((Empregado[])ois.readObject());

			// Fechando o arquivo
			ois.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Problema na recuperação dos objetos: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Problema na recuperação dos objetos: " + e.getMessage());
		}
	}

}
