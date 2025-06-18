package model.dao;

import model.Agencia;

public class DaoAgencia {
    final public static int TAM_INICIAL_ELEMENTOS = 5;
    final public static int FATOR_CRESCIMENTO = 3;
    final public static int NAO_ESTA_PRESENTE = -1;

    private static int numElementos = 0;
    private static Agencia[] arrayDeElementos = new Agencia[TAM_INICIAL_ELEMENTOS];

    public DaoAgencia() {
        super();
    }

    public boolean incluir(Agencia novo) {
        if (novo == null)
            return false;
        if (this.posicaoDe(novo.getNumero()) != NAO_ESTA_PRESENTE)
            return false; // Não permite incluir agências com mesmo número

        if (DaoAgencia.numElementos == DaoAgencia.arrayDeElementos.length) {
            Agencia[] novoArray = new Agencia[DaoAgencia.arrayDeElementos.length + FATOR_CRESCIMENTO];
            for (int i = 0; i < DaoAgencia.numElementos; i++)
                novoArray[i] = DaoAgencia.arrayDeElementos[i];
            DaoAgencia.arrayDeElementos = novoArray;
        }
        DaoAgencia.arrayDeElementos[DaoAgencia.numElementos] = novo;
        DaoAgencia.numElementos++;
        return true;
    }

    public boolean alterar(Agencia agenciaAlterada) {
        int pos = posicaoDe(agenciaAlterada.getNumero());
        if (pos == NAO_ESTA_PRESENTE)
            return false;
        arrayDeElementos[pos] = agenciaAlterada;
        return true;
    }

    public boolean remover(Agencia ex) {
        int pos = posicaoDe(ex.getNumero());
        if (pos == NAO_ESTA_PRESENTE)
            return false;
        for (int i = pos; i < DaoAgencia.numElementos - 1; i++) {
            DaoAgencia.arrayDeElementos[i] = DaoAgencia.arrayDeElementos[i + 1];
        }
        DaoAgencia.arrayDeElementos[DaoAgencia.numElementos - 1] = null;
        DaoAgencia.numElementos--;
        return true;
    }

    public int posicaoDe(int numero) {
        for (int i = 0; i < DaoAgencia.numElementos; i++) {
            if (DaoAgencia.arrayDeElementos[i] != null && DaoAgencia.arrayDeElementos[i].getNumero() == numero) {
                return i;
            }
        }
        return NAO_ESTA_PRESENTE;
    }

    public static Agencia[] obterTodos() {
        return DaoAgencia.arrayDeElementos;
    }

    static void recuperarTodos(Agencia[] array) {
        DaoAgencia.arrayDeElementos = array;
        for (numElementos = 0; numElementos < array.length; numElementos++)
            if (array[numElementos] == null)
                break;
    }
