package model.dao;

import model.Conta;

public class DaoConta {
    final public static int TAM_INICIAL_ELEMENTOS = 5;
    final public static int FATOR_CRESCIMENTO = 3;
    final public static int NAO_ESTA_PRESENTE = -1;

    private static int numElementos = 0;
    private static Conta[] arrayDeElementos = new Conta[TAM_INICIAL_ELEMENTOS];

    public DaoConta() {
        super();
    }

    public boolean incluir(Conta novo) {
        if (novo == null)
            return false;
        if (this.posicaoDe(novo.getNumero()) != NAO_ESTA_PRESENTE)
            return false; // Não permite incluir contas com mesmo número

        if (DaoConta.numElementos == DaoConta.arrayDeElementos.length) {
            Conta[] novoArray = new Conta[DaoConta.arrayDeElementos.length + FATOR_CRESCIMENTO];
            for (int i = 0; i < DaoConta.numElementos; i++)
                novoArray[i] = DaoConta.arrayDeElementos[i];
            DaoConta.arrayDeElementos = novoArray;
        }
        DaoConta.arrayDeElementos[DaoConta.numElementos] = novo;
        DaoConta.numElementos++;
        return true;
    }

    public boolean alterar(Conta contaAlterada) {
        int pos = posicaoDe(contaAlterada.getNumero());
        if (pos == NAO_ESTA_PRESENTE)
            return false;
        arrayDeElementos[pos] = contaAlterada;
        return true;
    }

    public boolean remover(Conta ex) {
        int pos = posicaoDe(ex.getNumero());
        if (pos == NAO_ESTA_PRESENTE)
            return false;
        for (int i = pos; i < DaoConta.numElementos - 1; i++) {
            DaoConta.arrayDeElementos[i] = DaoConta.arrayDeElementos[i + 1];
        }
        DaoConta.arrayDeElementos[DaoConta.numElementos - 1] = null;
        DaoConta.numElementos--;
        return true;
    }

    public int posicaoDe(int numero) {
        for (int i = 0; i < DaoConta.numElementos; i++) {
            if (DaoConta.arrayDeElementos[i] != null && DaoConta.arrayDeElementos[i].getNumero() == numero) {
                return i;
            }
        }
        return NAO_ESTA_PRESENTE;
    }
    
    public Conta obterContaPelaChavePix(String chavePix) {
        for (int i = 0; i < DaoConta.numElementos; i++) {
            if (arrayDeElementos[i] != null && arrayDeElementos[i].getChavePix().equals(chavePix)) {
                return arrayDeElementos[i];
            }
        }
        return null;
    }

    public static Conta[] obterTodos() {
        return DaoConta.arrayDeElementos;
    }

    static void recuperarTodos(Conta[] array) {
        DaoConta.arrayDeElementos = array;
        for (numElementos = 0; numElementos < array.length; numElementos++)
            if (array[numElementos] == null)
                break;
    }
}