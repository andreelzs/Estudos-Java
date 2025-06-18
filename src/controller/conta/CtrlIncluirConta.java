package controller.conta;

import controller.CtrlAbstrato;
import model.Agencia;
import model.Conta;
import model.ModelException;
import model.dao.DaoConta;

public class CtrlIncluirConta extends CtrlAbstratoConta {
    public CtrlIncluirConta(CtrlAbstrato ctrlPai) {
        super(ctrlPai);
    }

    @Override
    public void efetuar(int numero, String nomeCorrentista, String chavePix, double saldo, double limite, Agencia agencia) {
        try {
            Conta novaConta = new Conta(numero, nomeCorrentista, chavePix, saldo, limite, agencia);
            DaoConta dao = new DaoConta();
            dao.incluir(novaConta);
            this.conta = novaConta;
            this.janela.notificar("Conta incluída com sucesso!");
            this.encerrar();
        } catch (ModelException e) {
            this.janela.notificar(e.getMessage());
        }
    }
}