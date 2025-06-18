package model;

import java.io.Serializable;

public class Agencia implements Serializable {
    private static final long serialVersionUID = 1L;
    private int numero;
    private String bairro;
    private String cidade;

    public Agencia(int numero, String bairro, String cidade) throws ModelException {
        this.setNumero(numero);
        this.setBairro(bairro);
        this.setCidade(cidade);
    }

    public int getNumero() {
        return this.numero;
    }

    public void setNumero(int numero) throws ModelException {
        validarNumero(numero);
        this.numero = numero;
    }

    public String getBairro() {
        return this.bairro;
    }

    public void setBairro(String bairro) throws ModelException {
        validarBairro(bairro);
        this.bairro = bairro;
    }

    public String getCidade() {
        return this.cidade;
    }

    public void setCidade(String cidade) throws ModelException {
        validarCidade(cidade);
        this.cidade = cidade;
    }

    @Override
    public String toString() {
        return this.getNumero() + " - " + this.getCidade();
    }
    
    public static void validarNumero(int numero) throws ModelException {
        if (numero <= 0)
            throw new ModelException("O número da agência não pode ser negativo ou zero.");
    }
    
    public static void validarBairro(String bairro) throws ModelException {
        if (bairro == null || bairro.trim().isEmpty())
            throw new ModelException("O bairro não pode ser nulo.");
    }
    
    public static void validarCidade(String cidade) throws ModelException {
        if (cidade == null || cidade.trim().isEmpty())
            throw new ModelException("A cidade não pode ser nula.");
    }
}