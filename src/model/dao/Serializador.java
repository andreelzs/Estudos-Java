package model.dao;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JOptionPane;
import model.Agencia;
import model.Aluno;
import model.Conta;
import model.Curso;
import model.Departamento;
import model.Empregado;

public class Serializador {

    public static void salvarObjetos() {
        try (FileOutputStream fos = new FileOutputStream("objetos.dat");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            oos.writeObject(DaoAluno.obterTodos());
            oos.writeObject(DaoCurso.obterTodos());
            oos.writeObject(DaoDepartamento.obterTodos());
            oos.writeObject(DaoEmpregado.obterTodos());
            oos.writeObject(DaoAgencia.obterTodos());
            oos.writeObject(DaoConta.obterTodos());

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Problema no salvamento dos objetos: " + e.getMessage());
        }
    }

    public static void recuperarObjetos() {
        try (FileInputStream fis = new FileInputStream("objetos.dat");
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            DaoAluno.recuperarTodos((Aluno[]) ois.readObject());
            DaoCurso.recuperarTodos((Curso[]) ois.readObject());
            DaoDepartamento.recuperarTodos((Departamento[]) ois.readObject());
            DaoEmpregado.recuperarTodos((Empregado[]) ois.readObject());
            DaoAgencia.recuperarTodos((Agencia[]) ois.readObject());
            DaoConta.recuperarTodos((Conta[]) ois.readObject());

        } catch (IOException | ClassNotFoundException e) {
            // Se o arquivo não existe ou está corrompido, inicia com dados vazios.
            // Nenhuma ação é necessária aqui.
        }
    }
}