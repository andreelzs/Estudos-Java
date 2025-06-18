package model;

import java.io.Serializable;
import model.dao.DaoConta;

public class Conta implements Serializable {
    private static final long serialVersionUID = 1L;
    private int numero;
    private String nomeCorrentista;
    private String chavePix;
    private double saldo;
    private double limiteCredito;
    private Agencia agencia;

    public Conta(int numero, String nomeCorrentista, String chavePix, double saldo, double limiteCredito, Agencia agencia) throws ModelException {
        this.setNumero(numero);
        this.setNomeCorrentista(nomeCorrentista);
        this.setChavePix(chavePix, true); // Validação de inclusão
        this.setSaldo(saldo);
        this.setLimiteCredito(limiteCredito);
        this.setAgencia(agencia);
    }

    public int getNumero() {
        return this.numero;
    }

    public void setNumero(int numero) throws ModelException {
        validarNumero(numero);
        this.numero = numero;
    }

    public String getNomeCorrentista() {
        return this.nomeCorrentista;
    }

    public void setNomeCorrentista(String nomeCorrentista) throws ModelException {
        validarNomeCorrentista(nomeCorrentista);
        this.nomeCorrentista = nomeCorrentista;
    }

    public String getChavePix() {
        return this.chavePix;
    }

    public void setChavePix(String chavePix, boolean isInclusao) throws ModelException {
        if (isInclusao) {
            validarChavePixInclusao(chavePix);
        } else {
            validarChavePixAlteracao(chavePix, this.getNumero());
        }
        this.chavePix = chavePix;
    }
    
    public double getSaldo() {
        return this.saldo;
    }

    public void setSaldo(double saldo) throws ModelException {
        this.saldo = saldo;
    }

    public double getLimiteCredito() {
        return this.limiteCredito;
    }

    public void setLimiteCredito(double limiteCredito) {
        this.limiteCredito = limiteCredito;
    }

    public Agencia getAgencia() {
        return this.agencia;
    }

    public void setAgencia(Agencia agencia) throws ModelException {
        validarAgencia(agencia);
        this.agencia = agencia;
    }

    @Override
    public String toString() {
        return this.getNumero() + " - " + this.getNomeCorrentista();
    }
    
    public static void validarNumero(int numero) throws ModelException {
        if (numero <= 0)
            throw new ModelException("O número da conta não pode ser negativo ou zero.");
    }
    
    public static void validarNomeCorrentista(String nome) throws ModelException {
        if (nome == null || nome.trim().isEmpty())
            throw new ModelException("O nome do correntista não pode ser nulo.");
    }

    public static void validarChavePixInclusao(String chavePix) throws ModelException {
        if (chavePix == null || chavePix.trim().isEmpty()) {
            throw new ModelException("A chave PIX não pode ser nula.");
        }
        DaoConta dao = new DaoConta();
        if (dao.obterContaPelaChavePix(chavePix) != null) {
            throw new ModelException("A chave PIX informada já está em uso.");
        }
    }
    
    public static void validarChavePixAlteracao(String chavePix, int numeroConta) throws ModelException {
        if (chavePix == null || chavePix.trim().isEmpty()) {
            throw new ModelException("A chave PIX não pode ser nula.");
        }
        DaoConta dao = new DaoConta();
        Conta contaExistente = dao.obterContaPelaChavePix(chavePix);
        if (contaExistente != null && contaExistente.getNumero() != numeroConta) {
            throw new ModelException("A chave PIX informada já está em uso por outra conta.");
        }
    }
    
    public static void validarAgencia(Agencia agencia) throws ModelException {
        if (agencia == null) {
            throw new ModelException("A agência não pode ser nula.");
        }
    }
}